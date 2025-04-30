import java.io.*;
import java.util.*;

public class DynamicProgramming7 {
    /*
     * 최초 작성일시 : 2025-04-30
     * 최초 작성시간 : 17:28
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 9252
     * 문제 이름 : LCS 2
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    /*
     * 문제 설명:
     * 두 문자열 A와 B가 주어졌을 때, 이들의 최장 공통 부분 수열(LCS)을 구하는 문제입니다.
     * LCS는 두 문자열에서 순서대로 등장하는 공통된 문자의 최대 부분 수열을 의미합니다.
     * 예를 들어, 문자열 "ABCBDAB"와 "BDCAB"에 대해 LCS는 "BCAB"입니다.
     * 이 문제에서는 LCS의 길이와 함께 LCS 문자열을 구하는 것이 목표입니다.
     *
     * 입력:
     * 두 줄에 걸쳐 각각 A와 B 문자열이 주어집니다. 두 문자열의 길이는 최대 1000입니다.
     *
     * 출력:
     * 첫 번째 줄에는 LCS의 길이를 출력하고, 두 번째 줄에는 LCS 문자열을 출력합니다.
     * LCS가 여러 개일 경우 사전순으로 가장 앞서는 것을 출력합니다.
     *
     * 예시:
     * 입력:
     * ABCBDAB
     * BDCAB
     * 출력:
     * 4
     * BCAB
     *
     * 위 예시에서 LCS는 "BCAB"로 길이는 4입니다.
     *
     */

    // LCS 경로를 저장할 ArrayList 선언
    static ArrayList<Character> path = new ArrayList<>();

    // 입력 문자열 A와 B를 저장할 배열 선언
    static char[] A;
    static char[] B;

    // DP 테이블을 저장할 2D 배열 선언
    static int[][] dp;

    /*
     * DP 테이블을 초기화하고 LCS의 길이를 계산하는 함수
     * dp[i][j]는 A[0..i-1]과 B[0..j-1]까지의 LCS 길이를 의미한다.
     *
     * row : A 문자열의 길이
     * col : B 문자열의 길이
     */
    static void init(int row, int col) {
        // dp 테이블을 row+1 x col+1 크기로 초기화
        dp = new int[row+1][col+1];

        // 1부터 row까지, 1부터 col까지 순차적으로 dp 값을 계산
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 만약 A[i-1]과 B[j-1]이 같다면, 이전 LCS 값에 1을 더함
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 다르면, 이전 계산 값 중 더 큰 값으로 설정
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    /*
     * dp 테이블을 기반으로 실제 LCS 문자열을 찾아 path에 저장하는 함수
     *
     * row : A 문자열의 인덱스
     * col : B 문자열의 인덱스
     */
    static void LCS(int row, int col) {
        // 만약 한 문자열이 끝에 도달하면 더 이상 찾을 필요가 없음
        if (row == 0 || col == 0) {
            return;
        }

        // A[row-1]과 B[col-1]이 같으면 LCS의 일부로 포함
        if (A[row-1] == B[col-1]) {
            path.add(A[row-1]);
            // 그 후 이전 문자열로 계속해서 LCS를 찾음
            LCS(row-1, col-1);
        } else {
            // A[row-1] != B[col-1]일 경우, 더 긴 LCS를 추적하여 경로를 찾음
            if (dp[row-1][col] > dp[row][col-1]) {
                LCS(row-1, col); // 위쪽에서 더 큰 값을 찾았다면, 위로 이동
            } else {
                LCS(row, col-1); // 왼쪽에서 더 큰 값을 찾았다면, 왼쪽으로 이동
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader와 BufferedWriter를 이용해 입출력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // A와 B 문자열을 입력받아 char 배열로 변환
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        // dp 테이블 초기화 및 LCS 길이 계산
        init(A.length, B.length);

        // 최종 LCS 길이는 dp[A.length][B.length]에 저장됨
        int lcs = dp[A.length][B.length];

        // LCS 경로를 찾음
        LCS(A.length, B.length);

        // LCS 길이 출력
        bw.write(lcs + "\n");

        // 경로에 저장된 LCS 문자열을 역순으로 출력 (LCS는 뒤에서부터 차례로 추가되므로 역순으로 출력)
        for (int i = path.size() - 1; i >= 0; i--) {
            bw.write(path.get(i));
        }
        bw.write("\n");

        // 출력 버퍼를 flush하여 결과를 출력
        bw.flush();
    }
}