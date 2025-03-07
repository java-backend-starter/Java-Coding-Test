import java.io.*;
import java.util.*;

public class BinarySearch1 {
    /*
     * 최초 작성일시 : 2025-03-07
     * 최초 작성시간 : 10:07
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1920
     * 문제 이름 : 평균
     * 문제 난이도 : 브론즈 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static int binarySearch(int [] values, int start, int end, int key){
        if(start == end){
            return key == values[start] ? 1 : 0;
        }

        int mid = (start + end)/2;
        if(key == values[mid]){
            return 1;
        }
        else if(key < values[mid]){
            return binarySearch(values, start, mid - 1, key);
        }
        else {
            return binarySearch(values, mid + 1, end, key);
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        int find = Integer.parseInt(br.readLine());
        int [] finds = new int[find];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < find; i++){
            finds[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);
        for(int i = 0; i < find; i++){
            bw.append(binarySearch(values, 0, values.length-1, finds[i]) + "\n");
        }
        bw.flush();
    }
}