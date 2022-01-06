package test.sberfight;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EighthEx {

    public static void main(String[] args) {
        List<String> deal = List.of("a-b", "b-c", "c-a", "d");

        System.out.println(getResult(deal));
    }

    public static int getResult(List<String> deal) {

        Map<String,List<String>> mapDeal = deal.stream().collect(Collectors.toMap(
                x->x.substring(0,1),
                y->{
                    String[] z = y.split("-");
                    if (z.length == 1) z[z.length-1] = "";
                    return Arrays.stream(z[z.length-1].split("")).toList();
                }
                )
        );

        return 0;
    }

    class Node{

        String title;
        List<Node> linkTo = new LinkedList<>();
        boolean visited = false;

        Node(String title,List<Node> linkTo){
            this.title = title;
            this.linkTo = linkTo;
        }

    }


}
