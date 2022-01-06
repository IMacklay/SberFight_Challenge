package sberfight;

import java.util.Arrays;
import java.util.List;

public class SixthEx {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(100, 2, 1);
        System.out.println(getResult(list));
    }

    public static boolean getResult(List<Integer> fences) {

        for(int i=0;i<fences.size()-1;i++){
            int sum = fences.get(i);
            for(int j=i+1;j<fences.size();j++){
                if (sum == fences.size()-1) return true;
                sum += fences.get(j);
            }
        }

        return false;
    }
}
