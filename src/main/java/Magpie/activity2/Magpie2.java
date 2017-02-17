package magpie.activity2;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 * <ul><li>
 * Uses indexOf to find strings
 * </li><li>
 * Handles responding to simple words and phrases
 * </li></ul>
 * This version uses a nested if to handle default responses.
 *
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2 {
    /**
     * Get a default greeting
     *
     * @return a greeting
     */
    public String getGreeting() {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     *
     * @param statement the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) {
        String response = "";
        if (statement.trim().isEmpty()) {
            response = "Say something please.";
        } else if (statement.contains("college")) {
            response = "Please don't talk to me about college.";
        } else if (statement.contains("Darian")) {
            response = "Darian is a genius.";
        } else if (statement.contains("life")) {
            response = "What is the meaning of life?";
        } else if (statement.contains("Mr. Luc")) {
            response = "Mr. Luc sounds like a good teacher.";
        } else if (statement.contains("cat") || statement.contains("dog")) {
            response = "Tell me more about your pets.";
        } else if (statement.contains("no")) {
            response = "Why so negative?";
        } else if (statement.contains("mother")
                   || statement.contains("father")
                   || statement.contains("sister")
                   || statement.contains("brother")) {
            response = "Tell me more about your family.";
        } else {
            response = getRandomResponse();
        }
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     *
     * @return a non-committal string
     */
    private String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        } else if (whichResponse == 1) {
            response = "Hmmm.";
        } else if (whichResponse == 2) {
            response = "Do you really think so?";
        } else if (whichResponse == 3) {
            response = "You don't say.";
        } else if (whichResponse == 4) {
            response = "Do I care?";
        } else if (whichResponse == 5) {
            response = "Please repeat that again.";
        }

        return response;
    }
}
