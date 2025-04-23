import java.io.*;
import java.util.*;

public class Permutation1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-21
     * ���� �ۼ��ð� : 12:58
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1722
     * ���� �̸� : ������ ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ���� : ���ؿ� �ִ� ���� Ǯ��
     */
    /*
     * factorial : �� ���� ����(n)�� ���� ���� �� �ִ� ������ �� ����� �� ����
     *             ��: factorial[3] = 6 (3! = 3��2��1)
     *
     * permutation : ���� ������ ��� �迭 (1-based index ���)
     *
     * visited : ���� ����/Ž�� �� �̹� ���� �������� ���θ� üũ�ϱ� ���� �迭
     */
    static long[] factorial;
    static int[] permutation;
    static boolean[] visited;

    /*
     * k��° ������ ���ϴ� �Լ�
     *
     * �Է�:
     * - size : ������ ũ�� (��� ����)
     * - k    : �� ��° �������� (1-based index)
     *
     * ���:
     * - permutation �迭�� k��° ���� ����� �����
     */
    static void findPermutation(int size, long k) {
        // �� �ڸ�(position i)�� �� ���� ����
        for (int i = 1; i <= size; i++) {
            long count = 0; // ������ �� �� ���� ������ ���� ���� ��ġ�� ���� ���� ����

            for (int j = 1; j <= size; j++) {
                if (visited[j]) continue; // �̹� ���� ���� �ǳʶ�

                // (size - i)! ��ŭ�� ����� ���� �� ���ڸ��� �ݺ���
                // �� ���� �ȿ� ���� k�� ���´ٸ� j�� ���� ��ġ(i)�� ����
                if (k <= factorial[size - i] * (count + 1)) {
                    permutation[i] = j;       // ���� �ڸ�(i)�� j�� ����
                    visited[j] = true;       // �ش� ���� ���� ���Ǿ���
                    k -= factorial[size - i] * count; // ���� k�� ����
                    break;
                }
                count++; // ���� �ĺ� ���ڷ� �̵�
            }
        }
    }

    /*
     * ���� �Էµ� ������ ���� ������ �� ��° �������� ���ϴ� �Լ�
     *
     * �Է�:
     * - size : ������ ũ�� (��� ����)
     * - st   : ������ ��� StringTokenizer (�������� �и��� ���ڵ�)
     *
     * ���:
     * - �ش� ������ ���� ������ �� ��° �������� ��ȯ (1-based index)
     */
    static long findK(int size, StringTokenizer st) {
        long k = 1; // 1���� ���� (1��° ����)

        for (int i = 1; i <= size; i++) {
            // i��° ���ڸ� �Է¹޾� permutation �迭�� ����
            permutation[i] = Integer.parseInt(st.nextToken());
            long count = 0; // permutation[i]���� ���� �� �� ������ ���� �� ����

            // permutation[i]���� ���� �� �� ���� �湮���� ���� �� ���� ���
            for (int j = 1; j < permutation[i]; j++) {
                if (!visited[j]) {
                    count++;
                }
            }

            // count���� ���� i��° �ڸ��� �� �� �־����Ƿ�, �׸�ŭ ���� �� ����
            k += count * factorial[size - i];

            // ���� ���� ��� ó��
            visited[permutation[i]] = true;
        }

        return k; // ���������� ���� ���� ��ȣ ��ȯ
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());

        factorial = new long[size+1];
        permutation = new int[size+1];
        visited = new boolean[size+1];

        // ����� �� �ʱ�ȭ
        factorial[0] = 1;
        for(int i = 1; i <= size; i++){
            factorial[i] = factorial[i-1] * i;
        }

        st = new StringTokenizer(br.readLine());

        /*
         * query : ����ڷκ��� �Է¹޴� ���� ����
         * - 1�� ���: k��° ������ ���Ͽ� �����
         * - 2�� ���: �־��� ������ ���� ������ �� ��°���� ����Ͽ� �����
         */
        int query = Integer.parseInt(st.nextToken());
        if(query == 1){
            long k = Long.parseLong(st.nextToken());
            findPermutation(size, k);
            for(int i = 1; i <= size; i++){
                bw.write(permutation[i] + " ");
            }
            bw.flush();
        }
        else if(query == 2){
            long k = findK(size, st);
            System.out.println(k);
        }
    }
}