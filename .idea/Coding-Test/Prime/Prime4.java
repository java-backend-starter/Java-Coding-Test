import java.io.*;
import java.util.*;

public class Prime4 {
    /*
     * 최초 작성일시 : 2025-03-25
     * 최초 작성시간 : 14:30
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1016
     * 문제 이름 : 제곱 ㄴㄴ 수
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static boolean [] check;

    /*
     * 제곱 ㄴㄴ수 : 제곱수로 나누어 떨어지는 수가 아닌 수(제곱수의 배수가 아닌 수)
     * 이 알고리즘은 에라토스테네스의 체와 유사하게 동작한다.
     * 1. 바깥쪽 for문은 에라토스테네스의 체처럼 2부터 max^(1/2) 진행한다.
     * 2. 안쪽 for문은 에라토스테네스의 체처럼 제곱수의 배수를 지운다.
     */
    static void square(long min, long max){
        for(long i = 2; i * i <= max; i++){
            long pow = i * i;
            long start = min / pow;
            if(min % pow != 0){
                start++;
            }
            for(long j = start; j * pow <= max; j++){
                check[(int)((j * pow) - min)] = true;
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * min, max : 제곱 ㄴㄴ수를 구할 때 사용할 범위
         * check : 실질적으로 min부터 max까지만 체크하기에 배열의 범위는 (max - min + 1)이다.
         */
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        check = new boolean[(int) (max - min + 1)];

        square(min, max);

        /*
         * 제곱 ㄴㄴ수의 개수를 세는 과정
         * square 함수는 제곱수와 제곱수의 배수를 체크(true로 변경)했기에 check 배열에서 false 값을 가진 인덱스가 있으면 카운트한다.
         */
        int count = 0;
        for(int i = 0; i <= max - min; i++){
            if(!check[i]){
                count++;
            }
        }

        System.out.println(count);
    }
}