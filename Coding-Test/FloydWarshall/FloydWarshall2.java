import java.io.*;
import java.util.*;

public class FloydWarshall2 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 15:02
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11403
     * 문제 이름 : 경로 찾기
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    static int [][] graph;

    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){
            for(int i = 1; i <= node; i++){
                for(int j = 1; j <= node; j++){
                    if(graph[i][k] == 1 && graph[k][j] == 1){
                        graph[i][j] = 1;
                    }
                }
            }
        }
    }

    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());

        graph = new int[node+1][node+1];

        for(int i = 1; i <= node; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= node; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        floydWarshall(graph, node);

        print(graph, node);

    }
}