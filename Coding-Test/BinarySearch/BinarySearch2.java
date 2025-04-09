import java.io.*;
import java.util.*;

public class BinarySearch2 {
    /*
     * 최초 작성일시 : 2025-03-07
     * 최초 작성시간 : 11:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2343
     * 문제 이름 : 기타 레슨
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static int blueray(int[] values, int start, int end, int bluerays) {
        // 이진 탐색을 이용하여 블루레이의 최소 크기를 찾는 함수
        while (start <= end) {
            /*
             * mid : 중간값 (현재 블루레이 크기 후보)
             * sum : 현재 블루레이에 저장된 크기 합
             * count : 사용된 블루레이 개수
             */
            int mid = (start + end) / 2;
            int sum = 0;
            int count = 0;

            // 주어진 값들을 블루레이에 나누어 담기
            for (int i = 0; i < values.length; i++) {
                // 현재 블루레이에 추가할 경우 용량 초과라면 새로운 블루레이 필요
                if (sum + values[i] > mid) {
                    count++; // 블루레이 하나 사용
                    sum = 0; // 새로운 블루레이에 저장 시작
                }
                sum += values[i]; // 현재 블루레이에 값 추가
            }

            // 마지막 블루레이가 비어 있지 않으면 카운트 증가
            if (sum != 0) {
                count++;
            }

            // 블루레이 개수가 주어진 개수보다 많으면 크기를 늘려야 함
            if (count > bluerays) {
                start = mid + 1; // 최소 크기를 증가
            } else {
                end = mid - 1; // 최소 크기를 줄일 수 있음
            }
        }
        return start; // 가능한 최소 블루레이 크기 반환
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int lesson = Integer.parseInt(st.nextToken());
        int blueray = Integer.parseInt(st.nextToken());

        int [] values = new int[lesson];
        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 0;
        for(int i = 0; i < lesson; i++){
            values[i] = Integer.parseInt(st.nextToken());
            if(start < values[i]) {
                start = values[i];
            }
            end += values[i];
        }

        System.out.println(blueray(values, start, end, blueray));
    }
}