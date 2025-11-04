import java.io.*;
import java.util.*;

public class Binomial4 {
    /*
     * 최초 작성일시 : 2025-04-19
     * 최초 작성시간 : 10:12
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1010
     * 문제 이름 : 다리 놓기
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */

    // 이차원 배열을 사용하여 이항계수 저장
    static int[][] binomial;

    // 이항계수를 초기화하는 함수 (파스칼의 삼각형 이용)
    static void init(){
        binomial = new int[31][31]; // 최대 30까지의 이항계수 계산

        // 기본값 설정
        for(int i = 0; i < 31; i++){
            binomial[i][0] = 1;     // nC0 = 1
            binomial[i][1] = i;     // nC1 = n
            binomial[i][i] = 1;     // nCn = 1
        }

        // 파스칼의 삼각형을 이용한 이항계수 계산
        for(int i = 2; i < 31; i++){
            for(int j = 1; j < i; j++){
                binomial[i][j] = binomial[i-1][j-1] + binomial[i-1][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 및 출력을 위한 BufferedReader, BufferedWriter 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int query = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력

        init(); // 이항계수 미리 계산

        // 각 테스트 케이스에 대해 계산
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine()); // 한 줄 읽고 공백 기준으로 나눔
            int n = Integer.parseInt(st.nextToken()); // 서쪽 다리 수
            int m = Integer.parseInt(st.nextToken()); // 동쪽 다리 수

            // mCn 출력 (다리 놓는 경우의 수)
            bw.write(binomial[m][n] + "\n");
        }

        bw.flush(); // 출력 버퍼 비우기
    }
}
