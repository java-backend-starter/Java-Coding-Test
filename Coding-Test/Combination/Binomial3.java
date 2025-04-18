import java.io.*;

public class Binomial3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-18
     * ���� �ۼ��ð� : 11:23
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2775
     * ���� �̸� : �γ�ȸ���� �� �׾�
     * ���� ���̵� : ����� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // 15x15 ũ���� ����Ʈ �迭 ���� (������ ���� ȣ�� ���� �ο� ���� ����)
    static int [][] apartment = new int[15][15];

    /*
     * ����Ʈ �迭 �ʱ�ȭ �Լ�
     * 0���� �� ȣ�� �� ȣ ��ȣ�� ������ ��� ���� ����
     * �� ȣ�� �ο� ���� ������ �ش� ȣ�� �� ���� ȣ ��� ���� ������ ���
     */
    static void init(){
        // 0���� �� ȣ�� �� ��ȣ�� ��� ���� ���� (1ȣ���� 1��, 2ȣ���� 2��, ...)
        for(int i = 0; i < 15; i++){
            apartment[i][1] = 1;   // 1ȣ���� �׻� 1���� ���
            apartment[0][i] = i;   // 0���� �� ȣ�� ��� ��� ���� �ش� ȣ�� ��ȣ�� ����
        }

        // 1�� �̻��� ���� �� ȣ�� ��� ��� ���� ���� ���� �ش� ȣ�� ���� ȣ ��� ���� ���Ͽ� ���
        for(int i = 1; i < 15; i++){
            for(int j = 2; j < 15; j++){
                apartment[i][j] = apartment[i-1][j] + apartment[i][j-1];
            }
        }
    }

    public static void main(String [] args) throws IOException{
        // �Է��� ó���� BufferedReader�� ����� ����� BufferedWriter ����
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // �׽�Ʈ ���̽��� ������ �Է¹���
        int query = Integer.parseInt(br.readLine());

        // ����Ʈ �迭 �ʱ�ȭ (��� ����� �̸� �صξ� ���߿� ������ ����� ����� �� �ֵ���)
        init();

        // �� �׽�Ʈ ���̽��� ���� ����� ���
        for(int i = 0; i < query; i++){
            // �� �׽�Ʈ ���̽����� ���� ȣ ��ȣ�� �Է¹���
            int row = Integer.parseInt(br.readLine());  // ��
            int col = Integer.parseInt(br.readLine());  // ȣ ��ȣ

            // �ش� ���� ȣ�� ��� ���� ���
            bw.write(apartment[row][col] + "\n");
        }
        // ��� ���
        bw.flush();
    }
}