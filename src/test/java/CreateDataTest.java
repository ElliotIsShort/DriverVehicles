import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class CreateDataTest {

    @Test
    void CreateDriverSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertDriver("John", "Smith", java.sql.Date.valueOf("1990-01-30"), "01792123456", 7, connection);

        String SQL = "SELECT driver_id, first_name, last_name, dob, phone_number, address_id FROM driver WHERE first_name = 'John' AND last_name = 'Smith' and phone_number = '01792123456'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        String firstName = null;
        String lastName = null;
        String dob = null;
        String phoneNumber = null;
        String addressID = null;
        while (resultSet.next()) {
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            dob = resultSet.getString("dob");
            phoneNumber = resultSet.getString("phone_number");
            addressID = resultSet.getString("address_id");
        }

        DeleteData.removeDriver("John", "Smith", java.sql.Date.valueOf("1990-01-30"), connection);

        assertEquals("John", firstName);
        assertEquals("Smith", lastName);
        assertEquals("1990-01-30", dob);
        assertEquals("01792123456", phoneNumber);
        assertEquals("7", addressID);
    }
    @Test
    void CreateAddressSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertAddress("Test Street", "New York", "sa1 2ab", connection);

        String SQL = "SELECT address_id, street_name, city, postcode FROM address WHERE street_name = 'Test Street' AND city = 'New York' AND postcode = 'sa1 2ab'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        String streetName = null;
        String cityName = null;
        String postcode = null;
        while (resultSet.next()) {
            streetName = resultSet.getString("street_name");
            cityName = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
        }

        assertEquals("Test Street", streetName);
        assertEquals("New York", cityName);
        assertEquals("sa1 2ab", postcode);

        DeleteData.removeAddress("Test Street", "New York", connection);
    }
    @Test
    void CreateVehicleSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertVehicle("Test Ford", "Model Test", "AB12 CDE", true, java.sql.Date.valueOf("2021-01-30"), connection);

        String SQL = "SELECT vehicle_id, make, model, reg_plate, sorn, road_tax FROM vehicle WHERE reg_plate = 'AB12 CDE'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        String make = null;
        String model = null;
        String regPlate = null;
        String sorn = null;
        String roadTax = null;
        while (resultSet.next()) {
            make = resultSet.getString("make");
            model = resultSet.getString("model");
            regPlate = resultSet.getString("reg_plate");
            sorn = resultSet.getString("sorn");
            roadTax = resultSet.getString("road_tax");
        }

        assertEquals("Test Ford", make);
        assertEquals("Model Test", model);
        assertEquals("AB12 CDE", regPlate);
        assertEquals("t", sorn);
        assertEquals("2021-01-30", roadTax);

        DeleteData.removeVehicle("AB12 CDE", connection);
    }
}
