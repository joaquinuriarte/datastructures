import java.util.*;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Joaquin Uriarte
 * @userid juriarte3
 * @GTID 903485636
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) { //q change?
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start doesn't exist in the graph.");
        } else {
            Queue<Vertex<T>> queue = new LinkedList<>();
            Set<Vertex<T>> already = new HashSet<>();
            List<Vertex<T>> end = new ArrayList<>();

            queue.add(start);
            already.add(start);

            while (!queue.isEmpty() && already.size() <= graph.getVertices().size()) {
                Vertex<T> curr = queue.remove();
                end.add(curr);
                for (VertexDistance<T> one : graph.getAdjList().get(curr)) {
                    if (!already.contains(one.getVertex())) {
                        queue.add(one.getVertex());
                        already.add(one.getVertex());
                    }
                }
            }
            return end;
        }
        /**
        ///////////////////////////////////////////////////////////////////////////////////////////////
        if (start == null) {
            throw new NoSuchElementException("Start argument can't be null.");
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> already = new HashSet<>();
        List<Vertex<T>> end = new ArrayList<>();

        queue.add(start);
        already.add(start);

        while (!queue.isEmpty() && already.size() <= this.vertices.size()) {
            Vertex<T> curr = queue.remove();
            end.add(curr);
            for (VertexDistance<T> one : adjList.get(curr)) {
                if (!already.contains(one.vertex)) {
                    queue.add(one.vertex);
                    already.add(one.vertex);
                }
            }
        }
        return end;
        ///////////////////////////////////////////////////////////////////////////////////////////////
        **/
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     * <p>
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start doesn't exist in the graph.");
        } else {
            Set<Vertex<T>> already = new HashSet<>();
            List<Vertex<T>> end = new ArrayList<>();

            dfsHelperMethod(start, graph, end, already);
            return end;
        }
    }

    /**
     * DFS Helper method.
     *
     * @param vertex  the vertex to begin the dfs on
     * @param graph   the graph to search through
     * @param already a set
     * @param end     a list
     * @param <T>     the generic typing of the data
     */
    private static <T> void dfsHelperMethod(Vertex<T> vertex, Graph<T> graph, List<Vertex<T>> end,
                                            Set<Vertex<T>> already) {
        end.add(vertex);
        already.add(vertex);
        for (VertexDistance<T> one : graph.getAdjList().get(vertex)) {
            if (!already.contains(one.getVertex())) {
                dfsHelperMethod(one.getVertex(), graph, end, already);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     * <p>
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     * <p>
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     * <p>
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     * <p>
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start doesn't exist in the graph.");
        } else {
            Set<Vertex<T>> already = new HashSet<>();
            Map<Vertex<T>, Integer> endMap = new HashMap<>();
            Queue<VertexDistance<T>> queue = new PriorityQueue<>();
            for (Vertex<T> one : graph.getVertices()) {
                if (!one.equals(start)) {
                    endMap.put(one, Integer.MAX_VALUE);
                } else {
                    endMap.put(one, 0);
                }
            }
            queue.add(new VertexDistance<>(start, 0));
            while (!queue.isEmpty() && already.size() != graph.getVertices().size()) {
                VertexDistance<T> curr = queue.remove();
                if (!already.contains(curr.getVertex())) {
                    already.add(curr.getVertex());
                    for (VertexDistance<T> one : graph.getAdjList().get(curr.getVertex())) {
                        if (curr.getDistance() + one.getDistance() < endMap.get(one.getVertex())
                                && !already.contains(one.getVertex())) {
                            endMap.put(one.getVertex(), curr.getDistance() + one.getDistance());
                            queue.add(new VertexDistance<>(one.getVertex(), curr.getDistance()
                                    + one.getDistance()));
                        }
                    }
                }
            }
            return endMap;
        }
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops or parallel edges in the MST.
     * <p>
     * You may import/use PriorityQueue, java.util.Set, and any class that
     * implements the aforementioned interface.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start doesn't exist in the graph.");
        } else {
            Set<Vertex<T>> already = new HashSet<>();
            Set<Edge<T>> end = new HashSet<>();
            Queue<Edge<T>> queue = new PriorityQueue<>();
            for (VertexDistance<T> one : graph.getAdjList().get(start)) {
                queue.add(new Edge<>(start, one.getVertex(), one.getDistance()));
            }
            already.add(start);
            while (!queue.isEmpty() && already.size() != graph.getVertices().size()) {
                Edge<T> curr = queue.remove();
                if (!already.contains(curr.getV())) {
                    already.add(curr.getV());
                    end.add(curr);
                    end.add(new Edge<>(curr.getV(), curr.getU(), curr.getWeight()));
                    for (VertexDistance<T> each : graph.getAdjList().get(curr.getV())) {
                        queue.add(new Edge<>(curr.getV(), each.getVertex(), each.getDistance()));
                    }
                }
            }
            if (!(2 * (graph.getVertices().size() - 1) == end.size())) {
                return null;
            }

            return end;
        }
    }
}