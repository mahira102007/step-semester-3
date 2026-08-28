class Employee {

    // Instance fields
    String empName;
    double salary;

    // Static fields
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    // Constructor
    Employee(String empName, double salary) {

        this.empName = empName;
        this.salary = salary;

        employeeCount++;
    }

    // Static method
    static void printCompanyInfo() {

        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Main {
    public static void main(String[] args) {

        Employee e1 = new Employee("Divya", 65000);
        Employee e2 = new Employee("Arjun", 55000);
        Employee e3 = new Employee("Rahul", 60000);

        Employee.printCompanyInfo();
    }
}