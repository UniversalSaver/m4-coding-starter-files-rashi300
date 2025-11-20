package edu.csc207.fall2024;

/**
 * A calculator for credits and amount.
 */
public class AbstractPerformanceCalculator {
    private final Performance performance;
    private final Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        return new AbstractPerformanceCalculator(performance, play);
    }

    /**
     * Returns the amount of volume credits this performance gives.
     * @return number of volume cred9ts
     */
    public int volumeCredits() {
        int result = 0;
        result += Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        // add extra credit for every five comedy attendees
        if ("comedy".equals(play.getType())) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }

    /**
     * Gets the amount of money this play makes.
     * @return amount of money play makes
     * @throws PerformanceData.UnexpectedPlayTypeException if play type is not found
     */
    public int amountFor() {
        int result;

        switch (play.getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;
            default:
                throw new PerformanceData.UnexpectedPlayTypeException(
                        String.format("unknown type: %s", play.getType()));
        }
        return result;
    }
}
