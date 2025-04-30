import java.io.*;
import java.util.*;

public class DynamicProgramming8 {
    /*
     * 최초 작성일시 : 2025-04-30
     * 최초 작성시간 : 17:58
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1915
     * 문제 이름 : 가장 큰 정사각형
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    /*
     * 문제 설명:
     * 주어진 0과 1로 이루어진 이진 행렬에서, 가장 큰 정사각형을 찾아 그 크기를 출력하는 문제입니다.
     * 이 문제는 동적 계획법(DP)을 이용해 해결할 수 있습니다.
     *
     * 입력:
     * 첫 번째 줄에 행렬의 크기인 두 정수 N, M이 주어지고,
     * 두 번째 줄부터 N개의 줄에 걸쳐 각 행렬의 각 값이 주어집니다. (0 또는 1)
     *
     * 출력:
     * 가장 큰 정사각형의 넓이를 출력합니다.
     *
     * 예시:
     * 입력:
     * 3 4
     * 1010
     * 1111
     * 1111
     * 출력:
     * 4
     *
     * 설명:
     * 주어진 행렬에서 가장 큰 정사각형은 2x2 크기이며, 그 넓이는 4입니다.
     *
     */

    // DP 테이블을 저장할 2D 배열 선언
    static int[][] dp;

    /*
     * 주어진 행렬에서 가장 큰 정사각형의 크기를 구하는 함수
     * dp[i][j]는 (i, j) 지점을 오른쪽 아래 모서리로 갖는 정사각형의 크기를 저장
     *
     * row : 행렬의 행 수
     * col : 행렬의 열 수
     *
     * DP 계산 방식:
     * - dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     *   (위, 왼쪽, 대각선 왼쪽 위 셀의 값 중 최소값에 1을 더함)
     * - 만약 dp[i][j] == 1이라면 해당 위치를 끝으로 하는 정사각형을 만들 수 있음
     * - 가장 큰 정사각형의 크기를 구한 후, 그 넓이를 리턴
     */
    static int memoize(int row, int col) {
        int max = 0;
        // 행렬을 순차적으로 탐색하면서 dp 테이블을 업데이트
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 현재 위치가 1이면, 이전 값들을 참조하여 정사각형의 크기 업데이트
                if (dp[i][j] == 1 && i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                // 가장 큰 정사각형의 크기를 추적
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }
        // 가장 큰 정사각형의 한 변의 길이를 반환
        return max;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader와 StringTokenizer를 이용해 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행렬의 크기 입력
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // DP 테이블 초기화
        dp = new int[1001][1001];

        // 행렬의 각 행을 읽어서 dp 테이블에 저장 (0과 1로 이루어진 이진 행렬)
        for (int i = 0; i < row; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < temp.length; j++) {
                dp[i][j] = temp[j] - '0'; // char '0'을 숫자 0으로, '1'을 숫자 1로 변환
            }
        }

        // 가장 큰 정사각형의 크기 계산
        int max = memoize(row, col);

        // 가장 큰 정사각형의 넓이 출력
        System.out.println((int) Math.pow(max, 2)); // 가장 큰 정사각형의 크기를 제곱하여 넓이를 출력
    }
}