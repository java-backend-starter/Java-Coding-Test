import java.io.*;
import java.util.*;

public class DynamicProgramming10 {
    /*
     * ���� �ۼ��Ͻ� : 2025-05-13
     * ���� �ۼ��ð� : 22:02
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2342
     * ���� �̸� : Dance Dance Revolution
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * ���� ����:
     *
     * DDR(Dance Dance Revolution) ���� �� ����(1~4)�� �г��� �ְ�,
     * ����ڴ� �� ���� �̿��Ͽ� ���ÿ� ���� �ش� ������ ��´�.
     *
     * ���� �Է��� 1~4�� ���� �־�����, 0�� �ԷµǸ� ���Ḧ �ǹ��Ѵ�.
     * �� ���� ���ÿ� ���� ��ġ�� �� �� ����.
     * �ʱ⿡�� ����� �߾�(0)�� ��ġ�Ѵ�.
     *
     * �� ���� �̵���Ű�� �� ��� �������� ������ ����:
     * - ���� ��ġ�� �������� ���� ���: 1
     * - �߾�(0) �� ���� �̵�: 2
     * - ������ ���� �̵�: 3
     * - �ݴ� ���� �̵�: 4
     *
     * �־��� �Է¿� ���� ���ø� ������� ���� ��, ���� �������� �� �����Ͽ�
     * **�ʿ��� �ּ� ������ �Һ�**�� ���ϴ� �����̴�.
     *
     * ���� �Է�:
     * 1 2 2 4 0
     * ���:
     * 8
     *
     * ����:
     * - ���� ���� �ִ� 100,000��
     * - �ð� ����: 2��
     */

    // dp[step][left][right] = step��° ��ɱ��� ������ ���¿���
    // �޹��� left, �������� right�� ���� ���� �ּ� ������
    static int[][][] dp = new int[100001][5][5];

    // energy[i][j] = i ��ġ���� j ��ġ�� �̵��� �� ��� ������
    static final int[][] energy = {
            { 0, 2, 2, 2, 2 },  // 0��(�߾�)���� �� ��������
            { 2, 1, 3, 4, 3 },  // 1������ �� ��������
            { 2, 3, 1, 3, 4 },  // 2������ �� ��������
            { 2, 4, 3, 1, 3 },  // 3������ �� ��������
            { 2, 3, 4, 3, 1 }   // 4������ �� ��������
    };

    // dp �迭 �ʱ�ȭ (ū ������ �ʱ�ȭ�Ͽ� �ּҰ� �� �����ϰ�)
    static void init() {
        for (int k = 0; k < 100001; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[k][i][j] = 100001 * 4; // ū ������ �ʱ�ȭ
                }
            }
        }
    }

    // �Է� ���ø� �а� DP ���̺��� ä��� �Լ�
    static int mamoize(StringTokenizer st) {
        dp[0][0][0] = 0; // �ʱ� ����: ����� �߾�(0)�� ���� �� ������ 0
        int step = 1;

        while (true) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) break; // 0�� ������ �Է� ����

            // �޹� ����, ������ �̵�
            for (int i = 0; i < 5; i++) {
                if (num == i) continue; // ����� ���� ��ġ�� ���� �� ����
                for (int j = 0; j < 5; j++) {
                    dp[step][i][num] = Math.min(
                            dp[step][i][num],
                            dp[step - 1][i][j] + energy[j][num]
                    );
                }
            }

            // ������ ����, �޹� �̵�
            for (int i = 0; i < 5; i++) {
                if (num == i) continue;
                for (int j = 0; j < 5; j++) {
                    dp[step][num][i] = Math.min(
                            dp[step][num][i],
                            dp[step - 1][j][i] + energy[j][num]
                    );
                }
            }

            step++; // ���� �������� �̵�
        }

        return step - 1; // ������ ���� ��ȣ ��ȯ
    }

    // �ּ� �������� ���ϴ� �Լ�
    static int excute(StringTokenizer st) {
        int step = mamoize(st); // DP ���� ��
        int min = Integer.MAX_VALUE;

        // ������ �ܰ迡�� ������ ��� ��ġ ���տ� ���� �ּҰ� ���
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[step][i][j]);
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(); // DP �迭 �ʱ�ȭ

        int result = excute(new StringTokenizer(br.readLine())); // �Է� ó�� �� ���

        System.out.println(result); // �ּ� ������ ���
    }
}
