import java.io.*;
import java.util.*;

public class TopologicalSort1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-07
     * ���� �ۼ��ð� : 11:54
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2252
     * ���� �̸� : �ټ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * ���� �׷��� (����Ŭ ����)
     * indegree: �� ����� ���� ����
     * result: ���� ���� ���
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] indegree;
    static ArrayList<Integer> result = new ArrayList<>();

    /*
     * ���� ���� ����
     * - ���� ������ 0�� ������ ����
     * - ť�� �̿��� ������� Ž��
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
            result.add(now);
            for (int next : graph.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

    /*
     * ���� ���� ��� ���
     */
    static void print(ArrayList<Integer> values) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int value : values) {
            bw.write(value + " ");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());   // ��� ��
        int edge = Integer.parseInt(st.nextToken());   // ���� ��

        indegree = new int[node + 1];
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        // �׷��� ���� �� ���� ���� �ʱ�ȭ
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            indegree[v]++;
        }

        // ���� �湮 ������ �����ϵ��� ���� ����Ʈ ����
        for (int i = 1; i <= node; i++) {
            Collections.sort(graph.get(i));
        }

        topologicalSort();
        print(result);

        br.close();
    }
}