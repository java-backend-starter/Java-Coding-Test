import java.io.*;
import java.util.*;

public class Euclidean {
    /*
     * ���� �ۼ��Ͻ� : 2025-03-29
     * ���� �ۼ��ð� : 00:27
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1934
     * ���� �̸� : �ּҰ����
     * ���� ���̵� : ����� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    /*
     * ��Ŭ���� ȣ������ ���� �ִ����� �˰���
     * ����
     * 1. a�� b, b�� a%b�� ����
     * 2. b�� 0�� �� ������ 1�� �ݺ�
     * 3. 2�� ������ a�� ����� ���� �ִ�����
     *
     * ���� ����� while���� �̿��ϴ� ���� ����Լ��� �̿��ϴ� ���� ����
     */
    static int gcd(int a, int b){
        int temp;
        while(b != 0){
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
        /*
         * return b == 0 ? a : gcd(b, a % b);
         */
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * query : ������ ����
         * a, b : �ּ� ������� ���� �� ��
         * �ּ� ������� ������ ������ �̿��ؼ� ���Ѵ�
         * �� ���� �� = �ִ� ����� * �ּ� ����� -> �ּ� ����� = �� ���� �� / �ִ� �����
         */
        int query = Integer.parseInt(br.readLine());
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.append((a*b)/gcd(a, b) + "\n");
        }
        bw.flush();
    }
}