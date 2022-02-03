import java.util.List;

public class Main {

    public static void main(String[] args) {
        StatementUser statementUser = new StatementUser();
        ParserCSVFile parserCSVFile = new ParserCSVFile("folder/grades.csv");
        parserCSVFile.readFileEtReturnCollectLine();
        List<User> array = parserCSVFile.fillingUserField();
//        List<Long> arrayId = statementUser.readAllId();
//        System.out.println(array.get(7));
//       for (int i = 0 ; i< array.size();i++){
//           statementUser.create(array.get(i));
//       }
//
//        System.out.println(statementUser.read(3763065824021874688l));
        System.out.println(array.get(1));
        System.out.println(statementUser.update(7578126764776260608l,array.get(1)));
//        System.out.println(statementUser.delete(5395187528318694400l));
    }
}
