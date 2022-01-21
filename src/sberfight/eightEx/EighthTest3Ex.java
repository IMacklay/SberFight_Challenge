package sberfight.eightEx;

import java.util.*;
import java.util.stream.Collectors;

public class EighthTest3Ex {

    public static void main(String[] args) {
        List<String> deal = List.of("a-b","b-cfg","c-a","d-f","f-g","g-a");
        //List<String> deal = List.of("a-b", "b-c", "c-a");

        System.out.println(getResult(deal));
    }

    public static int getResult(List<String> deal) {
        Graph graph = new Graph(deal);

        graph.printGraph();
        System.out.println("==============");

        return graph.findAllCycle();
    }

    static class Graph{
        int countNode;
        int countFringe;
        List<Node> allNodes = new ArrayList<>();
        Node firstItem;

        Graph (List<String> listGraph){

            countNode = listGraph.size();

            int i = 0;
            for (String el: listGraph){
                i++;
                String[] separEl = el.split("-");

                String title = separEl[0];
                Node node = createNode(title);
                if (i==1) firstItem = node;

                if (separEl.length > 1)
                    Arrays.stream(separEl[separEl.length-1].split(""))
                            .filter(x->!x.equals(""))
                            .forEach(x->{
                                        Node n = createNode(x);
                                        node.addLinkTo(n);
                                        n.addLinkFrom(node);
                                        countFringe++;
                                    });
            }

        }

        Node createNode(String newNodeCandidate){

            Node node = new Node(newNodeCandidate);
            if (allNodes.contains(node))
                node = allNodes.get(allNodes.indexOf(node));
            else
                allNodes.add(node);

            return node;
        }

        void printGraph(){

            System.out.println("Количество вершин: "+countNode);
            System.out.println("Количество ребер: "+countFringe);

            for (Node n: allNodes) {
                n.print();
            }
        }

        int findAllCycle(){
            int totalCntCycle = 0;
            List<Set<Node>> setNode = new ArrayList<>();

            for (Node n: allNodes) {

                List<Node> path = new ArrayList<>();
                List<List<Node>> cyclePaths = new ArrayList<>();

                findCycle(n, path, cyclePaths);

                for(List<Node> cyclePath: cyclePaths) {

                    if (cyclePath != null && cyclePath.size() > 1 &&
                            !cycleExists(cyclePath,setNode)

                    ) {
                        totalCntCycle += 1;
                        System.out.println("<====================>");
                        System.out.println(cyclePath.stream().map(x -> " -> " + x.getTitle()).collect(Collectors.joining()));
                        System.out.println("<====================>");
                        setNode.add(new HashSet<>(cyclePath));
                    }
                }

            }

            return totalCntCycle;
        }

        void findCycle(Node startNode, List<Node> path, List<List<Node>> cyclePath){

            //if (!cyclePath.isEmpty()) return;
            if (startNode.getCurColor()== NodeColor.BLACK) return;
            if (startNode.getLinkTo().isEmpty() || startNode.getLinkFrom().isEmpty()) return;

            System.out.println("curNode = "+startNode.getTitle()+" , идём в " +
                    "" + startNode.getLinkTo().stream().map(x->x.getTitle()).toList());

            //if (startNode != path.get(0)) startNode.setVisited(true);


            if (!path.isEmpty() && startNode.getLinkTo().equals(path.get(0)) && startNode.getCurColor()==NodeColor.GREY){

                //path.add(startNode);
                cyclePath.add(new ArrayList<Node>(path));
                //path.remove(startNode);
                System.out.println("Добавили в cyclePath");
                System.out.print(" cyclePath: ");

                for (List<Node> el: cyclePath) {
                    System.out.println();
                    el.stream().forEach(x -> System.out.print(x.getTitle()+" -> "));

                }
                System.out.println();

                return;
            }

            path.add(startNode);

            for (Node n: startNode.getLinkTo()) {


                //if (!cyclePath.isEmpty()) path.containsAll(cyclePath.get(0));
                //if (!cyclePath.isEmpty()) cyclePath.get(0).retainAll(path);
                System.out.println("Идем непосредственно в "+n.getTitle());
                System.out.println("Текущий Path: "+path.stream().map(Node::getTitle).toList());
                if (n.getCurColor()==NodeColor.WHITE) {
                    n.setCurColor(NodeColor.GREY);
                    findCycle(n, path, cyclePath);
                }
//                if (n.getCurColor()==NodeColor.GREY) {
//                    System.out.println("Найден цикл: *********");
//
//                    path.stream().forEach(x -> System.out.print(x.getTitle()+" -> "));
//                    cyclePath.add(new ArrayList<>(path));
//                    System.out.println("\n**********************");
//                }
            }

            startNode.setCurColor(NodeColor.BLACK);
        }

        boolean cycleExists(List<Node> listCycle, List<Set<Node>> savedCycle){
            boolean result = false;

            for(Set<Node> elSavedCycle: savedCycle){
                result = elSavedCycle.containsAll(listCycle) && listCycle.containsAll(elSavedCycle);

                if (result) break;
            }
            return result;
        }
    }

    static class Node{

        private final String title;
        private final List<Node> linkTo = new ArrayList<>();
        private final List<Node> linkFrom = new ArrayList<>();
        private NodeColor curColor = NodeColor.WHITE;

        Node(String title){
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public List<Node> getLinkTo() {
            return linkTo;
        }

        public List<Node> getLinkFrom() {
            return linkFrom;
        }

        public NodeColor getCurColor() {
            return curColor;
        }

        public void setCurColor(NodeColor curColor) {
            this.curColor = curColor;
        }

        void addLinkTo(Node node){
            if (!linkTo.contains(node))
                linkTo.add(node);
        }

        void addLinkFrom(Node node){
            if (!linkFrom.contains(node))
                linkFrom.add(node);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "title='" + title + '\'' +
                    ", linkTo=" + linkTo.stream().map(Node::getTitle).collect(Collectors.toCollection(ArrayList::new)) +
                    ", linkFrom=" + linkFrom.stream().map(Node::getTitle).collect(Collectors.toCollection(ArrayList::new)) +
                    ", curColor=" + curColor +
                    '}';
        }

        public void print(){
            System.out.println(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            return title.equals(node.title);
        }

        @Override
        public int hashCode() {
            return title.hashCode();
        }
    }

    enum NodeColor{
        WHITE, GREY, BLACK;
    }

}
