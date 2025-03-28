import java.io.*;
import java.util.*;

public class Euclidean2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-03-29
     * ���� �ۼ��ð� : 00:48
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1850
     * ���� �̸� : �ִ�����
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * ��Ŭ���� ȣ������ ���� �ִ�����(GCD) �˰���
     * ����:
     * 1. a�� b, b�� a % b�� ����
     * 2. b�� 0�� �� ������ 1�� �ݺ�
     * 3. 2�� ������ a�� ����� ���� �ִ�����
     *
     * ���� ���:
     * - �ݺ���(while)�� �̿��ϴ� ���
     * - ����Լ��� �̿��ϴ� ��� (�ּ� ó���� �κ� ����)
     */
    static long gcd(long a, long b) {
        long temp;
        while (b != 0) {  // b�� 0�� �ƴ� ������ �ݺ�
            temp = b;     // ���� b ���� �ӽ� ���� temp�� ����
            b = a % b;    // a�� b�� ���� �������� b�� ����
            a = temp;     // ���� b ���� a�� ���� (swap)
        }
        return a;  // b�� 0�� �Ǹ� a�� �ִ�����(GCD)

        // �Ʒ��� ���� ��� ������ε� ���� ����
        // return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * �Է�: �� ���� ���� a, b (�ִ������� ���� �� ��)
         * - ���� �Է�: 3 2
         */
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());  // ù ��° ��
        long b = Long.parseLong(st.nextToken());  // �� ��° ��
        long gcd = gcd(a, b);  // �ִ����� ���

        /*
         * ���: �ִ�������ŭ '1' ���
         * - ���� �Է�: 3 2
         * - �ִ�����(GCD) = 1
         * - ���: "1"
         *
         * - ���� �Է�: 6 3
         * - �ִ�����(GCD) = 3
         * - ���: "111"
         */
        while (gcd > 0) {
            bw.append("1");  // GCD��ŭ '1'�� ���
            gcd--;
        }
        bw.flush();  // ��� ���� ����
    }
}