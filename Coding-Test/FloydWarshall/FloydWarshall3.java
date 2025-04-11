import java.io.*;
import java.util.*;

public class FloydWarshall3 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 15:28
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1389
     * 문제 이름 : 케빈 베이컨의 6단계 법칙
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 친구 관계를 나타내는 그래프 배열
    static int [][] graph;

    // 플로이드-워셜 알고리즘 (모든 정점 쌍 간 최단 거리 계산)
    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){ // 경유 노드
            for(int i = 1; i <= node; i++){ // 출발 노드
                for(int j = 1; j <= node; j++){ // 도착 노드
                    // i -> j보다 i -> k -> j가 더 짧으면 갱신
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());   // 사람 수 (노드 수)
        int friend = Integer.parseInt(st.nextToken()); // 친구 관계 수 (간선 수)

        graph = new int[node+1][node+1]; // 인덱스 1부터 사용

        // 그래프 초기화
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                if(i == j){
                    graph[i][j] = 0; // 자기 자신과의 거리는 0
                }
                else {
                    graph[i][j] = 10000001; // 초기값은 충분히 큰 수 (무한대 의미)
                }
            }
        }

        // 친구 관계 입력
        for(int i = 0; i < friend; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 친구 A
            int v = Integer.parseInt(st.nextToken()); // 친구 B

            graph[u][v] = 1; // A <-> B는 거리 1
            graph[v][u] = 1;
        }

        br.close(); // 입력 스트림 닫기

        // 모든 쌍 최단 거리 계산
        floydWarshall(graph, node);

        int min = Integer.MAX_VALUE; // 가장 작은 거리 합 저장
        int answer = -1;             // 정답 노드 번호

        // 각 사람마다 다른 사람들과의 거리 합 계산
        for(int i = 1; i <= node; i++){
            int temp = 0;
            for(int j = 1; j <= node; j++){
                temp += graph[i][j]; // i번 사람의 전체 거리 합
            }
            // 최소 거리 합이면 정답 갱신
            if(min > temp){
                min = temp;
                answer = i;
            }
        }

        // 케빈 베이컨 수가 가장 낮은 사람 번호 출력
        System.out.println(answer);
    }
}