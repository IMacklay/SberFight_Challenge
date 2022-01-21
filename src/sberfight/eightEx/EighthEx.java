package sberfight.eightEx;

import java.util.*;
import java.util.stream.Collectors;

public class EighthEx {

    public static void main(String[] args) {
        //List<String> deal = List.of("a-bf","b-c","c-a","d-f","f-g","g-a");
        List<String> deal = List.of("a-b", "b-c", "c-a");
        System.out.println(getResult(deal));
    }

    public static int getResult(List<String> deal) {

        Map<String,List<String>> mapDeal = deal.stream().collect(Collectors.toMap(
                x->x.substring(0,1),
                y->{
                    String[] z = y.split("-");
                    if (z.length == 1) z[z.length-1] = "";
                    return Arrays.stream(z[z.length-1].split(""))
                            .collect(Collectors.toCollection(ArrayList::new));
                }
                )
        );

//        Map<String,List<String>> mapDealCor = mapDeal.entrySet().stream().filter(x->!x.getValue().contains(""))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        List<String> listOfDealer = new ArrayList<>(mapDeal.keySet());
//        List<List<String>> listOfDeal = new ArrayList<>(mapDeal.values());

        Set<String> resultSet = new HashSet<>();


        int cntCindy = 0;



        return cntCindy;
    }


}
