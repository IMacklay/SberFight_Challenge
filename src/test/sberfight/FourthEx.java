package test.sberfight;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourthEx {
    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(3, 2, 4, 5);

        System.out.println(getResult(arr,6));

    }

    public static boolean getResult(List<Integer> arr, int w) {

        boolean result=false;

        int cntMaxIter = Collections.max(arr);

        if (cntMaxIter == 0) return result;

        int i=0;

        while( i <= cntMaxIter ){
            i++;


            arr.set(arr.indexOf(cntMaxIter),cntMaxIter/2);

            int maxEl = Collections.max(arr);
            System.out.println(cntMaxIter+" // 2 "+arr+" число проведенных операций = "+i+" ; число возможных операций: "+(maxEl));
            cntMaxIter = maxEl;
            int sum = arr.stream().reduce(0,(a,b)->a+b);

            result = sum<=w;
            if (result) break;
        };
        return result;
    }
}
