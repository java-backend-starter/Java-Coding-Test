import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum1 {
    /* 
    * 최초 작성일시 : 2025-02-07
    * 최초 작성시간 : 21:53
    * 최초 작성자 : 정성환
    * 
    * 마지막 작성일시 : 
    * 마지막 작성시간 : 
    * 마지막 작성자 : 
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11659
    * 문제 이름 : 구간 합 구하기 4
    * 문제 난이도 : 실버 Ⅲ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int [] datas = new int[size+1];

        for(int i = 1; i < datas.length; i++){
            datas[i] = Integer.parseInt(st.nextToken()) + datas[i-1];
        }

        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int sum = datas[end] - datas[start-1];
            bw.append(sum + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
