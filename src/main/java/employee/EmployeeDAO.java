package employee;

import com.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO {

    //for creating employees
    public void addEmployee(Employee emp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(emp);

        tx.commit();
        session.close();
    }
   //for deleting employees
    public void deleteEmployee(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }

        tx.commit();
        session.close();
    }

    // for updating employee data
    public void updateEmployeeField(int id, String name, Integer salary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            if (name != null) {
                emp.setName(name);
            }
            if (salary != null) {
                emp.setSalary(salary);
            }
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }

        tx.commit();
        session.close();
    }

    // find any employee by id
    public Optional<Employee> findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee emp = session.get(Employee.class, id);

        session.close();
        return Optional.ofNullable(emp);
    }

    // get all the employee
    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> list = session
                .createQuery("from Employee", Employee.class)
                .list();

        session.close();
        return list;
    }

    // find employee by salary
    public List<Employee> findBySalary(int salary) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> list = session
                .createQuery("from Employee where salary = :salary", Employee.class)
                .setParameter("salary", salary)
                .list();

        session.close();
        return list;
    }
}
