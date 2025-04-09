import java.io.*;
import java.util.*;

public class Euclidean2 {
    /*
     * 최초 작성일시 : 2025-03-29
     * 최초 작성시간 : 00:48
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1850
     * 문제 이름 : 최대공약수
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 유클리드 호제법에 의한 최대공약수(GCD) 알고리즘
     * 원리:
     * 1. a에 b, b에 a % b를 저장
     * 2. b가 0이 될 때까지 1을 반복
     * 3. 2가 끝나면 a에 저장된 값이 최대공약수
     *
     * 구현 방법:
     * - 반복문(while)을 이용하는 방법
     * - 재귀함수를 이용하는 방법 (주석 처리된 부분 참고)
     */
    static long gcd(long a, long b) {
        long temp;
        while (b != 0) {  // b가 0이 아닐 때까지 반복
            temp = b;     // 현재 b 값을 임시 변수 temp에 저장
            b = a % b;    // a를 b로 나눈 나머지를 b에 저장
            a = temp;     // 이전 b 값을 a에 저장 (swap)
        }
        return a;  // b가 0이 되면 a가 최대공약수(GCD)

        // 아래와 같이 재귀 방식으로도 구현 가능
        // return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * 입력: 두 개의 정수 a, b (최대공약수를 구할 두 수)
         * - 예제 입력: 3 2
         */
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());  // 첫 번째 수
        long b = Long.parseLong(st.nextToken());  // 두 번째 수
        long gcd = gcd(a, b);  // 최대공약수 계산

        /*
         * 출력: 최대공약수만큼 '1' 출력
         * - 예제 입력: 3 2
         * - 최대공약수(GCD) = 1
         * - 출력: "1"
         *
         * - 예제 입력: 6 3
         * - 최대공약수(GCD) = 3
         * - 출력: "111"
         */
        while (gcd > 0) {
            bw.append("1");  // GCD만큼 '1'을 출력
            gcd--;
        }
        bw.flush();  // 출력 버퍼 비우기
    }
}