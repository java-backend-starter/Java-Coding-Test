import java.io.*;
import java.util.*;

public class DynamicProgramming2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-24
     * ���� �ۼ��ð� : 13:51
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 14501
     * ���� �̸� : ���
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     *
     * ���� ����:
     * - N�� ���� ��� ������ ���� �� ���� ��, ����� ������ �����Ͽ� ���� �� �ִ� �ִ� ������ ���ϴ� ����
     * - �� ����� �Ⱓ(T)�� ����(P)�� �־�����, ��� ������ ������ ��㸸 ���� ����
     *
     * ����:
     *  - N = 7���̰� �� ������ ��� �ð��� ������ �־��� ��
     *    ������ ����� �����Ͽ� �ִ� ������ ���
     *
     * �ٽ� ���̵��:
     * - DP�� ����Ͽ� i�Ϻ��� �������� �� ���� �� �ִ� �ִ� ������ dp[i]�� ����
     *
     * ��ȭ��:
     * - (i + time[i] > N + 1) : ����� ������ �� ���� ��� �� dp[i] = dp[i + 1]
     * - (i + time[i] �� N + 1) : ����� ������ �� �ִ� ��� �� dp[i] = max(dp[i + 1], price[i] + dp[i + time[i]])
     */

    // dp[i] : i�Ϻ��� �������� ���� �ִ� ����
    static int[] dp;

    // �� ����� �ҿ� �ð��� ����
    static int[] time;
    static int[] price;

    // �Է� �� �迭 �ʱ�ȭ
    static void init(int size, BufferedReader br) throws IOException {
        // dp�� N + 2���� �ʿ� (N+1�� �����, �׻� ���� 0)
        dp = new int[size + 2];
        dp[size + 1] = 0; // ����Ͽ��� ��� �Ұ�

        time = new int[size + 1];
        price = new int[size + 1];

        StringTokenizer st;
        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());  // ��� �ҿ� �ð� T
            price[i] = Integer.parseInt(st.nextToken()); // ��� ���� P
        }
    }

    // DP�� �̿��� �ִ� ���� ���
    static void mamoize(int size) {
        for (int i = size; i > 0; i--) {
            // i�Ϻ��� �����ϴ� ����� ����ϱ��� ������ ������ ��� �Ұ�
            if ((i + time[i]) > size + 1) {
                dp[i] = dp[i + 1]; // ����� ���� �ʰ� ���� �� ���� ���
            } else {
                // ������� �ʴ� ���� ����ϴ� ��� �� �ִ밪 ����
                dp[i] = Math.max(dp[i + 1], price[i] + dp[i + time[i]]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine()); // ��ü �� �� �Է�

        init(size, br);     // �迭 �� �Է� �ʱ�ȭ
        mamoize(size);      // DP�� �ִ� ���� ���

        System.out.println(dp[1]); // ù° ������ �������� �� �ִ� ���� ���
    }
}