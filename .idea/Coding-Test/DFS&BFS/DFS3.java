import java.io.*;
import java.util.*;

public class DFS3 {
    /*
     * 작성일시 : 2025-03-04
     * 작성시간 : 15:32
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 13023
     * 문제 이름 : ABCDE
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * graph : 그래프 자료구조
     * visited : 방문 여부를 저장
     * arrive : DFS로 탐색한 노드의 깊이가 5인지 여부를 체크
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean [] visited;
    static boolean arrive;

    /*
     * 2023번과 같이 DFS 함수를 변형
     * 1. 탐색 깊이가 5일 때 탐색 종류
     * 2. 그 전에는 인접한 노드들을 모두 탐색해보고 탐색 깊이가 5가 아니면 방문하지 않은 것으로 간주
     */
    static void DFS(int vertex, int depth){
        if(depth == 5 || arrive){
            arrive = true;
            return;
        }

        visited[vertex] = true;
        for(int v : graph.get(vertex)){
            if(!visited[v]){
                DFS(v, depth+1);
            }
        }
        visited[vertex] = false;
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node : 정점의 개수
         * edge : 간선의 개수
         */
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        visited = new boolean[node+1];
        for(int i = 0; i <= node; i++){
            visited[i] = false;
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        /*
         * 모든 노드를 탐색하면서 문제의 조건을 충족시키는 노드들이 있는지 확인
         */
        for(int i = 1; i <= node; i++){
            DFS(i, 1);
            if(arrive){
                break;
            }
        }
        System.out.println(arrive ? "1" : "0");
    }
}