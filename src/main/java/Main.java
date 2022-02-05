
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PostgresUserRepository statementUser = new PostgresUserRepository();
        ParserCSVFile parserCSVFile = new ParserCSVFile("folder/grades.csv");
        parserCSVFile.readFileEtReturnCollectLine();
        List<User> array = parserCSVFile.fillingUserField();
//        List<Long> arrayId = statementUser.readAllId();
//        System.out.println(array.get(7));

       for (int i = 0 ; i< array.size();i++){
               statementUser.create(array.get(i));
       }
//
//        System.out.println(statementUser.read(3647999517480990720l));
//        System.out.println(array.get(1));
//        System.out.println(statementUser.update(3647999517480990720l,array.get(3)));
//        System.out.println(statementUser.delete(5395187528318694400l));


    }
}
