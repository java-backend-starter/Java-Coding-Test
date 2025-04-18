import java.io.*;
import java.util.*;

public class Binomial2 {
    /*
     * 최초 작성일시 : 2025-04-18
     * 최초 작성시간 : 10:43
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11051
     * 문제 이름 : 이항 계수 2
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 이항 계수를 저장하는 배열
    static int [][] binomial;

    /*
     * 배열 초기화
     * size : binomial의 크기 설정
     */
    static void init(int size){
        // 이항 계수 배열을 (size+1) x (size+1) 크기로 초기화
        binomial = new int[size+1][size+1];

        // 이항 계수의 기본 값 설정
        // nC0 = 1, nCn = 1, nC1 = n
        for(int i = 0; i <= size; i++){
            binomial[i][1] = i;  // nC1 = n
            binomial[i][0] = 1;  // nC0 = 1
            binomial[i][i] = 1;  // nCn = 1
        }

        // 이항 계수 계산: 이항 계수는 다음과 같은 재귀적 성질을 가짐
        // nCk = (n-1)C(k-1) + (n-1)Ck
        // 이 때, 계산된 이항 계수를 10007로 나눈 나머지를 저장
        for(int i = 2; i <= size; i++){
            for(int j = 1; j < i; j++){
                // 위의 점화식을 적용하여 이항 계수를 계산하고,
                // 10007로 나눈 나머지를 저장
                binomial[i][j] = (binomial[i-1][j-1] + binomial[i-1][j]) % 10007;
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 이항 계수에서 n 값
        int k = Integer.parseInt(st.nextToken()); // 이항 계수에서 k 값

        // 이항 계수 배열 초기화
        init(n);

        // binomial[n][k]는 nCk 값을 계산한 후, 10007로 나눈 나머지를 출력
        System.out.println(binomial[n][k]);
    }
}