import java.io.*;
import java.util.*;

public class DynamicProgramming1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-21
     * ���� �ۼ��ð� : 14:03
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1463
     * ���� �̸� : 1�� �����
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    // ���� ���α׷����� ���� �迭
    static int [] dp;

    // �޸������̼�(Memoization)�� �̿��� �ּ� ���� Ƚ�� ��� �Լ�
    static void mamoize(int[] dp) {
        /*
         * dp[1] = 0
         * ���� 1�� �̹� 1�̹Ƿ� ������ �ʿ� ����.
         */
        dp[1] = 0;

        /*
         * 2���� n������ ��� ������ ����,
         * �ش� ���ڸ� 1�� ����� ���� �ּ� ���� Ƚ���� ����
         *
         * ������ ����:
         * 1. x �� x - 1
         * 2. x �� x / 2 (��, x�� 2�� ������ ������ ����)
         * 3. x �� x / 3 (��, x�� 3���� ������ ������ ����)
         */
        for (int i = 2; i < dp.length; i++) {
            // �⺻������ -1 ������ ������ ����� ������ �ʱ�ȭ
            dp[i] = dp[i - 1] + 1;

            // 2�� ������ �������� ���: i / 2���� ���� ���� ��� ���
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // 3���� ������ �������� ���: i / 3���� ���� ���� ��� ���
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        dp = new int[size+1];

        mamoize(dp);

        System.out.println(dp[size]);
    }

}