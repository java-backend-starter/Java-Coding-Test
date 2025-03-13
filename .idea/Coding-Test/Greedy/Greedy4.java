import java.io.*;
import java.util.*;

public class Greedy4 {
    /*
     * 최초 작성일시 : 2025-03-13
     * 최초 작성시간 : 21:16
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1931
     * 문제 이름 : 회의실 배정
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력받을 회의 시간의 개수
         * values : 회의 시간을 기록하는 배열, 행은 회의 시간의 일련 번호, 열은 시작 시간(0)과 종료 시간(1)을 의미
         */
        int size = Integer.parseInt(br.readLine());
        int [][] values = new int[size][2];

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine());
            values[i][0] = Integer.parseInt(st.nextToken());
            values[i][1] = Integer.parseInt(st.nextToken());
        }

        /*
         * 회의 시간에 대해 정렬을 수행
         * 1. 종료 시간이 빠른 순으로 정렬
         * 2. 종료 시간이 같으면 시작 시간으로 정렬
         * 람다식 대신에 아래의 코드를 넣어도 무방
         */
        /*
        new Comparator<int []> () {
            @Override
            public int compare(int [] a, int [] b) {
                if(a[1] == b[1]){
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        }
         */
        Arrays.sort(values, (a, b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        /*
         * count : 곂치지 않는 회의 시간의 개수, 정답
         * end : 곂치지 않는 회의 시간을 찾기 위한 변수
         * for문 : 곂치지 않는 회의 시간을 체크
         */
        int count = 0;
        int end = -1;
        for(int i = 0; i < size; i++){
            if(values[i][0] >= end){
                count++;
                end = values[i][1];
            }
        }

        System.out.println(count);
    }
}