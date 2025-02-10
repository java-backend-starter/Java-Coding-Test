import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum3 {
    /* 
    * �ۼ��Ͻ� : 2025-02-10
    * �ۼ��ð� : 20:93
    * �ۼ��� : ����ȯ
    *
    * ���� ��ó : ����
    * ���� ��ȣ : 10986
    * ���� �̸� : ������ ��
    * ���� ���̵� : ��� ��
    *
    * �ۼ� ����
    * 
    * å�� �ִ� ���� ����
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : ������ �տ� ���� ����� ����
         * remider : ������ ��꿡 ���� �ǿ����� ��
         */
        int size = Integer.parseInt(st.nextToken());
        int reminder = Integer.parseInt(st.nextToken());

        /*
         * counter �迭 : �� ��Һ��� ������ ����� �ϰ� ���� ��(���� : 0 ~ reminder - 1)�� ī��Ʈ
         * sum �迭 : �� �迭
         * answer : ������ �������� ���� ����
         */
        long [] counter = new long[reminder];
        long [] sum = new long[size];
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        sum[0] = Long.parseLong(st.nextToken());
        /*
         * �� �迭 ����
         */
        for(int i = 1; i < size; i++){
            sum[i] = sum[i-1] + Long.parseLong(st.nextToken());
        }

        /*
         * ����
         * 1. �� �迭�� �� ����� ������ ���� ���Ѵ�.
         * 2. �� ����� ������ ���� 0�̸� answer�� 1�� ���Ѵ�.
         * 3. ������ ����ϰ� ���� ������ counter �迭�� Ư�� �ε����� ���� 1�� ���Ѵ�.
         * 
         * �� ������ ù ��° ��Һ��� i(i < size)��° ��ұ����� �տ��� reminder�� ������ �������� ��츦 ã�� ���� �����̴�.
         */
        for(int i = 0; i < size; i++){
            int rmd = (int) sum[i] % reminder;

            if(rmd == 0){
                answer++;
            }

            counter[rmd]++;
        }

        /*
         * �� ������ Ư�� ������ ���� reminder�� ������ �������� ��츦 ã�� ���� �����̴�.
         * 
         * ���� nC2�� ����ϴ� �̿�� �� �迭���� �������� ���� ��� �� ���� �̰� �� �� ��Ҹ� ����(ū ������ ���� ���� ����) �������� 0�� � ���� ������ �����̴�.
         */
        for(int i = 0; i < counter.length; i++){
            if(counter[i] > 1){
                answer += (counter[i] * (counter[i] - 1)) / 2;
            }
        }

        System.out.println(answer);

    }
}
