record Edge(int start, int end, int weight) implements Comparable<Edge> {
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "Ребро(" + start + " - " + end + " ) [вес=" + weight + "]";
    }
}