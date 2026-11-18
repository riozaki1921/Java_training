public record Edge(int start, int end, int weight) {
    public Edge(int start, int end, int weight) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }
}
