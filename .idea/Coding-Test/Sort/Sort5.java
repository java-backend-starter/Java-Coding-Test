import java.io.*;
import java.util.StringTokenizer;
import java.util.Random;

public class Sort5 {
    /*
     * 작성일시 : 2025-03-02
     * 작성시간 : 09:34
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11004
     * 문제 이름 : k번째 수
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     * 
     * 특이사항
     * 
     * 91%에서 시간초과로 Arrays.sort() 함수로 대신 통과한 문제
     */
    /*
     * quick select 알고리즘은 정렬했을 때 특정 위치의 요소를 전체 정렬을 하지 않고도 찾는 알고리즘이다.
     *
     */
    static final Random random = new Random();

    /*
     * quick select 알고리즘
     * 1. 요소가 1개일 때 : 해당 요소를 반환
     * 2. 요소가 2개 이상일 때 : partition 함수로 피봇의 위치(pivot)를 구하고 3가지 경우를 확인한다.
     * 2-1. 요소가 k번째 요소일 때 : 해당 요소를 반환
     * 2-2. 요소가 k번째 수보다 작을 때 : start부터 pivot - 1(피봇 앞의 요소)까지의 범위에서 탐색
     * 2-3. 요소가 k번째 수보다 클 때 : pivot + 1부터 end까지의 범위에서 탐색
     *
     * 2-2, 2-3은 quick select 함수를 재귀 호출한다.
     */
    static int quickSelect(int [] values, int start, int end, int k){
        if(start == end){
            return values[start];
        }
        int pivot = partition(values, start, end);
        if(k == pivot){
            return values[k];
        }
        else if(k < pivot){
            return quickSelect(values, start, pivot-1, k);
        }
        else {
            return quickSelect(values, pivot+1, end, k);
        }
    }

    /*
     * 피봇의 위치를 반환하는 함수
     * 1. 피봇을 random 클래스로 구한다.
     * 2. 피봇을 기준으로 두 개의 부분 집합을 형성
     * 3. 피봇 위치를 반환
     */
    static int partition(int [] values, int start, int end){
        int index = start + random.nextInt(end - start + 1);
        swap(values, index, end);
        int pivot = values[end];
        int left = start-1;
        for(int right = start; right < end; right++){
            if(values[right] < pivot){
                swap(values, ++left, right);
            }
        }
        swap(values, left+1, end);
        return left+1;
    }

    static void swap(int [] values, int i, int j){
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 입력받을 요소의 개수
         * k : 찾을 요소의 위치
         */
        int size = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int [] values = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(quickSelect(values, 0, size-1, k-1));
    }
}