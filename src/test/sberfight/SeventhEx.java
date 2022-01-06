package test.sberfight;

import java.util.*;
import java.util.stream.Collectors;

public class SeventhEx {

    public static void main(String[] args) {

        List<String> dislikeList = Arrays.asList("1-2,3", "3-4");
        int invited_list = 4;

        System.out.println(getResult(invited_list, dislikeList));
    }

    public static boolean getResult(int invitedList, List<String> dislikeList) {

        Map<String, List<String>> mapOfGuests = dislikeList.stream().collect(Collectors
                   .toMap( x->x.substring(0,1),
                           y-> Arrays.stream(Arrays.stream(y.split("-"))
                                   .collect(Collectors.toCollection(ArrayList::new))
                                   .get(1)
                                   .split(","))
                                   .collect(Collectors.toCollection(ArrayList::new))
                   )
        );

        //mapOfGuests.forEach((x,y)->System.out.println(x+" - "+Arrays.toString(y)));

        int totalComb = getFactorial(invitedList);

        for (Map.Entry<String,List<String>> guest: mapOfGuests.entrySet()) {
            totalComb /= (guest.getValue().size()+1) * invitedList-guest.getValue().size();
            System.out.println(guest.getKey()+" dislikes is "+guest.getValue().size());
            System.out.println("Количество полученных комб. для "+guest.getKey()+"-го гостя:"+totalComb);
        }

        return totalComb > 0;
    }

    private static int getFactorial(int n){
        if (n<2) return 1;
        return n * getFactorial(n-1);
    }
}
