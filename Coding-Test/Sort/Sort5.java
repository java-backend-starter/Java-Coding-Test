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
     * 2025.03.02 : quick-select 알고리즘을 썼으나 91%에서 시간초과로 Arrays.sort() 함수로 대신 통과한 문제
     *
     * 2025.11.11 : quick-sort 알고리즘에 사용되는 hoare 방식과 lomuto 방식을 공부하고 적용해서 통과해서 코드 수정
     * 
     */
    /*
     * 두 원소를 교환하는 함수
     * values : 배열
     * x, y : 교환할 두 원소의 인덱스
     */
    static void swap(int [] values, int x, int y){
        int temp = values[x];
        values[x] = values[y];
        values[y] = temp;
    }

    /*
     * Hoare 방식 퀵소트
     * 1. begin < end 조건에서만 처리
     * 2. hoarePartition 함수로 피벗 위치를 구한 뒤,
     *    피벗을 기준으로 좌우 부분 배열을 재귀적으로 정렬
     */
    static void hoare(int [] values, int begin, int end){
        if(begin < end){
            int pivot = hoarePartition(values, begin, end);
            hoare(values, begin, pivot);    // 왼쪽 부분 배열 정렬
            hoare(values, pivot+1, end);    // 오른쪽 부분 배열 정렬
        }
    }

    /*
     * Hoare Partition 함수
     * 1. 배열 중간값을 피벗으로 선택
     * 2. left, right 포인터를 양 끝에서 시작
     * 3. left는 피벗 이상, right는 피벗 이하인 값을 찾으면 교환
     * 4. 포인터가 교차하면 right 인덱스를 반환
     */
    static int hoarePartition(int [] values, int begin, int end){
        int left = begin - 1;
        int right = end + 1;
        int pivot = values[(begin + end)/2];

        while(true){
            do {
                left++;
            } while(values[left] < pivot);

            do {
                right--;
            } while(values[right] > pivot);

            if(left >= right) return right;

            swap(values, left, right);
        }
    }

    /*
     * Lomuto 방식 퀵소트
     * 1. begin < end 조건에서만 처리
     * 2. lomutoPartition 함수로 피벗 위치를 구한 뒤,
     *    피벗을 기준으로 좌우 부분 배열을 재귀적으로 정렬
     */
    static void lomuto(int [] values, int begin, int end){
        if(begin < end){
            int pivot = lomutoPartition(values, begin, end);
            lomuto(values, begin, pivot-1);   // 왼쪽 부분 배열 정렬
            lomuto(values, pivot+1, end);     // 오른쪽 부분 배열 정렬
        }
    }

    /*
     * Lomuto Partition 함수
     * 1. 마지막 요소를 피벗으로 선택
     * 2. left 포인터를 pivot보다 작은 값의 경계로 사용
     * 3. 배열을 순회하며 pivot보다 작은 값은 left 오른쪽으로 이동 후 교환
     * 4. 마지막에 pivot을 left+1 위치로 이동
     * 5. pivot 위치 반환
     */
    static int lomutoPartition(int [] values, int begin, int end){
        int left = begin -1;
        int right;
        int pivot = values[end];

        for(right = begin; right < end; right++){
            if(values[right] < pivot){
                swap(values, ++left, right);
            }
        }

        swap(values, left+1, end);
        return left+1;
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

        hoare(values, 0, size-1); // hoare 방식이 lomuto 방식보다 스왑횟수가 적어 성능이 더 좋다.
        System.out.println(values[k-1]);
    }
}