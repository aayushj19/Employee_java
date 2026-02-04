package employee;

import com.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            System.out.println("1.Add Employee \n 2.Search Employee by ID \n 3.Get All Employees \n 4.Search Employee by Salary \n 5.Update Employee Details \n 6.Delete an Employee\n 7.Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Salary: ");
                int salary = sc.nextInt();
                sc.nextLine();

                Employee emp = new Employee();
                emp.setName(name);
                emp.setSalary(salary);

                dao.addEmployee(emp);
                System.out.println("Employee added successfully!");
            }

            else if (choice == 2) {
                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();
                sc.nextLine();

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
                sc.nextLine();

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

            else if (choice == 5) {
                System.out.print("Enter employee ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.println("1. Name\n2. Salary\n3. Name & Salary");
                int option = sc.nextInt();
                sc.nextLine();

                String name = null;
                Integer salary = null;

                if (option == 1 || option == 3) {
                    System.out.print("Enter new name: ");
                    name = sc.nextLine();
                }
                if (option == 2 || option == 3) {
                    System.out.print("Enter new salary: ");
                    salary = sc.nextInt();
                    sc.nextLine();
                }

                dao.updateEmployeeField(id, name, salary);
            }

            else if (choice == 6) {
                System.out.print("Enter the Employee Id: ");
                int id = sc.nextInt();
                sc.nextLine();
                dao.deleteEmployee(id);
            }

            else if (choice == 7) {
                System.out.println("Thank you!");
                HibernateUtil.getSessionFactory().close();
                break;
            }

            else {
                System.out.println("Invalid choice, try again!");
            }
        }

        sc.close();
    }
}
