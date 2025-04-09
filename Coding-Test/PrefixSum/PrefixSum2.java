import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum2 {
    /* 
    * 작성일시 : 2025-02-10
    * 작성시간 : 19: 42
    * 작성자 : 장성환
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
         * size : n * n 크기의 2차원 배열을 만들 때 사용할 n값 저장
         * query : 구간 합 질의를 할 횟수
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        /*
         * 이 문제는 구간 합 구하기 4(11659번 문제)에서 사용한 1차원 배열을 2차원 배열로 확장한 문제이다.
         * 
         * 2차원 배열에서 구간 합을 구할 때에는 행과 열을 고려해서 합 배열을 만든 뒤에 구간 합을 계산한다.
         * 
         * 이 문제에서 합 배열을 만들지 않고 구간 합을 계산하면 질의 하나 당 
         * 
         * 이중 for 문으로 원본 배열의 요소들을 접근해서 계산해야 하기 때문에 Ο(n^2)이라는 시간 복잡도가 나와서 1초안에 이 문제를 풀 수 없다.
         * 
         * 하지만 합 배열을 한 번 만들고 구간 합을 계산하면 구간 합 공식만 적용하면 되고 합 배열의 특정 위치의 요소에 접근하는데 드는 시간 복잡도는 Ο(1) 이기 때문에
         * 
         * Ο(1)이라는 시간 복잡도로 구간 합을 구할 수 있다.
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
         * 원본 배열 A에서 합 배열 S를 만들 때 사용하는 공식은 다음과 같다.
         * 
         * S[i][j] = S[i-1][j] + S[i][j-1] + A[i][j] - S[i-1][j-1]
         * 
         * S[i-1][j-1]을 빼는 이유는 S[i-1][j]와 S[i][j-1]를 A[i][j]에 더할 때 S[i-1][j-1]가 두 번 더해지기 때문이다.
         */
        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] + datas[i][j] - sum[i-1][j-1];
            }
        }

        /*
         * 합 배열 S에서 (x1, y1)부터 (x2, y2)까지의 구간 합을 구할 때에는 다음 공식을 사용한다.
         * 
         * S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1]
         * 
         * S[x1-1][y1-1]와 S[x2][y1-1]은 (x1, y1)부터 (x2, y2)까지의 구간 바깥의 합이다.
         * 
         * S[x1-1][y1-1]을 더하는 이유는 S[x2][y2] - S[x1-1][y1-1] - S[x2][y1-1] 연산을 할 때, S[x1-1][y1-1]가 두 번 뺄셈한 결과가 나오기 떄문이다. 
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
