import java.io.*;
import java.util.*;

public class DFS1 {
    /*
     * 작성일시 : 2025-03-04
     * 작성시간 : 14:37
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11724
     * 문제 이름 : 연결 요소의 개수
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * graph : 노드와 간선을 표현하기 위한 그래프 자료구조
     * visited : 방문 여부를 표시
     * count : DFS 함수 호출 횟수로 DFS의 정의상 DFS의 호출 횟수가 문제에서 요구하는 연결 요소의 개수
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean [] visited;
    static int count = 0;

    /*
     * vertex를 방문하고 vertex와 인접한 노드들 중 방문하지 않은 노드를 방문
     */
    static void DFS(int vertex){
        visited[vertex] = true;
        for(int v : graph.get(vertex)){
            if(!visited[v]){
                DFS(v);
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

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
         * 모든 노드를 DFS 함수로 탐색하면서 연결 요소의 개수를 파악
         */
        for(int i = 1; i <= node; i++){
            if(!visited[i]){
                count++;
                DFS(i);
            }
        }

        System.out.println(count);
    }
}