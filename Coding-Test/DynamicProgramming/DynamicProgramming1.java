import java.io.*;
import java.util.*;

public class DynamicProgramming1 {
    /*
     * 최초 작성일시 : 2025-04-21
     * 최초 작성시간 : 14:03
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1463
     * 문제 이름 : 1로 만들기
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    // 동적 프로그래밍을 위한 배열
    static int [] dp;

    // 메모이제이션(Memoization)을 이용한 최소 연산 횟수 계산 함수
    static void mamoize(int[] dp) {
        /*
         * dp[1] = 0
         * 숫자 1은 이미 1이므로 연산이 필요 없다.
         */
        dp[1] = 0;

        /*
         * 2부터 n까지의 모든 정수에 대해,
         * 해당 숫자를 1로 만들기 위한 최소 연산 횟수를 구함
         *
         * 가능한 연산:
         * 1. x → x - 1
         * 2. x → x / 2 (단, x가 2로 나누어 떨어질 때만)
         * 3. x → x / 3 (단, x가 3으로 나누어 떨어질 때만)
         */
        for (int i = 2; i < dp.length; i++) {
            // 기본적으로 -1 연산을 수행한 경우의 값으로 초기화
            dp[i] = dp[i - 1] + 1;

            // 2로 나누어 떨어지는 경우: i / 2에서 오는 연산 경로 고려
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // 3으로 나누어 떨어지는 경우: i / 3에서 오는 연산 경로 고려
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        dp = new int[size+1];

        mamoize(dp);

        System.out.println(dp[size]);
    }

}