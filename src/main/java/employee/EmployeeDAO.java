package employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Employee";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employee (name, salary) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getSalary());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeField(int id, String name, Integer salary) {

        StringBuilder sql = new StringBuilder("UPDATE employee SET ");
        boolean comma = false;

        if (name != null) {
            sql.append("name = ?");
            comma = true;
        }

        if (salary != null) {
            if (comma) sql.append(", ");
            sql.append("salary = ?");
        }

        sql.append(" WHERE id = ?");

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;

            if (name != null) {
                ps.setString(index++, name);
            }

            if (salary != null) {
                ps.setInt(index++, salary);
            }

            ps.setInt(index, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Optional<Employee> findById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getInt("salary"));
                return Optional.of(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getInt("salary"));
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Employee> findBySalary(int salary) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE salary = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, salary);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getInt("salary"));
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

