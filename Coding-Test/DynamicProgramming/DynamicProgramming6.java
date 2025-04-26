import java.io.*;
import java.util.*;

public class DynamicProgramming6 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-26
     * ���� �ۼ��ð� : 15:12
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 13398
     * ���� �̸� : ���� ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     *
     */
    /*
     * ���� ����:
     * ���� ������ �־��� ��, ���ӵ� �� ���� ���� �����Ͽ� ���� �� �ִ� �ִ� ���� ���Ѵ�.
     * ��, �������� "�� ���� ���Ҹ� ������ �� ����".
     *
     * ��:
     * �Է�: 10 -4 3 1 5 6 -35 12 21 -1
     * ���: 54 (6�� ������ �� 12 + 21 + (-1) = 32, �Ǵ� -35�� ���� ��)
     *
     * �ذ� ����:
     * - �Ϲ����� ���� ��(DP)ó�� �����ϵ�,
     * - �� �ε����� �������� ���� ����Ͽ� ���� ������ �ִ� ���� ���� ���� ����
     *
     * ��ȭ��:
     * - leftSum[i]  = max(values[i], values[i] + leftSum[i-1])
     *   �� 0���� i������ �������� i�� ������ �ϴ� �ִ� ���� ��
     * - rightSum[i] = max(values[i], values[i] + rightSum[i+1])
     *   �� i���� ������ �������� i�� �������� �ϴ� �ִ� ���� ��
     *
     * - � �ϳ��� �ε��� i�� �������� ��,
     *   �ִ� ���� leftSum[i-1] + rightSum[i+1]
     *
     * ���� ������:
     * 1. �ƹ��͵� �������� �ʰ� ���� �ִ� ��
     * 2. �ϳ��� �ε����� �����ؼ� ��� �ִ� ��
     * �� �� ���� �� ū ��
     */

    // �Էµ� ����
    static int[] values;

    // �� ��ġ���� ���ʿ��������� �ִ� ���� ��
    static int[] leftSum;

    // �� ��ġ���� �����ʿ��������� �ִ� ���� ��
    static int[] rightSum;

    // ����/������ ������ �迭�� ä���, �������� �ʾ��� ���� �ִ� ���� ���� ���
    static int mamoize(int size) {
        leftSum = new int[size];
        rightSum = new int[size];

        // ���۰� �ʱ�ȭ
        leftSum[0] = values[0];
        rightSum[size - 1] = values[size - 1];

        // �������� �ʾ��� �� �ִ� ���� �� (�ʱ갪)
        int result = leftSum[0];

        // ���ʿ��� ���������� �ִ� ���� �� ��� (Kadane's �˰���)
        for (int i = 1; i < size; i++) {
            leftSum[i] = Math.max(values[i], values[i] + leftSum[i - 1]);
            result = Math.max(result, leftSum[i]); // �߰��߰� �ִ� ����
        }

        // �����ʿ��� �������� �ִ� ���� �� ���
        for (int i = size - 2; i >= 0; i--) {
            rightSum[i] = Math.max(values[i], rightSum[i + 1] + values[i]);
        }

        return result;
    }

    // �ϳ��� ���Ҹ� �������� ���� �ִ� ���� ���
    static int max(int size, int result) {
        for (int i = 1; i < size - 1; i++) {
            // i��° ���Ҹ� ������ ���: i-1������ �ִ� ���� �� + i+1������ �ִ� ���� ��
            int temp = leftSum[i - 1] + rightSum[i + 1];
            result = Math.max(result, temp); // ���� �ִ񰪰� ���Ͽ� ����
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        // ���� �Է��� ���� BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // ������ ����
        int size = Integer.parseInt(br.readLine());
        values = new int[size];

        // ���� �Է�
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 1. ���Ҹ� �������� ���� ��� �ִ� �� ���
        int result = mamoize(size);

        // 2. �ϳ��� ���Ҹ� �������� �� ������ �ִ� �� ��� �� �ִ� ��
        System.out.println(max(size, result));
    }
}
