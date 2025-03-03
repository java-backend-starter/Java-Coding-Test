import java.io.*;
import java.util.*;

public class Sort7 {
    /*
     * 작성일시 : 2025-03-03
     * 작성시간 : 17:22
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1517
     * 문제 이름 : 버블 소트
     * 문제 난이도 : 플레티넘 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 역순 쌍 : 배열이나 리스트에서 두 원소가 "뒤집힌" 순서를 의미(i < j이지만 arr[i] > arr[j]인 경우)
     * 다른 말로 "왼쪽에 있는 값이 오른쪽에 있는 값보다 클 때"이다.
     */
    static long answer = 0;  // 역순 쌍의 수를 저장할 변수
    
    static void mergeSort(int [] values, int start, int end){
        if(start < end){
            int mid = (start + end) / 2;
            mergeSort(values, start, mid);
            mergeSort(values, mid + 1, end);
            merge(values, start, mid, end);
        }
    }

    // 병합 함수 (실제로 병합하면서 역순 쌍을 셈)
    static void merge(int [] values, int start, int middle, int end){
        int left = start, right = middle + 1, t = 0;
        int [] sorted = new int[end - start + 1];  // 병합된 배열을 저장할 임시 배열

        // 왼쪽과 오른쪽 배열을 병합하면서 역순 쌍을 셈
        while(left <= middle && right <= end){
            if(values[left] <= values[right]){
                sorted[t++] = values[left++];  // 값이 작은 쪽을 병합
            }
            else{
                // 오른쪽 배열의 값이 왼쪽 배열의 값보다 작으면,
                // 왼쪽 배열에서 남은 값들의 개수만큼 역순 쌍이 발생함.
                // (middle - left + 1)는 left부터 middle까지의 남은 원소들의 개수입니다.
                // 이 부분은 "버블 소트"와 유사하게 왼쪽 배열의 값들이 오른쪽 배열의 값보다 클 때 발생하는 역순 쌍의 개수를 셈.
                answer += (middle - left + 1);
                sorted[t++] = values[right++];  // 오른쪽 배열의 값을 병합
            }
        }

        // 왼쪽 배열에 남은 값들 병합
        while(left <= middle){
            sorted[t++] = values[left++];
        }

        // 오른쪽 배열에 남은 값들 병합
        while(right <= end){
            sorted[t++] = values[right++];
        }

        // 병합된 결과를 원래 배열에 복사
        for(int i = 0; i < sorted.length; i++){
            values[start + i] = sorted[i];
        }
    }

    // 메인 함수
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력받을 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 병합 정렬을 시작하면서 역순 쌍을 세기 시작
        mergeSort(values, 0, size - 1);

        // 최종적으로 역순 쌍의 수 출력
        System.out.println(answer);
    }
}