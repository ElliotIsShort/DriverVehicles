import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteDataTest {

    @Test
    void DeleteDriverSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertDriver("John", "Smith", java.sql.Date.valueOf("1990-01-30"), "01792123456", 7, connection);
        DeleteData.removeDriver("John", "Smith", java.sql.Date.valueOf("1990-01-30"), connection);

        String SQL = "SELECT driver_id, first_name, last_name, dob, phone_number, address_id FROM driver WHERE first_name = 'John' AND last_name = 'Smith' and phone_number = '01792123456'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        boolean deletedData = false;
        if (resultSet.next() == false) {
            deletedData = true;
        }

        assertEquals(true, deletedData);
    }
    @Test
    void DeleteAddressSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertAddress("Test Street", "New York", "sa1 2ab", connection);
        DeleteData.removeAddress("Test Street", "New York", connection);

        String SQL = "SELECT address_id, street_name, city, postcode FROM address WHERE street_name = 'Test Street' AND city = 'New York' AND postcode = 'sa1 2ab'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        boolean deletedData = false;
        if (resultSet.next() == false) {
            deletedData = true;
        }

        assertEquals(true, deletedData);
    }
    @Test
    void DeleteVehicleSuccess() throws Exception {
        String jdbc = "jdbc:postgresql://localhost:5432/vehicleanddriver";
        String user = "postgres";
        String pass = "password";

        Connection connection = DriverManager.getConnection(jdbc, user, pass);

        CreateData.insertVehicle("Test Ford", "Model Test", "AB12 CDE", true, java.sql.Date.valueOf("2021-01-30"), connection);
        DeleteData.removeVehicle("AB12 CDE", connection);

        String SQL = "SELECT vehicle_id, make, model, reg_plate, sorn, road_tax FROM vehicle WHERE reg_plate = 'AB12 CDE'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        boolean deletedData = false;
        if (resultSet.next() == false) {
            deletedData = true;
        }

        assertEquals(true, deletedData);
    }
}
