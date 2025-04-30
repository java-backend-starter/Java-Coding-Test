import java.io.*;
import java.util.*;

public class DynamicProgramming8 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-30
     * ���� �ۼ��ð� : 17:58
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1915
     * ���� �̸� : ���� ū ���簢��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * ���� ����:
     * �־��� 0�� 1�� �̷���� ���� ��Ŀ���, ���� ū ���簢���� ã�� �� ũ�⸦ ����ϴ� �����Դϴ�.
     * �� ������ ���� ��ȹ��(DP)�� �̿��� �ذ��� �� �ֽ��ϴ�.
     *
     * �Է�:
     * ù ��° �ٿ� ����� ũ���� �� ���� N, M�� �־�����,
     * �� ��° �ٺ��� N���� �ٿ� ���� �� ����� �� ���� �־����ϴ�. (0 �Ǵ� 1)
     *
     * ���:
     * ���� ū ���簢���� ���̸� ����մϴ�.
     *
     * ����:
     * �Է�:
     * 3 4
     * 1010
     * 1111
     * 1111
     * ���:
     * 4
     *
     * ����:
     * �־��� ��Ŀ��� ���� ū ���簢���� 2x2 ũ���̸�, �� ���̴� 4�Դϴ�.
     *
     */

    // DP ���̺��� ������ 2D �迭 ����
    static int[][] dp;

    /*
     * �־��� ��Ŀ��� ���� ū ���簢���� ũ�⸦ ���ϴ� �Լ�
     * dp[i][j]�� (i, j) ������ ������ �Ʒ� �𼭸��� ���� ���簢���� ũ�⸦ ����
     *
     * row : ����� �� ��
     * col : ����� �� ��
     *
     * DP ��� ���:
     * - dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     *   (��, ����, �밢�� ���� �� ���� �� �� �ּҰ��� 1�� ����)
     * - ���� dp[i][j] == 1�̶�� �ش� ��ġ�� ������ �ϴ� ���簢���� ���� �� ����
     * - ���� ū ���簢���� ũ�⸦ ���� ��, �� ���̸� ����
     */
    static int memoize(int row, int col) {
        int max = 0;
        // ����� ���������� Ž���ϸ鼭 dp ���̺��� ������Ʈ
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // ���� ��ġ�� 1�̸�, ���� ������ �����Ͽ� ���簢���� ũ�� ������Ʈ
                if (dp[i][j] == 1 && i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                // ���� ū ���簢���� ũ�⸦ ����
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }
        // ���� ū ���簢���� �� ���� ���̸� ��ȯ
        return max;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader�� StringTokenizer�� �̿��� �Է� ó��
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // ����� ũ�� �Է�
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // DP ���̺� �ʱ�ȭ
        dp = new int[1001][1001];

        // ����� �� ���� �о dp ���̺� ���� (0�� 1�� �̷���� ���� ���)
        for (int i = 0; i < row; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < temp.length; j++) {
                dp[i][j] = temp[j] - '0'; // char '0'�� ���� 0����, '1'�� ���� 1�� ��ȯ
            }
        }

        // ���� ū ���簢���� ũ�� ���
        int max = memoize(row, col);

        // ���� ū ���簢���� ���� ���
        System.out.println((int) Math.pow(max, 2)); // ���� ū ���簢���� ũ�⸦ �����Ͽ� ���̸� ���
    }
}