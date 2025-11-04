import java.io.*;
import java.util.*;

public class TopologicalSort1 {
    /*
     * 최초 작성일시 : 2025-04-07
     * 최초 작성시간 : 11:54
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2252
     * 문제 이름 : 줄세우기
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    /*
     * 방향 그래프 (사이클 없음)
     * indegree: 각 노드의 진입 차수
     * result: 위상 정렬 결과
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] indegree;
    static ArrayList<Integer> result = new ArrayList<>();

    /*
     * 위상 정렬 수행
     * - 진입 차수가 0인 노드부터 시작
     * - 큐를 이용해 순서대로 탐색
     */
    static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);
            for (int next : graph.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

    /*
     * 위상 정렬 결과 출력
     */
    static void print(ArrayList<Integer> values) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int value : values) {
            bw.write(value + " ");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());   // 노드 수
        int edge = Integer.parseInt(st.nextToken());   // 간선 수

        indegree = new int[node + 1];
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 구성 및 진입 차수 초기화
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            indegree[v]++;
        }

        // 정점 방문 순서가 일정하도록 인접 리스트 정렬
        for (int i = 1; i <= node; i++) {
            Collections.sort(graph.get(i));
        }

        topologicalSort();
        print(result);

        br.close();
    }
}