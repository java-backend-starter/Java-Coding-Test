import java.io.*;
import java.util.*;

public class Permutation2 {
    /*
     * 최초 작성일시 : 2025-04-23
     * 최초 작성시간 : 15:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1947
     * 문제 이름 : 선물 전달
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    // derangement(완전 순열, 자기 자리에 오지 않는 순열) 수를 저장할 배열
    static long[] permutation;

    // derangement 수를 구하기 위한 초기화 메서드
    static void init(int size) {
        permutation = new long[size + 1];  // 인덱스 1부터 사용하기 위해 size + 1 크기로 배열 생성
        permutation[1] = 0;                // D(1) = 0: 자기 자신밖에 없기 때문에 derangement 불가능
        permutation[2] = 1;                // D(2) = 1: 2개의 원소가 서로 자리를 바꿀 수 있는 경우 1가지

        // 점화식 D(n) = (n - 1) * (D(n - 1) + D(n - 2))을 이용해 DP 방식으로 계산
        for (int i = 3; i <= size; i++) {
            permutation[i] = (i - 1) * (permutation[i - 1] + permutation[i - 2]) % 1000000000;
            // 결과값이 커질 수 있으므로 1,000,000,000(10^9)으로 나눈 나머지를 저장 (오버플로 방지)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 사용자 입력을 받기 위한 Scanner 객체 생성

        int size = sc.nextInt();  // 사용자로부터 입력 크기(n)를 입력받음
        init(size);               // derangement 배열 초기화 및 계산 수행

        System.out.println(permutation[size]);  // n에 대한 derangement 수 출력
    }

}