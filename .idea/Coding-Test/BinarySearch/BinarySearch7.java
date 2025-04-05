import java.io.*;
import java.util.*;

public class BinarySearch7 {
    /*
     * 최초 작성일시 : 2025-04-05
     * 최초 작성시간 : 13:21
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 12015
     * 문제 이름 : 가장 긴 증가하는 부분 수열 2
     * 문제 난이도 : 골드 Ⅱ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    // 이분 탐색 함수: 주어진 값의 적절한 위치를 찾아 반환 (값이 아닌 인덱스를 반환)
    static int binarySearch(int[] values, int begin, int end, int value) {
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (values[mid] == value) {
                return mid;  // 정확히 일치하는 값이 있으면 그 위치 반환
            } else if (values[mid] < value) {
                begin = mid + 1;  // 값이 더 크면 오른쪽 부분 탐색
            } else {
                end = mid - 1;  // 값이 더 작으면 왼쪽 부분 탐색
            }
        }
        // 값이 발견되지 않으면 삽입할 위치를 반환
        return -(begin + 1);
    }

    // LIS (최장 증가 부분 수열) 길이를 찾는 함수
    static int findLISLength(int[] values) {
        int size = values.length;
        int[] LIS = new int[size];  // LIS 배열: 최장 증가 부분 수열을 추적
        int length = 0;  // 현재 LIS의 길이

        for (int i = 0; i < size; i++) {
            int index = binarySearch(LIS, 0, length - 1, values[i]);
            if (index < 0) {
                index = -(index + 1);  // 삽입할 위치를 양의 인덱스로 변환
            }
            LIS[index] = values[i];  // 해당 위치에 값을 삽입

            if (index == length) {
                length++;  // 새로운 값이 끝에 추가되면 LIS 길이를 증가시킴
            }
        }

        return length;  // LIS의 길이를 반환
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());  // 입력 크기
        int[] values = new int[size];  // 입력 값 배열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            values[i] = Integer.parseInt(st.nextToken());  // 값 입력
        }

        System.out.println(findLISLength(values));  // LIS 길이 출력
    }

}