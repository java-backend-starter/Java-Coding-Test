import java.io.*;
import java.util.*;

public class Dijkstra1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-08
     * ���� �ۼ��ð� : 10:01
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1753
     * ���� �̸� : �ִܰ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * graph : �Է¹��� �׷���
     * visited : �湮 ���θ� �����ϴ� �迭
     * distance : ����ġ �迭
     */
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean [] visited;
    static int [] distance;

    /*
     * ���ͽ�Ʈ�� �˰���
     * - �ϳ��� ���� �������� �ٸ� ��� ���������� �ִ� ��θ� ����
     * - ����/������ �׷��� ��� ���� (��, ���� ����ġ�� ������ ����)
     * - �켱���� ť�� ����� ���� ����� �������� ó��
     * - ����ġ�� �ִ� �׷����� BFS ���̵� Ȯ���� ���
     */
    static void dijkstra(int start) {
        // �ּ� �Ÿ� �켱������ ���� ó��
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0)); // ���� ������ ť�� ����
        distance[start] = 0; // ���� ���������� �Ÿ��� 0

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // ������� ���� ª�� �Ÿ��� ���� ����
            int now = node.getVertex();

            // �湮���� ���� �����̶�� ó��
            if (!visited[now]) {
                visited[now] = true;

                // ���� ������ ��� ���� ���� Ȯ��
                for (Node next : graph.get(now)) {
                    int nextVertex = next.getVertex();
                    int newDist = distance[now] + next.getWeight();

                    // �� ª�� ��ΰ� �߰ߵǸ� ����
                    if (!visited[nextVertex] && distance[nextVertex] > newDist) {
                        distance[nextVertex] = newDist;
                        queue.add(new Node(nextVertex, newDist));
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node : ��� ����
         * edge : ���� ����
         * start : ���� ���
         */
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i <= node; i++){
            graph.add(new ArrayList<>());
        }
        /*
         * �ʱ�ȭ
         * distance�� ���Ѵ�� �ʱ�ȭ�ؾ� �ϱ⶧���� Integer.MAX_VALUE�� ���� ä��
         */
        visited = new boolean[node + 1];
        distance = new int[node + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dijkstra(start);

        for(int i = 1; i <= node; i++){
            bw.write((visited[i] ? distance[i] : "INF") + "\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}

/*
 * ���� ��带 ǥ���� Ŭ����
 * ��� ��ȣ�� ����ġ ����
 */
class Node implements Comparable<Node>{
    private int vertex;
    private int weight;

    Node(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node n){
        if(this.weight > n.getWeight()){
            return 1;
        }
        else {
            return -1;
        }
    }
}