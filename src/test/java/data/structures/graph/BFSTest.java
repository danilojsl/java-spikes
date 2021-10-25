package data.structures.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    public void shouldSearchVertex() {
        int numberOfVertices = 5;

        ArrayList<Integer> adjacentVertex0 = new ArrayList<>();
        adjacentVertex0.add(1);
        adjacentVertex0.add(2);
        adjacentVertex0.add(3);

        ArrayList<Integer> adjacentVertex2 = new ArrayList<>();
        adjacentVertex2.add(4);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(adjacentVertex0);
        graph.add(new ArrayList<>());
        graph.add(adjacentVertex2);
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());

        ArrayList<Integer> result = BFS.bfsOfGraph(numberOfVertices, graph);
        System.out.println(result.size());
    }

}