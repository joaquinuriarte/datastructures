import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Set of tests for HW 10 (THE LAST ONE!!!) Graph Algorithms.
 *
 * Let me know if anything needs to be fixed/added!
 *
 * @author Liam Jones
 * @Version 1.1
 */
public class LiamGraphTests {

    private Graph<Integer> directedGraph;
    private Graph<Integer> undirectedGraph;
    private Graph<Integer> disconnectedGraph;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directedGraph = createDirectedGraph();
        undirectedGraph = createUndirectedGraph();
        disconnectedGraph = createDisconnectedGraph();
    }

    /**
     * Creates a directed graph with 15 vertices
     *
     * @return The graph
     */
    private Graph<Integer> createDirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 1; i <= 15; i++) {
            vertices.add(new Vertex<>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(3), 4));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(6), 7));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        edges.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        edges.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        edges.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        edges.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        edges.add(new Edge<>(new Vertex<>(10), new Vertex<>(11), 8));
        edges.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        edges.add(new Edge<>(new Vertex<>(12), new Vertex<>(7), 7));
        edges.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        edges.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        edges.add(new Edge<>(new Vertex<>(13), new Vertex<>(8), 5));
        edges.add(new Edge<>(new Vertex<>(13), new Vertex<>(14), 7));
        edges.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));
        edges.add(new Edge<>(new Vertex<>(15), new Vertex<>(6), 10));

        return new Graph<>(vertices, edges);
    }

    /**
     * Uses the same nodes from the directed graph, the edges are just duplicated so that they become undirected.
     *
     * @return The undirected graph
     */
    private Graph<Integer> createUndirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 1; i <= 15; i++) {
            vertices.add(new Vertex<>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<>();
        for (Edge<Integer> edge : directedGraph.getEdges()) {
            edges.add(edge);
            edges.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates a simple disconnected graph to test Prim's and Dijkstra's
     *
     * @return The graph
     */
    private Graph<Integer> createDisconnectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            vertices.add(new Vertex<>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(1), 2));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(2), 5));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(4), 5));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(1), 5));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 3));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(3), 3));

        return new Graph<>(vertices, edges);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS1() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(1), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{1,2,3,4,5,6,7,12,8,13,14,9,15,10,11};
        for (int i : list) {
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS2() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(2), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{2,3,4,5,1,6,7,12,8,13,14,9,15,10,11};
        for (int i : list) {
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS3() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(3), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{3,1,2,4,5,6,7,12,8,13,14,9,15,10,11};
        for (int i : list) {
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS4() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(4), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{4,6,7,12,8,13,14,9,15,10,11};
        for (int i : list) {
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS5() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(5), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{5,6,7,12,8,13,14,9,15,10,11};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS6() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(6), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{6,7,12,8,13,14,9,15,10,11};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS7() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(7), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{7,8,9,10,11,6,12,13,14,15};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS8() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(8), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{8,9,10,11,6,7,12,13,14,15};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS9() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(9), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{9,10,11,6,7,12,8,13,14,15};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS10() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(10), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{10,11,6,7,12,8,13,14,9,15};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS11() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(11), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{11,6,7,12,8,13,14,9,15,10};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS12() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(12), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{12,7,13,14,8,15,9,6,10,11};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS13() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(13), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{13,8,14,9,15,10,6,11,7,12};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS14() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(14), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{14,15,6,7,12,8,13,9,10,11};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testBFS15() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(15), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        int[] list = new int[]{15,6,7,12,8,13,14,9,10,11};
        for (int i : list){
            bfsExpected.add(new Vertex<>(i));
        }

        assertEquals(bfsExpected, bfsActual);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSException1() {
        GraphAlgorithms.bfs(null, directedGraph);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSException2() {
        GraphAlgorithms.bfs(new Vertex<>(1), null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSException3() {
        GraphAlgorithms.bfs(new Vertex<>(16), directedGraph);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS1() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(1), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{1,2,3,4,6,7,8,9,10,11,12,13,14,15,5};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);

        }

    @Test (timeout = TIMEOUT)
    public void testDFS2() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(2), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{2,3,1,4,6,7,8,9,10,11,12,13,14,15,5};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS3() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(3), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{3,1,2,4,6,7,8,9,10,11,12,13,14,15,5};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS4() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(4), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{4,6,7,8,9,10,11,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS5() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(5), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{5,6,7,8,9,10,11,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS6() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(6), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{6,7,8,9,10,11,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS7() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(7), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{7,8,9,10,11,6,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS8() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(8), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{8,9,10,11,6,7,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS9() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(9), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{9,10,11,6,7,8,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS10() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(10), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{10,11,6,7,8,9,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS11() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(11), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{11,6,7,8,9,10,12,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS12() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(12), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{12,7,8,9,10,11,6,13,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS13() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(13), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{13,8,9,10,11,6,7,12,14,15};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS14() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(14), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{14,15,6,7,8,9,10,11,12,13};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT)
    public void testDFS15() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(15), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();

        int[] list = new int[]{15,6,7,8,9,10,11,12,13,14};
        for (int i : list) {
            dfsExpected.add(new Vertex<>(i));
        }

        assertEquals(dfsExpected, dfsActual);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSException1() {
        GraphAlgorithms.dfs(null, directedGraph);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSException2() {
        GraphAlgorithms.dfs(new Vertex<>(1), null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSException3() {
        GraphAlgorithms.dfs(new Vertex<>(16), directedGraph);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras1() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(1), undirectedGraph);

        int[] list = new int[]{0,2,3,7,4,9,12,15,18,21,15,11,13,12,13};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras2() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(2), undirectedGraph);

        int[] list = new int[]{2,0,4,5,2,7,10,13,16,19,13,9,11,10,11};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras3() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(3), undirectedGraph);

        int[] list = new int[]{3,4,0,9,6,11,14,17,20,23,17,13,15,14,15};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras4() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(4), undirectedGraph);

        int[] list = new int[]{7,5,9,0,7,7,10,13,16,19,13,9,11,10,11};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras5() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(5), undirectedGraph);

        int[] list = new int[]{4,2,6,7,0,5,8,11,14,17,11,7,9,8,9};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras6() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(6), undirectedGraph);

        int[] list = new int[]{9,7,11,7,5,0,3,6,9,12,6,2,4,3,4};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras7() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(7), undirectedGraph);

        int[] list = new int[]{12,10,14,10,8,3,0,3,6,9,9,5,7,6,7};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras8() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(8), undirectedGraph);

        int[] list = new int[]{15,13,17,13,11,6,3,0,3,6,12,7,5,8,9};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras9() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(9), undirectedGraph);

        int[] list = new int[]{18,16,20,16,14,9,6,3,0,3,11,10,8,11,12};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras10() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(10), undirectedGraph);

        int[] list = new int[]{21,19,23,19,17,12,9,6,3,0,8,13,11,14,15};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras11() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(11), undirectedGraph);

        int[] list = new int[]{15,13,17,13,11,6,9,12,11,8,0,8,10,9,10};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras12() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(12), undirectedGraph);

        int[] list = new int[]{11,9,13,9,7,2,5,7,10,13,8,0,2,1,2};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras13() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(13), undirectedGraph);

        int[] list = new int[]{13,11,15,11,9,4,7,5,8,11,10,2,0,3,4};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras14() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(14), undirectedGraph);

        int[] list = new int[]{12,10,14,10,8,3,6,8,11,14,9,1,3,0,1};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstras15() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(15), undirectedGraph);

        int[] list = new int[]{13,11,15,11,9,4,7,9,12,15,10,2,4,1,0};
        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            expected.put(new Vertex<>(i), list[i - 1]);
        }

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstrasDisconnected1() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(1), disconnectedGraph);

        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>(1), 0);
        expected.put(new Vertex<>(2), 2);
        expected.put(new Vertex<>(3), Integer.MAX_VALUE);
        expected.put(new Vertex<>(4), 5);
        expected.put(new Vertex<>(5), Integer.MAX_VALUE);

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testDijkstrasDisconnected2() {
        Map<Vertex<Integer>, Integer> actual = GraphAlgorithms.dijkstras(new Vertex<>(3), disconnectedGraph);

        Map<Vertex<Integer>, Integer> expected = new HashMap<>();
        expected.put(new Vertex<>(1), Integer.MAX_VALUE);
        expected.put(new Vertex<>(2), Integer.MAX_VALUE);
        expected.put(new Vertex<>(3), 0);
        expected.put(new Vertex<>(4), Integer.MAX_VALUE);
        expected.put(new Vertex<>(5), 3);

        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasException1() {
        GraphAlgorithms.dijkstras(null, undirectedGraph);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasException2() {
        GraphAlgorithms.dijkstras(new Vertex<>(1), null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstrasException3() {
        GraphAlgorithms.dijkstras(new Vertex<>(16), undirectedGraph);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims1() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(1), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims2() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(2), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims3() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(3), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims4() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(4), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims5() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(5), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims6() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(6), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims7() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(7), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims8() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(8), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims9() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(9), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims10() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(10), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims11() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(11), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims12() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(12), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims13() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(13), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims14() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(14), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrims15() {
        Set<Edge<Integer>> actual = GraphAlgorithms.prims(new Vertex<>(15), undirectedGraph);

        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 2));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 5));
        expected.add(new Edge<>(new Vertex<>(2), new Vertex<>(5), 2));
        expected.add(new Edge<>(new Vertex<>(3), new Vertex<>(1), 3));
        expected.add(new Edge<>(new Vertex<>(5), new Vertex<>(6), 5));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 3));
        expected.add(new Edge<>(new Vertex<>(6), new Vertex<>(12), 2));
        expected.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 3));
        expected.add(new Edge<>(new Vertex<>(8), new Vertex<>(9), 3));
        expected.add(new Edge<>(new Vertex<>(9), new Vertex<>(10), 3));
        expected.add(new Edge<>(new Vertex<>(11), new Vertex<>(6), 6));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(13), 2));
        expected.add(new Edge<>(new Vertex<>(12), new Vertex<>(14), 1));
        expected.add(new Edge<>(new Vertex<>(14), new Vertex<>(15), 1));

        Set<Edge<Integer>> duplicates = new HashSet<>();
        for (Edge<Integer> edge : expected) {
            duplicates.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
        }
        for (Edge<Integer> edge : duplicates) {
            expected.add(edge);
        }
        assertEquals(expected, actual);
    }

    @Test (timeout = TIMEOUT)
    public void testPrimsDisconnected1() {
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>(1), disconnectedGraph));
    }

    @Test (timeout = TIMEOUT)
    public void testPrimsDisconnected2() {
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>(3), disconnectedGraph));
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrimsException1() {
        GraphAlgorithms.prims(null, undirectedGraph);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrimsException2() {
        GraphAlgorithms.prims(new Vertex<>(1), null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrimsException3() {
        GraphAlgorithms.prims(new Vertex<>(16), undirectedGraph);
    }
}
