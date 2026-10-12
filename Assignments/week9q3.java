import java.util.HashMap;
import java.util.Map;

public class week9q3 {
    public static String[] mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = 0;

        for (String item : orders) {
            int count = counts.get(item);

            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }

        return new String[]{bestItem, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        String[] result = mostPopular(orders);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}