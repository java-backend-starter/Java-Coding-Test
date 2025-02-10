import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum1 {
    /* 
    * ���� �ۼ��Ͻ� : 2025-02-07
    * ���� �ۼ��ð� : 21:53
    * �ۼ��� : �强ȯ
    * 
    * ������ �ۼ��Ͻ� : 2025-02-10
    * ������ �ۼ��ð� : 20:44
    * �ۼ��� : �强ȯ
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
         * size : ���� ���� ���ϴµ� ����� ����� ����
         * query : ���� ���� ������ ����
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        /*
         * datas �迭�� �� ��Ҹ� �����ϸ鼭 ���ÿ� �� �迭�� ����µ� ����� ���̴�. 
         * 
         * �� �迭���� ���� ���� ���ϴ� ������ S[j] - S[i-1]�̴�. ���� i-1�� 0�� ���ų� Ŀ�� �Ѵ�(i-1�� �迭�� �ε��� ������ �ʰ��ϸ� �ȵǱ� ����)
         */
        int [] datas = new int[size+1];
        /*
         * �Ʒ��� for�������� �� �迭�� ����� �����̴�.
         * 
         * ���� �迭�� ����� �� �迭�� ���� �ð� ���⵵�� ��(n)�̱⿡ � ������� ó���ص� �ð� �ʰ��� ���� �ʴ´�.
         */
        for(int i = 1; i < datas.length; i++){
            datas[i] = Integer.parseInt(st.nextToken()) + datas[i-1];
        }

        /*
         * �Ʒ��� for������ ���� ���� ���ϰ� �ִ�.
         * 
         * start�� �������� ù ��° ���, end�� �������� ������ ����̴�.
         * 
         * bw(BufferedWriter)�� �� ������ �� ���Ƿ� ���� ���� ���� �����ؼ� �� ���� ����ϱ� �����̶�� ������ ������
         * 
         * System.out�� ���� print �Լ����� ���ҽ��� ���� �Դ´�.
         * 
         * BufferedReader�� BufferedWriter�� ���ؼ��� ���� �ٷﺼ �����̴�.
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
