import java.io.*;
import java.util.*;

public class DynamicProgramming7 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-30
     * ���� �ۼ��ð� : 17:28
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 9252
     * ���� �̸� : LCS 2
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * ���� ����:
     * �� ���ڿ� A�� B�� �־����� ��, �̵��� ���� ���� �κ� ����(LCS)�� ���ϴ� �����Դϴ�.
     * LCS�� �� ���ڿ����� ������� �����ϴ� ����� ������ �ִ� �κ� ������ �ǹ��մϴ�.
     * ���� ���, ���ڿ� "ABCBDAB"�� "BDCAB"�� ���� LCS�� "BCAB"�Դϴ�.
     * �� ���������� LCS�� ���̿� �Բ� LCS ���ڿ��� ���ϴ� ���� ��ǥ�Դϴ�.
     *
     * �Է�:
     * �� �ٿ� ���� ���� A�� B ���ڿ��� �־����ϴ�. �� ���ڿ��� ���̴� �ִ� 1000�Դϴ�.
     *
     * ���:
     * ù ��° �ٿ��� LCS�� ���̸� ����ϰ�, �� ��° �ٿ��� LCS ���ڿ��� ����մϴ�.
     * LCS�� ���� ���� ��� ���������� ���� �ռ��� ���� ����մϴ�.
     *
     * ����:
     * �Է�:
     * ABCBDAB
     * BDCAB
     * ���:
     * 4
     * BCAB
     *
     * �� ���ÿ��� LCS�� "BCAB"�� ���̴� 4�Դϴ�.
     *
     */

    // LCS ��θ� ������ ArrayList ����
    static ArrayList<Character> path = new ArrayList<>();

    // �Է� ���ڿ� A�� B�� ������ �迭 ����
    static char[] A;
    static char[] B;

    // DP ���̺��� ������ 2D �迭 ����
    static int[][] dp;

    /*
     * DP ���̺��� �ʱ�ȭ�ϰ� LCS�� ���̸� ����ϴ� �Լ�
     * dp[i][j]�� A[0..i-1]�� B[0..j-1]������ LCS ���̸� �ǹ��Ѵ�.
     *
     * row : A ���ڿ��� ����
     * col : B ���ڿ��� ����
     */
    static void init(int row, int col) {
        // dp ���̺��� row+1 x col+1 ũ��� �ʱ�ȭ
        dp = new int[row+1][col+1];

        // 1���� row����, 1���� col���� ���������� dp ���� ���
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // ���� A[i-1]�� B[j-1]�� ���ٸ�, ���� LCS ���� 1�� ����
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // �ٸ���, ���� ��� �� �� �� ū ������ ����
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    /*
     * dp ���̺��� ������� ���� LCS ���ڿ��� ã�� path�� �����ϴ� �Լ�
     *
     * row : A ���ڿ��� �ε���
     * col : B ���ڿ��� �ε���
     */
    static void LCS(int row, int col) {
        // ���� �� ���ڿ��� ���� �����ϸ� �� �̻� ã�� �ʿ䰡 ����
        if (row == 0 || col == 0) {
            return;
        }

        // A[row-1]�� B[col-1]�� ������ LCS�� �Ϻη� ����
        if (A[row-1] == B[col-1]) {
            path.add(A[row-1]);
            // �� �� ���� ���ڿ��� ����ؼ� LCS�� ã��
            LCS(row-1, col-1);
        } else {
            // A[row-1] != B[col-1]�� ���, �� �� LCS�� �����Ͽ� ��θ� ã��
            if (dp[row-1][col] > dp[row][col-1]) {
                LCS(row-1, col); // ���ʿ��� �� ū ���� ã�Ҵٸ�, ���� �̵�
            } else {
                LCS(row, col-1); // ���ʿ��� �� ū ���� ã�Ҵٸ�, �������� �̵�
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader�� BufferedWriter�� �̿��� ����� ó��
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // A�� B ���ڿ��� �Է¹޾� char �迭�� ��ȯ
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        // dp ���̺� �ʱ�ȭ �� LCS ���� ���
        init(A.length, B.length);

        // ���� LCS ���̴� dp[A.length][B.length]�� �����
        int lcs = dp[A.length][B.length];

        // LCS ��θ� ã��
        LCS(A.length, B.length);

        // LCS ���� ���
        bw.write(lcs + "\n");

        // ��ο� ����� LCS ���ڿ��� �������� ��� (LCS�� �ڿ������� ���ʷ� �߰��ǹǷ� �������� ���)
        for (int i = path.size() - 1; i >= 0; i--) {
            bw.write(path.get(i));
        }
        bw.write("\n");

        // ��� ���۸� flush�Ͽ� ����� ���
        bw.flush();
    }
}