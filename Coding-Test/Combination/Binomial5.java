import java.io.*;
import java.util.*;

public class Binomial5 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-19
     * ���� �ۼ��ð� : 10:30
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 13251
     * ���� �̸� : ���൹ ������
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */

    static int[] marbleCounts;                   // �� ���� ���൹ ����
    static double[] colorProbabilities;          // �� ���� �ܵ����� ���õ� Ȯ��

    static int totalMarbles = 0;                 // ��ü ���൹ ��
    static int colorCount;                       // ���� ���� ��
    static int drawCount;                        // ���� ���൹ ��

    static double totalProbability = 0.0;        // ��� Ȯ��

    // Ư�� ������ ���൹�� ���� Ȯ�� ���
    static double calculate(int index) {
        if (marbleCounts[index] >= drawCount) {
            colorProbabilities[index] = 1.0;

            for (int i = 0; i < drawCount; i++) {
                colorProbabilities[index] *=
                        (double)(marbleCounts[index] - i) / (totalMarbles - i);
            }
        }
        return colorProbabilities[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        colorCount = Integer.parseInt(br.readLine());
        marbleCounts = new int[colorCount];
        colorProbabilities = new double[colorCount];

        // �� ���� ���൹ �� �Է¹ް� ��ü ���� ���
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < colorCount; i++) {
            marbleCounts[i] = Integer.parseInt(st.nextToken());
            totalMarbles += marbleCounts[i];
        }

        drawCount = Integer.parseInt(br.readLine());

        // Ȯ�� ���
        for (int i = 0; i < colorCount; i++) {
            totalProbability += calculate(i);
        }

        System.out.println(totalProbability);
    }
}
