import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum1 {
    /* 
    * ���� �ۼ��Ͻ� : 2025-02-07
    * ���� �ۼ��ð� : 21:53
    * ���� �ۼ��� : ����ȯ
    * 
    * ������ �ۼ��Ͻ� : 
    * ������ �ۼ��ð� : 
    * ������ �ۼ��� : 
    *
    * ���� ��ó : ����
    * ���� ��ȣ : 11659
    * ���� �̸� : ���� �� ���ϱ� 4
    * ���� ���̵� : �ǹ� ��
    *
    * �ۼ� ����
    * 
    * å�� �ִ� ���� ����
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : �Է� ���� �������� ����
         * query : ���� ����
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        /*
         * datas �迭�� ũ�⸦ �Է� ���� �������� �������� 1�� ���� ������
         * ù ��° �����ͺ��� ������ ��ġ�� �����ͱ����� ���� ���� ���� ��
         * ���� ���� ���ϴ� ������ S[j] - S[i-1]�� ����ϸ� �ε��� ������ �ѱ� �����̴�(i-1�� ������ �Ǹ� �ȵǱ� ����)
         */
        int [] datas = new int[size+1];
        /*
         * �Ʒ��� for ���� �� �迭�� �����͸� �����鼭 ������ ���� ��(ù ��° �����ͺ��� i-1��° �����ͱ����� ��)�� ���Ѵ�.
         * �׷��⿡ �Ʒ��� for ������ ���� �迭�� ������ �ʰ� �ٷ� �� �迭�� ����� ���̴�.
         */
        for(int i = 1; i < datas.length; i++){
            datas[i] = Integer.parseInt(st.nextToken()) + datas[i-1];
        }

        /*
         * �Ʒ��� for���� ���� ���� ���ϴ� �����̴�.
         * start�� ������ ����, end�� ������ �������̴�.
         * bw(BufferedWriter)�� �� ������ ��� ���ǿ� ���� ���� ���� ������ �� ���� ����� �� �ְ�
         * System.out�� �ִ� print �Լ����� ���ҽ��� ���� �Ա� ������ ����ߴ�.
         * 
         * �̿� ���� ������ BufferedReader�� Java.util�� �ִ� Scanner���� ���ҽ��� ���� �Դ´�.
         * 
         * BufferedReader�� BufferedWriter�� ���ؼ��� ���� ������ �����̴�.
         */
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int sum = datas[end] - datas[start-1];
            bw.append(sum + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
