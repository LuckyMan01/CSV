import java.util.List;

public class Main {

    public static void main(String[] args) {
        StatementUser statementUser = new StatementUser();
        ParserCSVFile parserCSVFile = new ParserCSVFile("folder/grades.csv");
        parserCSVFile.readFileEtReturnCollectLine();
        List<User> array = parserCSVFile.fillingUserField();
//        System.out.println(array.get(7));
//       for (int i = 0 ; i< array.size();i++){
//           statementUser.create(array.get(i));
//       }
//        statementUser.delete(313795143143086080l);
    }
}
