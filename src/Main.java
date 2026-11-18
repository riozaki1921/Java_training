import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Edge> graph = List.of(
                new Edge(1, 2, 9),
                new Edge(1, 3, 2),

                new Edge(2, 4, 4),
                new Edge(2, 5, 12),

                new Edge(3, 2, 6),
                new Edge(3, 5, 28),
                new Edge(3, 6, 10),

                new Edge(4, 5, 16),
                new Edge(4, 7, 19),

                new Edge(5, 7, 3),

                new Edge(6, 5, 15),
                new Edge(6, 7, 25)
        );

        System.out.println("Динамическое программирование");
        solveDP(graph, 7, 1, 7);

        List<Edge> edges = List.of(
                new Edge(1, 2, 8),
                new Edge(1, 4, 5),
                new Edge(1, 6, 9),

                new Edge(2, 3, 2),
                new Edge(2, 4, 1),

                new Edge(3, 5, 3),
                new Edge(3, 8, 10),

                new Edge(4, 5, 9),
                new Edge(4, 6, 3),

                new Edge(5, 7, 4),
                new Edge(5, 8, 6),

                new Edge(6, 7, 12),

                new Edge(7, 8, 7)
        );

        System.out.println("\nАлгоритм Дейкстры");
        dijkstra(edges, 8, 1, 8);
    }

    public static void solveDP(List<Edge> graph, int n, int start, int end) {
        int[] dp = new int[n + 1];
        int[] parent = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;

        int[] order = {1, 3, 2, 4, 6, 5, 7};

        for (int u : order) {
            if (dp[u] == Integer.MAX_VALUE) continue;

            for (Edge edge : graph) {
                if (edge.start() == u) {
                    int v = edge.end();
                    if (dp[u] + edge.weight() < dp[v]) {
                        dp[v] = dp[u] + edge.weight();
                        parent[v] = u;
                    }
                }
            }
        }

        printResult(start, end, dp[end], parent);
    }

    public static void dijkstra(List<Edge> edges, int n, int start, int end) {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.start()).add(new Edge(e.start(), e.end(), e.weight()));
            adj.get(e.end()).add(new Edge(e.end(), e.start(), e.weight()));
        }

        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex();

            if (current.dist() > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.end();
                int newDist = dist[u] + edge.weight();

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    parent[v] = u;
                    pq.add(new Node(v, newDist));
                }
            }
        }

        printResult(start, end, dist[end], parent);
    }

    private static void printResult(int start, int end, int minDistance, int[] parent) {
        List<Integer> path = new ArrayList<>();
        for (int curr = end; curr != 0; curr = parent[curr]) {
            path.add(curr);
        }
        Collections.reverse(path);

        System.out.println("Минимальное расстояние из " + start + " в " + end + ": " + minDistance);
        System.out.println("Оптимальный маршрут: " + path);
    }
}
