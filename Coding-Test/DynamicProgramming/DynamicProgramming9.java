import java.io.*;
import java.util.*;

public class DynamicProgramming9 {
    /*
     * 최초 작성일시 : 2025-05-01
     * 최초 작성시간 : 12:05
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1328
     * 문제 이름 : 고층 빌딩
     * 문제 난이도 : 플레티넘 5
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    /* 문제 설명:
     * 고층 빌딩 문제는 주어진 높이의 빌딩에서 왼쪽과 오른쪽에서 각각
     * 제한된 개수만큼 작은 빌딩을 배치하여 빌딩을 쌓는 방법을 구하는 문제입니다.
     * 각 빌딩은 왼쪽이나 오른쪽에 놓일 때 쌓을 수 있는 제한이 있으며, 이 제한을
     * 만족하는 경우의 수를 구하는 문제입니다.
     *
     * 이 문제는 동적 계획법(DP)을 사용하여 해결할 수 있습니다. DP 배열을
     * 활용해 이전의 계산된 결과를 저장하고 이를 바탕으로 점화식을 만들어
     * 문제를 해결합니다.
     */

    // 3차원 DP 배열 선언 (size x left x right)
    // dp[i][j][k]는 크기 i인 빌딩에서 왼쪽에 j개의 작은 빌딩과 오른쪽에 k개의 작은 빌딩을 배치하는 경우의 수
    static long [][][] dp = new long[101][101][101];

    // 나머지 연산을 위한 상수값 (1000000007)
    static long mod = 1000000007L;

    // DP 계산을 수행하는 메모이제이션 함수
    static void memoize(int size, int left, int right){
        // 초기값 설정 (1개 빌딩일 때는 1가지 방법)
        dp[1][1][1] = 1;

        // DP 배열 채우기
        // size는 빌딩의 크기, left는 왼쪽 작은 빌딩 수, right는 오른쪽 작은 빌딩 수
        for(int i = 2; i <= size; i++){
            for(int j = 1; j <= left; j++){
                for(int k = 1; k <= right; k++){
                    // dp[i][j][k] 값 계산
                    // 점화식:
                    // dp[i-1][j][k] * (i-2): 이전 크기에서 i-2개의 빌딩이 중앙에 올 때
                    // dp[i-1][j-1][k]: 왼쪽에 하나의 작은 빌딩을 추가한 경우
                    // dp[i-1][j][k-1]: 오른쪽에 하나의 작은 빌딩을 추가한 경우
                    dp[i][j][k] = (dp[i-1][j][k] * (i-2) + dp[i-1][j-1][k] + dp[i-1][j][k-1]) % mod;
                }
            }
        }
    }

    // 메인 함수
    public static void main(String [] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 값 파싱
        int size = Integer.parseInt(st.nextToken());  // 빌딩의 크기
        int left = Integer.parseInt(st.nextToken());  // 왼쪽에 놓을 수 있는 작은 빌딩의 개수
        int right = Integer.parseInt(st.nextToken()); // 오른쪽에 놓을 수 있는 작은 빌딩의 개수

        // 메모이제이션을 수행하여 DP 배열 계산
        memoize(size, left, right);

        // 결과 출력 (크기 size인 빌딩에서 왼쪽에 left개의 작은 빌딩과 오른쪽에 right개의 작은 빌딩을 배치하는 경우의 수)
        System.out.println(dp[size][left][right]);
    }
}