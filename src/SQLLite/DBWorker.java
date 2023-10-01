package SQLLite;

import model.Formula;
import model.SI;
import model.Variable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "database.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection conn;

    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Драйвер: " + meta.getDriverName());
                //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к БД: " + ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public static SI getSI(int SIid) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SI WHERE SI.id =" + SIid);
        SI si = new SI(resultSet.getInt("ID"), resultSet.getString("name"));
        resultSet.close();
        statement.close();
        return si;
    }
    public static Formula getFormula(int formulaID) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Formula WHERE formula.id =" + formulaID);
        Formula formula = new Formula(resultSet.getInt("ID"), resultSet.getString("name"),
                resultSet.getString("formulation"),
                resultSet.getString("formula"),getSI(resultSet.getInt("SI")));
        resultSet.close();
        statement.close();
        return formula;
    }
    public static List<Formula> getAllFormula() throws SQLException {
        Statement statement = conn.createStatement();
        List<Formula> list = new ArrayList<Formula>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Formula");
        while (resultSet.next()) {
            list.add(new Formula(resultSet.getInt("ID"), resultSet.getString("name"),
                    resultSet.getString("formulation"),
                    resultSet.getString("formula"),getSI(resultSet.getInt("SI"))));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static List<SI> getAllSI() throws SQLException {
        Statement statement = conn.createStatement();
        List<SI> list = new ArrayList<SI>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SI");
        while (resultSet.next()) {
            list.add(new SI(resultSet.getInt("ID"), resultSet.getString("name")));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static List<Variable> getAllVariable() throws SQLException {
        Statement statement = conn.createStatement();
        List<Variable> list = new ArrayList<Variable>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Variable");
        while (resultSet.next()) {
            list.add(new Variable(resultSet.getInt("ID"), resultSet.getString("name"),
                    resultSet.getString("designation"),
                    getFormula(resultSet.getInt("formula"))));
        }
        resultSet.close();
        statement.close();
        return list;
    }
    public static void addFormula(Formula formula) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Formula('ID','name','formulation','formula','SI') " +
                        "VALUES(?,?,?,?,?)");
        statement.setObject(1, formula.getID());
        statement.setObject(2, formula.getName());
        statement.setObject(3, formula.getFormulation());
        statement.setObject(4, formula.getFormula());
        statement.setObject(5, formula.getSi().getID());
        statement.execute();
        statement.close();
    }
    public static void addSI(SI si) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO SI('ID','name') " +
                        "VALUES(?,?)");
        statement.setObject(1, si.getID());
        statement.setObject(2, si.getName());
        statement.execute();
        statement.close();
    }
    public static void addVariable(Variable variable) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO Variable('ID','name','designation','formula') " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, variable.getID());
        statement.setObject(2, variable.getName());
        statement.setObject(3, variable.getDesignation());
        statement.setObject(4, variable.getFormula().getID());
        statement.execute();
        statement.close();
    }
    public static void deleteSI(SI si) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM SI WHERE SI.ID =" + si.getID());
        statement.close();
    }
    public static void deleteFormula(Formula formula) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM Formula WHERE Formula.ID =" + formula.getID());
//        deleteSI(formula.getSi());
        statement.close();
    }
   /* public static Driver getDriver(int driverid) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM drivers WHERE drivers.id =" + driverid);
        Driver driver = new Driver(resultSet.getInt("id"), resultSet.getString("surname"),
                resultSet.getString("name"), resultSet.getString("patronymic"),
                resultSet.getInt("work_experience"));
        resultSet.close();
        statement.close();
        return driver;
    }*/

   /* public static Bus getBus(int busid) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM buses WHERE buses.id =" + busid);
        Bus bus = new Bus(resultSet.getInt("id"), resultSet.getString("license_plate"), resultSet.getString("brand"),
                resultSet.getInt("places"));
        resultSet.close();
        statement.close();
        return bus;
    }

    public static void deleteDriver(Driver driver) throws SQLException {
        Statement statement = conn.createStatement();
        deleteFlight(driver);
        statement.execute("DELETE FROM drivers WHERE drivers.id =" + driver.getId());
        System.out.println("Водитель удалён!");
        statement.close();
    }

    public static void deleteBus(Bus bus) throws SQLException {
        Statement statement = conn.createStatement();
        deleteFlight(bus);
        statement.execute("DELETE FROM buses WHERE buses.id =" + bus.getId());
        System.out.println("Автобус удалён!");
        statement.close();
    }

    public static void deleteFlight(Flight flight) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM flights WHERE flights.id =" + flight.getId());
        System.out.println("Рейс удалён!");
        statement.close();
    }

    public static void deleteFlight(Bus bus) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM flights WHERE flights.bus =" + bus.getId());
        System.out.println("Рейс удалён!");
        statement.close();
    }

    public static void deleteFlight(Driver driver) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM flights WHERE flights.driver =" + driver.getId());
        System.out.println("Рейс удалён!");
        statement.close();
    }

    public static void addDriver(Driver driver) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO drivers('id','surname','name','patronymic', 'work_experience') " +
                        "VALUES(?,?,?,?,?)");
        statement.setObject(1, driver.getId());
        statement.setObject(2, driver.getSurname());
        statement.setObject(3, driver.getName());
        statement.setObject(4, driver.getPatronymic());
        statement.setObject(5, Integer.toString(driver.getWork_experience()));
        statement.execute();
        statement.close();
        System.out.println("Добавление водителя!");
    }

    public static void addBus(Bus bus) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO buses('id',`license_plate`,`brand`,`places`) " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, bus.getId());
        statement.setObject(2, bus.getLicense_plate());
        statement.setObject(3, bus.getBrand());
        statement.setObject(4, bus.getPlaces());
        statement.execute();
        statement.close();
    }

    public static void addFlight(Flight flight) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO flights('id','number','cost','driver','bus') " +
                        "VALUES(?,?,?,?,?)");
        statement.setObject(1, flight.getId());
        statement.setObject(2, flight.getNumber());
        statement.setObject(3, flight.getCost());
        statement.setObject(4, flight.getDriver().getId());
        statement.setObject(5, flight.getBus().getId());
        statement.execute();
        statement.close();
    }*/
}
