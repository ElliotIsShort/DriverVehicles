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

public class menu {

    static Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";
        try {
            Connection connection = DriverManager.getConnection(jdbc, user, pass);
            System.out.println("Connection Successful");
            boolean quit = false;
            System.out.println("Welcome to the Vehicle and Driver system. What would you like to do?");

            while (!quit) {
                System.out.println("1: View Data");
                System.out.println("2: Create Data");
                System.out.println("3: Delete Data");
                System.out.println("4: Update Data");
                System.out.println("5: Assign vehicle to driver");
                System.out.println("Enter any other value to exit");
                String choice = stdin.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("1: View Drivers");
                        System.out.println("2: View Addresses");
                        System.out.println("3: View Vehicles");
                        System.out.println("4: View Vehicles with nearly expired road tax (Withing 30 days of expiring)");
                        System.out.println("5: View Driver's Vehicles");
                        String viewChoice = stdin.nextLine();
                        viewMenu(viewChoice, connection);
                    }
                    case "2" -> {
                        System.out.println("1: Create Drivers");
                        System.out.println("2: Create Addresses");
                        System.out.println("3: Create Vehicles");
                        String createChoice = stdin.nextLine();
                        createMenu(createChoice, connection);
                    }
                    case "3" -> {
                        System.out.println("1: Delete Drivers");
                        System.out.println("2: Delete Addresses");
                        System.out.println("3: Delete Vehicles");
                        System.out.println("4: Delete Vehicle from driver");
                        String deleteChoice = stdin.nextLine();
                        deleteMenu(deleteChoice, connection);
                    }
                    case "4" -> {
                        System.out.println("1: Update Drivers");
                        System.out.println("2: Update Addresses");
                        System.out.println("3: Update Vehicles");
                        System.out.println("4: Update Vehicle owner");
                        String updateChoice = stdin.nextLine();
                        updateMenu(updateChoice, connection);
                    }
                    case "5" -> {
                        System.out.println("1: Search for vehicle by reg plate");
                        System.out.println("2: Search for driver by name and date of birth");
                        System.out.println("3: Assign vehicle to driver");
                        System.out.println("To assign a vehicle to a driver you will need to know the vehicle ID and the driver ID.\nBoth are the first number displayed if you search for specific driver or vehicle");
                        String ownerChoice = stdin.nextLine();
                        vehicleOwnerMenu(ownerChoice, connection);
                    }
                    default -> {
                        quit = true;
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Error");
            e.printStackTrace();
        }
    }
    public static void viewMenu(String viewChoice, Connection connection) throws SQLException {
        switch (viewChoice) {
            case "1" -> {
                ViewData.getDriver(connection);
                continueKey();
            }
            case "2" -> {
                ViewData.getAddress(connection);
                continueKey();
            }
            case "3" -> {
                ViewData.getVehicle(connection);
                continueKey();
            }
            case "4" -> {
                ViewData.getVehicleByRoadTax(connection);
                continueKey();
            }
            case "5" -> {
                ViewData.driversOwnedVehicles(connection);
                continueKey();
            }
            default -> {
                System.out.println("Invalid choice");
                continueKey();
            }
        }
    }
    public static void createMenu(String createChoice, Connection connection) throws SQLException {
        switch (createChoice) {
            case "1" -> {
                CreateData.addDriver(connection);
                continueKey();
            }
            case "2" -> {
                CreateData.addAddress(connection);
                continueKey();
            }
            case "3" -> {
                CreateData.addVehicle(connection);
                continueKey();
            }
            default -> {
                System.out.println("Invalid choice");
                continueKey();
            }
        }
    }
    public static void deleteMenu(String deleteChoice, Connection connection) throws SQLException {
        switch (deleteChoice) {
            case "1" -> {
                DeleteData.deleteDriver(connection);
                continueKey();
            }
            case "2" -> {
                DeleteData.deleteAddress(connection);
                continueKey();
            }
            case "3" -> {
                DeleteData.deleteVehicle(connection);
                continueKey();
            }
            case "4" -> {
                DeleteData.deleteVehicleOwner(connection);
                continueKey();
            }
            default -> {
                System.out.println("Invalid choice");
                continueKey();
            }
        }
    }
    public static void updateMenu(String updateChoice, Connection connection) throws SQLException {
        switch (updateChoice) {
            case "1" -> {
                UpdateData.updateDriver(connection);
                continueKey();
            }
            case "2" -> {
                UpdateData.updateAddress(connection);
                continueKey();
            }
            case "3" -> {
                UpdateData.updateVehicle(connection);
                continueKey();
            }
            case "4" -> {
                UpdateData.updateVehicleOwner(connection);
                continueKey();
            }
            default -> {
                System.out.println("Invalid choice");
                continueKey();
            }
        }
    }
    public static void vehicleOwnerMenu(String ownerChoice, Connection connection) throws SQLException {
        switch (ownerChoice) {
            case "1" -> {
                ViewData.findVehicle(connection);
                continueKey();
            }
            case "2" -> {
                ViewData.findDriver(connection);
                continueKey();
            }
            case "3" -> {
                CreateData.assignVehicleToDriver(connection);
                continueKey();
            }
            default -> {
                System.out.println("Invalid choice");
                continueKey();
            }
        }
    }
    public static void continueKey() {
        System.out.println("Press enter to continue");
        String i = stdin.nextLine();
    }
}
