package com.khelenyuk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class Start {

    public static void main(String[] args) throws IOException {
        /*
         * Read data from file 'data.json' and map it to Map
         */
        byte[] jsonData = Files.readAllBytes(Paths.get("data.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Set<Edge>> graph = objectMapper.readValue(jsonData, new TypeReference<HashMap<String, Set<Edge>>>() {});

        String start = "D";
        String end = "A";
        List<Node> result = searchShortestRoute(graph, start, end);
        result.stream()
                .filter(node -> node.getPrevious() != null)
                .forEach(node -> System.out.println(node.getPrevious() + " -> " + node.getCurrent()));
        System.out.println("Total cost: " + result.get(result.size()-1).getCost());
    }

    static List<Node> searchShortestRoute(Map<String, Set<Edge>> graph, String start, String end) {
        List<String> notVisited = new ArrayList<>(graph.keySet());
        Map<String, Node> resultTable = new HashMap<>();
        resultTable.put(start, new Node(start, null, 0));

        while (true) {
            String toOpen = null;
            Integer bestCost = Integer.MAX_VALUE;

            for (String p : notVisited) {
                if (resultTable.containsKey(p) && resultTable.get(p).getCost() < bestCost) {
                    toOpen = p;
                    bestCost = resultTable.get(p).getCost();
                }
            }

            if (toOpen.equals(end)) {
                break;
            }

            for (Edge edge : graph.get(toOpen)) {
                Integer currentCost = resultTable.get(toOpen).getCost() + edge.getCost();

                if (!resultTable.containsKey(edge.getNode()) || resultTable.get(edge.getNode()).getCost() > currentCost) {
                    resultTable.put(edge.getNode(), new Node(edge.getNode(), toOpen, currentCost));
                }
            }
            notVisited.remove(toOpen);
        }

        List<Node> result = new ArrayList<>();

        String pointer = end;
        while (!pointer.equals(start)) {
            result.add(resultTable.get(pointer));
            pointer = resultTable.get(pointer).getPrevious();
        }
        Collections.reverse(result);

        return result;
    }
}





