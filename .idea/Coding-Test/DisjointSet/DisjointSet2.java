import java.io.*;
import java.util.*;

public class DisjointSet2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-05
     * ���� �ۼ��ð� : 11:40
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1976
     * ���� �̸� : ���� ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * ��ȣ��Ÿ�� ����(Disjoint Set Union - DSU) �ڷᱸ���� �̿��� Ǯ��
     * �ٽ� ����: makeSet (�ʱ�ȭ), findSet (�θ� ã��, ��� ����), unionSet (��ġ��)
     */
    static int[] makeSet(int size) {
        // �� ���Ҹ� �ڱ� �ڽ����� �ʱ�ȭ (�ڱ� �ڽ��� ��ǥ��)
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    static int findSet(int[] set, int x) {
        // ��� ������ �̿��� ��Ʈ(��ǥ��)�� ã�� �Լ�
        if (set[x] != x) {
            set[x] = findSet(set, set[x]); // �θ� ��Ʈ�� ����
        }
        return set[x];
    }

    static void unionSet(int[] set, int x, int y) {
        // �� ������ ������ ��ġ�� ���� (��Ʈ�� ��������)
        x = findSet(set, x);
        y = findSet(set, y);
        if (x != y) {
            set[y] = x; // y�� ��Ʈ�� x�� ����
        }
    }

    /*
     * ���� ��ȹ(route)�� �ִ� ��� ���õ���
     * ���� ���տ� ���ϴ���(=���� ��Ʈ�� ��������) Ȯ���ϴ� �Լ�
     */
    static boolean check(int[] set, int[] route) {
        int root = findSet(set, route[1]); // ù ������ ��Ʈ�� ��������
        for (int i = 2; i < route.length; i++) {
            if (root != findSet(set, route[i])) {
                // �ϳ��� �ٸ� ��Ʈ�� �ִٸ� ���� �Ұ�
                return false;
            }
        }
        return true; // ��� ���� ���տ� ����
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cities = Integer.parseInt(br.readLine()); // ���� �� (N)
        int routes = Integer.parseInt(br.readLine()); // ���� ��ο� �ִ� ���� �� (M)

        // ��ȣ��Ÿ�� ���� �ʱ�ȭ (���� ��ȣ 1~N�̹Ƿ� N+1 ũ��)
        int[] set = makeSet(cities + 1);

        // ���� ���� ���� �Է� (���� ���)
        int[][] city = new int[cities + 1][cities + 1];
        for (int i = 1; i <= cities; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= cities; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    // ����� ���ö�� ���� �������� ����
                    unionSet(set, i, j);
                }
            }
        }

        // ���� ��� �Է�
        int[] route = new int[routes + 1]; // 1-based �ε��� ���
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= routes; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // ��� ���ð� ���� ����(�����)�� ���� �ִٸ� "YES", �ƴϸ� "NO"
        System.out.println(check(set, route) ? "YES" : "NO");
    }

}