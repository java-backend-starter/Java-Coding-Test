import java.io.*;
import java.util.*;

public class LCA1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-17
     * ���� �ۼ��ð� : 11:41
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11437
     * ���� �̸� : LCA
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // Ʈ���� ���� ����Ʈ ���·� ����
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    /*
     * depth[i] : i�� ����� ���� (��Ʈ�� 0)
     * parent[i] : i�� ����� �θ� ��� ��ȣ
     * visited[i] : BFS ���� �� �湮 ���θ� üũ
     */
    static int[] depth;
    static int[] parent;
    static boolean[] visited;

    /**
     * �� ����� �ּ� ���� ����(LCA: Lowest Common Ancestor)�� ���ϴ� �Լ�
     * @param a ��� a
     * @param b ��� b
     * @return ��� a�� b�� �ּ� ���� ����
     */
    static int lca(int a, int b) {
        // �׻� a�� �� ���� ��尡 �ǵ��� ����
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // ���̸� ���߱� ���� a�� �θ� �������� �̵�
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        // �� ��尡 ������ ������ ���ÿ� �θ� ���� �̵�
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        // �ᱹ a�� b�� ������ ������ �ּ� ���� ����
        return a;
    }

    /**
     * BFS(�ʺ� �켱 Ž��)�� ���� �� ����� ����(depth)�� �θ�(parent)�� �����ϴ� �Լ�
     * @param node Ž�� ���� ��� (���� ��Ʈ ��� 1��)
     */
    static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);         // ���� ��带 ť�� �߰�
        visited[node] = true;    // ���� ��带 �湮 ó��

        /*
         * level : ���� Ž�� ���� ����
         * nowSize : ���� ���������� ��� ��
         * count : ���� ���̿��� ó���� ��� ��
         */
        int level = 1;
        int nowSize = 1;
        int count = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();  // ť���� ��带 ����

            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;     // �湮 ó��
                    queue.add(next);          // �ڽ� ��� ť�� �߰�
                    parent[next] = now;       // ���� ��带 �ڽ��� �θ�� ����
                    depth[next] = level;      // �ڽ� ����� ���̸� ���� ������ ����
                }
            }

            count++;  // ���� ���̿��� ó���� ��� �� ����

            // ���� ������ ��� ��带 ó�������� ���� ���̷� �̵�
            if (count == nowSize) {
                count = 0;
                nowSize = queue.size(); // ���� ���̿� �ִ� ��� ��
                level++;                // ���� ����
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine()); // ����� ���� �Է�

        /*
         * Ʈ�� ���� �ʱ�ȭ (1�� ������ �����ϱ� ���� node+1 ũ��� �迭 ����)
         */
        depth = new int[node + 1];
        parent = new int[node + 1];
        visited = new boolean[node + 1];

        // ���� ����Ʈ �ʱ�ȭ
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // ���� ���� �Է� (Ʈ���� node - 1���� �������� ����)
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v); // u�� v�� ����� ����
            tree.get(v).add(u);
        }

        int query = Integer.parseInt(br.readLine()); // �ּ� ���� ������ ���� ���� ��

        BFS(1); // ��Ʈ ���(1��)�� �������� Ʈ�� Ž���Ͽ� depth�� parent ����

        // �� �������� �ּ� ���� ���� ���
        for (int i = 0; i < query; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b) + "\n"); // �ּ� ���� ������ ���
        }

        bw.flush(); // ��� ���� ����
    }
}