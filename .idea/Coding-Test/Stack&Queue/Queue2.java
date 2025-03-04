import java.io.*;
import java.util.*;

public class Queue2 {
    /* 
    * 작성일시 : 2025-02-18
    * 작성시간 : 18:46
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11286
    * 문제 이름 : 절대값 힙
    * 문제 난이도 : 실버 Ⅰ
    *
    * 작성 목적
    *  
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
         * size : 입력 받을 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());


        /*
         * 절대값 힙을 구현하기 위한 자료구조로 우선순위 큐를 사용.
         * PriorityQueue는 요소가 삽입될 때마다 Ο(n * log n)의 시간 복잡도로 큐 내부의 요소들을 정렬해주기 때문에 사용됨
         * PriorityQueue는 기본적으로 오름차순으로 정렬하기 때문에 절대값 힙을 구현하기 위해Comparator를 정의해야 한다.
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b){
                int x = Math.abs(a);
                int y = Math.abs(b);
                if(x == y){
                    return a.intValue() - b.intValue();
                }
                else {
                    return x - y;
                }
            }
        });

        /* 
         * comparator 대신에 람다식으로 아래의 방식으로도 정렬할 수 있다.
         */
        /*
        PriorityQueue<Integer> queue1 = new PriorityQueue<>((a, b) -> {
           int x = Math.abs(a);
           int y = Math.abs(b);
           if(x == y){
            return a - b;
           }
           else {
            return x - y;
           }
        });
        */

        /*
         * now의 값에 따라 처리하는 내용이 달라짐
         * 1. now가 0인 경우
         * 1-1. queue가 공백 큐인 경우 : 출력할 내용이 없기 때문에 0을 출력
         * 1-2. queue가 공백 큐가 아닌 경우 : queue에 있는 값들 중 최솟값을 출력
         * 2. now가 0이 아닌 경우 : queue에 now를 삽입
         */
        for(int i = 0; i < size; i++){
            int now = Integer.parseInt(br.readLine());
            if(now == 0){
                bw.append(queue.isEmpty() ?  "0\n" : queue.poll() + "\n");
            }
            else {
                queue.add(now);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
