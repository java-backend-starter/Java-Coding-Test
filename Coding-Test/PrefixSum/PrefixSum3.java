import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum3 {
    /* 
    * 작성일시 : 2025-02-10
    * 작성시간 : 20: 53
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 10986
    * 문제 이름 : 나머지 합 구하기
    * 문제 난이도 : 골드 Ⅲ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 나머지 합을 구할 때 사용할 요소의 개수
         * remider : 나머지 연산에서 사용할 피연산자
         */
        int size = Integer.parseInt(st.nextToken());
        int reminder = Integer.parseInt(st.nextToken());

        /*
         * counter 배열 : 이 배열의 인덱스는 나머지, 인덱스로 접근해서 얻은 값은 나머지 계산해서 인덱스가 나온 요소의 개수를 의미한다.
         * sum 배열 : 첫 번째 요소부터 특정 요소까지의 합을 저장한 배열
         * answer : reminder로 나누어 떨어지는 구간 합의 개수를 저장한다.
         */
        long [] counter = new long[reminder];
        long [] sum = new long[size];
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        sum[0] = Long.parseLong(st.nextToken());
        /*
         * sum 배열에는 구간 합을 계산한다.
         */
        for(int i = 1; i < size; i++){
            sum[i] = sum[i-1] + Long.parseLong(st.nextToken());
        }

        /*
         * 이 for문은 첫 번째 요소부터 i번째 요소까지의 구간 합인 누적 합에서 reminder로 나누어 떨어지는 수의 개수를 저장한다.
         */
        for(int i = 0; i < size; i++){
            int rmd = (int) sum[i] % reminder;

            if(rmd == 0){
                answer++;
            }

            counter[rmd]++;
        }

        /*
         * 이 for문은 누적 합이 아닌 구간 합에서 reminder로 나누어 떨어지는 수의 개수를 구한다.
         * 
         * 나머지가 같은 두 개의 누적 합으로 만들어진 구간 합은 reminder로 나누어 떨어지는 수이다.
         * 
         * 따라서 counter[i] > 1은 나머지가 같은 2개 이상의 누적 합들을 찾기 위한 조건이다.
         * 
         * counter와 answer가 long 타입인 이유는 (counter[i] * (counter[i] - 1)) / 2가 int의 범위를 넘어갈 수 있기 때문에 오버플로우를 방지하기 위함이다.
         */
        for(int i = 0; i < counter.length; i++){
            if(counter[i] > 1){
                answer += (counter[i] * (counter[i] - 1)) / 2;
            }
        }

        System.out.println(answer);

    }
}
