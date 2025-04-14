import java.io.*;
import java.util.*;

public class Tree1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-14
     * ���� �ۼ��ð� : 13:40
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11725
     * ���� �̸� : Ʈ���� �θ� ã��
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    /*
     * tree: Ʈ�� ������ �����ϴ� ���� ����Ʈ
     * visited: ��� �湮 ���� Ȯ�� �迭
     * result: �� ����� �θ� ��带 �����ϴ� �迭
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[] result;

    /*
     * DFS(���� �켱 Ž��)
     * ����: �� ����� �θ� ��带 ã�� result �迭�� ����
     * ���: �ڽ� ��带 �湮�� �� result[next] = node�� �θ� ���� (next: �ڽ�, node�� �θ�)
     * ��, ���� ��带 �θ�� �ΰ� �ڽ� ��带 Ž���ϸ� �����
     */
    static void DFS(int node) {
        visited[node] = true;
        for (int next : tree.get(node)) {
            if (!visited[next]) {
                result[next] = node;
                DFS(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // ��� ���� �Է�
        int node = Integer.parseInt(br.readLine());

        visited = new boolean[node + 1];
        result = new int[node + 1];

        // Ʈ�� �ʱ�ȭ
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // Ʈ�� ���� ���� �Է� (������ �׷���)
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree.get(s).add(e);
            tree.get(e).add(s);
        }

        // DFS�� ���� �θ� ��� ��� (��Ʈ�� 1�� ���)
        DFS(1);

        // ��Ʈ(1��)�� ������ �� ����� �θ� ��� ���
        for (int i = 2; i <= node; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }
}