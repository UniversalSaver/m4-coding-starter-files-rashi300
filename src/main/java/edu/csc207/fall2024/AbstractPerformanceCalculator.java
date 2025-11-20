package edu.csc207.fall2024;

/**
 * A calculator for credits and amount.
 */
public abstract class AbstractPerformanceCalculator {
    private final Performance performance;

    protected AbstractPerformanceCalculator(Performance performance) {
        this.performance = performance;
    }

    Performance getPerformance() {
        return performance;
    }

    static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {

        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance);
            case "comedy":
                return new ComedyCalculator(performance);
            case "pastoral":
                return new PastoralCalculator(performance);
            case "history":
                return new HistoryCalculator(performance);
            default:
                throw new PerformanceData.UnexpectedPlayTypeException(
                        String.format("unknown type: %s", play.getType()));
        }
    }

    /**
     * Returns the amount of volume credits this performance gives.
     * @return number of volume cred9ts
     */
    public int volumeCredits() {
        int result = 0;
        result += Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        return result;
    }

    /**
     * Gets the amount of money this play makes.
     * @return amount of money play makes
     * @throws PerformanceData.UnexpectedPlayTypeException if play type is not found
     */
    public abstract int amountFor();
}
