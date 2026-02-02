package employee;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            System.out.println(" 1.Add Employee\n 2.Search Employee by ID\n 3.Get All Employees\n 4.Search Employee by Salary\n 5.Update Employee Details\n 6.Delete an Employee\n 7.Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = sc.next();

                System.out.print("Enter Salary: ");
                int salary = sc.nextInt();

                Employee emp = new Employee();
                emp.setName(name);
                emp.setSalary(salary);

                dao.addEmployee(emp);
                System.out.println("Employee added successfully!");

            }

            else if (choice == 2) {
                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();

                Optional<Employee> empOpt = dao.findById(id);

                if (empOpt.isPresent()) {
                    Employee e = empOpt.get();
                    System.out.println("ID: " + e.getId());
                    System.out.println("Name: " + e.getName());
                    System.out.println("Salary: " + e.getSalary());
                } else {
                    System.out.println("Employee not found!");
                }
            }

            else if (choice == 3) {
                List<Employee> employees = dao.getAllEmployees();

                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } else {
                    for (Employee e : employees) {
                        System.out.println("ID: " + e.getId());
                        System.out.println("Name: " + e.getName());
                        System.out.println("Salary: " + e.getSalary());
                        System.out.println("----------------------");
                    }
                }
            }
            else if (choice == 4) {
                System.out.print("Enter salary: ");
                int salary = sc.nextInt();

                List<Employee> list = dao.findBySalary(salary);

                if (list.isEmpty()) {
                    System.out.println("No employee found with this salary.");
                } else {
                    for (Employee e : list) {
                        System.out.println("ID: " + e.getId());
                        System.out.println("Name: " + e.getName());
                        System.out.println("Salary: " + e.getSalary());
                        System.out.println("----------------------");
                    }
                }
            }
            else if(choice == 5){
                System.out.print("Enter employee ID: ");
                int id = sc.nextInt();

                System.out.println("What do you want to update?");
                System.out.println("1. Name");
                System.out.println("2. Salary");
                System.out.println("3. Name & Salary");

                int option = sc.nextInt();

                String name = null;
                Integer salary = null;

                if (option == 1) {
                    System.out.print("Enter new name: ");
                    name = sc.next();
                }
                else if (option == 2) {
                    System.out.print("Enter new salary: ");
                    salary = sc.nextInt();
                }
                else if (option == 3) {
                    System.out.print("Enter new name: ");
                    name = sc.next();
                    System.out.print("Enter new salary: ");
                    salary = sc.nextInt();
                }
                else {
                    System.out.println("Invalid option");
                }

                dao.updateEmployeeField(id, name, salary);

            }
            else if(choice  == 6){
                System.out.print("Enter the Employee Id:");
                int id = sc.nextInt();
                dao.deleteEmployee(id);
            }

            else if (choice == 7) {
                System.out.println("Thank you!");
                break;
            }

            else {
                System.out.println("Invalid choice, try again!");
            }
        }

        sc.close();
    }
}
