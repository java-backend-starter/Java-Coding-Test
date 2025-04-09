import java.io.*;
import java.util.*;

public class Greedy4 {
    /*
     * 최초 작성일시 : 2025-03-11
     * 최초 작성시간 : 17:47
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1744
     * 문제 이름 : 수 묶기
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * 두 개의 숫자를 queue에서 뽑아 곱한 값들을 더한 후 반환하는 함수
     * PriorityQueue를 매개변수로 받아 두 수의 곱들의 합을 최댓값 또는 최솟값으로 만듦
     */
    static int sum(PriorityQueue<Integer> queue) {
        int sum = 0;
        while (queue.size() > 1) {
            int operand1 = queue.remove();
            int operand2 = queue.remove();
            sum += (operand1 * operand2);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * size : 입력받을 숫자의 개수
         */
        int size = Integer.parseInt(br.readLine());

        /*
         * one : 1의 개수
         * zero : 0의 개수
         * plusQueue : 양수 저장 (최댓값을 위해 내림차순 정렬)
         * minusQueue : 음수 저장 (최댓값을 위해 오름차순 정렬)
         */
        int one = 0;
        int zero = 0;
        PriorityQueue<Integer> plusQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusQueue = new PriorityQueue<>();

        /*
         * 입력 값 분류
         * 1. 0이면 zero 증가
         * 2. 1이면 one 증가
         * 3. 양수(2 이상)는 plusQueue에 저장
         * 4. 음수는 minusQueue에 저장
         */
        for (int i = 0; i < size; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) {
                zero++;
            } else if (value == 1) {
                one++;
            } else if (value < 0) {
                minusQueue.add(value);
            } else {
                plusQueue.add(value);
            }
        }

        /*
         * 최댓값 계산 과정
         * 1. plusQueue에서 두 수씩 뽑아 곱한 값을 sum에 추가
         * 2. plusQueue에 숫자가 하나 남아 있으면 sum에 더함
         * 3. minusQueue에서 두 수씩 뽑아 곱한 값을 sum에 추가
         * 4. minusQueue에 숫자가 하나 남아 있으면 zero의 존재 여부 확인
         *    4-1. zero가 있으면 남은 음수를 제거 (최댓값을 위해)
         *    4-2. zero가 없으면 sum에 더함
         * 5. 입력받은 1의 개수를 sum에 추가
         *    5-1. 1은 다른 숫자와 곱해도 값이 변하지 않으므로, 묶지 않고 그대로 더하는 것이 최댓값을 만들 수 있음
         *    5-2. 예를 들어, 1과 2를 곱하면 2이지만, 1을 따로 더하면 1 + 2 = 3으로 더 큰 값을 얻을 수 있음
         */
        int sum = 0;
        
        sum = sum(plusQueue);
        
        if (!plusQueue.isEmpty()) {
            sum += plusQueue.remove();
        }

        sum += sum(minusQueue);
        
        if (!minusQueue.isEmpty()) {
            if (zero == 0) {
                sum += minusQueue.remove();
            }
        }

        sum += one;

        /*
         * 위의 과정으로 구한 최댓값을 출력
         */
        System.out.println(sum);
    }
}