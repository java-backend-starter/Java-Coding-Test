import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum2 {
    /* 
    * ���� �ۼ��Ͻ� : 2025-02-10
    * ���� �ۼ��ð� : 19:42
    * ���� �ۼ��� : ����ȯ
    * 
    * ������ �ۼ��Ͻ� : 
    * ������ �ۼ��ð� : 
    * ������ �ۼ��� : 
    *
    * ���� ��ó : ����
    * ���� ��ȣ : 11660
    * ���� �̸� : ���� �� ���ϱ� 5
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
         * size : n * n ũ���� 2���� �迭���� n ��
         * query : ���� ���� ������ ����
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        /*
         * �ش� ������ ������ 11659�� ������ ���� �� ���ϱ� 4�� 2���� �迭�� Ȯ���� ���̴�.
         * 
         * ���� ���� �� ���ϱ� 5�� ��� ���̶�� �� ������ ����ؼ� ������ ������ �Ѵ�.
         * 
         * �� ���������� ù ��° ��Ұ� �ִ� (1, 1)���� (i, j)(1 <= i <= n, 1 <= j <= n)������ �� �迭�� ���� ������
         * 
         * (i1, j1)(i1 <= n, j1 <= n)���� (i2, j2)(i1 <= i2 <= n, j1 <= j2 <= n)������ ���� ���� ����Ѵ�.
         */
        int [][] datas = new int[size+1][size+1];
        int [][] sum = new int[size+1][size+1];

        for(int i = 1; i <= size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= size; j++){
                datas[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
         * �� �迭�� ����� ������ �� ���Ǹ��� �迭 �ȿ� �ִ� ��ҵ��� ���� �����ؼ� ���� ���� ����� ���� ��ȿ�����̱� �����̴�.
         * 
         * ������ 11659�� ������ 1���� �迭�� �ִ� ��ҵ鿡 ���� ���� ���� �� �迭 ���� ���ϸ� ��(n)�� ������
         * 
         * �� ������ 2���� �迭�� �ִ� ��ҵ鿡 ���� ���� ���� ���ؾ� �ϱ� ������ �� �迭 ���� ����ϸ�
         * 
         * �� ���Ǹ��� ��(n^2)�� ��� ������ 1�� �ȿ� �� ������ Ǯ �� ����.
         * 
         * ��(n^2)��� �ð� ���⵵�� ��� ������ �־��� ���� 2���� �迭�� ��� ��Ҹ� �� �����ϴ� ���̱� �����̴�.
         * 
         * ���� �� �迭�� �� �� �����ΰ� �� ���Ǹ� ó���ϸ� ȿ�������� �� ������ ���� ���� �� �ִ�.
         * 
         * 
         * (1, 1)���� (i, j)(1 <= i <= n, 1 <= j <= n)������ ���� ���ϴ� ������
         * 
         * (���� �迭�� (i, j)��° ���) + (�� �迭�� (i-1, j)��° ��) + (�� �迭�� (i, j-1)��° ��) - (�չ迭�� (i-1, j-1)��° ��)
         * 
         * �̴�. �չ迭�� (i-1, j-1)��° ���� ���� ������ �չ迭�� (i-1, j)��° ���� �չ迭�� (i, j-1)��° �� ���
         * 
         * �չ迭�� (i-1, j-1)��° ���� ���ԵǾ� �ֱ⿡ �ߺ��� ���� ���ִ� ���̴�.
         */
        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] + datas[i][j] - sum[i-1][j-1];
            }
        }

        /*
         * �� for���� ���� ���� ���ϴ� for���̴�.
         * 
         * 2���� �迭���� ���� ���� ���ϴ� ������ 
         * 
         * (�� �迭�� (i2, j2)��° ��) - (�� �迭�� (i1-1, j2)��° ��) - (�� �迭�� (i2, j1-1)��° ��) + (�� �迭�� (i1 - 1, j1 - 1)��° ��)
         * 
         * �̴�. (�� �迭�� (i1-1, j2)��° ��)�� (�� �迭�� (i2, j1-1)��° ��)�� ���� ������ (i1, j1)���� (i2, j2) ������ ���Ե��� �ʴ� �����̱� �����̰�
         * 
         * (�� �迭�� (i1 - 1, j1 - 1)��° ��)�� ���ϴ� ������ (�� �迭�� (i1-1, j2)��° ��)�� (�� �迭�� (i2, j1-1)��° ��)�� ���� �������� 
         * 
         * (�� �迭�� (i1 - 1, j1 - 1)��° ��)�� �� �� ���� ������ �� ���� �����ϱ� �����̴�.
         * 
         */
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int prefixSum = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
            bw.append(prefixSum + "\n");
        }

        bw.flush();
    }
}
