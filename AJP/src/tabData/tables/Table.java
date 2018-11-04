package tabData.tables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.DoubleStream;

/**
 * Table of rows and columns mapped to values.
 * IPA?
 * @author Alexander Paul
 *
 */
public class Table {
	
	/**
	 * 2-D array representation of numeric values constrained to rows and columns.
	 */
	private final double[][] table;
	
	private final int tableWidth, tableHeight;
	
	/**
	 * A map of numeric intervals mapped to corresponding index of the rows of the table.
	 */
	private final Map<Double, Integer> rowMap, columnMap;

	/**
	 * Set of numeric values representing the intervals that are mapped to corresponding index of the rows of the table.
	 */
	private final NavigableSet<Double> rowIntervals, columnIntervals;	
	
	private final double rowMin, rowMax, columnMin, columnMax;
	/**
	 * Creates a Table using the given file path, and the given Row and Column Bounds.
	 * @param path - to parse the file from.
	 * @param rowBounds - containing the min, max, and interval properties of this table's row.
	 * @param columnBounds - containing the min, max, and interval properties of this table's column.
	 */
	private Table(final Data path, final Boundary rowBounds, final Boundary columnBounds) {
		this.table = path.table;
		rowMap = rowBounds.map;
		columnMap = columnBounds.map;
		rowIntervals = new TreeSet<>(rowMap.keySet());
		columnIntervals = new TreeSet<>(columnMap.keySet());
		rowMin = rowBounds.min;
		rowMax = rowBounds.max;
		columnMin = columnBounds.min;
		columnMax = columnBounds.max;
		tableWidth = path.tableWidth;
		tableHeight = path.tableHeight;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		String path;
		double rowMin, rowMax, rowInterval, columnMin, columnMax, columnInterval;

		public Builder withData(final String path) {
			this.path = path;
			return this;
		}

		public Builder rowMin(final double rowMin) {
			this.rowMin = rowMin;
			return this;
		}

		public Builder rowMax(final double rowMax) {
			this.rowMax = rowMax;
			return this;
		}

		public Builder rowInterval(final double rowInterval) {
			this.rowInterval = rowInterval;
			return this;
		}

		public Builder columnMin(final double columnMin) {
			this.columnMin = columnMin;
			return this;
		}

		public Builder columnMax(final double columnMax) {
			this.columnMax = columnMax;
			return this;
		}

		public Builder columnInterval(final double columnInterval) {
			this.columnInterval = columnInterval;
			return this;
		}

		public Table build() {
			return new Table(new Data(path), new Boundary(rowMin, rowMax, rowInterval),
					new Boundary(columnMin, columnMax, columnInterval));
		}

	}

	/**
	 * Data Object used to store and parse file to 2-D array representation.
	 * <strong>
	 * Text File to be parsed must have numeric values in table-like format.
	 * Numbers must me split with a tab else parsing could have unusual results.
	 * Each Row of the text file with reflect the row of this table.
	 * If an index can't be parsed, it will be be considered NaN.
	 * </strong>
	 * @author Alexander Paul
	 */
	private static class Data {

		private final int tableWidth;
		private final int tableHeight;
		final double[][] table;

		private Data(final String path) {
			final List<String> data = readfromTextFile(path);

			this.tableWidth = data.stream().map(s -> s.split("\t").length).max(Integer::compare).get();

			this.tableHeight = data.size();

			final double[][] table = new double[tableHeight][];

			final Iterator<String> rowIterator = data.iterator();

			for (int i = 0; i < data.size(); i++)
				table[i] = toDoubleArray(rowIterator.next(), tableWidth);

			this.table = table;
		}

		/**
		 * @param maxLength
		 *            of the all the rows.
		 * @param file
		 *            data row to be parsed into {@code double[]}.
		 * @return {@code double[]} representation of tabDataRow.
		 */
		private double[] toDoubleArray(final String fileDataRow, final int maxLength) {
			final String[] split = fileDataRow.split("\t");
			final double[] doubles = new double[maxLength];
			for (int i = 0; i < maxLength; i++) {
				try {
					doubles[i] = i < split.length ? Double.parseDouble(split[i]) : Double.NaN;
				} catch (NumberFormatException e) {
					doubles[i] = Double.NaN;
				}
			}
			return doubles;
		}

		/**
		 * @param path
		 *            to be read and parsed.
		 * @return List of Strings representing each row of the file.
		 */
		private List<String> readfromTextFile(final String path) {
			try {
				return Files.readAllLines(Paths.get(path));
			} catch (IOException e) {
				throw new RuntimeException("Failed to load " + path);
			}
		}

	}
	
	/**
	 * Data Object used to store table properties such as min, max, and interval values that constrain the table's rows and columns.
	 * <blockquote>
	 * Example: Rows for elevation from 0ft to 16,000ft with 500ft intervals.
	 * 			Columns for temperature from -45 degree to 55 degrees with 5 degree intervals.
	 * </blockquote>
	 */
	private static class Boundary { 
		private final double min;
		private final double max;
		private final HashMap<Double, Integer> map;
		
		private Boundary(double min, double max, double interval) {
			this.min = min;
			this.max = max;
			map = new HashMap<Double, Integer>();
			final var tableIndex = new Iterator<Integer>() {

				private int i = 0;
				
				@Override
				public boolean hasNext() {
					return true;
				}

				public Integer next() {
					return i++;
				}
				
			};
			
			DoubleStream.iterate(min, mapIndex -> mapIndex <= max, mapIndex -> mapIndex += interval)
						.forEach(mapIndex -> map.put(mapIndex, tableIndex.next()));
		}
		
	}
	
