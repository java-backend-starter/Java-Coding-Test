import java.io.*;
import java.util.*;

public class Greedy2 {
    /*
     * 최초 작성일시 : 2025-03-11
     * 최초 작성시간 : 17:47
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1715
     * 문제 이름 : 카드 정렬하기
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * size : 입력 받을 요소의 개수
         * queue : 입력받을 요소를 자동으로 오름차순으로 정렬하는 자료구조
         */
        int size = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < size; i++){
            queue.add(Integer.parseInt(br.readLine()));
        }

        /*
         * sum : 카드 묶음의 비교횟수
         * while문의 과정
         * 1. 가장 적은 두 개의 카드 묶음을 선택
         * 2. 두 카드 묶음의 비교횟수를 저장(sum += (a + b)인 이유 : 기존에 비교한 것도 추가해야 하기 때문)
         * 3. 두 묶음을 하나의 묶음으로 만들면서 비교한 횟수를 queue에 저장
         */
        int sum = 0;
        while(queue.size() != 1){
            int a = queue.remove();
            int b = queue.remove();
            sum += (a + b);
            queue.add((a+b));
        }

        /*
         * 여기의 sum : 입력받은 카드 묶음들을 하나의 묶음으로 만들 때 나올 수 있는 최소한의 비교횟수
         */
        System.out.println(sum);
    }
}