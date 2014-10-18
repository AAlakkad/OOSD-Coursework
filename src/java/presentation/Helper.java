package presentation;

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
     * Original source for this method: http://www.javalobby.org/forums/thread.jspa?threadID=16906&tstart=0
     * I've updated it a little to be more useful
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

}
