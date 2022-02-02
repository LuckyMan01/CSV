import java.sql.*;

public class StatementUser implements UserRepository {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "admin@ukr.net";
    private final static String PASSWORD = "admin1";

    private static long createIdUser() {
        return Long.MIN_VALUE + (long) (Math.random() * Long.MIN_VALUE) ;
    }

//    private String buildStringValues(User user) {
//        String userField =
//                createIdUser()+ " , " +
//                        user.getLastName() + ", " +
//                        user.getFirstName() + ", " +
//                        user.getSsn() + ", " +
//                        user.getTest1() + " , " +
//                        user.getTest2() + " , " +
//                        user.getTest3() + " , " +
//                        user.getTest4() + " , " +
//                        user.getFinalTest() + ", " +
//                        user.getGrade();
//        return userField;
//    }

    @Override
    public long create(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO public.users(id, last_name, first_name, ssn, test1, test2, test3, test4, final, grade) VALUES (" + buildStringValues(user) + ");";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getId();
    }


    @Override
    public User read(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            String sql = "SELECT * FROM public.users WHERE id=" + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            User user = new User();
            while (resultSet.next()){
                user.setLastName(resultSet.getString(Fields.LAST_NAME.getName()));
                user.setFirstName(resultSet.getString(Fields.FIRST_NAME.getName()));
                user.setSsn(resultSet.getString(Fields.SSN.getName()));
                user.setTest1(resultSet.getInt(Fields.TEST1.getName()));
                user.setTest2(resultSet.getInt(Fields.TEST2.getName()));
                user.setTest3(resultSet.getInt(Fields.TEST3.getName()));
                user.setTest4(resultSet.getInt(Fields.TEST4.getName()));
                user.setFinalTest(resultSet.getInt(Fields.FINAL.getName()));
                user.setGrade(resultSet.getString(Fields.GRADE.getName()));
                user.setId(resultSet.getLong("id"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
