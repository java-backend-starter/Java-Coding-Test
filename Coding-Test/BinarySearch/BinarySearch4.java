import java.io.*;
import java.util.*;

public class BinarySearch4 {
    /*
     * 최초 작성일시 : 2025-03-09
     * 최초 작성시간 : 20:00
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 10816
     * 문제 이름 : 숫자 카드 2
     * 문제 난이도 : 실버 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */

    /*
     * lowerBound와 upperBound는 이분 탐색의 개념을 활용한 함수
     * lowerBound 함수는 임의의 수 value가 나오는 첫 번째 인덱스를 반환
     * upperBound 함수는 임의의 수 value가 나오는 마지막 인덱스를 반환
     * upperBount 함수는 value보다 큰 수들 중 가장 작은 수가 나오는 위치를 이용
     */
    static int lowerBound(int [] values, int value){
        int result = values.length;
        int start = 0;
        int end = values.length - 1;

        int mid;
        while(start <= end){
            mid = (start+end)/2;

            if(values[mid] < value){
                start = mid + 1;
            }
            else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    static int upperBound(int [] values, int value){
        int result = values.length;
        int start = 0;
        int end = values.length - 1;

        int mid;
        while(start <= end){
            mid = (start+end)/2;

            if(values[mid] <= value){
                start = mid + 1;
            }
            else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력 받을 요소의 개수
         * values : 원본 배열
         */
        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * find : 찾을 요소의 개수
         * find : 찾을 요소들을 저장한 배열
         */
        int find = Integer.parseInt(br.readLine());
        int [] finds = new int[find];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < find; i++){
            finds[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * lower bound와 upper bound를 구하기 위해 정렬.
         * 정렬하지 않으면 정확한 개수를 파악하기 어렵다.
         */
        Arrays.sort(values);

        StringBuilder sb = new StringBuilder();
        /*
         * upper bound로 구한 인덱스 - lower bound로 구한 인덱스 = 임의의 수 value의 개수
         */
        for(int f : finds){
            int start = lowerBound(values, f);
            int end = upperBound(values, f);
            sb.append((end-start) + " ");
        }

        System.out.println(sb.toString());
    }
}