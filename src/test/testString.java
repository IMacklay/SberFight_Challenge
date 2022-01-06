package test;

public class testString {
    public static void main(String[] args) {

        String demoString = "Mother was 8 dry the frame";

        System.out.println( MakeAction(demoString) );


        String stringDig = "4";

        int dig = Integer.parseInt(stringDig);

        System.out.printf("Digit is %d",dig);
    }

    private static String MakeAction(String inputString){
        return inputString.substring(3,3);
    }

}
