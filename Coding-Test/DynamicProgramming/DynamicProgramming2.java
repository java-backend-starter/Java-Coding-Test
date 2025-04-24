import java.io.*;
import java.util.*;

public class DynamicProgramming2 {
    /*
     * 최초 작성일시 : 2025-04-24
     * 최초 작성시간 : 13:51
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 14501
     * 문제 이름 : 퇴사
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     *
     * 문제 설명:
     * - N일 동안 상담 일정을 잡을 수 있을 때, 상담을 적절히 선택하여 얻을 수 있는 최대 수익을 구하는 문제
     * - 각 상담은 기간(T)과 수익(P)이 주어지며, 퇴사 전까지 끝나는 상담만 수행 가능
     *
     * 예시:
     *  - N = 7일이고 각 날마다 상담 시간과 수익이 주어질 때
     *    적절히 상담을 선택하여 최대 수익을 계산
     *
     * 핵심 아이디어:
     * - DP를 사용하여 i일부터 시작했을 때 얻을 수 있는 최대 수익을 dp[i]에 저장
     *
     * 점화식:
     * - (i + time[i] > N + 1) : 상담을 진행할 수 없는 경우 → dp[i] = dp[i + 1]
     * - (i + time[i] ≤ N + 1) : 상담을 진행할 수 있는 경우 → dp[i] = max(dp[i + 1], price[i] + dp[i + time[i]])
     */

    // dp[i] : i일부터 시작했을 때의 최대 수익
    static int[] dp;

    // 각 상담의 소요 시간과 수익
    static int[] time;
    static int[] price;

    // 입력 및 배열 초기화
    static void init(int size, BufferedReader br) throws IOException {
        // dp는 N + 2까지 필요 (N+1은 퇴사일, 항상 수익 0)
        dp = new int[size + 2];
        dp[size + 1] = 0; // 퇴사일에는 상담 불가

        time = new int[size + 1];
        price = new int[size + 1];

        StringTokenizer st;
        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());  // 상담 소요 시간 T
            price[i] = Integer.parseInt(st.nextToken()); // 상담 수익 P
        }
    }

    // DP를 이용한 최대 수익 계산
    static void mamoize(int size) {
        for (int i = size; i > 0; i--) {
            // i일부터 시작하는 상담이 퇴사일까지 끝나지 않으면 상담 불가
            if ((i + time[i]) > size + 1) {
                dp[i] = dp[i + 1]; // 상담을 하지 않고 다음 날 수익 사용
            } else {
                // 상담하지 않는 경우와 상담하는 경우 중 최대값 선택
                dp[i] = Math.max(dp[i + 1], price[i] + dp[i + time[i]]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine()); // 전체 일 수 입력

        init(size, br);     // 배열 및 입력 초기화
        mamoize(size);      // DP로 최대 수익 계산

        System.out.println(dp[1]); // 첫째 날부터 시작했을 때 최대 수익 출력
    }
}