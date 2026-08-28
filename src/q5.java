class Ride {
    int rideNo;
    String rideName;

    Ride(int rideNo, String rideName) {
        this.rideNo = rideNo;
        this.rideName = rideName;
    }

    void displayPattern() {
        System.out.print(rideName + " : ");

        for (int j = 1; j <= rideNo; j++) {
            System.out.print("*");
        }

        System.out.println();
    }
}

public class q5{
    public static void main(String[] args) {


        Ride[] rides = new Ride[5];

        rides[0] = new Ride(1, "Roller Coaster");
        rides[1] = new Ride(2, "Ferris Wheel");
        rides[2] = new Ride(3, "Bumper Cars");
        rides[3] = new Ride(4, "Water Ride");
        rides[4] = new Ride(5, "Haunted House");

        for (int i = 0; i < rides.length; i++) {
            rides[i].displayPattern();
        }
    }
}