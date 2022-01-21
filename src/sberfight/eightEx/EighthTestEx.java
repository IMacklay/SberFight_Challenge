package sberfight.eightEx;

import java.util.*;
import java.util.stream.Collectors;

public class EighthTestEx {

    public static void main(String[] args) {
        List<String> deal = List.of("a-b", "b-dc", "c-a", "d-y","y-b");
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
            List<Node> res;
            for (Node n: allNodes) {
                n.setVisited(false);
                res = findCycle(n,new ArrayList<Node>());
                if (res != null && res.size()>1) totalCntCycle+=1;
                System.out.println(res.stream().map(x->" -> "+x.getTitle()).collect(Collectors.joining()));
            }

            return totalCntCycle;
        }

        List<Node> findCycle(Node startNode, List<Node> listNode){
            if (startNode.isVisited()) return listNode;
            if (startNode.getLinkTo().isEmpty() || startNode.getLinkFrom().isEmpty()){
                startNode.setVisited(true);
                return listNode;
            }

            if (!listNode.isEmpty() && startNode.equals(listNode.get(0)) ){
                return listNode;
            }


            listNode.add(startNode);
            startNode.setVisited(true);

            int cntCycle = 0;
            for (Node n: startNode.getLinkTo()) {

                List<Node> tempGraph = findCycle(n,listNode);

                System.out.println("--- temp Graph ---"+n.getTitle());
                System.out.println(tempGraph);
                System.out.println("------------------");

                if (n.getLinkTo().contains(listNode.get(0))) break;
            }

            return listNode;
        }
    }

    static class Node{

        private final String title;
        private final List<Node> linkTo = new ArrayList<>();
        private final List<Node> linkFrom = new ArrayList<>();
        private boolean visited = false;

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

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
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
                    ", visited=" + visited +
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


}
