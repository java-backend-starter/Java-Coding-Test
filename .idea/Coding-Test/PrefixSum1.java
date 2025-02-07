import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum1 {
    /* 
    * 최초 작성일시 : 2025-02-07
    * 최초 작성시간 : 21:53
    * 최초 작성자 : 정성환
    * 
    * 마지막 작성일시 : 
    * 마지막 작성시간 : 
    * 마지막 작성자 : 
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11659
    * 문제 이름 : 구간 합 구하기 4
    * 문제 난이도 : 실버 Ⅲ
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
         * size : 입력 받을 데이터의 개수
         * query : 질의 개수
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        /*
         * datas 배열의 크기를 입력 받을 데이터의 개수보다 1이 많은 이유는
         * 첫 번째 데이터부터 임의의 위치의 데이터까지의 구간 합을 구할 때
         * 구간 합을 구하는 공식인 S[j] - S[i-1]을 사용하면 인덱스 범위를 넘기 때문이다(i-1이 음수가 되면 안되기 때문)
         */
        int [] datas = new int[size+1];
        /*
         * 아래의 for 문은 합 배열에 데이터를 넣으면서 이전에 구한 값(첫 번째 데이터부터 i-1번째 데이터까지의 합)을 더한다.
         * 그렇기에 아래의 for 문으로 원본 배열을 만들지 않고 바로 합 배열을 만드는 것이다.
         */
        for(int i = 1; i < datas.length; i++){
            datas[i] = Integer.parseInt(st.nextToken()) + datas[i-1];
        }

        /*
         * 아래의 for문은 구간 합을 구하는 과정이다.
         * start는 구간의 시작, end는 구간의 마지막이다.
         * bw(BufferedWriter)를 쓴 이유는 모든 질의에 대한 답을 구한 다음에 한 번에 출력할 수 있고
         * System.out에 있는 print 함수보다 리소스를 적게 먹기 때문에 사용했다.
         * 
         * 이와 같은 원리로 BufferedReader도 Java.util에 있는 Scanner보다 리소스를 적게 먹는다.
         * 
         * BufferedReader와 BufferedWriter에 대해서는 따로 정리할 예정이다.
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
