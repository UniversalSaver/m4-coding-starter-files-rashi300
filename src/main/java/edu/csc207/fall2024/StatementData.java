package edu.csc207.fall2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class meant to represent the statement data.
 */
public class StatementData {
    private final Invoice invoice;
    private final List<PerformanceData> performanceDataList;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        performanceDataList = createPerformanceData(plays);
    }

    private List<PerformanceData> createPerformanceData(Map<String, Play> plays) {
        final List<PerformanceData> result;
        result = new ArrayList<>();

        for (Performance performance : invoice.getPerformances()) {
            final AbstractPerformanceCalculator performanceCalculator = AbstractPerformanceCalculator
                    .createPerformanceCalculator(performance, plays.get(performance.getPlayID()));

            result.add(new PerformanceData(performance, plays.get(performance.getPlayID()),
                    performanceCalculator.amountFor(), performanceCalculator.volumeCredits()));
        }
        return result;
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<PerformanceData> getPerformances() {
        return performanceDataList;
    }

    /**
     * Returns the total cost of all performances.
     * @return total cost of performances
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : getPerformances()) {
            // print line for this order
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Returns total number of volume credits across all performances.
     * @return total number of volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : getPerformances()) {

            // add volume credits
            result += performanceData.getVolumeCredits();

        }
        return result;
    }
}
