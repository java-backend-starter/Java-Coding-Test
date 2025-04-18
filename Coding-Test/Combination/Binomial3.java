import java.io.*;

public class Binomial3 {
    /*
     * 최초 작성일시 : 2025-04-18
     * 최초 작성시간 : 11:23
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2775
     * 문제 이름 : 부녀회장이 될 테야
     * 문제 난이도 : 브론즈 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 15x15 크기의 아파트 배열 선언 (각각의 층과 호에 대한 인원 수를 저장)
    static int [][] apartment = new int[15][15];

    /*
     * 아파트 배열 초기화 함수
     * 0층의 각 호는 그 호 번호와 동일한 사람 수를 가짐
     * 각 호의 인원 수는 위층의 해당 호와 그 이전 호 사람 수의 합으로 계산
     */
    static void init(){
        // 0층은 각 호가 그 번호의 사람 수를 가짐 (1호에는 1명, 2호에는 2명, ...)
        for(int i = 0; i < 15; i++){
            apartment[i][1] = 1;   // 1호에는 항상 1명이 살고
            apartment[0][i] = i;   // 0층의 각 호에 사는 사람 수는 해당 호의 번호와 동일
        }

        // 1층 이상의 층은 각 호에 사는 사람 수를 이전 층의 해당 호와 이전 호 사람 수를 더하여 계산
        for(int i = 1; i < 15; i++){
            for(int j = 2; j < 15; j++){
                apartment[i][j] = apartment[i-1][j] + apartment[i][j-1];
            }
        }
    }

    public static void main(String [] args) throws IOException{
        // 입력을 처리할 BufferedReader와 결과를 출력할 BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스의 개수를 입력받음
        int query = Integer.parseInt(br.readLine());

        // 아파트 배열 초기화 (모든 계산을 미리 해두어 나중에 빠르게 결과를 출력할 수 있도록)
        init();

        // 각 테스트 케이스에 대해 결과를 출력
        for(int i = 0; i < query; i++){
            // 각 테스트 케이스에서 층과 호 번호를 입력받음
            int row = Integer.parseInt(br.readLine());  // 층
            int col = Integer.parseInt(br.readLine());  // 호 번호

            // 해당 층과 호의 사람 수를 출력
            bw.write(apartment[row][col] + "\n");
        }
        // 결과 출력
        bw.flush();
    }
}