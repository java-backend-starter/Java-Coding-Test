import java.io.*;
import java.util.*;

public class BinarySearch5 {
    /*
     * 최초 작성일시 : 2025-03-13
     * 최초 작성시간 : 21:40
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 10815
     * 문제 이름 : 숫자 카드
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */

    static int binarySearch(int [] values, int start, int end, int key){
        if (start > end) {  // 탐색 범위를 벗어난 경우
            return 0;
        }

        int mid = (start + end) / 2;
        if (values[mid] == key) {
            return 1;
        } else if (values[mid] < key) {
            return binarySearch(values, mid + 1, end, key);  // 오른쪽 탐색
        } else {
            return binarySearch(values, start, mid - 1, key);  // 왼쪽 탐색
        }
    }

    public static void main(String [] args) throws IOException {
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
            bw.append(binarySearch(values, 0, values.length-1, finds[i]) + " ");
        }
        bw.flush();
    }
}