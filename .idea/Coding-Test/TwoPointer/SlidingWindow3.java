import java.io.*;
import java.util.*;

/* 
 * 작성일시 : 2025-02-15
 * 작성시간 : 17:27
 * 작성자 : 장성환
 *
 * 문제 출처 : 백준
 * 문제 번호 : 11003
 * 문제 이름 : 최솟값 찾기
 * 문제 난이도 : 플레티넘 Ⅴ
 *
 * 작성 목적
 * 
 * 책에 있는 문제 복습
 */

/*
 * Node 클래스 : 부분 배열의 최솟값을 찾기 위한 배열의 정보를 저장
 * index는 배열 인덱스, value는 배열에서 index로 접근할 때 얻을 수 있는 값
 * Comparator를 구현한 이유 : 문제에서 부분 배열의 최솟값을 찾아야 하기 때문에 정렬이 필요하기 때문, comparable을 구현해도 무방
 */
class Node implements Comparator<Node>{
    private int index;
    private int value;

    Node(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int getIndex(){
        return index;
    }

    public int getValue(){
        return value;
    }

    /*
     * 이 comparae 함수는 오름차순으로 구현한다.
     */
    @Override
    public int compare(Node a, Node b){
        if(a.getValue() > b.getValue()){
            return 1;
        }
        else if(a.getValue() < b.getValue()){
            return -1;
        }
        else {
            return 0;
        }
    }
}

public class SlidingWindow3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 입력받을 요소의 개수
         * partial : 슬라이딩 윈도우의 크기
         */
        int size = Integer.parseInt(st.nextToken());
        int partial = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> queue = new LinkedList<>();
        /*
         * while문의 진행 과정
         * 1. i번째 인덱스의 값을 구한다.
         * 2. 슬라이딩 윈도우(queue)에 들어 있는 값들 중 i번째 인덱스의 값보다 큰 값을 제거(문제는 최솟값을 구하기 때문에 슬라이딩 윈도우에 데이터를 유지할 이유가 없기 때문)
         * 3. i번째 인덱스의 값을 슬라이딩 윈도우의 마지막에 삽입
         * 4. queue의 첫 번째 요소(최솟값을 저장한 요소)가 슬라이딩 윈도우의 범위를 넘어섰으면 제거
         * 5. 최솟값(queue의 첫번째 요소의 value 값)을 기록
         */
        for(int i = 0; i < size; i++){
            int value = Integer.parseInt(st.nextToken());

            while(!queue.isEmpty() && queue.getLast().getValue() > value){
                queue.removeLast();
            }

            queue.addLast(new Node(i, value));

            if(queue.getFirst().getIndex() < i - partial){
                queue.removeFirst();
            }

            bw.append(queue.getFirst().getValue() + " ");
        }

        bw.flush();
    }
}
