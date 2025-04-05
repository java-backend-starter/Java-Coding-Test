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
     * 문제 난이도 :
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    static int lowBounder(int [] values, int value){
        int result = values.length;
        int start = 0;
        int end = values.length-1;

        int mid;
        while(start <= end){
            mid = (start + end)/2;

            if(values[mid] < value){
                start = mid + 1;
            }
            else{
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
            mid = (start + end)/2;

            if(values[mid] <= value){
                start = mid + 1;
            }
            else {
                result = mid;
                end = mid - 1;
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);

    }
}