import java.io.*;
import java.util.*;

public class DynamicProgramming4 {
    /*
     * 최초 작성일시 : 2025-04-24
     * 최초 작성시간 : 15:15
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11726
     * 문제 이름 : 2×n 타일링
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     *
     * 문제 설명:
     * - 2×1 크기의 타일을 가지고 2×n 크기의 직사각형을 채우는 방법의 수를 구하는 문제
     * - 타일은 세로로 1개 또는 가로로 2개를 이어 붙이는 형태로 사용 가능
     * - 채우는 방법의 수를 10,007로 나눈 나머지를 출력해야 함
     *
     * 예시:
     *  - n = 1: 한 칸을 세로로 타일 하나 => 1가지
     *  - n = 2: 세로 1 + 세로 1 또는 가로 2 => 2가지
     *  - n = 3: [세로+세로+세로], [가로+세로], [세로+가로] => 3가지
     *
     * 점화식:
     *  - dp[n] = dp[n - 1] + dp[n - 2]
     *    (마지막에 세로 타일을 붙이는 경우 + 가로 타일 두 개를 붙이는 경우)
     */

    // dp[i] : 2×i 크기의 직사각형을 타일로 채우는 방법의 수
    static long[] dp;

    // dp 배열 초기화
    static void init(int size){
        dp = new long[size + 1];
        dp[1] = 1;
        if (size >= 2) {
            dp[2] = 2;
        }
    }

    // 점화식으로 dp 배열 채우기
    static void mamoize(int size){
        for(int i = 3; i <= size; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007; // 결과는 10007로 나눈 나머지
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt(); // 2×n 직사각형에서 n 입력

        init(size);        // 초기화
        mamoize(size);     // DP로 결과 계산

        System.out.println(dp[size]); // 결과 출력
    }
}