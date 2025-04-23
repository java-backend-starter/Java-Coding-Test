import java.io.*;
import java.util.*;

public class Binomial5 {
    /*
     * 최초 작성일시 : 2025-04-23
     * 최초 작성시간 : 14:05
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1256
     * 문제 이름 : 사전
     * 문제 난이도 : 골드 Ⅱ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    // 최대 조합 수를 저장할 배열 (binomial[n][k]는 nCk를 의미)
    static int [][] binomial = new int[201][201];

    // 조합값을 미리 계산해서 저장하는 함수
    static void init(){
        // 기본 조합값 초기화
        for(int i = 0; i < 201; i++){
            binomial[i][0] = 1;  // nC0 = 1
            binomial[i][1] = i;  // nC1 = n
            binomial[i][i] = 1;  // nCn = 1
        }

        // 점화식을 이용한 나머지 조합값 계산
        for(int i = 2; i < 201; i++){
            for(int j = 1; j < i; j++){
                // nCk = (n-1)Ck + (n-1)C(k-1)
                binomial[i][j] = binomial[i-1][j] + binomial[i-1][j-1];

                // 값이 1,000,000,000을 넘으면 오버플로우 방지를 위해 1,000,000,001로 고정
                if(binomial[i][j] > 1000000000){
                    binomial[i][j] = 1000000001;
                }
            }
        }
    }

    // n개의 'a'와 m개의 'z'로 만들 수 있는 문자열 중 k번째 사전 순 문자열을 반환하는 함수
    static String findString(int n, int m, int k){
        // 만들 수 있는 모든 문자열 개수가 k보다 작으면 -1 반환
        if(binomial[n+m][m] < k){
            return "-1";
        } else {
            StringBuilder sb = new StringBuilder();  // 결과 문자열을 저장할 StringBuilder

            // n과 m이 모두 0이 될 때까지 반복
            while(!(n == 0 && m == 0)){
                /* 현재 문자를 'a'로 선택할 경우, 남은 자리에서 만들 수 있는 문자열의 수는
                 * '현재 a를 하나 넣고', 남은 n-1개의 a와 m개의 z로 만들 수 있는 문자열 수
                 * 즉, 총 남은 자리 수는 (n + m - 1), 그 중 m개의 z 위치를 고르는 조합
                 */
                if(binomial[n+m-1][m] >= k){
                    sb.append("a");  // 'a'를 선택하고
                    n--;            // a 개수 하나 감소
                } else {
                    sb.append("z");  // 'z'를 선택하고
                    k -= binomial[n+m-1][m];  // a로 시작하는 경우를 건너뛰기 위해 k를 감소 == 앞에 있었던 모든 'a'로 시작하는 경우를 건너뛰기 위해 k를 줄임
                    m--;            // z 개수 하나 감소
                }
            }
            return sb.toString();  // 결과 문자열 반환
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: a의 개수(n), z의 개수(m), k번째 문자열(k)
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        init();  // 조합값 초기화

        System.out.println(findString(n, m, k));  // 결과 출력
    }
}