import java.io.*;
import java.util.*;

public class Dijkstra13 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-08
     * ���� �ۼ��ð� : 14:51
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1854
     * ���� �̸� : K��° �ִ� ���
     * ���� ���̵� : �÷�Ƽ�� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * graph : �Է¹��� �׷���
     * distance : �� ��庰 �Ÿ� ������ ���� �迭
     */
    static int[][] graph = new int[1001][1001];
    static ArrayList<PriorityQueue<Integer>> distance = new ArrayList<>();

    // k��° �ִ� ��θ� ���ϴ� ���ͽ�Ʈ�� ���� �˰���
// start: ���� ����, node: �� ���� ��, k: �� �������� �ִ� �� ���� �ִ� ��θ� ��������
    static void dijkstra(int start, int node, int k) {
        // �ּ� �Ÿ� �������� ������ ó���ϱ� ���� �켱���� ť
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // ���� ���������� �Ÿ� 0���� �ʱ�ȭ�ϰ� ť�� ����
        queue.add(new Node(start, 0));
        distance.get(1).add(0); // ���� ���� 1���� ���� �Ÿ� ����Ʈ�� 0 �߰�

        // ť�� �� ������ �ݺ�
        while (!queue.isEmpty()) {
            Node now = queue.poll(); // ������� ���� ª�� �Ÿ��� ���� ����

            // ��� �ٸ� ������ ���� Ȯ��
            for (int next = 1; next <= node; next++) {
                // ���� �������� next �������� ���� ������ ������ ���
                if (graph[now.getVertex()][next] != 0) {
                    // ���� �Ÿ� + ���� ����ġ
                    int newDist = now.getWeight() + graph[now.getVertex()][next];

                    // ���� k���� ��ΰ� ������� �ʾ����� �׳� �߰�
                    if (distance.get(next).size() < k) {
                        distance.get(next).add(newDist);
                        queue.add(new Node(next, newDist));

                        // �̹� k���� á����, ���� �Ÿ����� ū ���� �ִٸ� ��ü
                    } else if (distance.get(next).peek() > newDist) {
                        distance.get(next).poll(); // ���� ū �� ���� (�켱���� ť�� max-heap�� ���)
                        distance.get(next).add(newDist); // ���ο� �� ª�� �Ÿ� �߰�
                        queue.add(new Node(next, newDist));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // �Ÿ� �迭 �ʱ�ȭ (Max Heap)
        for (int i = 0; i <= node; i++) {
            distance.add(new PriorityQueue<>(k, (o1, o2) -> o2 - o1)); // �������� = Max Heap
        }

        // ���� ���� �Է�
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
        }

        dijkstra(1, node, k); // ���� ���� 1

        // ���
        for (int i = 1; i <= node; i++) {
            if (distance.get(i).size() == k) {
                bw.write(distance.get(i).peek() + "\n");
            } else {
                bw.write("-1\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

/*
 * ���� ��带 ǥ���� Ŭ����
 * ��� ��ȣ�� ����ġ ����
 */
class Node implements Comparable<Node> {
    private int vertex;
    private int weight;

    Node(int vertex, int weight) {
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
    public int compareTo(Node n) {
        return Integer.compare(this.weight, n.getWeight());
    }
}
