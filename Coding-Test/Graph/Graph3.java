import java.io.*;
import java.util.*;

public class Graph3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-04
     * ���� �ۼ��ð� : 14:33
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1707
     * ���� �̸� : �̺� �׷���
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    // ���� ����Ʈ�� �׷��� ����
    static ArrayList<ArrayList<Integer>> graph;
    // �湮 ���� ���� �迭
    static boolean[] visited;
    // �� ����� ���� (0 �Ǵ� 1)
    static int[] check;
    // �̺� �׷��� ���� �Ǵ� ����
    static boolean isEven;

    // DFS�� ���� �׷��� Ž�� �� �̺� �׷������� �˻�
    static void DFS(int node) {
        visited[node] = true; // ���� ��� �湮 ó��

        // ���� ���� ��ȸ
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                // �湮���� ���� ���, ���� ���� �ٸ� �� ����
                visited[next] = true;
                check[next] = (check[node] + 1) % 2;
                DFS(next); // ��������� ���� ��� Ž��
            } else if (check[next] == check[node]) {
                // �̹� �湮�߰� ���� ���ٸ� �̺� �׷����� �ƴ�
                isEven = false;
            }
        }
    }

    // ���� �޼���
    public static void main(String[] args) throws IOException {
        // �Է��� ���� BufferedReader, ��¿� BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine()); // �׽�Ʈ���̽� ���� �Է�
        StringTokenizer st;

        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken()); // ��� ��
            int edge = Integer.parseInt(st.nextToken()); // ���� ��

            // �ʱ�ȭ
            graph = new ArrayList<>();
            visited = new boolean[node + 1];
            check = new int[node + 1];

            // �׷��� ����Ʈ �ʱ�ȭ
            for (int j = 0; j <= node; j++) {
                graph.add(new ArrayList<>());
            }

            // ���� ���� �Է� �� ����� ����
            for (int j = 0; j < edge; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // �̺� �׷��� ���� �ʱ�ȭ
            isEven = true;

            // �׷����� ��� ������ �˻�
            for (int j = 1; j <= node; j++) {
                if (!visited[j]) { // ���� �湮���� �ʾҴٸ� Ž�� ����
                    check[j] = 0;  // ���� ��� �� ����
                    DFS(j);
                }

                if (!isEven) break; // �̹� �̺� �׷��� �ƴ��� �Ǻ��Ǹ� ����
            }

            // ��� ���
            bw.append((isEven ? "YES" : "NO") + "\n");
        }
        bw.flush(); // ��� ���� ����
    }
}