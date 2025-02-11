import java.util.Scanner;
public class TwoPointer1 {
    /* 
    * 작성일시 : 2025-02-11
    * 작성시간 : 20:39
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2018
    * 문제 이름 : 수들의 합 5
    * 문제 난이도 : 실버 Ⅳ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        sc.close();

        /*
         * count : 연속된 자연수의 합이 number인 개수를 저장, 초깃값이 1인 경우는 두 개의 포인터가 number를 가리킬 때, 항상 조건을 만족하기 때문
         * start, end : 투 포인터로 사용할 변수, 같은 위치에서 시작하여 같은 방향으로 진행
         * sum : start부터 end까지의 합을 저장
         */
        int count = 1;
        int start = 1;
        int end = 1;
        int sum = 1;

        /*
         * while문의 종료 조건이 end != number인 이유 : end가 number를 넘어가면 조건을 만족시킬 수 없기 때문
         * 
         * 이 알고리즘의 동작 원리
         * 1. sum > number일 때
         *      start부터 end까지의 연속된 자연수의 합이 number보다 크기 때문에
         *      start 값을 빼고 start가 1칸 이동
         * 2. sum < number일 때
         *      start부터 end까지의 연속된 자연수의 합이 number보다 작기 때문에
         *      end + 1을 더하고 end가 1칸 이동
         * 3. sum == number일 때
         *      start부터 end까지의 연속된 자연수의 합이 number과 같기 때문에
         *      count로 개수를 샌다.
         *      end++와 sum += end가 있는 이유는 다른 경우를 찾기 위함이다.
         */
        while(end != number){
            if(sum > number){
                sum -= start;
                start++;
            }
            else if(sum < number){
                end++;
                sum+= end;
            }
            else {
                end++;
                sum += end;
                count++;
            }
        }

        System.out.println(count);
    }
}