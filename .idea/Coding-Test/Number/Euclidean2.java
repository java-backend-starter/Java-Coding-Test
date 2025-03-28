import java.io.*;
import java.util.*;

public class Euclidean {
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
     * 유클리드 호제법에 의한 최대공약수 알고리즘
     * 원리
     * 1. a에 b, b에 a%b를 저장
     * 2. b가 0이 될 때까지 1을 반복
     * 3. 2가 끝나면 a에 저장된 값이 최대공약수
     *
     * 구현 방법은 while문을 이용하는 법과 재귀함수를 이용하는 법이 존재
     */
    static long gcd(long a, long b){
        long temp;
        while(b != 0){
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
        /*
         * return b == 0 ? a : gcd(b, a % b);
         */
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * a, b : 최대 공약수를 구할 두 수
         */
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long gcd = gcd(a, b);

        while(gcd > 0){
            bw.append("1");
            gcd--;
        }
        bw.flush();
    }
}