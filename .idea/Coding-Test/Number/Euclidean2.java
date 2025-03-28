import java.io.*;
import java.util.*;

public class Euclidean {
    /*
     * ���� �ۼ��Ͻ� : 2025-03-29
     * ���� �ۼ��ð� : 00:48
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1850
     * ���� �̸� : �ִ�����
     * ���� ���̵� : �ǹ� ��
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
    static long gcd(long a, long b){
        long temp;
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
         * a, b : �ִ� ������� ���� �� ��
         */
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long gcd = gcd(a, b);

        while(gcd > 0){
            bw.append("1");
            gcd--;
        }
        bw.flush();
    }
}