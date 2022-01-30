import java.util.List;

public class Main {

    public static void main(String[] args) {
        StatementUser statementUser = new StatementUser();
        ParserCSVFile parserCSVFile = new ParserCSVFile("folder/grades.csv");
        parserCSVFile.readFileEtReturnCollectLine();
        List<User> array =  parserCSVFile.fillingUserField();
        System.out.println(array.get(7));
//        statementUser.create(array.get(7));
    }
}
