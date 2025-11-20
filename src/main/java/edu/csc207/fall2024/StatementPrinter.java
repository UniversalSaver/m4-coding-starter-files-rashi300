package edu.csc207.fall2024;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    private final StatementData statementData;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {

        return renderPlainText();
    }

    private String renderPlainText() {
        final StringBuilder result = new StringBuilder("Statement for " + statementData.getCustomer() + "\n");
        for (PerformanceData performanceData : statementData.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)%n",
                    performanceData.getName(), usd(performanceData.getAmount()),
                    performanceData.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n", usd(statementData.totalAmount())));
        result.append(String.format("You earned %s credits%n", statementData.volumeCredits()));
        return result.toString();
    }

    static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(totalAmount / Constants.PERCENT_FACTOR);
    }

    StatementData getStatementData() {
        return statementData;
    }
}