	/**
	 * Returns value in respect to precise intervals of the table, else an empty optional.
	 * @param row to be searched by row interval.
	 * @param column to be searched by column interval.
	 * @return corresponding index in the table.
	 */
	public Optional<Double> getPrecise(final double row, final double column) {
		checkBounds(row, column);
		try {
			return Optional.ofNullable(getUnsafe(rowMap.get(row), columnMap.get(column)));
		}catch(NullPointerException | NoSuchElementException e) {
			return Optional.empty();
		}
	}

	/**
	 * Assumes row and column parameters are within bounds, 
	 * else throw {@code NullPointerException or NoSuchElementException}.
	 * @param row to be searched by row interval.
	 * @param column to be searched by column interval.
	 * @return corresponding index in the table.
	 * @throws NullPointerException
	 * @throws NoSuchElementException
	 */
	private double getUnsafe(final int row, final int column) {
		return table[row][column];
	}

	private void checkBounds(final double row, final double column) {
		if(row < rowMin || row > rowMax || column < columnMin || column > columnMax) 
			throw new RuntimeException("Row and/or Column arguments are out of bounds.");
	}
	
	public Optional<Double> interpolate(final double row, final double column){
		return getPrecise(row, column).or(() -> interpolate(row, column, (a,b,c,d) ->  ""));
	}
	
	public void showInterpolation(final double row, final double column){
		checkBounds(row, column);
		interpolate(row, column, (rowLoIn, rowHiIn, colLoIn, colHiIn) -> {
			var table = new StringBuilder();

			var rowIt = rowIntervals.iterator();

			// print column intervals first.
			table.append("       ");
			for (double d : columnIntervals) {
				table.append(String.format(" %3.0f ", d));
			}
			table.append("\n");

			for (int rowIndex = 0; rowIndex < tableHeight; rowIndex++) {

				// mark given row.
				if (rowIndex == rowHiIn) {
					table.append("       ");
					for (int i = 0; i < tableWidth; i++) {
						table.append(" --- ");
					}
					table.append("\n");
				}

				for (int colIndex = 0; colIndex < tableWidth; colIndex++) {
					// print intervals first.
					if (colIndex == 0)
						table.append(String.format(" %5.0f ", rowIt.next()));
					// mark given column.
					table.append(colIndex == colHiIn ? "|" : " ");
					table.append(String.format("%3.0f", this.table[rowIndex][colIndex]));
					// mark given column.
					table.append(colIndex == colLoIn ? "|" : " ");
				}
				table.append("\n");
				// mark given row.
				if (rowIndex == rowLoIn) {
					table.append("       ");
					for (int i = 0; i < tableWidth; i++) {
						table.append(" --- ");
					}
					table.append("\n");
				}
			}
			table.append("\n");
			return table.toString();
		}).ifPresentOrElse(System.out::println, () -> System.out.println("Not valid."));
	}
	
	/**
	 * Gets optional value that has been interpolated corresponding to row and column inputs.
	 * @param row
	 * @param column
	 * @return interpolated value corresponding to row and column.
	 */
	private Optional<Double> interpolate(final double row, final double column, Quad work) {
		double rowLowerInterval = rowIntervals.floor(row);
		double rowHigherInterval = rowIntervals.ceiling(row);
		
		Double rowRatio = getRatio(row, rowLowerInterval, rowHigherInterval);

		int rowLowerIndex = rowMap.get(rowLowerInterval);
		int rowHigherIndex = rowMap.get(rowHigherInterval);

		double columnLowerInterval = columnIntervals.floor(column);
		double columnHigherInterval = columnIntervals.ceiling(column);

		Double columnRatio = getRatio(column, columnLowerInterval, columnHigherInterval);

		int columnLowerIndex = columnMap.get(columnLowerInterval);
		int columnHigherIndex = columnMap.get(columnHigherInterval);

		//print work.
		System.out.print(work.perform(rowLowerIndex, rowHigherIndex, columnLowerIndex, columnHigherIndex));

		double topLeft = getUnsafe(rowHigherIndex, columnLowerIndex);
		double topRight = getUnsafe(rowHigherIndex, columnHigherIndex);
		double bottomLeft = getUnsafe(rowLowerIndex, columnLowerIndex);
		double bottomRight = getUnsafe(rowLowerIndex, columnHigherIndex);

		if (DoubleStream.of(topLeft, topRight, bottomLeft, bottomRight).anyMatch(Double::isNaN))
			return Optional.empty();

		double topMean = getMean(columnRatio, topLeft, topRight);

		double bottomMean = getMean(columnRatio, bottomLeft, bottomRight);
		
		double resultMean = getMean(rowRatio, bottomMean, topMean);

		return Optional.of(resultMean);
	}

	private double getMean(Double ratio, double base, double rangeReference) {
		return ((rangeReference - base) * ratio) + base;
	}

	private double getRatio(final double column, double columnLowerInterval, double columnHigherInterval) {
		return columnLowerInterval == columnHigherInterval ? 1
				: (column - columnLowerInterval) / (columnHigherInterval - columnLowerInterval);
	}
		
	@FunctionalInterface
	private interface Quad{
		public String perform(int rowLoIn, int rowHiIn,  int colLoIn, int colHiIn);
	}
	
	public String toString() {
		var table = new StringBuilder();
		
		var rowIt = rowIntervals.iterator();
		
		table.append("      ");
		
		for(double d : columnIntervals) {
			table.append(String.format("%3.0f ", d));
		}
		
		table.append("\n");
		
		for(int row = 0; row < tableHeight; row++) {
			for(int col = 0; col < tableWidth; col++) {
				if(col == 0) table.append(String.format("%5.0f ", rowIt.next()));
				table.append(String.format("%3.0f ", this.table[row][col]));
			}
			table.append("\n");
		}
		return table.toString();
	}
	
}
