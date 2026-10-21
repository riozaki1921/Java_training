import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int[] parent;

    private static int find(int vertex){
        if (parent[vertex] == vertex) return vertex;
        return parent[vertex] = find(parent[vertex]);
    }

    private static boolean union(int e1, int e2) {
        int rootU = find(e1);
        int rootV = find(e2);
        if (rootU != rootV) {
            parent[rootV] = rootU;
            return true;
        }
        return false;
    }

    public static void main (String[] args) {
        List<Edge> RawEdges = List.of(
                new Edge(1, 5, 2),
                new Edge(1, 2, 6),
                new Edge(2, 5, 1),
                new Edge(6, 5, 7),
                new Edge(2, 6, 5),
                new Edge(2, 3, 9),
                new Edge(2, 7, 3),
                new Edge(6, 7, 4),
                new Edge(3, 7, 8),
                new Edge(1, 5, 2),
                new Edge(7, 8, 16),
                new Edge(7, 4, 3),
                new Edge(3, 4, 12),
                new Edge(4, 8, 4)
        );

        int NumberOfVertices = 8;

        System.out.println("--------Нахождение остовного дерева--------");
        System.out.println("--------Алгоритм Краскала--------");
        List<Edge> SortedEdges = RawEdges.stream().sorted().toList();

        parent = new int[NumberOfVertices + 1];
        for (int i = 1; i < NumberOfVertices + 1; i++) parent[i] = i;
        List<Edge> kruskal = new ArrayList<>();
        int SummaryWeightKruskal = 0;

        for (Edge edge : SortedEdges) {
            if (union(edge.start(), edge.end())) {
                kruskal.add(edge);
                SummaryWeightKruskal +=edge.weight();
            }
        }
        kruskal.forEach(System.out::println);
        System.out.println("Общий вес по Краскалу: " + SummaryWeightKruskal);

        System.out.println("\n--------Алгоритм Прима--------");
        List<Edge> prima = new ArrayList<>();
        int SummaryWeightPrima = 0;
        boolean[] visited = new boolean[NumberOfVertices + 1];
        visited[1] = true;

        while (prima.size() < NumberOfVertices - 1) {
            Edge minEdge = null;

            for (Edge edge : RawEdges) {
                if (visited[edge.start()] && !visited[edge.end()] || !visited[edge.start()] && visited[edge.end()]) {
                    if (minEdge == null || minEdge.weight() > edge.weight()) {
                        minEdge = edge;
                    }
                }
            }
            if (minEdge == null) break;

            prima.add(minEdge);
            SummaryWeightPrima += minEdge.weight();
            visited[minEdge.start()] = true;
            visited[minEdge.end()] = true;
        }
        prima.forEach(System.out::println);
        System.out.println("Общий вес по Приму: " + SummaryWeightPrima);
    }
}