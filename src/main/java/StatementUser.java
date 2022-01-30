import java.sql.*;

public class StatementUser implements UserRepository{
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "admin@ukr.net";
    private final static String PASSWORD = "admin1";

    @Override
    public long create(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO public.users(id, last_name, first_name, ssn, test1, test2, test3, test4, final_test, grade) " + "VALUES ( "+ user +");";
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
        return false;
    }

}
