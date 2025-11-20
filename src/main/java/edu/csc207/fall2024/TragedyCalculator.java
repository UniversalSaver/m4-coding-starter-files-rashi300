package edu.csc207.fall2024;

/**
 * A calculator for tragedy plays.
 */
public class TragedyCalculator extends AbstractPerformanceCalculator {
    public TragedyCalculator(Performance performance) {
        super(performance);
    }

    /**
     * Gets the amount of money this play makes.
     * @return amount of money play makes
     * @throws PerformanceData.UnexpectedPlayTypeException if play type is not found
     */
    @Override
    public int amountFor() {
        int result;

        result = Constants.TRAGEDY_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
            result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
        }

        return result;
    }
}
