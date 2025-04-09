import java.io.*;
import java.util.*;

public class ExtendedEuclidean1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-03
     * ���� �ۼ��ð� : 13:11
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 21568
     * ���� �̸� : Ax+By=C
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * Ȯ�� ��Ŭ���� ȣ����(Extended Euclidean Algorithm) ����
     *
     * Ȯ�� ��Ŭ���� ȣ������ ax + by = gcd(a, b)�� �����ϴ� x, y�� ã�� �˰����̴�.
     * �Ϲ����� ��Ŭ���� ȣ������ Ȯ���Ͽ� ���� �׵��(B?zout's identity)�� ������ ��.
     */
    static long[] extendedEuclidean(long a, long b) {
        // b�� 0�̸�, gcd(a, 0) = a�̰�, x = 1, y = 0�� �����Ѵ�.
        if (b == 0) {
            return new long[]{a, 1, 0}; // {gcd, x, y}
        }

        // ��������� gcd(b, a % b)�� ���ϰ� x, y ���� ����
        long[] next = extendedEuclidean(b, a % b);
        long gcd = next[0]; // gcd(a, b)
        long x = next[2];   // ���� �ܰ��� y ���� ���ο� x ��
        long y = next[1] - (a / b) * next[2]; // ���� �׵���� ����Ͽ� y �� ����

        return new long[]{gcd, x, y}; // {gcd, x, y} ��ȯ
    }

    /*
     * ��Ŭ���� ȣ����(Euclidean Algorithm) ����
     *
     * �� �� a, b�� �ִ�����(GCD)�� ���ϴ� �Լ�
     */
    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        // ���� �Է��� ���� BufferedReader ���
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // �Է°� �б� (a, b, c)
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        // a�� b�� �ִ�����(GCD) ���
        long gcd = gcd(a, b);

        // c�� gcd(a, b)�� ����� �ƴϸ� ���� �ذ� �������� ����
        if (c % gcd != 0) {
            System.out.println("-1"); // ���� �ذ� ������ �ǹ�
        } else {
            // c�� gcd�� ���� �� (Ȯ�� ��Ŭ���� �˰����� ������ ���)
            long divisor = c / gcd;

            // Ȯ�� ��Ŭ���� �˰��� �����Ͽ� x, y ���ϱ�
            long [] result = extendedEuclidean(a, b);
            long x = result[1] * divisor; // x ���� c/gcd �踸ŭ ����
            long y = result[2] * divisor; // y ���� c/gcd �踸ŭ ����

            // ��� ��� (Ax + By = C�� ������)
            System.out.println(x + " " + y);
        }

        // ���ҽ� ����
        br.close();
    }
}