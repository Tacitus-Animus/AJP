package tabData.tables;

import static tabData.tables.Tables.*;

import java.util.Objects;

public class TabDataInput {
	
	final double gw_lbs, atf_ratio, canWeigh_lbs, doesWeigh_lbs, oge_lbs, max_q_percent, q_ige_percent,
			predicted_hover_percent, q_oge_percent, zf_weight;
	
	private final String OUTPUT;
	
	private TabDataInput(double ATF, double TEMP, double PA, double HOVER, double ZF_WEIGHT, double FUEL_WEIGHT) {
		
		atf_ratio = Objects.isNull(ATF) ? (ATF * 10.0) - 9.0 : 0.5;
		
		gw_lbs = getGrossWeight(atf_ratio, PA, TEMP);
		q_ige_percent = Tables.get_trq_ige_percent(atf_ratio, PA, TEMP);
		max_q_percent = Tables.get_trq_ige_percent(atf_ratio, PA, TEMP);
		q_oge_percent = Tables.get_trq_oge_percent(ATF, PA, TEMP);
		
		canWeigh_lbs = gw_lbs * 100.0;

		oge_lbs = canWeigh_lbs - doesWeigh_lbs;

		predicted_hover_percent = Objects.isNull(HOVER) ? q_ige_percent - (oge_lbs / 200.0) : HOVER;
		
		doesWeigh_lbs = -((q_ige_percent * 200.0) - canWeigh_lbs - (predicted_hover_percent * 200.0));

		zf_weight = Objects.isNull(ZF_WEIGHT) ? doesWeigh_lbs - FUEL_WEIGHT : ZF_WEIGHT;
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("AC can weigh: %,.0f lbs\n", canWeigh_lbs));
		sb.append(String.format("AC Predicted Weight: %,.0f lbs\n", doesWeigh_lbs));
		sb.append(String.format("OGE Capability by %.0f lbs %.1f%%\n", oge_lbs, oge_lbs / 200));
		sb.append(String.format("Max TRQ: %.1f%%\n", max_q_percent));
		sb.append(String.format("Hover: %.1f%%\n", predicted_hover_percent));
		sb.append(String.format("GW ~ 100 lbs: %.1f lbs\n", gw_lbs));
		sb.append(String.format("Q ~ OGE: %.1f%%\n", q_oge_percent));
		sb.append(String.format("Q ~ IGE: %.1f%%\n", q_ige_percent));
		OUTPUT = sb.toString();
		
	}
	
	private TabDataInput(double ATF, double ZF_WEIGHT, double FUEL_WEIGHT, double TEMP, double PA) {
		
		atf_ratio = Objects.isNull(ATF) ? (ATF * 10.0) - 9.0 : 0.5;

		gw_lbs = getGrossWeight(atf_ratio, PA, TEMP);
		max_q_percent = get_max_trq_percent(atf_ratio, PA, TEMP);
		q_ige_percent = get_trq_ige_percent(atf_ratio, PA, TEMP);
		q_oge_percent = get_trq_oge_percent(atf_ratio, PA, TEMP);


		canWeigh_lbs = gw_lbs * 100.0;

		zf_weight = Objects.isNull(ZF_WEIGHT) ? doesWeigh_lbs - FUEL_WEIGHT : ZF_WEIGHT;

		doesWeigh_lbs = zf_weight + FUEL_WEIGHT;

		oge_lbs = canWeigh_lbs - doesWeigh_lbs;

		predicted_hover_percent = q_ige_percent - (oge_lbs / 200.0);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("AC can weigh: %,.0f lbs\n", canWeigh_lbs));
		sb.append(String.format("AC does weigh: %,.0f lbs\n", doesWeigh_lbs));
		sb.append(String.format("OGE Capability by %.0f lbs %.1f%%\n", oge_lbs, oge_lbs / 200));
		sb.append(String.format("Max TRQ: %.1f%%\n", max_q_percent));
		sb.append(String.format("Predicted Hover: %.1f%%\n", predicted_hover_percent));
		sb.append(String.format("GW ~ 100 lbs: %.1f lbs\n", gw_lbs));
		sb.append(String.format("Q ~ OGE: %.1f%%\n", q_oge_percent));
		sb.append(String.format("Q ~ IGE: %.1f%%\n", q_ige_percent));
		OUTPUT = sb.toString();
		
	}
	
	public String toString() {
		return OUTPUT;
	}

}
