package edu.csc207.fall2024;

/**
 *
 */
public class AbstractPerformanceCalculator {
    private Performance performance;
    private Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        return new AbstractPerformanceCalculator(performance, play);
    }
}
