import java.util.*;

public class Totient1 {
    /*
     * 최초 작성일시 : 2025-03-25
     * 최초 작성시간 : 14:30
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11689
     * 문제 이름 : GCD(n, k) = 1
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 오일러의 피 알고리즘 (Euler's Totient Function)
     * 오일러의 피 함수(φ(n))는 1부터 n까지의 숫자 중 n과 서로소인 숫자의 개수를 구하는 함수이다.
     *
     * 오일러의 피 함수의 계산법:
     * 1. n의 소인수를 찾는다.
     * 2. 각 소인수 p에 대해 φ(n) = n * (1 - 1/p) 를 적용한다.
     * 3. 남은 n이 소수라면, 한 번 더 처리한다.
     *
     * 시간복잡도:
     * - 이 구현은 O(√n)으로 동작하며, 소인수분해를 기반으로 한다.
     *
     * 다른 구현 방법
     * 1. 기본적인 정의를 이용한 O(n) 브루트포스 방법
     * 2. 에라토스테네스의 체를 이용한 O(n log log n) 전처리 방법
     */
    static long Totient(long value) {
        long result = value; // 결과값 초기화 (초기값: value)

        // 2부터 sqrt(value)까지의 모든 값(i)에 대해 검사
        for (long i = 2; i <= Math.sqrt(value); i++) {
            // value가 i로 나누어 떨어진다면 (즉, i가 value의 소인수라면)
            if (value % i == 0) {
                result -= result / i; // 오일러의 피 공식 적용: result *= (1 - 1/i)

                // value에서 해당 소인수 i를 모두 제거
                while (value % i == 0) {
                    value /= i;
                }
            }
        }

        // 마지막 남은 값이 1보다 크다면, 이는 소수이므로 추가 처리
        if (value > 1) {
            result -= result / value;
        }

        return result; // 오일러의 피 함수 값 반환
    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        long value = sc.nextLong();

        System.out.println(Totient(value));
    }
}