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
    // 그래프를 저장할 2차원 배열 선언
    static int [][] graph;
    // 무한대를 표현하기 위한 상수 (int형 최대값)
    final static int INF = Integer.MAX_VALUE;

    // 플로이드-워셜 알고리즘 구현
    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){ // 중간 정점
            for(int i = 1; i <= node; i++){ // 시작 정점
                for(int j = 1; j <= node; j++){ // 도착 정점
                    // i에서 j로 가는 비용보다, i -> k -> j 경로가 더 짧으면 갱신
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    // 결과 출력 함수
    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                // 도달할 수 없는 경우 0 출력
                if(graph[i][j] == INF){
                    bw.write("0 ");
                }
                else{
                    // 최소 비용 출력
                    bw.write(graph[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush(); // 출력 버퍼 비우기
        bw.close(); // 출력 스트림 닫기
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int city = Integer.parseInt(br.readLine()); // 도시(노드) 수 입력
        int line = Integer.parseInt(br.readLine()); // 간선 수 입력

        graph = new int[city+1][city+1]; // 그래프 초기화 (1번 인덱스부터 사용)

        // 그래프 초기화
        for(int i = 1; i <= city; i++){
            for(int j = 1; j <= city; j++){
                if(i == j){
                    graph[i][j] = 0; // 자기 자신으로 가는 비용은 0
                }
                else {
                    graph[i][j] = INF; // 초기에는 모든 비용을 무한대로 설정
                }
            }
        }

        // 간선 정보 입력 및 그래프 갱신
        for(int i = 0; i < line; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발 도시
            int v = Integer.parseInt(st.nextToken()); // 도착 도시
            int w = Integer.parseInt(st.nextToken()); // 비용

            // 같은 경로에 대해 더 작은 비용이 있다면 갱신
            if(w < graph[u][v]) {
                graph[u][v] = w;
            }
        }

        br.close(); // 입력 스트림 닫기

        // 플로이드-워셜 알고리즘 수행
        floydWarshall(graph, city);

        // 결과 출력
        print(graph, city);
    }
}