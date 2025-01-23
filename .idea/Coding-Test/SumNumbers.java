import java.io.*;
public class SumNumbers{
    /* 
    * ���� �ۼ��Ͻ� : 2025-01-24
    * ���� �ۼ��ð� : 00:45
    * ���� �ۼ��� : ����ȯ
    * 
    * ������ �ۼ��Ͻ� : 2025-01-24
    * ������ �ۼ��ð� : 01:43
    * ������ �ۼ��� : �强ȯ
    *
    * ���� ��ó : ����
    * ���� ��ȣ : 11720
    * ���� �̸� : ������ ��
    * ���� ���̵� : ����� ��
    *
    * �ۼ� ����
    * 
    * å�� �ִ� ���� ����
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * size : ���� ������ ����
         * data : �Է¹��� ����(�� �ڸ����� ���ڵ��� ���ϴ� ��)
         */
        int size = Integer.parseInt(br.readLine());
        String data = br.readLine();

        /*
         * �����͸� ���ڿ��� �ް� �ش� ���ڿ��� ���ڷ� ��ȯ�ؼ� ���� ���Ѵ�.
         * ���ڿ��� ���ڷ� ��ȯ�� �� ���� �ϳ��� ���ڷ� �ٲ۴�.
         * ���ڷ� �� ���ڵ�(�� : '0', '1')�� �ƽ�Ű�ڵ忡�� 48���� �����ϹǷ�
         * 48�̳� '0'�� ���� ���� ��Ȯ�ϰ� ���� �� �ִ�. 
         */
        
        int sum = 0;

        // charAt() �Լ��� ������ ���
        for(int i = 0; i < size; i++){
            sum += (data.charAt(i) - '0'); 
            // sum += (data.charAt(i) - 48); // '0' ��� 48�� ���� ����� ����.
        }
        System.out.println("charAt() �Լ��� �̿��� ��� : " + sum);

        sum = 0;
        // char Ÿ���� array�� ����ϴ� ���
        char [] datas = data.toCharArray();
        for(int i = 0; i < datas.length; i++){
            sum += (datas[i] - '0');
            // sum += (datas[i] - 48);
        }
        System.out.println("char �迭�� �̿��� ��� : " + sum);
    }
}