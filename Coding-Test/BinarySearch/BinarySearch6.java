import java.io.*;
import java.util.*;

public class BinarySearch6 {
    /*
     * 최초 작성일시 : 2025-03-13
     * 최초 작성시간 : 22:10
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2110
     * 문제 이름 : 공유기 설치
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    static boolean canPlaceRouters(int [] house, int distance, int minCount){
        /*
         * 첫 번째 집에 라우터를 설치한 것으로 간주하고 초기화
         * count: 설치한 라우터의 개수
         * lastPlaced: 마지막으로 라우터를 설치한 집의 위치
         */
        int count = 1;
        int lastPlaced = house[0];

        /*
         * 두 번째 집부터 순차적으로 확인하면서 라우터를 설치할 수 있는지 확인
         */
        for(int i = 1; i < house.length; i++){
            /*
             * 마지막 설치된 집과 현재 집 사이의 거리가 주어진 최소 거리(distance) 이상일 경우
             * 라우터를 설치하고, lastPlaced와 count 값을 갱신
             */
            if(house[i] - lastPlaced >= distance){
                lastPlaced = house[i];
                count++;
            }
        }

        /*
         * 주어진 거리(distance)로 minCount개의 라우터를 설치할 수 있는지 확인
         */
        return count >= minCount;
    }

    static int binarySearch(int [] house, int count){
        /*
         * 이진 탐색의 초기값 설정
         * start: 라우터를 설치할 수 있는 최소 거리 (집들이 인접했을 때)
         * end: 라우터를 설치할 수 있는 최대 거리 (집들 간의 최대 거리)
         * answer: 가능한 최대 거리를 저장할 변수
         */
        int start = 1;
        int end = house[house.length-1] - house[0];
        int answer = 0;

        /*
         * 이진 탐색을 이용해 효율적으로 최대 거리 계산
         */
        while(start <= end){
            int mid = (start + end) / 2;

            /*
             * canPlaceRouters 함수로 주어진 거리(mid)로 count개의 라우터를 설치할 수 있는지 확인
             * 1. 가능한 경우: 정답 후보로 설정하고 더 큰 거리로 탐색
             * 2. 불가능한 경우: 작은 거리로 탐색
             */
            if(canPlaceRouters(house, mid, count)){
                answer = mid;
                start = mid + 1; // 더 큰 거리로 탐색
            }
            else {
                end = mid - 1; // 더 작은 거리로 탐색
            }
        }

        /*
         * 이진 탐색을 통해 찾은 최적의 거리 반환
         */
        return answer;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * house: 집의 수
         * router: 설치할 라우터 수
         */
        int house = Integer.parseInt(st.nextToken());
        int router = Integer.parseInt(st.nextToken());

        /*
         * houses: 집들의 위치 배열
         */
        int [] houses = new int[house];
        for(int i = 0; i < house; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집들의 위치를 오름차순으로 정렬
        Arrays.sort(houses);

        // 이진 탐색을 통해 가능한 최대 거리 출력
        System.out.println(binarySearch(houses, router));
    }
}