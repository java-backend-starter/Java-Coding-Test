import java.io.*;
import java.util.*;

public class Graph3 {
    /*
     * 최초 작성일시 : 2025-04-04
     * 최초 작성시간 : 14:33
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1707
     * 문제 이름 : 이분 그래프
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    // 인접 리스트로 그래프 저장
    static ArrayList<ArrayList<Integer>> graph;
    // 방문 여부 저장 배열
    static boolean[] visited;
    // 각 노드의 색깔 (0 또는 1)
    static int[] check;
    // 이분 그래프 여부 판단 변수
    static boolean isEven;

    // DFS를 통해 그래프 탐색 및 이분 그래프인지 검사
    static void DFS(int node) {
        visited[node] = true; // 현재 노드 방문 처리

        // 인접 노드들 순회
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                // 방문하지 않은 경우, 현재 노드와 다른 색 지정
                visited[next] = true;
                check[next] = (check[node] + 1) % 2;
                DFS(next); // 재귀적으로 다음 노드 탐색
            } else if (check[next] == check[node]) {
                // 이미 방문했고 색이 같다면 이분 그래프가 아님
                isEven = false;
            }
        }
    }

    // 메인 메서드
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader, 출력용 BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine()); // 테스트케이스 개수 입력
        StringTokenizer st;

        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken()); // 노드 수
            int edge = Integer.parseInt(st.nextToken()); // 간선 수

            // 초기화
            graph = new ArrayList<>();
            visited = new boolean[node + 1];
            check = new int[node + 1];

            // 그래프 리스트 초기화
            for (int j = 0; j <= node; j++) {
                graph.add(new ArrayList<>());
            }

            // 간선 정보 입력 및 양방향 연결
            for (int j = 0; j < edge; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // 이분 그래프 여부 초기화
            isEven = true;

            // 그래프의 모든 정점을 검사
            for (int j = 1; j <= node; j++) {
                if (!visited[j]) { // 아직 방문하지 않았다면 탐색 시작
                    check[j] = 0;  // 시작 노드 색 설정
                    DFS(j);
                }

                if (!isEven) break; // 이미 이분 그래프 아님이 판별되면 종료
            }

            // 결과 출력
            bw.append((isEven ? "YES" : "NO") + "\n");
        }
        bw.flush(); // 출력 버퍼 비우기
    }
}