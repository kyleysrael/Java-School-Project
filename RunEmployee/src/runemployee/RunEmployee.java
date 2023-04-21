package runemployee;

import java.util.Scanner;

public class RunEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Press F for Full Time or P for Part Time ");
        String employeeType = sc.nextLine();
        if (employeeType.equalsIgnoreCase("F")) {
            System.out.print("Enter monthly salary: ");
            double monthlySalary = sc.nextDouble();
            FullTimeEmployee employee = new FullTimeEmployee(name, monthlySalary);
            employee.displayInfo();
        } else if (employeeType.equalsIgnoreCase("P")) {
            System.out.print("Enter rate per hour and no. of hours worked separated by space (e.g. 107.50 13): ");
            double rate = sc.nextDouble();
            int hoursWorked = sc.nextInt();
            PartTimeEmployee employee = new PartTimeEmployee(name, rate, hoursWorked);
            employee.displayInfo();
        } else {
            System.out.println("Invalid employee type.");
        }
        sc.close();
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.printf("Monthly Salary: ₱%.2f\n", monthlySalary);
    }
}

class PartTimeEmployee extends Employee {
    private double rate;
    private int hoursWorked;

    public PartTimeEmployee(String name, double rate, int hoursWorked) {
        super(name);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double calculateWage() {
        return rate * hoursWorked;
    }

    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.printf("Wage: ₱%.2f\n", calculateWage());
    }
}
