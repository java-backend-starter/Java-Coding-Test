import java.io.*;
import java.util.*;

public class TopologicalSort3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-07
     * ���� �ۼ��ð� : 13:01
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1948
     * ���� �̸� : �Ӱ� ���
     * ���� ���̵� : �÷�Ƽ�� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    // graph: ������ �׷��� (���� ���� ����)
    // reverse: ������ �׷��� (�Ӱ� ��� ������)
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Node>> reverse = new ArrayList<>();

    static int[] indegree;    // �� ����� ���� ����
    static boolean[] visited; // ������ Ž�� �� �湮 ����
    static int[] result;      // ��������� �� ������ �����ϴ� �� �ɸ��� �ִ� �ð�
    static int count = 0;     // �Ӱ� ��ο� ���Ե� ���� ��

    /*
     * ���� ������ �����ϸ� �� ������ �����ϴ� �ִ� �ð��� ���
     * ��� ���ÿ��� �����Ͽ� ������ ��� ��θ� ���󰡸� �ִ밪 ����
     */
    static void topologicalSort(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Node next : graph.get(now)) {
                int nextNode = next.getNode();
                int time = next.getWeight();

                // ���� ��θ� ���� �ð� ����
                result[nextNode] = Math.max(result[nextNode], result[now] + time);

                // ���� ���� ����
                indegree[nextNode]--;
                if (indegree[nextNode] == 0) {
                    queue.offer(nextNode);
                }
            }
        }
    }

    /*
     * ������ BFS�� ���� ���� ��ο� ���Ե� ������ ���� ���� ����
     * ���� ���ú��� �����ؼ� ��� ���ñ��� �Ųٷ� ����
     * ����: �ش� ������ ���� ����� �Ϻ����� Ȯ��(result[now] == result[prevNode] + time)
     */
    static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Node prev : reverse.get(now)) {
                int prevNode = prev.getNode();
                int time = prev.getWeight();

                // �Ӱ� ��� ���� ���� �� ���� �� ����
                if (result[now] == result[prevNode] + time) {
                    count++;

                    // �湮���� ���� ���� ť�� �߰�
                    if (!visited[prevNode]) {
                        visited[prevNode] = true;
                        queue.offer(prevNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int city = Integer.parseInt(br.readLine());  // ���� ��
        int load = Integer.parseInt(br.readLine());  // ���� ��

        indegree = new int[city + 1];
        visited = new boolean[city + 1];
        result = new int[city + 1];

        // �׷��� �ʱ�ȭ
        for (int i = 0; i <= city; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        // ���� ���� �Է�
        for (int i = 0; i < load; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // ��� ����
            int v = Integer.parseInt(st.nextToken()); // ���� ����
            int w = Integer.parseInt(st.nextToken()); // �ҿ� �ð�

            graph.get(u).add(new Node(v, w));
            reverse.get(v).add(new Node(u, w));
            indegree[v]++;  // ���� ������ ���� ���� ����
        }

        // ��� ���ÿ� ���� ���� �Է�
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // ���� ������ ���� ���� ��� ���
        topologicalSort(start);

        // ���� ��ο� ���Ե� ���� �� ���
        BFS(end);

        // ��� ���: �� �ҿ� �ð�, �Ӱ� ��� ���� ��
        System.out.println(result[end]);
        System.out.println(count);
    }
}

/*
 * Node: ����� ���ÿ� �ҿ� �ð� ������ ��� Ŭ����
 */
class Node {
    private int node;
    private int weight;

    Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public int getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}