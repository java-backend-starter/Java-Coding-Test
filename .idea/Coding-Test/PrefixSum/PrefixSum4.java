import java.io.*;
import java.util.*;

public class PrefixSum4 {
    /* 
    * 작성일시 : 2025-02-18
    * 작성시간 : 19:20
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2042
    * 문제 이름 : 구간 합 구하기
    * 문제 난이도 : 실버 Ⅰ
    *
    * 작성 목적
    *  
    * 백준에 있는 문제 풀이
    */
    static long [] datas;
    static long [] sum;

    static void makeSum(long [] numbers, long [] sum){
        for(int i = 1; i < numbers.length; i++){
            sum[i] = numbers[i] + sum[i-1];
        }
    }

    static void changeData(long [] numbers, int index, long value){
        numbers[index] = value;
    }

    static long prefixSum(long [] sum, int start, int end){
        return sum[end] - sum[start-1];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        datas = new long[size+1];
        sum = new long[size+1];

        for(int i = 1; i <= size; i++){
            datas[i] = Long.parseLong(br.readLine());
        }

        makeSum(datas, sum);

        for(int i = 0; i < (change + query); i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command == 1){
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                changeData(datas, index, value);
                makeSum(datas, sum);
            }
            else if(command == 2){
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                bw.append(prefixSum(sum, start, end) + "\n");
            }
            else {
                continue;
            }
        }

        bw.flush();
    }
}