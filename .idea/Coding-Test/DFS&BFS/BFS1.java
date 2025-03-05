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
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean [] visited;


    static BufferedWriter DFSbw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedWriter BFSbw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void DFS(int vertex) throws IOException{
        visited[vertex] = true;
        DFSbw.append(vertex + " ");
        for(int v : graph.get(vertex)){
            if(!visited[v]){
                DFS(v);
            }
        }
    }

    static void BFS(int vertex) throws IOException {
        visited[vertex] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        BFSbw.append(vertex + " ");
        while(!queue.isEmpty()){
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