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

public class UpdateData {

    static Scanner stdin = new Scanner(System.in);

    public static void updateDriver(Connection connection) throws SQLException {

        System.out.println("Please enter the driver you would like to update");
        System.out.println("Please enter first name");
        String firstName = stdin.nextLine();
        System.out.println("Please enter last name");
        String lastName = stdin.nextLine();
        System.out.println("Please enter date of birth");
        Date dob = Date.valueOf(stdin.nextLine());

        changeDriver(firstName, lastName, dob, connection);

    }
    public static void changeDriver(String firstName, String lastName, Date dob, Connection connection) throws SQLException {

        String change = null;
        String column = null;

        boolean quit = false;
        while (!quit) {
            System.out.println("What field would you like to edit?");
            System.out.println("1: First Name\n2: Last Name\n3: Date of Birth (yyyy-mm-dd)\n4: Phone Number\n5: Address ID\nPress any other key to quit");
            String choice = stdin.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Please enter new first name");
                    change = stdin.nextLine();
                    column = "first_name";
                }
                case "2" -> {
                    System.out.println("Please enter new last name");
                    change = stdin.nextLine();
                    column = "last_name";
                }
                case "3" -> {
                    System.out.println("Please enter new date of birth");
                    change = stdin.nextLine();
                    column = "dob";
                }
                case "4" -> {
                    System.out.println("Please enter new phone number");
                    change = stdin.nextLine();
                    column = "phone_number";
                }
                case "5" -> {
                    System.out.println("Please enter new address id");
                    change = stdin.nextLine();
                    column = "address_id";
                }
                default -> quit = true;
            }

            String paramState = column + " = " + "'" + change + "'";
            String SQL = "UPDATE driver SET " + paramState + " WHERE first_name = ? AND last_name = ? AND dob = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, dob);

            switch (column) {
                case "first_name" -> firstName = change;
                case "last_name" -> lastName = change;
                case "dob" -> dob = Date.valueOf(change);
            }

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Data edited: " + change + "\n");
            }
        }
    }

    public static void updateAddress(Connection connection) throws SQLException {

        System.out.println("Please enter the address you would like to update");
        System.out.println("Please enter street name");
        String streetName = stdin.nextLine();
        System.out.println("Please enter city name");
        String cityName = stdin.nextLine();

        changeAddress(streetName, cityName, connection);

    }
    public static void changeAddress(String streetName, String cityName, Connection connection) throws SQLException {

        String change = null;
        String column = null;

        boolean quit = false;
        while (!quit) {
            System.out.println("What field would you like to edit?");
            System.out.println("1: Street Name\n2: City Name\n3: Post Code\nPress any other key to quit");
            String choice = stdin.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Please enter new street name");
                    change = stdin.nextLine();
                    column = "street_name";
                }
                case "2" -> {
                    System.out.println("Please enter new city name");
                    change = stdin.nextLine();
                    column = "city";
                }
                case "3" -> {
                    System.out.println("Please enter new pose code");
                    change = stdin.nextLine();
                    column = "postcode";
                }
                default -> quit = true;
            }

            String paramState = column + " = " + "'" + change + "'";
            String SQL = "UPDATE address SET " + paramState + " WHERE street_name = ? AND city = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, streetName);
            statement.setString(2, cityName);

            if (column.equals("street_name")) {
                streetName = change;
            } else if (column.equals("city")) {
                cityName = change;
            }

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Data edited: " + change + "\n");
            }
        }
    }

    public static void updateVehicle(Connection connection) throws SQLException {

        System.out.println("Please enter the vehicle you would like to update");
        System.out.println("Please enter reg plate");
        String regPlate = stdin.nextLine();

        changeVehicle(regPlate, connection);

    }
    public static void changeVehicle(String regPlate, Connection connection) throws SQLException {

        String change = null;
        String column = null;

        boolean quit = false;
        while (!quit) {
            System.out.println("What field would you like to edit?");
            System.out.println("1: Vehicle Make\n2: Vehicle Model\n3: Reg Plate\n4: SORN\n5: Road Tax ID\nPress any other key to quit");
            String choice = stdin.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Please enter new vehicle make");
                    change = stdin.nextLine();
                    column = "make";
                }
                case "2" -> {
                    System.out.println("Please enter new vehicle model");
                    change = stdin.nextLine();
                    column = "model";
                }
                case "3" -> {
                    System.out.println("Please enter new reg plate");
                    change = stdin.nextLine();
                    column = "reg_plate";
                }
                case "4" -> {
                    System.out.println("Please enter new SORN status");
                    change = stdin.nextLine();
                    column = "sorn";
                }
                case "5" -> {
                    System.out.println("Please enter new road tax expiry date");
                    change = stdin.nextLine();
                    column = "road_tax";
                }
                default -> quit = true;
            }

            String paramState = column + " = " + "'" + change + "'";
            String SQL = "UPDATE vehicle SET " + paramState + " WHERE reg_plate = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, regPlate);

            if (column.equals("reg_plate")) {
                regPlate = change;
            }

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Data edited: " + change + "\n");
            }
        }
    }
    public static void updateVehicleOwner(Connection connection) throws SQLException {
        System.out.println("Please enter the vehicle you would like to update");
        System.out.println("Please enter vehicle id");
        int vehicleID = Integer.parseInt(stdin.nextLine());
        System.out.println("Please enter the new vehicle owner's driver id");
        int driverID = Integer.parseInt(stdin.nextLine());

        changeVehicleOwner(vehicleID, driverID, connection);
    }
    public static void changeVehicleOwner(int vehicleID, int driverID, Connection connection) throws SQLException {
        String SQL = "UPDATE vehicle_owner SET driver_id = ? WHERE vehicle_id = ?";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1,driverID);
        statement.setInt(2, vehicleID);
        statement.executeUpdate();
    }
}
