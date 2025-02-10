import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum2 {
    /* 
    * 최초 작성일시 : 2025-02-10
    * 최초 작성시간 : 19:42
    * 최초 작성자 : 정성환
    * 
    * 마지막 작성일시 : 
    * 마지막 작성시간 : 
    * 마지막 작성자 : 
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11660
    * 문제 이름 : 구간 합 구하기 5
    * 문제 난이도 : 실버 Ⅰ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : n * n 크기의 2차원 배열에서 n 값
         * query : 구간 합의 질의의 개수
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        /*
         * 해당 문제는 백준의 11659번 문제인 구간 합 구하기 4를 2차원 배열로 확장한 것이다.
         * 
         * 따라서 구간 합 구하기 5는 행과 열이라는 두 방향을 고려해서 계산식을 세워야 한다.
         * 
         * 이 문제에서는 첫 번째 요소가 있는 (1, 1)에서 (i, j)(1 <= i <= n, 1 <= j <= n)까지의 합 배열을 만든 다음에
         * 
         * (i1, j1)(i1 <= n, j1 <= n)부터 (i2, j2)(i1 <= i2 <= n, j1 <= j2 <= n)까지의 구간 합을 계산한다.
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
         * 합 배열을 만드는 이유는 매 질의마다 배열 안에 있는 요소들을 각각 접근해서 구간 합을 만드는 것은 비효율적이기 때문이다.
         * 
         * 백준의 11659번 문제는 1차원 배열에 있는 요소들에 대한 구간 합을 합 배열 없이 구하면 Ο(n)이 들지만
         * 
         * 이 문제는 2차원 배열에 있는 요소들에 대한 구간 합을 구해야 하기 때문에 합 배열 없이 계산하면
         * 
         * 매 질의마다 Ο(n^2)가 들기 때문에 1초 안에 이 문제를 풀 수 없다.
         * 
         * Ο(n^2)라는 시간 복잡도가 드는 이유는 최악의 경우는 2차원 배열의 모든 요소를 다 접근하는 것이기 때문이다.
         * 
         * 따라서 합 배열을 한 번 만들어두고 매 질의를 처리하면 효율적으로 이 문제의 답을 구할 수 있다.
         * 
         * 
         * (1, 1)부터 (i, j)(1 <= i <= n, 1 <= j <= n)까지의 합을 구하는 공식은
         * 
         * (원본 배열의 (i, j)번째 요소) + (합 배열의 (i-1, j)번째 값) + (합 배열의 (i, j-1)번째 값) - (합배열의 (i-1, j-1)번째 값)
         * 
         * 이다. 합배열의 (i-1, j-1)번째 값을 빼는 이유는 합배열의 (i-1, j)번째 값과 합배열의 (i, j-1)번째 값 모두
         * 
         * 합배열의 (i-1, j-1)번째 값이 포함되어 있기에 중복된 값을 빼주는 것이다.
         */
        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] + datas[i][j] - sum[i-1][j-1];
            }
        }

        /*
         * 이 for문은 구간 합을 구하는 for문이다.
         * 
         * 2차원 배열에서 구간 합을 구하는 공식은 
         * 
         * (합 배열의 (i2, j2)번째 값) - (합 배열의 (i1-1, j2)번째 값) - (합 배열의 (i2, j1-1)번째 값) + (합 배열의 (i1 - 1, j1 - 1)번째 값)
         * 
         * 이다. (합 배열의 (i1-1, j2)번째 값)과 (합 배열의 (i2, j1-1)번째 값)을 빼는 이유는 (i1, j1)부터 (i2, j2) 구간에 포함되지 않는 구간이기 때문이고
         * 
         * (합 배열의 (i1 - 1, j1 - 1)번째 값)을 더하는 이유는 (합 배열의 (i1-1, j2)번째 값)과 (합 배열의 (i2, j1-1)번째 값)을 빼는 과정에서 
         * 
         * (합 배열의 (i1 - 1, j1 - 1)번째 값)을 두 번 빼기 때문에 이 값을 보정하기 위함이다.
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
