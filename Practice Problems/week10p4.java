
public class week10p4 {
    public static void main(String[] args) {
        String[] names = {"Asha", "Ravi", "Neha"};

        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] totals = new int[marks.length];
        int[] subjectTotals = new int[3];

        int topperIndex = 0;

        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < 3; j++) {
                totals[i] += marks[i][j];
                subjectTotals[j] += marks[i][j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        System.out.print("Totals ");
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i] + " " + totals[i]);
            if (i < names.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        System.out.printf(
            "averages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / (double) marks.length,
            subjectTotals[1] / (double) marks.length,
            subjectTotals[2] / (double) marks.length
        );

        System.out.println("topper " + names[topperIndex]
                + " (" + totals[topperIndex] + ")");
    }
}

