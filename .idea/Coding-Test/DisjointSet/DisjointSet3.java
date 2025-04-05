import java.io.*;
import java.util.*;

public class DisjointSet3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-05
     * ���� �ۼ��ð� : 12:32
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1043
     * ���� �̸� : ���� ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * set : �� ����� ��ǥ ��带 �����ϴ� �迭 (���Ͽ� ���ε��)
     * know : ������ �ƴ� ������� ��ȣ
     * party : �� ��Ƽ�� �����ϴ� ������� ��� (2���� ����Ʈ)
     * count : �������� �ص� �Ǵ� ��Ƽ�� ����
     */
    static int[] set;
    static int[] know;
    static ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    static int count = 0;

    /*
     * makeSet: ���Ͽ� ���ε带 ���� �ʱ� ����
     * �� ����� �ڽ��� ��ǥ�ڷ� �������� ����
     */
    static int[] makeSet(int size) {
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    /*
     * findSet: �ش� ������ ��Ʈ(��ǥ��)�� ã��
     * ��� ����(Path Compression) ����� ����Ͽ� Ʈ�� ���̸� ����
     */
    static int findSet(int x) {
        if (set[x] != x) {
            set[x] = findSet(set[x]);  // ��������� ��Ʈ�� ã�� �ٷ� ����
        }
        return set[x];
    }

    /*
     * unionSet: �� ���Ұ� ���� ������ �ϳ��� ��ħ
     * ��ǥ�ڰ� �ٸ��� �ϳ��� ����
     */
    static void unionSet(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (x != y) {
            set[y] = x;
        }
    }

    /*
     * checkSameSet: �� ����� ���� ����(��ǥ�� ����)���� Ȯ��
     */
    static boolean checkSameSet(int x, int y) {
        return findSet(x) == findSet(y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int people = Integer.parseInt(st.nextToken());       // ��ü ��� ��
        int partiesCount = Integer.parseInt(st.nextToken()); // ��Ƽ ����

        set = makeSet(people + 1); // 1������ people������, 0�� ��� �� ��

        st = new StringTokenizer(br.readLine());
        int knowCount = Integer.parseInt(st.nextToken()); // ������ �ƴ� ��� ��

        know = new int[knowCount];
        for (int i = 0; i < knowCount; i++) {
            know[i] = Integer.parseInt(st.nextToken());
        }

        // �� ��Ƽ�� ������ ��� ����
        for (int i = 0; i < partiesCount; i++) {
            st = new StringTokenizer(br.readLine());
            int memberCount = Integer.parseInt(st.nextToken());

            ArrayList<Integer> members = new ArrayList<>();
            for (int j = 0; j < memberCount; j++) {
                members.add(Integer.parseInt(st.nextToken()));
            }
            party.add(members);
        }

        // ��Ƽ�� ���� ������� ���� �������� ���� (���Ͽ�)
        for (ArrayList<Integer> members : party) {
            for (int j = 1; j < members.size(); j++) {
                unionSet(members.get(0), members.get(j)); // ù ����� �������� ��� ����
            }
        }

        // �� ��Ƽ���� ������ �ƴ� ����� ����Ǿ� �ִ��� Ȯ��
        for (ArrayList<Integer> members : party) {
            boolean possible = true;
            int rep = members.get(0); // �ش� ��Ƽ�� ��ǥ��

            for (int person : know) {
                if (checkSameSet(rep, person)) {
                    // �� ��Ƽ�� ������ �ƴ� ����� ����� ����� ������ ������ �Ұ�
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++; // �������� ������ ��Ƽ
            }
        }

        System.out.println(count); // ������ ��Ƽ �� ���
    }
}