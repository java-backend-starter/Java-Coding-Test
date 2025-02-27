import java.io.*;
import java.util.Arrays;

/*
 * 버블 소트로 정렬할 요소들에 대한 정보를 저장하는 클래스
 * index : 정렬하기 전 요소의 인덱스 정보
 * value : 요소의 값
 * 
 * 사용자 정의 클래스는 Comparable하지 않기 때문에 비교할 수 있도록 Comparable이나 Comparator를 구현해야 한다. 
 */
class BubbleNode implements Comparable<BubbleNode> {
    private int index;
    private int value;

    BubbleNode(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int getIndex(){
        return this.index;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public int compareTo(BubbleNode node){
        return this.value - node.getValue();
    }
}
public class Sort2 {
    /* 
    * 작성일시 : 2025-02-27
    * 작성시간 : 21:03
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1377
    * 문제 이름 : 버블 소트
    * 문제 난이도 : 골드 Ⅱ
    *
    * 작성 목적
    *  
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * size : 입력받을 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());

        /*
         * 입력 받을 요소들
         * 초기 인덱스와 값 정보를 저장
         */
        BubbleNode [] values = new BubbleNode[size];
        for(int i = 0; i < size; i++){
            values[i] = new BubbleNode(i, Integer.parseInt(br.readLine()));
        }

        /*
         * 최대 50만개의 요소들을 비교 정렬해야 하기 때문에 최악의 경우에도 Ο(n log n)의 시간 복잡도를 가지는 정렬 알고리즘을 사용해야 한다.
         * 자바에서 최악의 경우에도 Ο(n log n)의 시간 복잡도를 가지는 정렬 알고리즘으로 구현된 대표적인 함수가 Arrays.sort()이다.
         */
        Arrays.sort(values);

        /*
         * max : 버블 정렬로 스왑된 횟수
         * 이 문제에서 보기로 주어진 버블 소트 알고리즘은 왼쪽에서 오른쪽으로 정렬이 이루어진다.
         * 따라서 바깥쪽 for 루프가 한 번 반복할 때 요소가 왼쪽에서 오른쪽으로 몇 번이고 갈 수 있지만 오른쪽에서 왼쪽으로 가는 것은 1번만 가능하다.
         * 그렇기에 버블 소트로 정렬된 요소들의 인덱스와 정렬되기 전의 인덱스를 비교해서 왼쪽으로 이동한 횟수들 중 최댓값을 얻으면 바깥쪽 for 루프가 몇 번 동작했는지 알 수 있다.
         */
        int max = 0;
        for(int i = 0; i < size; i++){
            if(values[i].getIndex() - i > max){
                max = values[i].getIndex() - i;
            }
        }
        /*
         * max에 1을 더하는 이유는 문제의 버블 소트는 마지막에 요소들이 정렬되었는지 확인하는 과정이 있기 때문이다.
         */
        System.out.println(max+1);
    }    
}
