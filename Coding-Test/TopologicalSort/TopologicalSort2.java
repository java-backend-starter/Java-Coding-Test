import java.io.*;
import java.util.*;

public class TopologicalSort2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-07
     * ���� �ۼ��ð� : 12:18
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2252
     * ���� �̸� : ���� ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] indegree;     // �� �ǹ��� ���� ����
    static int[] buildCost;    // �� �ǹ��� �Ǽ� ���
    static int[] result;       // ���� �Ǽ� �ð� (���� �ǹ� ����)

    /*
     * ���� ���� ����
     * - ���� ������ 0�� ������ ����
     * - ���� ��� �湮 �� ���� ���� ���� �� ���� �ð� ����
     */
    static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[now] + buildCost[now]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

    /*
     * ��� ���
     * - ���� �ð�(result) + �ڽ��� �Ǽ� ���(buildCost)�� ���
     */
    static void print(int[] values, int[] costs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < values.length; i++) {
            bw.write((values[i] + costs[i]) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int buildings = Integer.parseInt(br.readLine());
        indegree = new int[buildings + 1];
        buildCost = new int[buildings + 1];
        result = new int[buildings + 1];

        // �׷��� �ʱ�ȭ
        for (int i = 0; i <= buildings; i++) {
            graph.add(new ArrayList<>());
        }

        // �Է� ó��
        // ù ���� �Ǽ� �ð�, ���� ������ ���� �ǹ� ��ȣ (-1�� ����)
        StringTokenizer st;
        for (int i = 1; i <= buildings; i++) {
            st = new StringTokenizer(br.readLine());
            buildCost[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int u = Integer.parseInt(st.nextToken());
                if (u == -1) break;
                graph.get(u).add(i);
                indegree[i]++;
            }
        }

        topologicalSort();
        print(result, buildCost);
        br.close();
    }
}