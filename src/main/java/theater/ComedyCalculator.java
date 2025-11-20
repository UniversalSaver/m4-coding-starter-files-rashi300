package theater;

/**
 * A calculator for tragedy plays.
 */
public class ComedyCalculator extends AbstractPerformanceCalculator {
    public ComedyCalculator(Performance performance) {
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

        result = Constants.COMEDY_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
            result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                    + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
        }
        result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * getPerformance().getAudience();

        return result;
    }

    /**
     * Returns the amount of volume credits this performance gives.
     * @return number of volume cred9ts
     */
    @Override
    public int volumeCredits() {
        int result = 0;
        result += Math.max(getPerformance().getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        // add extra credit for every five comedy attendees
        result += getPerformance().getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        return result;
    }
}
