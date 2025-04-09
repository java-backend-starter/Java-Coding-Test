import java.io.*;
import java.util.*;

public class Graph2 {
    /*
     * 최초 작성일시 : 2025-04-04
     * 최초 작성시간 : 13:25
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1325
     * 문제 이름 : 효율적인 해킹
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;

    /*
     * BFS: 시작 노드로부터 도달할 수 있는 모든 노드를 방문하고,
     * 해당 노드의 result 값을 증가시킴
    */
    static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    result[next]++;  // next 노드는 node로부터 도달 가능하므로 result 증가
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken()); // 노드(컴퓨터) 수
        int edge = Integer.parseInt(st.nextToken()); // 간선(신뢰 관계) 수

        result = new int[node + 1];

        // 그래프 초기화
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력 (u -> v: u가 v를 해킹 가능)
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v); // 방향 그래프 구성
        }

        // 각 노드에서 BFS 수행
        for (int i = 1; i <= node; i++) {
            visited = new boolean[node + 1]; // 방문 배열 매번 초기화
            BFS(i); // i번 노드에서 BFS 수행
        }

        // 가장 많이 해킹당한(도달된) 횟수 찾기
        int max = result[1];
        for (int i = 2; i <= node; i++) {
            max = Math.max(max, result[i]);
        }

        // 최대 해킹당한 횟수를 가진 노드들 출력
        for (int i = 1; i <= node; i++) {
            if (result[i] == max) {
                bw.append(i + " ");
            }
        }
        bw.flush(); // 출력 버퍼 비우기
    }
}