class MovieTicket {

    private int seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    MovieTicket(int seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("default") ||
                fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result = classifyAccess(
                attempt[0],
                attempt[1]
            );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

public class week5p1 {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessChecker.summarizeBatch(attempts)
        );
    }
}