import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserRepository implements UserRepository,AutoCloseable {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "admin@ukr.net";
    private final static String PASSWORD = "admin1";
    private  static PreparedStatement statementCreate;

    static {
        try{
           statementCreate = DriverManager.getConnection(URL, USER_NAME, PASSWORD).prepareStatement("INSERT INTO public.users(id, last_name, first_name, ssn, test1, test2, test3, test4, final, grade) VALUES (?,?,?,?,?,?,?,?,?,? );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static long createIdUser() {
        return Long.MIN_VALUE + (long) (Math.random() * Long.MIN_VALUE);
    }
    @Override
    public long create(User user)  {
        long id = createIdUser();
        try{
            statementCreate.setLong(1,id);
            statementCreate.setString(2,user.getLastName().replace('\'', ' ').trim());
            statementCreate.setString(3,user.getFirstName().replace('\'', ' ').trim());
            statementCreate.setString(4,user.getSsn().replace('\'', ' ').trim());
            statementCreate.setDouble(5,user.getTest1());
            statementCreate.setDouble(6,user.getTest1());
            statementCreate.setDouble(7,user.getTest1());
            statementCreate.setDouble(8,user.getTest1());
            statementCreate.setDouble(9,user.getFinalTest());
            statementCreate.setString(10,user.getGrade().replace('\'', ' ').trim());

            statementCreate.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Long> readAllId() {
        List<Long> array = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.users ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                array.add(resultSet.getLong("id"));
            }
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User read(long id){
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.users WHERE id=" + id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setLastName(resultSet.getString(Fields.LAST_NAME.getName().replace('\'', ' ').trim()));
                user.setFirstName(resultSet.getString(Fields.FIRST_NAME.getName().replace('\'', ' ').trim()));
                user.setSsn(resultSet.getString(Fields.SSN.getName().replace('\'', ' ').trim()));
                user.setTest1(resultSet.getInt(Fields.TEST1.getName()));
                user.setTest2(resultSet.getInt(Fields.TEST2.getName()));
                user.setTest3(resultSet.getInt(Fields.TEST3.getName()));
                user.setTest4(resultSet.getInt(Fields.TEST4.getName()));
                user.setFinalTest(resultSet.getInt(Fields.FINAL.getName()));
                user.setGrade(resultSet.getString(Fields.GRADE.getName().replace('\'', ' ').trim()));
                resultSet.getLong("id");
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(long id, User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.users SET last_name=?, first_name=?, ssn=?, test1=?, test2=?, test3=?, test4=?, final=?, grade=?,id =?  WHERE id=" + id + ";");
            statement.setString(1, user.getLastName().replace('\'', ' ').trim());
            statement.setString(2, user.getFirstName().replace('\'', ' ').trim());
            statement.setString(3, user.getSsn().replace('\'', ' ').trim());
            statement.setDouble(4, user.getTest1());
            statement.setDouble(5, user.getTest2());
            statement.setDouble(6, user.getTest3());
            statement.setDouble(7, user.getTest4());
            statement.setDouble(8, user.getFinalTest());
            statement.setString(9, user.getGrade().replace('\'', ' ').trim());
            statement.setLong(10, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(long id){
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM public.users WHERE id =" + id + " ;");
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        statementCreate.close();
    }
}
