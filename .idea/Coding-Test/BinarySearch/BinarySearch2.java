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
     * 문제 이름 : 평균
     * 문제 난이도 : 브론즈 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static int blueray(int [] values, int start, int end, int bluerays){
        while(start <= end){
            int mid = (start + end)/2;
            int sum = 0;
            int count = 0;
            for(int i = 0; i < values.length; i++){
                if(sum + values[i] > mid){
                    count++;
                    sum = 0;
                }
                sum += values[i];
            }
            if(sum != 0){
                count++;
            }

            if(count > bluerays){
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start;
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