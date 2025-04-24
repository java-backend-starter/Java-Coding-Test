import java.io.*;
import java.util.*;

public class DynamicProgramming3 {
    /*
     * 최초 작성일시 : 2025-04-24
     * 최초 작성시간 : 14:49
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2193
     * 문제 이름 : 이친수
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     *
     * 문제 설명:
     * - 이친수는 특별한 조건을 만족하는 이진수이다.
     *   1. 이친수는 0으로 시작하지 않는다.
     *   2. 이친수에는 1이 두 번 연속으로 나타나지 않는다.
     *
     *   예시:
     *     - 1: 이친수 (1자리)
     *     - 10: 이친수 (2자리)
     *     - 100, 101: 이친수 (3자리)
     *     - 1000, 1001, 1010: 이친수 (4자리)
     *     - 10000, 10001, 10010, 10100, 10101: 이친수 (5자리)
     *
     * 목표: N자리 이친수의 개수를 구하는 문제
     */

    // dp[i][0] : i자리 이친수 중 마지막 자리가 0인 수의 개수
    // dp[i][1] : i자리 이친수 중 마지막 자리가 1인 수의 개수
    static long[][] dp;

    // dp 배열 초기화
    static void init(int size) {
        dp = new long[size + 1][2];
        dp[1][0] = 0; // 1자리 이친수 중 마지막이 0인 수는 없음
        dp[1][1] = 1; // 1자리 이친수는 1 하나뿐
    }

    // 점화식을 이용한 dp 배열 채우기
    static void mamoize(int size) {
        for (int i = 2; i <= size; i++) {
            // 마지막 자리가 0인 경우: 이전 자리가 0이거나 1일 수 있음
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            // 마지막 자리가 1인 경우: 이전 자리에는 무조건 0만 올 수 있음
            dp[i][1] = dp[i - 1][0];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt(); // N자리 이친수를 만들기 위한 입력

        init(size);        // 초기값 설정
        mamoize(size);     // DP로 이친수 개수 계산

        // N자리 이친수의 총 개수 = 마지막 자리가 0인 경우 + 1인 경우
        long result = dp[size][0] + dp[size][1];
        System.out.println(result);
    }
}
