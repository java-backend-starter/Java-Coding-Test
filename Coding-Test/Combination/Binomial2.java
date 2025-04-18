import java.io.*;
import java.util.*;

public class Binomial2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-18
     * ���� �ۼ��ð� : 10:43
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11051
     * ���� �̸� : ���� ��� 2
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // ���� ����� �����ϴ� �迭
    static int [][] binomial;

    /*
     * �迭 �ʱ�ȭ
     * size : binomial�� ũ�� ����
     */
    static void init(int size){
        // ���� ��� �迭�� (size+1) x (size+1) ũ��� �ʱ�ȭ
        binomial = new int[size+1][size+1];

        // ���� ����� �⺻ �� ����
        // nC0 = 1, nCn = 1, nC1 = n
        for(int i = 0; i <= size; i++){
            binomial[i][1] = i;  // nC1 = n
            binomial[i][0] = 1;  // nC0 = 1
            binomial[i][i] = 1;  // nCn = 1
        }

        // ���� ��� ���: ���� ����� ������ ���� ����� ������ ����
        // nCk = (n-1)C(k-1) + (n-1)Ck
        // �� ��, ���� ���� ����� 10007�� ���� �������� ����
        for(int i = 2; i <= size; i++){
            for(int j = 1; j < i; j++){
                // ���� ��ȭ���� �����Ͽ� ���� ����� ����ϰ�,
                // 10007�� ���� �������� ����
                binomial[i][j] = (binomial[i-1][j-1] + binomial[i-1][j]) % 10007;
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // ���� ������� n ��
        int k = Integer.parseInt(st.nextToken()); // ���� ������� k ��

        // ���� ��� �迭 �ʱ�ȭ
        init(n);

        // binomial[n][k]�� nCk ���� ����� ��, 10007�� ���� �������� ���
        System.out.println(binomial[n][k]);
    }
}