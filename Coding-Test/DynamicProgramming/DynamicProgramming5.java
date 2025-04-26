import java.io.*;
import java.util.*;

public class DynamicProgramming5 {
    /*
     * 최초 작성일시 : 2025-04-26
     * 최초 작성시간 : 14:24
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 10844
     * 문제 이름 : 쉬운 계단수
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     *
     */
    /*
     * 문제 설명 :
     * 계단 수란 인접한 모든 자리의 차이가 1인 수를 말한다.
     * 예) 45656, 123, 210 등
     * 단, 0으로 시작하는 수는 계단수가 아니다.
     *
     * 해결 전략 :
     * 동적 프로그래밍을 이용하여 자릿수 N일 때 가능한 계단 수의 개수를 구한다.
     *
     * 점화식:
     * dp[i][j] = 길이가 i이고, 마지막 숫자가 j인 계단 수의 개수
     *
     * 점화식 구성:
     * - j = 0: dp[i][0] = dp[i-1][1]
     *   (0 앞에는 1만 올 수 있으므로)
     *
     * - j = 9: dp[i][9] = dp[i-1][8]
     *   (9 앞에는 8만 올 수 있으므로)
     *
     * - j = 1~8: dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
     *   (j 앞에는 j-1 또는 j+1이 올 수 있으므로)
     *
     * 초기 조건:
     * dp[1][1~9] = 1 (1자리 수는 0을 제외하고 1개씩 존재)
     * dp[1][0] = 0 (0으로 시작하는 수는 허용되지 않음)
     */
    // 나머지 연산에 사용할 상수값 (결과가 매우 커질 수 있으므로)
    static long mod = 1000000000;

    // dp[i][j] : 길이가 i이고 마지막 숫자가 j인 계단 수의 개수
    static long [][] dp;

    // DP 배열을 초기화하는 메서드
    static void init(int size){
        dp = new long[size+1][11]; // 인덱스를 0~10까지 사용할 수 있게 선언
        // 1자리 수 초기화 (1~9만 유효, 0은 제외)
        for(int i = 1; i < 10; i++){
            dp[1][i] = 1;
        }
    }

    // 점화식을 바탕으로 DP 테이블을 채우는 메서드
    static void mamoize(int size){
        for(int i = 2; i <= size; i++){
            // 마지막 숫자가 0인 경우, 이전 자리수의 마지막 숫자가 1인 경우만 가능
            dp[i][0] = dp[i-1][1];

            // 마지막 숫자가 9인 경우, 이전 자리수의 마지막 숫자가 8인 경우만 가능
            dp[i][9] = dp[i-1][8];

            // 마지막 숫자가 1~8인 경우, 이전 자리수의 (j-1) 또는 (j+1)에서 가능
            for(int j = 1; j <= 8; j++){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
            }
        }
    }

    // 마지막 자리수가 0~9인 모든 경우의 수를 더한 후 반환
    static long sum(int size){
        long sum = 0;
        for(int i = 0; i < 10; i++){
            sum = (sum + dp[size][i]) % mod;
        }
        return sum;
    }

    public static void main(String [] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // size 자릿수 입력
        int size = sc.nextInt();

        // 초기화 및 점화식 적용
        init(size);
        mamoize(size);

        // 결과 출력
        long result = sum(size);
        System.out.println(result);
    }
}
