import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum3 {
    /* 
    * 작성일시 : 2025-02-10
    * 작성시간 : 20:93
    * 작성자 : 정성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 10986
    * 문제 이름 : 나머지 합
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
         * size : 나머지 합에 사용될 요소의 개수
         * remider : 나머지 계산에 사용될 피연산자 값
         */
        int size = Integer.parseInt(st.nextToken());
        int reminder = Integer.parseInt(st.nextToken());

        /*
         * counter 배열 : 각 요소별로 나머지 계산을 하고 나온 값(범위 : 0 ~ reminder - 1)을 카운트
         * sum 배열 : 합 배열
         * answer : 나누어 떨어지는 수의 개수
         */
        long [] counter = new long[reminder];
        long [] sum = new long[size];
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        sum[0] = Long.parseLong(st.nextToken());
        /*
         * 합 배열 형성
         */
        for(int i = 1; i < size; i++){
            sum[i] = sum[i-1] + Long.parseLong(st.nextToken());
        }

        /*
         * 과정
         * 1. 합 배열의 각 요소의 나머지 값을 구한다.
         * 2. 각 요소의 나머지 값이 0이면 answer에 1을 더한다.
         * 3. 나머지 계산하고 나온 값으로 counter 배열의 특정 인덱스의 값에 1을 더한다.
         * 
         * 이 과정은 첫 번째 요소부터 i(i < size)번째 요소까지의 합에서 reminder로 나누어 떨어지는 경우를 찾기 위한 과정이다.
         */
        for(int i = 0; i < size; i++){
            int rmd = (int) sum[i] % reminder;

            if(rmd == 0){
                answer++;
            }

            counter[rmd]++;
        }

        /*
         * 이 과정은 특정 구간의 합이 reminder로 나누어 떨어지는 경우를 찾기 위한 과정이다.
         * 
         * 조합 nC2를 사용하는 이요는 합 배열에서 나머지가 같은 요소 두 개를 뽑고 이 두 요소를 빼면(큰 값에서 작은 값을 빼기) 나머지가 0인 어떤 값이 나오기 때문이다.
         */
        for(int i = 0; i < counter.length; i++){
            if(counter[i] > 1){
                answer += (counter[i] * (counter[i] - 1)) / 2;
            }
        }

        System.out.println(answer);

    }
}
