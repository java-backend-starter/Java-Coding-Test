import java.io.*;
import java.util.*;

public class Binomial5 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-23
     * ���� �ۼ��ð� : 14:05
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1256
     * ���� �̸� : ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    // �ִ� ���� ���� ������ �迭 (binomial[n][k]�� nCk�� �ǹ�)
    static int [][] binomial = new int[201][201];

    // ���հ��� �̸� ����ؼ� �����ϴ� �Լ�
    static void init(){
        // �⺻ ���հ� �ʱ�ȭ
        for(int i = 0; i < 201; i++){
            binomial[i][0] = 1;  // nC0 = 1
            binomial[i][1] = i;  // nC1 = n
            binomial[i][i] = 1;  // nCn = 1
        }

        // ��ȭ���� �̿��� ������ ���հ� ���
        for(int i = 2; i < 201; i++){
            for(int j = 1; j < i; j++){
                // nCk = (n-1)Ck + (n-1)C(k-1)
                binomial[i][j] = binomial[i-1][j] + binomial[i-1][j-1];

                // ���� 1,000,000,000�� ������ �����÷ο� ������ ���� 1,000,000,001�� ����
                if(binomial[i][j] > 1000000000){
                    binomial[i][j] = 1000000001;
                }
            }
        }
    }

    // n���� 'a'�� m���� 'z'�� ���� �� �ִ� ���ڿ� �� k��° ���� �� ���ڿ��� ��ȯ�ϴ� �Լ�
    static String findString(int n, int m, int k){
        // ���� �� �ִ� ��� ���ڿ� ������ k���� ������ -1 ��ȯ
        if(binomial[n+m][m] < k){
            return "-1";
        } else {
            StringBuilder sb = new StringBuilder();  // ��� ���ڿ��� ������ StringBuilder

            // n�� m�� ��� 0�� �� ������ �ݺ�
            while(!(n == 0 && m == 0)){
                /* ���� ���ڸ� 'a'�� ������ ���, ���� �ڸ����� ���� �� �ִ� ���ڿ��� ����
                 * '���� a�� �ϳ� �ְ�', ���� n-1���� a�� m���� z�� ���� �� �ִ� ���ڿ� ��
                 * ��, �� ���� �ڸ� ���� (n + m - 1), �� �� m���� z ��ġ�� ���� ����
                 */
                if(binomial[n+m-1][m] >= k){
                    sb.append("a");  // 'a'�� �����ϰ�
                    n--;            // a ���� �ϳ� ����
                } else {
                    sb.append("z");  // 'z'�� �����ϰ�
                    k -= binomial[n+m-1][m];  // a�� �����ϴ� ��츦 �ǳʶٱ� ���� k�� ���� == �տ� �־��� ��� 'a'�� �����ϴ� ��츦 �ǳʶٱ� ���� k�� ����
                    m--;            // z ���� �ϳ� ����
                }
            }
            return sb.toString();  // ��� ���ڿ� ��ȯ
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // �Է�: a�� ����(n), z�� ����(m), k��° ���ڿ�(k)
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        init();  // ���հ� �ʱ�ȭ

        System.out.println(findString(n, m, k));  // ��� ���
    }
}