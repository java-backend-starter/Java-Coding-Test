import java.io.*;

public class DFS2 {
    /*
     * 작성일시 : 2025-03-04
     * 작성시간 : 15:09
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2023
     * 문제 이름 : 신기한 소수
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * digit : 자리수
     * bw : 신기한 소수 데이터 저장용
     */
    static int digit;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /*
     * DFS 함수를 변형한 함수
     * 변형된 점
     * 1. current(현재 자리수)가 digit(입력받은 자리수)와 일치할 때 소수인지 확인
     * 2. current가 digit보다 작으면 vertex * 10에 홀수를 더했을 때 소수가 나오는지 여부에 따라 DFS 실행 여부 결정
     * 짝수일 때 넘기는 이유 : 모든 짝수는 2의 배수이기 때문
     */
    static void DFS(int vertex, int current) throws IOException {
        if(current == digit){
            if(isPrime(vertex)){
                bw.append(vertex + "\n");
            }
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(i % 2 == 0){
                continue;
            }
            if(isPrime(vertex * 10 + i)){
                DFS(vertex * 10 + i, current+1);
            }
        }
    }

    /*
     * 소수 판정 함수
     * 에라토스테네스의 체 원리로 계산
     */
    static boolean isPrime(int number){
        boolean prime = true;
        for(int i = 2; i <= number/2; i++){
            if(number%i == 0){
                prime = false;
            }
        }
        return prime;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        digit = Integer.parseInt(br.readLine());

        /*
         * 2, 3, 5, 7은 소수기 때문에 4가지 숫자로 탐색
         */
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

        bw.flush();
    }
}