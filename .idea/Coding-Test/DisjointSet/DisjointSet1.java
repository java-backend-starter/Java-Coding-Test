import java.io.*;
import java.util.*;

public class DisjointSet1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-05
     * ���� �ۼ��ð� : 11:13
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1717
     * ���� �̸� : ������ ǥ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * ��ȣ��Ÿ�� ����(Disjoint Set) ����
     * �ֿ� ����: makeSet, findSet (with path compression), union
     */
    static int[] makeSet(int size) {
        // �� ���Ұ� �ڱ� �ڽ��� ��ǥ�� ������ ���� �ʱ�ȭ
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    static int findSet(int[] set, int x) {
        // ��� ������ �����Ͽ� ��ǥ ��带 ã��
        if (set[x] != x) {
            set[x] = findSet(set, set[x]);
        }
        return set[x];
    }

    static void union(int[] set, int x, int y) {
        // x�� y�� ���� ������ ��ǥ ��带 ã��, ���� �ٸ��� ��ħ
        int rootX = findSet(set, x);
        int rootY = findSet(set, y);
        if (rootX != rootY) {
            set[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // ���� ������ ���� �� �Է�
        int size = Integer.parseInt(st.nextToken());
        int queries = Integer.parseInt(st.nextToken());

        // 1������ size������ ����� �� �ֵ��� size + 1 ũ���� �迭 ����
        int[] set = makeSet(size + 1);

        for (int i = 0; i < queries; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (query == 0) {
                // ������ ����
                union(set, x, y);
            } else if (query == 1) {
                // ���� ���տ� ���ϴ��� ���� ���
                bw.write(findSet(set, x) == findSet(set, y) ? "YES\n" : "NO\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}