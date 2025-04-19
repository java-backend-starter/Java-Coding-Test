import java.io.*;
import java.util.*;

public class Binomial4 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-19
     * ���� �ۼ��ð� : 10:12
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1010
     * ���� �̸� : �ٸ� ����
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */

    // ������ �迭�� ����Ͽ� ���װ�� ����
    static int[][] binomial;

    // ���װ���� �ʱ�ȭ�ϴ� �Լ� (�Ľ�Į�� �ﰢ�� �̿�)
    static void init(){
        binomial = new int[31][31]; // �ִ� 30������ ���װ�� ���

        // �⺻�� ����
        for(int i = 0; i < 31; i++){
            binomial[i][0] = 1;     // nC0 = 1
            binomial[i][1] = i;     // nC1 = n
            binomial[i][i] = 1;     // nCn = 1
        }

        // �Ľ�Į�� �ﰢ���� �̿��� ���װ�� ���
        for(int i = 2; i < 31; i++){
            for(int j = 1; j < i; j++){
                binomial[i][j] = binomial[i-1][j-1] + binomial[i-1][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // �Է� �� ����� ���� BufferedReader, BufferedWriter ����
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int query = Integer.parseInt(br.readLine()); // �׽�Ʈ ���̽� �� �Է�

        init(); // ���װ�� �̸� ���

        // �� �׽�Ʈ ���̽��� ���� ���
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine()); // �� �� �а� ���� �������� ����
            int n = Integer.parseInt(st.nextToken()); // ���� �ٸ� ��
            int m = Integer.parseInt(st.nextToken()); // ���� �ٸ� ��

            // mCn ��� (�ٸ� ���� ����� ��)
            bw.write(binomial[m][n] + "\n");
        }

        bw.flush(); // ��� ���� ����
    }
}
