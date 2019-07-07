package com.jpm.dailytrade.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jpm.dailytrade.bo.Ranking;
import com.jpm.dailytrade.bo.RankingAndTradeAmountCalculator;
import com.jpm.dailytrade.bo.SettlementDateCalculator;
import com.jpm.dailytrade.model.Order;

public class ReportGenerator {

	public String generateOrderReport(Set<Order> orders) {
		// first calculate the correct settlement dates
		// SettlementDateCalculator settlementDateCalulator = new
		// SettlementDateCalculator();
		SettlementDateCalculator.calculateSettlementDates(orders);

		StringBuffer theReport = new StringBuffer();

		theReport = generateDailyIncomingRanking(orders).append(generateDailyOutgoingRanking(orders))
				.append(generateDailyIncomingAmount(orders)).append(generateDailyOutgoingAmount(orders));
		return (theReport.toString());
	}

	private static StringBuffer generateDailyOutgoingAmount(Set<Order> orders) {
		StringBuffer theReport = new StringBuffer();
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = RankingAndTradeAmountCalculator
				.calculateDailyOutgoingTradeAmount(orders);

		theReport.append("\n\n         Outgoing Daily Amount          \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			theReport.append(date).append("                ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return theReport;
	}

	private static StringBuffer generateDailyIncomingAmount(Set<Order> orders) {
		StringBuffer theReport = new StringBuffer();

		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = RankingAndTradeAmountCalculator
				.calculateDailyIncomingTradeAmount(orders);

		theReport.append("\n\n         Incoming Daily Amount          \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			theReport.append(date).append("                ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return theReport;
	}

	private static StringBuffer generateDailyOutgoingRanking(Set<Order> orders) {
		StringBuffer theReport = new StringBuffer();

		final List<Ranking> dailyOutgoingRanking = RankingAndTradeAmountCalculator
				.calculateOutgoingEntityRanking(orders);

		theReport.append("\n\n         Outgoing Daily Ranking          \n")
				.append("----------------------------------------\n");

		dailyOutgoingRanking.forEach(ranking -> {
			theReport.append(ranking.getEntity()).append("          ").append(ranking.getTradeAmount())
					.append("          ").append("\n");
		});

		return theReport;
	}

	private static StringBuffer generateDailyIncomingRanking(Set<Order> orders) {
		StringBuffer theReport = new StringBuffer();

		final List<Ranking> dailyIncomingRanking = RankingAndTradeAmountCalculator
				.calculateIncomingEntityRanking(orders);

		theReport.append("\n\n         Incoming Daily Ranking          \n")
				.append("----------------------------------------\n");

		dailyIncomingRanking.forEach(ranking -> {
			theReport.append(ranking.getEntity()).append("          ").append(ranking.getTradeAmount())
					.append("          ").append("\n");
		});

		return theReport;
	}
}
