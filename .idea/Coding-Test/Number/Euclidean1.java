import java.io.*;
import java.util.*;

public class Euclidean {
    /*
     * 최초 작성일시 : 2025-03-29
     * 최초 작성시간 : 00:27
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1934
     * 문제 이름 : 최소공배수
     * 문제 난이도 : 브론즈 Ⅰ
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
    static int gcd(int a, int b){
        int temp;
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
         * query : 질의의 개수
         * a, b : 최소 공배수를 구할 두 수
         * 최소 공배수는 다음의 공식을 이용해서 구한다
         * 두 수의 곱 = 최대 공약수 * 최소 공배수 -> 최소 공배수 = 두 수의 곱 / 최대 공약수
         */
        int query = Integer.parseInt(br.readLine());
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.append((a*b)/gcd(a, b) + "\n");
        }
        bw.flush();
    }
}