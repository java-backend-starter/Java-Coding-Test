import java.io.*;
import java.util.*;

public class SegmentTree3 {
    /*
     * �ۼ��Ͻ� : 2025-04-16
     * �ۼ��ð� : 16:49
     * �ۼ��� : �强ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2042
     * ���� �̸� : ���� �� ���ϱ�
     * ���� ���̵� : ��� I
     *
     * �ۼ� ���� :
     * ���׸�Ʈ Ʈ�� ���� ���� ���� Ǯ��
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node   : �Է¹��� ������ ����
         * change : �� ���� Ƚ��
         * sum    : ������ ���ϱ� Ƚ��
         * count  : �� ���� ����
         */
        int node = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());
        int count = change + sum;

        /*
         * values : �Էµ� ���� �迭 (1-indexed)
         */
        long[] values = new long[node + 1];
        for (int i = 1; i <= node; i++) {
            values[i] = Long.parseLong(br.readLine());
        }

        /*
         * ���׸�Ʈ Ʈ�� ����
         */
        SegmentTree tree = new SegmentTree(values, node);

        /*
         * ���� ó��
         * 1. query == 1 : Ư�� �ε����� ���� ����
         * 2. query == 2 : Ư�� ������ ���� ����
         */
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if (query == 1) {
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                tree.update(1, 1, node, index, value);
            } else if (query == 2) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                bw.write(tree.prefixSum(1, 1, node, left, right) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class SegmentTree {
    /*
     * values : ���� �迭
     * tree   : ���׸�Ʈ Ʈ�� �迭 (������ ����)
     * size   : ���� �迭�� ũ��
     */
    private long[] values;
    private long[] tree;
    private int size;

    /*
     * ������ ����:
     * 1. Ʈ�� ���̸� ����ϰ� tree �迭�� �ʱ�ȭ��
     * 2. ���� �迭 ����
     * 3. ���׸�Ʈ Ʈ�� �ʱ�ȭ ����
     */
    SegmentTree(long[] values, int size) {
        this.size = size;
        int height = (int) Math.ceil(Math.log(size) / Math.log(2));
        int treeSize = (int) Math.pow(2, height + 1);
        tree = new long[treeSize];
        this.values = values;
        init(1, 1, size);
    }

    /*
     * ���׸�Ʈ Ʈ�� �ʱ�ȭ
     * ���� ���: ���� �� ����
     * ���� ���: ��/�� �ڽ��� �� ����
     */
    private long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = values[start];
        } else {
            int mid = (start + end) / 2;
            return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
        }
    }

    /*
     * �� ����
     * index ��ġ�� ���� value�� �����ϰ� ���õ� ������ ����
     */
    public void update(int node, int start, int end, int index, long value) {
        // ���� ���� ��� ����
        if (index < start || index > end) return;

        // ���� ���: ���� �� ����
        if (start == end) {
            values[index] = value;
            tree[node] = value;
            return;
        }

        // ���� ���: ���� Ʈ�� ��������� ���� �� ������ ����
        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, value);
        update(node * 2 + 1, mid + 1, end, index, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    /*
     * ������ ���
     * ���� [left, right]�� ���� ���� ��ȯ
     */
    public long prefixSum(int node, int start, int end, int left, int right) {
        // ������ ���� ��ġ�� ������ 0 ��ȯ
        if (end < left || start > right) return 0;

        // ������ ���ԵǴ� �����̸� �ٷ� ��ȯ
        if (left <= start && end <= right) return tree[node];

        // �Ϻθ� ���Ե� ��� ��/�� �ڽ� ��� ��� Ž���Ͽ� �ջ�
        int mid = (start + end) / 2;
        return prefixSum(node * 2, start, mid, left, right)
                + prefixSum(node * 2 + 1, mid + 1, end, left, right);
    }
}