import java.io.*;
import java.util.*;

public class ExtendedEuclidean1 {
    /*
     * 최초 작성일시 : 2025-04-03
     * 최초 작성시간 : 13:11
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 21568
     * 문제 이름 : Ax+By=C
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 확장 유클리드 호제법(Extended Euclidean Algorithm) 구현
     *
     * 확장 유클리드 호제법은 ax + by = gcd(a, b)를 만족하는 x, y를 찾는 알고리즘이다.
     * 일반적인 유클리드 호제법을 확장하여 베주 항등식(B?zout's identity)을 적용한 것.
     */
    static long[] extendedEuclidean(long a, long b) {
        // b가 0이면, gcd(a, 0) = a이고, x = 1, y = 0이 성립한다.
        if (b == 0) {
            return new long[]{a, 1, 0}; // {gcd, x, y}
        }

        // 재귀적으로 gcd(b, a % b)를 구하고 x, y 값을 갱신
        long[] next = extendedEuclidean(b, a % b);
        long gcd = next[0]; // gcd(a, b)
        long x = next[2];   // 이전 단계의 y 값이 새로운 x 값
        long y = next[1] - (a / b) * next[2]; // 베주 항등식을 사용하여 y 값 갱신

        return new long[]{gcd, x, y}; // {gcd, x, y} 반환
    }

    /*
     * 유클리드 호제법(Euclidean Algorithm) 구현
     *
     * 두 수 a, b의 최대공약수(GCD)를 구하는 함수
     */
    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 읽기 (a, b, c)
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        // a와 b의 최대공약수(GCD) 계산
        long gcd = gcd(a, b);

        // c가 gcd(a, b)의 배수가 아니면 정수 해가 존재하지 않음
        if (c % gcd != 0) {
            System.out.println("-1"); // 정수 해가 없음을 의미
        } else {
            // c를 gcd로 나눈 몫 (확장 유클리드 알고리즘을 적용할 배수)
            long divisor = c / gcd;

            // 확장 유클리드 알고리즘 실행하여 x, y 구하기
            long [] result = extendedEuclidean(a, b);
            long x = result[1] * divisor; // x 값을 c/gcd 배만큼 조정
            long y = result[2] * divisor; // y 값을 c/gcd 배만큼 조정

            // 결과 출력 (Ax + By = C의 정수해)
            System.out.println(x + " " + y);
        }

        // 리소스 정리
        br.close();
    }
}