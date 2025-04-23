import java.io.*;
import java.util.*;

public class Permutation2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-23
     * ���� �ۼ��ð� : 15:18
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1947
     * ���� �̸� : ���� ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    // derangement(���� ����, �ڱ� �ڸ��� ���� �ʴ� ����) ���� ������ �迭
    static long[] permutation;

    // derangement ���� ���ϱ� ���� �ʱ�ȭ �޼���
    static void init(int size) {
        permutation = new long[size + 1];  // �ε��� 1���� ����ϱ� ���� size + 1 ũ��� �迭 ����
        permutation[1] = 0;                // D(1) = 0: �ڱ� �ڽŹۿ� ���� ������ derangement �Ұ���
        permutation[2] = 1;                // D(2) = 1: 2���� ���Ұ� ���� �ڸ��� �ٲ� �� �ִ� ��� 1����

        // ��ȭ�� D(n) = (n - 1) * (D(n - 1) + D(n - 2))�� �̿��� DP ������� ���
        for (int i = 3; i <= size; i++) {
            permutation[i] = (i - 1) * (permutation[i - 1] + permutation[i - 2]) % 1000000000;
            // ������� Ŀ�� �� �����Ƿ� 1,000,000,000(10^9)���� ���� �������� ���� (�����÷� ����)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // ����� �Է��� �ޱ� ���� Scanner ��ü ����

        int size = sc.nextInt();  // ����ڷκ��� �Է� ũ��(n)�� �Է¹���
        init(size);               // derangement �迭 �ʱ�ȭ �� ��� ����

        System.out.println(permutation[size]);  // n�� ���� derangement �� ���
    }

}