import java.io.*;
import java.util.*;

public class LCA2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-17
     * ���� �ۼ��ð� : 13:14
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11438
     * ���� �̸� : LCA2
     * ���� ���̵� : �÷�Ƽ�� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    /*
     * max : �ִ� 2^max ���̱��� ������ ������ �� �ֵ��� �ϴ� ���� (log2(node) + 1)
     * depth[i] : i�� ����� ����
     * parent[i][j] : i�� ����� 2^j��° ����
     * tree : Ʈ�� ������ ���� ����Ʈ ���·� ����
     */
    static int max;
    static int[] depth;
    static int[][] parent;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    /**
     * DFS�� ���� �� ����� ���̿� 2^0��° �θ�(��, �ٷ� �� �θ�)�� ����
     * @param node ���� ���
     * @param par ���� ����� �θ� ���
     * @param dep ���� ����� ����
     */
    static void DFS(int node, int par, int dep) {
        depth[node] = dep;          // ���� ����� ���� ����
        parent[node][0] = par;      // �ٷ� �� �θ� ����

        for (int next : tree.get(node)) {
            if (next != par) {
                DFS(next, node, dep + 1); // �ڽ� ���� DFS ��� ȣ��
            }
        }
    }

    /**
     * ���� �������� ���� parent �迭 �ʱ�ȭ
     * parent[i][j] = i�� ����� 2^j��° ����
     * @param size ��� ��
     */
    static void init(int size) {
        for (int j = 1; j < max; j++) {
            for (int i = 1; i <= size; i++) {
                if (parent[i][j - 1] != -1) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    /**
     * �� ����� �ּ� ���� ����(LCA)�� ���ϴ� �Լ�
     * @param u ��� u
     * @param v ��� v
     * @return u�� v�� �ּ� ���� ����
     */
    static int LCA(int u, int v) {
        // �׻� u�� �� ���� ��尡 �ǵ��� ����
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // u�� v�� ���̱��� ����ø�
        for (int i = max - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        // �̹� ���� ������ ���
        if (u == v) return u;

        // ���� ������ �� ������ ���� ������
        for (int i = max - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        // ���������� ���� ������ �ٷ� �Ʒ��� ��ġ�� �� ����� �θ� LCA
        return parent[u][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine()); // ��� ���� �Է�

        // �ִ� log2(node) ��ŭ�� ���� ���� �ʿ�
        max = (int) Math.ceil(Math.log(node) / Math.log(2)) + 1;

        // �迭 �ʱ�ȭ (1�� ������ ���)
        depth = new int[node + 1];
        parent = new int[node + 1][max];
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // ���� ���� �Է�
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int query = Integer.parseInt(br.readLine()); // ���� �� �Է�

        DFS(1, -1, 0); // ��Ʈ ��带 �������� DFS ����

        init(node); // ���� �������� ���� parent ���̺� �ʱ�ȭ

        // ���� ó��
        for (int i = 0; i < query; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(LCA(a, b) + "\n"); // �ּ� ���� ���� ���
        }

        bw.flush(); // ��� ���� ����
    }
}