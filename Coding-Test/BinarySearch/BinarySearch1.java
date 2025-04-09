import java.io.*;
import java.util.*;

public class BinarySearch1 {
    /*
     * 최초 작성일시 : 2025-03-07
     * 최초 작성시간 : 10:07
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1920
     * 문제 이름 : 수 찻기
     * 문제 난이도 : 실버 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 이진 탐색 알고리즘
     * 동작 과정
     * 1. start == end : 요소가 한 개인 경우, 키값과 일치 여부를 확인
     * 2. 중간 인덱스를 찾고 키값과 비교
     * 2-1. key == values[mid] : 해당 요소를 찾았을 때, 1 리턴
     * 2-2. key < values[mid] : 해당 요소가 중간에 있는 요소보다 작을 때, 왼쪽 부분 탐색
     * 2-3. key > values[mid] : 해당 요소가 중간에 있는 요소보다 클 때, 오른쪽 부분을 탐색
     */
    static int binarySearch(int [] values, int start, int end, int key){
        if(start == end){
            return key == values[start] ? 1 : 0;
        }

        int mid = (start + end)/2;
        if(key == values[mid]){
            return 1;
        }
        else if(key < values[mid]){
            return binarySearch(values, start, mid - 1, key);
        }
        else {
            return binarySearch(values, mid + 1, end, key);
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * size : 입력 받은 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * find : 찾을 요소의 개수
         * find : 찾을 요소들의 집합
         */
        int find = Integer.parseInt(br.readLine());
        int [] finds = new int[find];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < find; i++){
            finds[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * 이진 탐색은 정렬된 요소 집합에서 사용하므로 정렬부터 수행
         */
        Arrays.sort(values);
        for(int i = 0; i < find; i++){
            bw.append(binarySearch(values, 0, values.length-1, finds[i]) + "\n");
        }
        bw.flush();
    }
}