import java.io.*;
import java.util.*;

public class Graph2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-04
     * ���� �ۼ��ð� : 13:25
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1325
     * ���� �̸� : ȿ������ ��ŷ
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;

    /*
     * BFS: ���� ���κ��� ������ �� �ִ� ��� ��带 �湮�ϰ�,
     * �ش� ����� result ���� ������Ŵ
    */
    static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    result[next]++;  // next ���� node�κ��� ���� �����ϹǷ� result ����
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken()); // ���(��ǻ��) ��
        int edge = Integer.parseInt(st.nextToken()); // ����(�ŷ� ����) ��

        result = new int[node + 1];

        // �׷��� �ʱ�ȭ
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        // �׷��� �Է� (u -> v: u�� v�� ��ŷ ����)
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v); // ���� �׷��� ����
        }

        // �� ��忡�� BFS ����
        for (int i = 1; i <= node; i++) {
            visited = new boolean[node + 1]; // �湮 �迭 �Ź� �ʱ�ȭ
            BFS(i); // i�� ��忡�� BFS ����
        }

        // ���� ���� ��ŷ����(���޵�) Ƚ�� ã��
        int max = result[1];
        for (int i = 2; i <= node; i++) {
            max = Math.max(max, result[i]);
        }

        // �ִ� ��ŷ���� Ƚ���� ���� ���� ���
        for (int i = 1; i <= node; i++) {
            if (result[i] == max) {
                bw.append(i + " ");
            }
        }
        bw.flush(); // ��� ���� ����
    }
}