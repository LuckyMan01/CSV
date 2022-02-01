import java.sql.*;

public class StatementUser implements UserRepository {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "admin@ukr.net";
    private final static String PASSWORD = "admin1";

    private String buildStringValues(User user) {
        String userField =
                user.getId() + " , " +
                        user.getLastName() + ", " +
                        user.getFirstName() + ", " +
                        user.getSsn() + ", " +
                        user.getTest1() + " , " +
                        user.getTest2() + " , " +
                        user.getTest3() + " , " +
                        user.getTest4() + " , " +
                        user.getFinalTest() + ", " +
                        user.getGrade();
        return userField;
    }

    @Override
    public long create(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO public.users(id, last_name, first_name, ssn, test1, test2, test3, test4, final_test, grade) VALUES (" + buildStringValues(user) + ");";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getId();
    }


    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public boolean update(long id, User user) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM public.users WHERE id =" + id + " ;";
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
