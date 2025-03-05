import java.io.*;
import java.util.*;

public class BFS1 {
    /*
     * 작성일시 : 2025-03-05
     * 작성시간 : 10:51
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1260
     * 문제 이름 : DFS와 BFS
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * graph : 입력받은 간선을 토대로 만든 인접 리스트
     * visited : 방문 여부를 체크하는 배열
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean [] visited;


    static BufferedWriter DFSbw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedWriter BFSbw = new BufferedWriter(new OutputStreamWriter(System.out));

    /*
     * DFS : 깊이 우선 탐색 알고리즘, 재귀 호출로 구현
     * BFS : 너비 우선 탐색 알고리즘, Queue로 구현
     */
    static void DFS(int vertex) throws IOException{
        /*
         * 노드를 방문하고 기록
         */
        visited[vertex] = true;
        DFSbw.append(vertex + " ");
        for(int v : graph.get(vertex)){
            /*
             * 인접 노드에서 방문하지 않은 노드를 방문
             */
            if(!visited[v]){
                DFS(v);
            }
        }
    }

    static void BFS(int vertex) throws IOException {
        /*
         * 시작 정점을 방문하고 기록
         */
        visited[vertex] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        BFSbw.append(vertex + " ");
        while(!queue.isEmpty()){
            /*
             * 큐에서 나온 노드에 대해서 인접 노드들을 방문하고 기록
             */
            int u = queue.poll();
            for(int v : graph.get(u)){
                if(!visited[v]){
                    visited[v] = true;
                    queue.add(v);
                    BFSbw.append(v + " ");
                }
            }
        }
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node : 노드의 개수
         * edge : 간선의 개수
         * start : 시작 정점
         */
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= node; i++){
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
         * 작은 노드부터 방문하기 위해 정렬을 수행
         */
        for(int i = 1; i <= node; i++){
            Collections.sort(graph.get(i));
        }

        visited = new boolean[node+1];
        DFS(start);
        DFSbw.append("\n");

        visited = new boolean[node+1];
        BFS(start);
        BFSbw.append("\n");

        DFSbw.flush();
        BFSbw.flush();

    }
}