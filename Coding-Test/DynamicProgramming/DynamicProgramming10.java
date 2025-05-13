import java.io.*;
import java.util.*;

public class DynamicProgramming10 {
    /*
     * 최초 작성일시 : 2025-05-13
     * 최초 작성시간 : 22:02
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2342
     * 문제 이름 : Dance Dance Revolution
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    /*
     * 문제 설명:
     *
     * DDR(Dance Dance Revolution) 기계는 네 방향(1~4)의 패널이 있고,
     * 사용자는 두 발을 이용하여 지시에 따라 해당 방향을 밟는다.
     *
     * 지시 입력은 1~4의 수로 주어지며, 0이 입력되면 종료를 의미한다.
     * 두 발은 동시에 같은 위치에 올 수 없다.
     * 초기에는 양발이 중앙(0)에 위치한다.
     *
     * 각 발을 이동시키는 데 드는 에너지는 다음과 같다:
     * - 같은 위치를 연속으로 밟을 경우: 1
     * - 중앙(0) → 방향 이동: 2
     * - 인접한 방향 이동: 3
     * - 반대 방향 이동: 4
     *
     * 주어진 입력에 따라 지시를 순서대로 밟을 때, 발의 움직임을 잘 조절하여
     * **필요한 최소 에너지 소비량**을 구하는 문제이다.
     *
     * 예제 입력:
     * 1 2 2 4 0
     * 출력:
     * 8
     *
     * 제한:
     * - 지시 수는 최대 100,000개
     * - 시간 제한: 2초
     */

    // dp[step][left][right] = step번째 명령까지 수행한 상태에서
    // 왼발이 left, 오른발이 right에 있을 때의 최소 에너지
    static int[][][] dp = new int[100001][5][5];

    // energy[i][j] = i 위치에서 j 위치로 이동할 때 드는 에너지
    static final int[][] energy = {
            { 0, 2, 2, 2, 2 },  // 0번(중앙)에서 각 방향으로
            { 2, 1, 3, 4, 3 },  // 1번에서 각 방향으로
            { 2, 3, 1, 3, 4 },  // 2번에서 각 방향으로
            { 2, 4, 3, 1, 3 },  // 3번에서 각 방향으로
            { 2, 3, 4, 3, 1 }   // 4번에서 각 방향으로
    };

    // dp 배열 초기화 (큰 값으로 초기화하여 최소값 비교 가능하게)
    static void init() {
        for (int k = 0; k < 100001; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[k][i][j] = 100001 * 4; // 큰 값으로 초기화
                }
            }
        }
    }

    // 입력 지시를 읽고 DP 테이블을 채우는 함수
    static int mamoize(StringTokenizer st) {
        dp[0][0][0] = 0; // 초기 상태: 양발이 중앙(0)에 있을 때 에너지 0
        int step = 1;

        while (true) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) break; // 0이 나오면 입력 종료

            // 왼발 고정, 오른발 이동
            for (int i = 0; i < 5; i++) {
                if (num == i) continue; // 양발이 같은 위치에 있을 수 없음
                for (int j = 0; j < 5; j++) {
                    dp[step][i][num] = Math.min(
                            dp[step][i][num],
                            dp[step - 1][i][j] + energy[j][num]
                    );
                }
            }

            // 오른발 고정, 왼발 이동
            for (int i = 0; i < 5; i++) {
                if (num == i) continue;
                for (int j = 0; j < 5; j++) {
                    dp[step][num][i] = Math.min(
                            dp[step][num][i],
                            dp[step - 1][j][i] + energy[j][num]
                    );
                }
            }

            step++; // 다음 스텝으로 이동
        }

        return step - 1; // 마지막 스텝 번호 반환
    }

    // 최소 에너지를 구하는 함수
    static int excute(StringTokenizer st) {
        int step = mamoize(st); // DP 수행 후
        int min = Integer.MAX_VALUE;

        // 마지막 단계에서 가능한 모든 위치 조합에 대해 최소값 계산
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[step][i][j]);
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(); // DP 배열 초기화

        int result = excute(new StringTokenizer(br.readLine())); // 입력 처리 및 계산

        System.out.println(result); // 최소 에너지 출력
    }
}
