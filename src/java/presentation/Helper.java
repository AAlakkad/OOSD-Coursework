package presentation;

import java.util.Random;

/**
 *
 * @author ammar
 */
public class Helper {

    /**
     * Get string representation of a number, e.g. 1st, 2nd, 3rd, 4th, etc.
     *
     * @param contestantScore
     * @return String
     *
     * Original source for this method: http://www.javalobby.org/forums/thread.jspa?threadID=16906&tstart=0 I've updated it a little to be more useful
     */
    public static String getOrdinalFor(Integer contestantScore) {
        int hundredRemainder = contestantScore % 100;
        int tenRemainder = contestantScore % 10;
        String suffix = "th";
        if (hundredRemainder - tenRemainder != 10) {

            switch (tenRemainder) {
                case 1:
                    suffix = "st";
                case 2:
                    suffix = "nd";
                case 3:
                    suffix = "rd";
            }
        }
        return contestantScore + suffix;
    }

    /**
     * Get random number between (min, max) where result != notEqual parameter
     *
     * @param min
     * @param max
     * @param notEqual
     * @return
     */
    public static Integer getRandomNumber(Integer min, Integer max, Integer notEqual) {
        Integer random = getRandomNumberBetween(min, max);
        while (random == notEqual) {
            random = getRandomNumberBetween(min, max);
        }
        return random;
    }

    /**
     * Get random number between (min, max) where result != notEqual1 and != notEqual2 parameters
     *
     * @param min
     * @param max
     * @param notEqual1
     * @param notEqual2
     * @return
     */
    public static Integer getRandomNumber(Integer min, Integer max, Integer notEqual1, Integer notEqual2) {
        Integer random = getRandomNumberBetween(min, max);
        while (random == notEqual1 || random == notEqual2) {
            random = getRandomNumberBetween(min, max);
        }
        return random;
    }

    /**
     * Get random number between (min, max) Returns a pseudo-random number between min and max, inclusive. The difference between min and max can be at most <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     *
     * Source from: https://stackoverflow.com/a/363692/408271
     */
    private static Integer getRandomNumberBetween(Integer min, Integer max) {
        Random rand = new Random();
        Integer random = rand.nextInt((max - min) + 1) + min;
        return random;
    }

}
