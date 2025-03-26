import java.util.*;

public class Totient1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-03-25
     * ���� �ۼ��ð� : 14:30
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11689
     * ���� �̸� : GCD(n, k) = 1
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * ���Ϸ��� �� �˰��� (Euler's Totient Function)
     * ���Ϸ��� �� �Լ�(��(n))�� 1���� n������ ���� �� n�� ���μ��� ������ ������ ���ϴ� �Լ��̴�.
     *
     * ���Ϸ��� �� �Լ��� ����:
     * 1. n�� ���μ��� ã�´�.
     * 2. �� ���μ� p�� ���� ��(n) = n * (1 - 1/p) �� �����Ѵ�.
     * 3. ���� n�� �Ҽ����, �� �� �� ó���Ѵ�.
     *
     * �ð����⵵:
     * - �� ������ O(��n)���� �����ϸ�, ���μ����ظ� ������� �Ѵ�.
     *
     * �ٸ� ���� ���
     * 1. �⺻���� ���Ǹ� �̿��� O(n) ���Ʈ���� ���
     * 2. �����佺�׳׽��� ü�� �̿��� O(n log log n) ��ó�� ���
     */
    static long Totient(long value) {
        long result = value; // ����� �ʱ�ȭ (�ʱⰪ: value)

        // 2���� sqrt(value)������ ��� ��(i)�� ���� �˻�
        for (long i = 2; i <= Math.sqrt(value); i++) {
            // value�� i�� ������ �������ٸ� (��, i�� value�� ���μ����)
            if (value % i == 0) {
                result -= result / i; // ���Ϸ��� �� ���� ����: result *= (1 - 1/i)

                // value���� �ش� ���μ� i�� ��� ����
                while (value % i == 0) {
                    value /= i;
                }
            }
        }

        // ������ ���� ���� 1���� ũ�ٸ�, �̴� �Ҽ��̹Ƿ� �߰� ó��
        if (value > 1) {
            result -= result / value;
        }

        return result; // ���Ϸ��� �� �Լ� �� ��ȯ
    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        long value = sc.nextLong();

        System.out.println(Totient(value));
    }
}