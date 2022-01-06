package test.sberfight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondEx {

    public static void main(String[] args) {

        List<Integer> arrayStart = Arrays.asList(1,3,4,2,5,6);
        List<Integer> arrayGoal = Arrays.asList (6,3,1,5,4,2);

        System.out.println( getResult(arrayStart, arrayGoal) );
    }


    public static int getResult(List<Integer> arrayStart, List<Integer> arrayGoal) {        

        List<Integer> aS = new ArrayList<>(arrayStart);
        
        int result = 0;

        if ( aS.size() == 1 ) return result;

        Integer lastStart = aS.get(aS.size()-1);
        Integer curGoal = arrayGoal.get(aS.size()-1);

        if ( lastStart != curGoal ){
            aS.set(aS.indexOf(curGoal), lastStart);
            result = 1;
        }
        aS.remove(aS.size()-1);
        System.out.println(aS);

        return result + getResult(aS, arrayGoal);
    }

}
