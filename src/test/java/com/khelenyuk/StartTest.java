package com.khelenyuk;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StartTest {
    private Map<String, Set<Edge>> graph = new HashMap<>();

    @Before
    public void setUp(){
        graph.put("A", Stream.of(new Edge("B", 2),
                new Edge("C", 4),
                new Edge("D", 10)).collect(Collectors.toSet()));

        graph.put("B", Stream.of(new Edge("A", 2),
                new Edge("D", 17)).collect(Collectors.toSet()));

        graph.put("C", Stream.of(new Edge("A", 4),
                new Edge("E", 15),
                new Edge("D", 3)).collect(Collectors.toSet()));

        graph.put("D", Stream.of(new Edge("A", 10),
                new Edge("B", 17),
                new Edge("E", 9),
                new Edge("C", 3)).collect(Collectors.toSet()));

        graph.put("E", Stream.of(new Edge("C", 15),
                new Edge("D", 9)).collect(Collectors.toSet()));

    }

    @Test
    public void verifyShortestRoute() {
        String start = "B";
        String end = "E";

        List<Node> list = Start.searchShortestRoute(graph, start, end);
        List <String> actual = list.stream().map(Node::getCurrentNode).collect(Collectors.toList());

        assertEquals(4, list.size());
        assertEquals(Arrays.asList("A", "C", "D", "E"), actual);
    }

    @Test
    public void verifyShortestCost() {
        String start = "C";
        String end = "B";

        List<Node> list = Start.searchShortestRoute(graph, start, end);
        assertEquals(6, list.get(list.size() - 1).getCost().intValue());
    }

    @Test
    public void verifySymmetry() {
        String start1 = "D";
        String end1 = "A";

        String start2 = "A";
        String end2 = "D";

        List<Node> list1 = Start.searchShortestRoute(graph, start1, end1);
        List<Node> list2 = Start.searchShortestRoute(graph, start2, end2);

        assertEquals(list1.size(), list2.size());
        assertEquals(list1.get(list1.size() - 1).getCost(), list2.get(list1.size() - 1).getCost());
    }


}