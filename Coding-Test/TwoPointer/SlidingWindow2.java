import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SlidingWindow2 {
    /* 
    * 작성일시 : 2025-02-14
    * 작성시간 : 20:53
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 14465
    * 문제 이름 : 소가 길을 건너간 이유 5
    * 문제 난이도 : 실버 Ⅳ
    *
    * 작성 목적
    * 
    * 백준의 슬라이딩 윈도우 문제 풀이
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size: 신호등 개수, 
         * partial : 연속된 구간 크기, 
         * breaked: 고장난 신호등 개수
         */
        int size = Integer.parseInt(st.nextToken());  
        int partial = Integer.parseInt(st.nextToken());
        int breaked = Integer.parseInt(st.nextToken());
    
        /*
         * lights : 신호등 상태를 저장하는 배열 (1-based index 사용)
         * 모든 신호등을 정상(true)으로 초기화하고 고장난 신호등에 대해서 false로 저장
         * breakIndex는 고장난 신호등의 위치를 의미
         */
        boolean[] lights = new boolean[size + 1];
        Arrays.fill(lights, true);
        for (int i = 0; i < breaked; i++) {
            int breakIndex = Integer.parseInt(br.readLine());
            lights[breakIndex] = false;
        }
    
        /*
         * min : 최소 수리 횟수
         * current : 현재 수리 횟수
         * 첫 번째 구간 [1, partial]에서의 고장난 신호등 개수를 계산하고
         * 나머지 구간 [partial+1, size]에 대해서 고장난 신호등 개수를 계산
         */
        int min = 0; 
        for (int i = 1; i <= partial; i++) {
            if (!lights[i]) { // 고장난 신호등이면 카운트 증가
                min++;
            }
        }
        int current = min;
    
        for (int i = partial + 1; i <= size; i++) {
            /*
             * 두 if문은 새롭게 추가되는 신호등과 슬라이딩 윈도우에서 벗어나는 신호등에 대해서 처리하는 코드
             * min은 슬라이딩 윈도우가 이동함에 따라서 최소 수리 횟수가 바뀌기 때문에 갱신
             */
            if (!lights[i]) { 
                current++;
            }
            if (!lights[i - partial]) { 
                current--;
            }
            min = Math.min(current, min);
        }   
        System.out.println(min);
    }
}    