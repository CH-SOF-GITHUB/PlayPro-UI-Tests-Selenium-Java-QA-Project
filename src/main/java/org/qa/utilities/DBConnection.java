package org.qa.utilities;

import org.apache.logging.log4j.Logger;
import org.qa.base.BaseClass;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/orangehrm";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    public static final Logger loggr = BaseClass.loggr;

    // Add a method for connection to database
    public static Connection getDBConnection() {
        try {
            loggr.info("Starting DB Connection ...");
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            loggr.info("DB Connection Successful ! ");
            return con;
        } catch (Exception e) {
            loggr.error("Error of establishing DB Connection : {}", e.getMessage());
            e.fillInStackTrace();
            return null;
        }
    }

    // Get the employee details from database format MAP
    public static Map<String, String> getEmployeeDetails(String employee_id) {
        // define the query to BD
        String query = "SELECT emp_firstname, emp_middle_name, emp_lastname FROM hs_hr_employee WHERE employee_id = " + employee_id;

        Map<String, String> employeeDetails = new HashMap<>();

        try {
            Connection conn = getDBConnection();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(query);
                ResultSet rs = stmt.getResultSet();
                loggr.info("Executing query : {}", query);
                if (rs.next()) {
                    String firstName = rs.getString("emp_firstname");
                    String middleName = rs.getString("emp_middle_name");
                    String lastName = rs.getString("emp_lastname");

                    // Store in a map
                    employeeDetails.put("firstName", firstName);
                    // erreur : NullPointerException - add a ternary operator for middle name
                    employeeDetails.put("middleName", middleName != null ? middleName : "");
                    employeeDetails.put("lastName", lastName);

                    loggr.info("Query Executing Successfully !");
                    loggr.info("Employees Data Fetch : {}", employeeDetails);
                } else {
                    loggr.error("Query Executing Failed and Employee not Found !");
                }
            }
            return employeeDetails;
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }
}
