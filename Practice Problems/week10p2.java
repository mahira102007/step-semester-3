
import java.util.*;

public class week10p2 {
    interface Chargeable {
        void charge();
    }

    static abstract class Vehicle {
        String passNumber;
        String owner;

        Vehicle(String passNumber, String owner) {
            this.passNumber = passNumber;
            this.owner = owner;
        }

        abstract String getType();

        abstract int getFee();
    }

    static class Bike extends Vehicle {
        Bike(String pass, String owner) {
            super(pass, owner);
        }

        String getType() { return "Bike"; }
        int getFee() { return 300; }
    }

    static class Car extends Vehicle {
        Car(String pass, String owner) {
            super(pass, owner);
        }

        String getType() { return "Car"; }
        int getFee() { return 1000; }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String pass, String owner) {
            super(pass, owner);
        }

        String getType() { return "EBike"; }
        int getFee() { return 300; }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String pass, String owner) {
            super(pass, owner);
        }

        String getType() { return "ECar"; }
        int getFee() { return 1000; }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("PASS")) {
                String type = p[1];
                String pass = p[2];
                String owner = p[3];

                Vehicle v;

                switch (type) {
                    case "Bike":
                        v = new Bike(pass, owner);
                        break;
                    case "Car":
                        v = new Car(pass, owner);
                        break;
                    case "EBike":
                        v = new EBike(pass, owner);
                        break;
                    case "ECar":
                        v = new ECar(pass, owner);
                        break;
                    default:
                        System.out.println("Unknown vehicle type");
                        continue;
                }

                vehicles.put(pass, v);
                System.out.println(pass + " (" + v.getType()
                        + ") pass fee " + v.getFee());

            } else if (p[0].equals("CHARGE")) {
                String pass = p[1];
                Vehicle v = vehicles.get(pass);

                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).charge();
                } else {
                    System.out.println(pass
                            + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
