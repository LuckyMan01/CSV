import java.io.*;
import java.util.*;

public class ParserCSVFile {
    private final List<String> arrayLine = new ArrayList<>();
    private final String SEPARATOR = ",";
    private final String file;

    public ParserCSVFile(String file) {
        this.file = file;
    }

    public void readFileEtReturnCollectLine() {
        File newFile = new File(file);
        try {
            FileReader fr = new FileReader(newFile);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                arrayLine.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<Map<String, String>> mapUserFields() {
        List<Map<String, String>> array = new ArrayList<>();
        for (int i = 0; i < arrayLine.size(); i++) {
            Map<String, String> fieldsUser = new HashMap<>();
            String[] arrayFields = arrayLine.get(i).split(SEPARATOR);
            if (i < 1) {
                for (String arrayField : arrayFields) {
                    fieldsUser.put(arrayField, "");
                }
            }
            fieldsUser.put(Fields.LAST_NAME.getName(),arrayFields[Fields.LAST_NAME.getIndex()]);
            fieldsUser.put(Fields.FIRST_NAME.getName(),arrayFields[Fields.FIRST_NAME.getIndex()]);
            fieldsUser.put(Fields.SSN.getName(),arrayFields[Fields.SSN.getIndex()]);
            fieldsUser.put(Fields.TEST1.getName(),arrayFields[Fields.TEST1.getIndex()]);
            fieldsUser.put(Fields.TEST2.getName(),arrayFields[Fields.TEST2.getIndex()]);
            fieldsUser.put(Fields.TEST3.getName(),arrayFields[Fields.TEST3.getIndex()]);
            fieldsUser.put(Fields.TEST4.getName(),arrayFields[Fields.TEST4.getIndex()]);
            fieldsUser.put(Fields.FINAL.getName(),arrayFields[Fields.FINAL.getIndex()]);
            fieldsUser.put(Fields.GRADE.getName(),arrayFields[Fields.GRADE.getIndex()]);
            array.add(fieldsUser);

        }
        return array;
    }

    public List<User> fillingUserField() {
        List<User> arrayUser = new ArrayList<>();
        for (int i = 1; i < arrayLine.size(); i++) {
            User user = new User();
            String[] fieldUser = arrayLine.get(i).replace('\"','\'').split(SEPARATOR);
            user.setLastName(fieldUser[Fields.LAST_NAME.getIndex()]);
            user.setFirstName(fieldUser[Fields.FIRST_NAME.getIndex()]);
            user.setSsn(fieldUser[Fields.SSN.getIndex()]);
            user.setTest1(Double.parseDouble(fieldUser[Fields.TEST1.getIndex()]));
            user.setTest2(Double.parseDouble(fieldUser[Fields.TEST2.getIndex()]));
            user.setTest3(Double.parseDouble(fieldUser[Fields.TEST3.getIndex()]));
            user.setTest4(Double.parseDouble(fieldUser[Fields.TEST4.getIndex()]));
            user.setFinalTest(Double.parseDouble(fieldUser[Fields.FINAL.getIndex()]));
            user.setGrade(fieldUser[Fields.GRADE.getIndex()]);
            arrayUser.add(user);
        }

        return arrayUser;
    }
}
