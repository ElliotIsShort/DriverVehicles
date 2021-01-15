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
public class DeleteData {

    static Scanner stdin = new Scanner(System.in);
    // Takes in parameters from user such as first and last name then parses them through function which removes them
    public static void deleteDriver(Connection connection) throws SQLException {

        System.out.println("Enter the name of the driver you would like to delete");
        System.out.println("Please enter first name: ");
        String firstName = stdin.nextLine();
        System.out.println("Please enter last name: ");
        String lastName = stdin.nextLine();
        System.out.println("Please enter date of birth");
        Date dob = java.sql.Date.valueOf(stdin.nextLine());

        removeDriver(firstName, lastName, dob, connection);

        System.out.println("Deleted Data:");
        System.out.println("First Name: " + firstName + "\nLast Name: " + lastName);
    }
    // Takes in user input from separate functions and then deletes desired driver
    public static void removeDriver(String firstName, String lastName, Date dob, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM driver WHERE first_name = ? AND last_name = ? AND dob = ?");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setDate(3, dob);

        statement.executeUpdate();
        statement.close();
    }
    // Takes in parameters from user such as street and city name then parses them through function which removes them
    public static void deleteAddress(Connection connection) throws SQLException {

        System.out.println("WARNING! Can only delete address if no user is currently using address");
        System.out.println("Enter the name of the address you would like to delete");
        System.out.println("Please enter street name: ");
        String streetName = stdin.nextLine();
        System.out.println("Please enter city name: ");
        String cityName = stdin.nextLine();

        removeAddress(streetName, cityName, connection);

        System.out.println("Deleted Data:");
        System.out.println("Street Name: " + streetName + "\nCity: " + cityName);
    }
    // Takes in user input from separate functions and then deletes desired address
    public static void removeAddress(String streetName, String cityName, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM address WHERE street_name = ? AND city = ?");
        statement.setString(1, streetName);
        statement.setString(2, cityName);

        statement.executeUpdate();
        statement.close();
    }
    // Takes in parameters from user such as reg plate then parses them through function which removes them
    public static void deleteVehicle(Connection connection) throws SQLException {

        System.out.println("Enter the name of the vehicle you would like to delete");
        System.out.println("Please enter reg plate: ");
        String regPlate = stdin.nextLine();

        removeVehicle(regPlate, connection);

        System.out.println("Deleted Data:");
        System.out.println("Reg Plate: " + regPlate);
    }
    // Takes in user input from separate functions and then deletes desired vehicle
    public static void removeVehicle(String regPlate, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicle WHERE reg_plate = ?");
        statement.setString(1, regPlate);

        statement.executeUpdate();
        statement.close();

    }
    // Takes in parameters from user such as vehicle ID then parses them through function which removes them
    public static void deleteVehicleOwner(Connection connection) throws SQLException {

        System.out.println("Enter the vehicle id of the vehicle owner you would like to delete");
        System.out.println("Please enter reg plate: ");
        int vehicleID = Integer.parseInt(stdin.nextLine());

        removeVehicleOwner(vehicleID, connection);

        System.out.println("Deleted Data:");
        System.out.println("VehicleID: " + vehicleID);
    }
    // Takes in user input from separate functions and then deletes desired vehicle from owner
    public static void removeVehicleOwner(int vehicleID, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicle_owner WHERE vehicle_id = ?");
        statement.setInt(1, vehicleID);

        statement.executeUpdate();
        statement.close();

    }
}
