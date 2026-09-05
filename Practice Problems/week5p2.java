class MovieTicket2 {

    protected double ticketPrice;

    MovieTicket2(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

class PremiumMovieTicket extends MovieTicket2 {

    PremiumMovieTicket(double ticketPrice) {
        super(ticketPrice);
    }

    double getTicketPrice() {
        return ticketPrice;
    }
}

class AccessChecker2 {

    static String classifyAccess(
        String fieldModifier,
        String accessorContext
    ) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("default") ||
                fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }
}

public class week5p2 {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker2.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker2.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}