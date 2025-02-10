import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum1 {
    /* 
    * 최초 작성일시 : 2025-02-07
    * 최초 작성시간 : 21:53
    * 작성자 : 장성환
    * 
    * 마지막 작성일시 : 2025-02-10
    * 마지막 작성시간 : 20:44
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11659
    * 문제 이름 : 구간 합 구하기 4
    * 문제 난이도 : 실버 Ⅳ
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
         * size : 구간 합을 구하는데 사용할 요소의 개수
         * query : 구간 합의 질의의 개수
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        /*
         * datas 배열은 각 요소를 저장하면서 동시에 합 배열로 만드는데 사용할 것이다. 
         * 
         * 합 배열에서 구간 합을 구하는 공식은 S[j] - S[i-1]이다. 따라서 i-1은 0과 같거나 커야 한다(i-1이 배열의 인덱스 범위를 초과하면 안되기 때문)
         */
        int [] datas = new int[size+1];
        /*
         * 아래의 for문에서는 합 배열을 만드는 과정이다.
         * 
         * 원본 배열을 만들고 합 배열을 만들어도 시간 복잡도는 Ο(n)이기에 어떤 방식으로 처리해도 시간 초과는 되지 않는다.
         */
        for(int i = 1; i < datas.length; i++){
            datas[i] = Integer.parseInt(st.nextToken()) + datas[i-1];
        }

        /*
         * 아래의 for문에서 구간 합을 구하고 있다.
         * 
         * start는 구간에서 첫 번째 요소, end는 구간에서 마지막 요소이다.
         * 
         * bw(BufferedWriter)를 쓴 이유는 각 질의로 구한 구간 합을 저장해서 한 번에 출력하기 위함이라는 이유도 있지만
         * 
         * System.out에 속한 print 함수보다 리소스를 적게 먹는다.
         * 
         * BufferedReader와 BufferedWriter에 대해서는 따로 다뤄볼 예정이다.
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
