import java.io.*;
import java.util.*;

public class DynamicProgramming9 {
    /*
     * ���� �ۼ��Ͻ� : 2025-05-01
     * ���� �ۼ��ð� : 12:05
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1328
     * ���� �̸� : ���� ����
     * ���� ���̵� : �÷�Ƽ�� 5
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    /* ���� ����:
     * ���� ���� ������ �־��� ������ �������� ���ʰ� �����ʿ��� ����
     * ���ѵ� ������ŭ ���� ������ ��ġ�Ͽ� ������ �״� ����� ���ϴ� �����Դϴ�.
     * �� ������ �����̳� �����ʿ� ���� �� ���� �� �ִ� ������ ������, �� ������
     * �����ϴ� ����� ���� ���ϴ� �����Դϴ�.
     *
     * �� ������ ���� ��ȹ��(DP)�� ����Ͽ� �ذ��� �� �ֽ��ϴ�. DP �迭��
     * Ȱ���� ������ ���� ����� �����ϰ� �̸� �������� ��ȭ���� �����
     * ������ �ذ��մϴ�.
     */

    // 3���� DP �迭 ���� (size x left x right)
    // dp[i][j][k]�� ũ�� i�� �������� ���ʿ� j���� ���� ������ �����ʿ� k���� ���� ������ ��ġ�ϴ� ����� ��
    static long [][][] dp = new long[101][101][101];

    // ������ ������ ���� ����� (1000000007)
    static long mod = 1000000007L;

    // DP ����� �����ϴ� �޸������̼� �Լ�
    static void memoize(int size, int left, int right){
        // �ʱⰪ ���� (1�� ������ ���� 1���� ���)
        dp[1][1][1] = 1;

        // DP �迭 ä���
        // size�� ������ ũ��, left�� ���� ���� ���� ��, right�� ������ ���� ���� ��
        for(int i = 2; i <= size; i++){
            for(int j = 1; j <= left; j++){
                for(int k = 1; k <= right; k++){
                    // dp[i][j][k] �� ���
                    // ��ȭ��:
                    // dp[i-1][j][k] * (i-2): ���� ũ�⿡�� i-2���� ������ �߾ӿ� �� ��
                    // dp[i-1][j-1][k]: ���ʿ� �ϳ��� ���� ������ �߰��� ���
                    // dp[i-1][j][k-1]: �����ʿ� �ϳ��� ���� ������ �߰��� ���
                    dp[i][j][k] = (dp[i-1][j][k] * (i-2) + dp[i-1][j-1][k] + dp[i-1][j][k-1]) % mod;
                }
            }
        }
    }

    // ���� �Լ�
    public static void main(String [] args) throws IOException {
        // �Է� �ޱ�
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // �Է� �� �Ľ�
        int size = Integer.parseInt(st.nextToken());  // ������ ũ��
        int left = Integer.parseInt(st.nextToken());  // ���ʿ� ���� �� �ִ� ���� ������ ����
        int right = Integer.parseInt(st.nextToken()); // �����ʿ� ���� �� �ִ� ���� ������ ����

        // �޸������̼��� �����Ͽ� DP �迭 ���
        memoize(size, left, right);

        // ��� ��� (ũ�� size�� �������� ���ʿ� left���� ���� ������ �����ʿ� right���� ���� ������ ��ġ�ϴ� ����� ��)
        System.out.println(dp[size][left][right]);
    }
}