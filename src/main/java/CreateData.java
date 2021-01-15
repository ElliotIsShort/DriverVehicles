import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Properties;
import java.sql.Date;
public class CreateData {

    static Scanner stdin = new Scanner(System.in);
    // Asks the user for driver input, saves their answers then parses them through another function
    public static void addDriver(Connection connection) throws SQLException {

        System.out.println("Please enter driver's first name: ");
        String firstName = stdin.nextLine();

        System.out.println("Please enter driver's last name: ");
        String lastName = stdin.nextLine();

        System.out.println("Please enter driver's date of birth (yyyy-mm-dd): ");
        Date dob = java.sql.Date.valueOf(stdin.nextLine());

        System.out.println("Please enter driver's phone_number: ");
        String phoneNumber = stdin.nextLine();

        System.out.println("Please enter driver's address id: ");
        int addressID = Integer.parseInt(stdin.nextLine());
        insertDriver(firstName, lastName, dob, phoneNumber, addressID, connection);
        System.out.println("New data inserted: " + firstName + " " + lastName);

    }
    // Adds the information inputted from addDriver into the database
    public static void insertDriver(String firstName, String lastName, Date dob, String phoneNumber, int addressID, Connection connection) throws SQLException {
        String SQL = "INSERT INTO driver (first_name, last_name, dob, phone_number, address_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL);

        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setDate(3, dob);
        statement.setString(4, phoneNumber);
        statement.setInt(5, addressID);

        statement.executeUpdate();
    }
    // Asks the user for address input, saves their answers then parses them through another function
    public static void addAddress(Connection connection) throws  SQLException {

        System.out.println("Please enter street name: ");
        String streetName = stdin.nextLine();

        System.out.println("Please enter city: ");
        String city = stdin.nextLine();

        System.out.println("Please enter post code: ");
        String postcode = stdin.nextLine();
        insertAddress(streetName, city, postcode, connection);
        System.out.println("New data inserted: " + streetName);
    }
    // Adds the information inputted from addAddress into the database
    public static void insertAddress(String streetName, String city, String postcode, Connection connection) throws SQLException {
        String SQL = "INSERT INTO address (street_name, city, postcode) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL);

        statement.setString(1, streetName);
        statement.setString(2, city);
        statement.setString(3, postcode);

        statement.executeUpdate();
    }
    // Asks the user for vehicle input, saves their answers then parses them through another function
    public static void addVehicle(Connection connection) throws SQLException {

        System.out.println("Please enter vehicle make: ");
        String vehicleMake = stdin.nextLine();

        System.out.println("Please enter vehicle model: ");
        String vehicleModel = stdin.nextLine();

        System.out.println("Please enter vehicle reg plate: ");
        String regPlate = stdin.nextLine();

        System.out.println("Please enter if the vehicle is SORN: ");
        Boolean sorn = Boolean.parseBoolean(stdin.nextLine());

        System.out.println("Please enter when the vehicle's road tax expires (yyyy-mm-dd): ");
        Date roadTax = java.sql.Date.valueOf(stdin.nextLine());
        insertVehicle(vehicleMake, vehicleModel, regPlate, sorn, roadTax, connection);
        System.out.println("New data inserted: " + vehicleMake);
    }
    // Adds the information inputted from addVehicle into the database
    public static void insertVehicle(String vehicleMake, String vehicleModel, String regPlate, boolean sorn, Date roadTax, Connection connection) throws SQLException {
        String SQL = "INSERT INTO vehicle (make, model, reg_plate, sorn, road_tax) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL);

        statement.setString(1, vehicleMake);
        statement.setString(2, vehicleModel);
        statement.setString(3, regPlate);
        statement.setBoolean(4, sorn);
        statement.setDate(5, roadTax);

        statement.executeUpdate();
    }
    public static void assignVehicleToDriver(Connection connection) throws SQLException {
        System.out.println("Please enter driver ID");
        int driverID = Integer.parseInt(stdin.nextLine());

        System.out.println("Please enter vehicle ID");
        int vehicleID = Integer.parseInt(stdin.nextLine());
        insertVehicleAndDriver(driverID, vehicleID, connection);
    }
    public static void insertVehicleAndDriver(int driverID, int vehicleID, Connection connection) throws SQLException {
        String SQL = "INSERT INTO vehicle_owner (driver_id, vehicle_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL);

        statement.setInt(1, driverID);
        statement.setInt(2, vehicleID);
        statement.executeUpdate();

    }
}