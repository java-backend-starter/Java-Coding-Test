import java.util.*;

public class Queue1 {
    /* 
    * 작성일시 : 2025-02-18
    * 작성시간 : 18:34
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2164
    * 문제 이름 : 카드2
    * 문제 난이도 : 실버 Ⅳ
    *
    * 작성 목적
    *  
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
         * number : 입력받을 요소의 개수
         */
        int number = sc.nextInt();
        sc.close();

        /*
         * 큐를 초기화(1번 카드부터 number번 카드를 입력)
         * 큐를 사용하는 이유 : 문제에서는 가장 작은 수부터 빠져나가야 하기 때문
         */
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= number; i++){
            queue.add(i);
        }

        /*
         * 과정
         * 1. 두 장의 카드를 뺀다
         * 2. 두 번째로 빠진 카드를 넣는다.
         * 3. 큐에 카드 한 장이 남을 때까지 반복한다.
         */
        while(queue.size() > 1){
            queue.poll();
            queue.add(queue.poll());
        }
        System.out.println(queue.poll());
    }
}
