package test.sberfight;

import java.util.*;
import java.util.stream.Collectors;

public class ThirdthEx {

    public static void main(String[] args) {
       List<Integer> numb = List.of(-4, 0, 8, -1, 10);

        System.out.println(getResult(numb));

    }

    public static List<Integer> getResult(List<Integer> numb) {

       List<Integer> numbb = new ArrayList<>(numb);

       List<Integer> listNegative = numbb.stream().filter(x->x<0)
               .collect(Collectors.toCollection(ArrayList::new));

       if (listNegative.size() == numbb.size()-1)
           return numbb.stream().reduce(Integer::max).stream()
                   .collect(Collectors.toCollection(ArrayList::new));

        Map<Integer,List<Integer>> maxMap = new HashMap<>();
        List<Integer> elList = new ArrayList<>();

        for (int i=0;i<numbb.size();i++) {
            for (Integer el : numbb) {
                elList.add(el);

                //if (maxMap.get(elList.stream().reduce((x, y) -> x + y).get())==null)
                    maxMap.put(elList.stream().reduce((x, y) -> x + y).get(), new ArrayList<>(elList));
            }
            elList.clear();
            numbb.remove(0);
        }
        System.out.println(maxMap.values());
        return maxMap.get( maxMap.keySet().stream().max(Integer::compare).orElse(0));
    }
}
