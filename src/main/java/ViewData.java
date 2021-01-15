import org.postgresql.util.PSQLState;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Properties;
import java.util.Date;
public class ViewData {

    static Scanner stdin = new Scanner(System.in);
    // Create SQL statement that selects all the columns in the driver table to display
    public static void getDriver(Connection connection) throws SQLException {

        String SQL = "SELECT driver_id, first_name, last_name, dob, phone_number, address_id FROM driver";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        displayAllDriver(resultSet);
        System.out.println("");
    }
    // Executes the sql statement and prints query results into console
    public static void displayAllDriver(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("driver_id") + "\t"
                    + resultSet.getString("first_name") + "\t"
                    + resultSet.getString("last_name") + "\t"
                    + resultSet.getString("dob") + "\t"
                    + resultSet.getString("phone_number") + "\t"
                    + resultSet.getString("address_id"));
        }
    }
    // Create SQL statement that selects all the columns in the address table to display
    public static void getAddress(Connection connection) throws SQLException {

        String SQL = "SELECT address_id, street_name, city, postcode FROM address";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        displayAllAddress(resultSet);
        System.out.println("");
    }
    // Executes the sql statement and prints query results into console
    public static void displayAllAddress(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("address_id") + "\t"
                    + resultSet.getString("street_name") + "\t"
                    + resultSet.getString("city") + "\t"
                    + resultSet.getString("postcode"));
        }
    }
    // Create SQL statement that selects all the columns in the vehicle table to display
    public static void getVehicle(Connection connection) throws SQLException {

        String SQL = "SELECT vehicle_id, make, model, reg_plate, sorn, road_tax FROM vehicle";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        displayAllVehicle(resultSet);
        System.out.println("");
    }
    // Executes the sql statement and prints query results into console
    public static void displayAllVehicle(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("vehicle_id") + "\t"
                    + resultSet.getString("make") + "\t"
                    + resultSet.getString("model") + "\t"
                    + resultSet.getString("reg_plate") + "\t"
                    + resultSet.getString("sorn") + "\t"
                    + resultSet.getString("road_tax"));
        }
    }
    // Creates SQL statement that searches vehicle table for road tax expiry dates between today and 30 days time
    public static void getVehicleByRoadTax(Connection connection) throws SQLException {
        Date date = new Date();
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        // Gets current date in the Java
        SimpleDateFormat todayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        // Adds 30 days to the date
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date currentDate = calendar.getTime();
        String monthDate = todayDateFormat.format(currentDate);

        System.out.println(todayDate);
        System.out.println(monthDate);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicle WHERE road_tax BETWEEN '" + todayDate + "' AND '" + monthDate + "'");
        displayAllVehicle(resultSet);
    }
    public static void findVehicle(Connection connection) throws SQLException {
        System.out.println("Please enter the reg plate of the vehicle you would like to find");
        String regPlate = stdin.nextLine();

        ResultSet resultSet = searchVehicle(regPlate, connection);
        displayAllVehicle(resultSet);
        System.out.println("");
    }
    public static ResultSet searchVehicle(String regPlate, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM vehicle WHERE reg_plate = '" + regPlate + "'");
    }
    public static void findDriver(Connection connection) throws SQLException {
        System.out.println("Please enter the first name of the driver you would like to find");
        String firstName = stdin.nextLine();
        System.out.println("Please enter the last name of the driver you would like to find");
        String lastName = stdin.nextLine();
        System.out.println("Please enter the date of birth of the driver you would like to find");
        String dob = stdin.nextLine();

        ResultSet resultSet = searchDriver(firstName, lastName, dob, connection);
        displayAllDriver(resultSet);
        System.out.println("");
    }
    public static ResultSet searchDriver(String firstName, String lastName, String dob, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM driver WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "' AND dob = '" + dob + "'");
    }
    public static void driversOwnedVehicles(Connection connection) throws SQLException {
        System.out.println("Enter details of driver");
        System.out.println("Please enter first name");
        String firstName = stdin.nextLine();

        System.out.println("Please enter last name");
        String lastName = stdin.nextLine();

        System.out.println("Please enter date of birth");
        String dob = stdin.nextLine();

        ResultSet resultSetDriver = searchDriver(firstName, lastName, dob, connection);
        String driverID = null;
        while (resultSetDriver.next()){
            driverID = resultSetDriver.getString("driver_id");
        }

        String SQL = "SELECT * FROM vehicle_owner WHERE driver_id = '" + driverID + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSetDriverVehicles = statement.executeQuery(SQL);
        //displayAllDriver(resultSetDriverVehicles);

        int[] vehicles = new int[9999];
        int i = 0;
        while (resultSetDriverVehicles.next()) {
            vehicles[i] = resultSetDriverVehicles.getInt("vehicle_id");
            i++;
        }

        ResultSet resultSetVehicles;
        int count = viewVehicleCount(driverID, connection);
        int x = 0;

        while (x < count) {
            Statement statement1 = connection.createStatement();
            String vehicleSQL = "SELECT * FROM vehicle WHERE vehicle_id = '" + vehicles[x] + "'";
            resultSetVehicles = statement.executeQuery(vehicleSQL);
            displayAllVehicle(resultSetVehicles);
            x++;
        }

    }
    public static int viewVehicleCount(String driverID, Connection connection)  throws SQLException {
        String SQL = "SELECT count(*) FROM vehicle_owner WHERE driver_id = '" + driverID + "'";
        int count = 0;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.next();
        count = resultSet.getInt(1);
        return count;
    }
}
