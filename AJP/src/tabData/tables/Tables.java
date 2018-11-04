package tabData.tables;

public class Tables {

	protected final static Table MAX_OGE_GOOD = getTable("max_oge_1.0.txt");
	protected final static Table MAX_OGE_BAD = getTable("max_oge_0.9.txt");
	
	protected final static Table TRQ_OGE_GOOD = getTable("q_oge_1.0.txt");
	protected final static Table TRQ_OGE_BAD = getTable("q_oge_0.9.txt");
	
	protected final static Table TRQ_IGE_GOOD = getTable("q_ige_1.0.txt");
	protected final static Table TRQ_IGE_BAD = getTable("q_ige_0.9.txt");

	protected final static Table MAX_TRQ_GOOD = getTable("max_trq_1.0.txt");
	protected final static Table MAX_TRQ_BAD = getTable("max_trq_1.0.txt");
	
	private final static String tables;
	
	static {
		var sb = new StringBuilder();
		sb.append(MAX_OGE_GOOD);
		sb.append("\n");
		sb.append(MAX_OGE_BAD); 
		sb.append("\n");
		sb.append(TRQ_OGE_GOOD);
		sb.append("\n");
		sb.append(TRQ_OGE_BAD); 
		sb.append("\n");
		sb.append(TRQ_IGE_GOOD);
		sb.append("\n");
		sb.append(TRQ_IGE_BAD);
		sb.append("\n");
		sb.append(MAX_TRQ_GOOD);
		sb.append("\n");
		sb.append(MAX_TRQ_BAD);
		sb.append("\n");
		tables = sb.toString();
	}
	
	private Tables() {}
	
	public String toString() {
		return tables;
	}
	
	private static Table getTable(String path) {
		return Table.builder()
				   .withData(path)
				   .rowMin(0)
				   .rowMax(16_000)
				   .rowInterval(500)
				   .columnMin(-45)
				   .columnMax(55)
				   .columnInterval(5)
				   .build();
	}
	
	private static double interpolate(final double ratio, final double cap, final double base) {
		return ((cap - base) * ratio) + base;
	}
	
	public static double getGrossWeight(double atf_ratio, double pa, double temp) {
		return interpolate(atf_ratio, MAX_OGE_GOOD.interpolate(pa, temp).get(),
									  MAX_OGE_BAD.interpolate(pa, temp).get());
	}

	public static double get_trq_ige_percent(double atf_ratio, double pa, double temp) {
		return interpolate(atf_ratio, TRQ_IGE_GOOD.interpolate(pa, temp).get(), 
									  TRQ_IGE_BAD.interpolate(pa, temp).get());
	}

	public static double get_trq_oge_percent(double atf_ratio, double pa, double temp) {
		return interpolate(atf_ratio, TRQ_OGE_GOOD.interpolate(pa, temp).get(), 
									  TRQ_OGE_BAD.interpolate(pa, temp).get());
	}

	public static double get_max_trq_percent(double atf_ratio, double pa, double temp) {
		return interpolate(atf_ratio, MAX_TRQ_GOOD.interpolate(pa, temp).get(), 
				   					  MAX_TRQ_BAD.interpolate(pa, temp).get());
	}
}
