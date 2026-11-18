record Node(int vertex, int dist) implements Comparable<Node> {
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.dist, other.dist);
    }
}