import java.io.*;
import java.util.*;

public class Tree2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-14
     * ���� �ۼ��ð� : 14:04
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1068
     * ���� �̸� : Ʈ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    /*
     * tree: Ʈ�� ������ �����ϴ� ���� ����Ʈ
     * visited: ��� �湮 ���� Ȯ�� �迭
     * node: ��ü ��� ��
     * root: ��Ʈ ��� ��ȣ
     * deleted: ������ ��� ��ȣ
     * answer: ���� ��� ���� ���
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;

    static int node;
    static int root;
    static int deleted;
    static int answer = 0;

    /*
     * DFS(���� �켱 Ž��)
     * ����: ������ ��带 ������ Ʈ������ ���� ��� ������ ���
     * ���: �ڽ� ��带 Ž���ϸ�, �ڽ��� ���� ���(=child == 0)�� ���� ���� �����Ͽ� answer ����
     * ����: ������ ���� Ž������ ���� (next != deleted)
     */
    static void DFS(int node) {
        visited[node] = true;
        int child = 0;

        for (int next : tree.get(node)) {
            if (!visited[next] && next != deleted) {
                child++;
                DFS(next);
            }
        }

        // �ڽ� ��尡 ������ ���� ���� �Ǵ�
        if (child == 0) {
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // ��� ���� �Է�
        node = Integer.parseInt(br.readLine());
        visited = new boolean[node];

        // Ʈ�� �ʱ�ȭ (��� ����ŭ ����Ʈ ����)
        for (int i = 0; i < node; i++) {
            tree.add(new ArrayList<>());
        }

        // �� ����� �θ� ���� �Է�
        // -1�̸� ��Ʈ ���
        // ������ �׷���ó�� ����� ���� (�ڽ�-�θ� ���� ��� ����)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < node; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(i).add(parent);
                tree.get(parent).add(i);
            }
        }

        // ������ ��� ��ȣ �Է�
        deleted = Integer.parseInt(br.readLine());

        // ������ ��尡 ��Ʈ�� ��� ��ü Ʈ���� �����ǹǷ� ���� ���� 0��
        if (deleted == root) {
            System.out.println(0);
        } else {
            // DFS ���� �� ���� ��� ���� ���
            DFS(root);
            System.out.println(answer);
        }
    }
}