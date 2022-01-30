public enum Fields {
    LAST_NAME(0,"Last_Name"),
    FIRST_NAME(1,"First_name"),
    SSN(2,"SSN"),
    TEST1(3,"Test1"),
    TEST2(4,"Test2"),
    TEST3(5,"Test3"),
    TEST4(6,"Test4"),
    FINAL(7,"Final"),
    GRADE(8,"Grade");


    private final int index;
    private final String name;

    Fields(int index, String name){
        this.index = index;
        this.name = name;

    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
