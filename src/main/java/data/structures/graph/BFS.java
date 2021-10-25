package data.structures.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS {

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<Integer> bfsTrack = new ArrayList<>();
        //Integer currentVertex = adj.indexOf(adj.get(0));

        // Mark the current node as visited and enqueue it
        visited[0]=true;
        queue.add(0);
        bfsTrack.add(0);

        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            Integer currentVertex = queue.poll();

            // Get all adjacent vertices of the current vertex
            // If an adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = adj.get(currentVertex).listIterator();
            while (i.hasNext())
            {
                int vertex = i.next();
                if (!visited[vertex])
                {
                    visited[vertex] = true;
                    queue.add(vertex);
                    bfsTrack.add(vertex);
                }
            }

        }

        return bfsTrack;

    }

}
