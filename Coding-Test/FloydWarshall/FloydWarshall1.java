import java.io.*;
import java.util.*;

public class FloydWarshall {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 12:05
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11404
     * 문제 이름 : 플로이드
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    static int [][] graph;
    final static int INF = Integer.MAX_VALUE;

    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){
            for(int i = 1; i <= node; i++){
                for(int j = 1; j <= node; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                if(graph[i][j] == INF){
                    bw.write("0 ");
                }
                else{
                    bw.write(graph[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int city = Integer.parseInt(br.readLine());
        int line = Integer.parseInt(br.readLine());

        graph = new int[city+1][city+1];

        for(int i = 1; i <= city; i++){
            for(int j = 1; j <= city; j++){
                if(i == j){
                    graph[i][j] = 0;
                }
                else {
                    graph[i][j] = INF;
                }
            }
        }

        for(int i = 0; i < line; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(w < graph[u][v]) {
                graph[u][v] = w;
            }
        }

        br.close();

        floydWarshall(graph, city);

        print(graph, city);

    }
}