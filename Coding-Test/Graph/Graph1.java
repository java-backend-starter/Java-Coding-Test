import java.io.*;
import java.util.*;

public class Graph1 {
    /*
     * 최초 작성일시 : 2025-04-04
     * 최초 작성시간 : 12:33
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 18352
     * 문제 이름 : 특정 거리의 도시 찾기
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int [] visited;
    static ArrayList<Integer> result = new ArrayList<>();

    public class Main {
        // 그래프를 저장할 인접 리스트 (2차원 ArrayList)
        static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // 방문 여부 및 최단 거리 저장 배열 (-1로 초기화)
        static int[] visited;

        // 특정 거리(K)에 해당하는 도시를 저장할 리스트
        static ArrayList<Integer> result = new ArrayList<>();

        // 너비 우선 탐색 (BFS)
        static void BFS(int node) {
            Queue<Integer> queue = new LinkedList<>(); // BFS를 위한 큐 생성
            visited[node] = 0; // 시작 노드의 거리를 0으로 설정
            queue.add(node); // 큐에 시작 노드 삽입

            while (!queue.isEmpty()) {
                int now = queue.poll(); // 현재 노드를 꺼냄

                // 현재 노드와 연결된 모든 인접 노드를 확인
                for (int next : graph.get(now)) {
                    // 방문하지 않은 노드라면
                    if (visited[next] == -1) {
                        visited[next] = visited[now] + 1; // 최단 거리 갱신
                        queue.add(next); // 큐에 추가
                    }
                }
            }
        }

        public static void main(String[] args) throws IOException {
            // 입력을 빠르게 받기 위한 BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 출력을 빠르게 하기 위한 BufferedWriter
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 첫 번째 입력 줄을 읽어와서 공백 기준으로 분리
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 입력값: 도시 수, 도로 수, 목표 거리 K, 출발 도시 번호 X
            int city = Integer.parseInt(st.nextToken());
            int load = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            // 방문 배열 초기화 (모든 값을 -1로 설정)
            visited = new int[city + 1];
            Arrays.fill(visited, -1);

            // 그래프 초기화 (도시 개수만큼 리스트 생성)
            for (int i = 0; i <= city; i++) {
                graph.add(new ArrayList<>());
            }

            // 도로 정보 입력 받기 (단방향 그래프)
            for (int i = 0; i < load; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
            }

            // BFS 실행 (출발 도시부터 탐색 시작)
            BFS(start);

            // 최단 거리가 K인 도시를 결과 리스트에 추가
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == distance) {
                    result.add(i);
                }
            }

            // 결과 리스트를 오름차순 정렬
            Collections.sort(result);

            // 결과 출력 (거리가 K인 도시가 없다면 -1 출력)
            if (result.isEmpty()) {
                bw.append("-1\n");
            } else {
                for (int value : result) {
                    bw.append(value + "\n");
                }
            }

            // 출력 버퍼 비우기 (flush)
            bw.flush();
        }
    }
}