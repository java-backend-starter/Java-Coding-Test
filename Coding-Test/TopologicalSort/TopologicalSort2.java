import java.io.*;
import java.util.*;

public class TopologicalSort2 {
    /*
     * 최초 작성일시 : 2025-04-07
     * 최초 작성시간 : 12:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2252
     * 문제 이름 : 게임 개발
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] indegree;     // 각 건물의 진입 차수
    static int[] buildCost;    // 각 건물의 건설 비용
    static int[] result;       // 누적 건설 시간 (선행 건물 포함)

    /*
     * 위상 정렬 수행
     * - 진입 차수가 0인 노드부터 시작
     * - 인접 노드 방문 시 진입 차수 감소 및 누적 시간 갱신
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
            for (int next : graph.get(now)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[now] + buildCost[now]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }

    /*
     * 결과 출력
     * - 누적 시간(result) + 자신의 건설 비용(buildCost)을 출력
     */
    static void print(int[] values, int[] costs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < values.length; i++) {
            bw.write((values[i] + costs[i]) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int buildings = Integer.parseInt(br.readLine());
        indegree = new int[buildings + 1];
        buildCost = new int[buildings + 1];
        result = new int[buildings + 1];

        // 그래프 초기화
        for (int i = 0; i <= buildings; i++) {
            graph.add(new ArrayList<>());
        }

        // 입력 처리
        // 첫 값은 건설 시간, 이후 값들은 선행 건물 번호 (-1로 종료)
        StringTokenizer st;
        for (int i = 1; i <= buildings; i++) {
            st = new StringTokenizer(br.readLine());
            buildCost[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int u = Integer.parseInt(st.nextToken());
                if (u == -1) break;
                graph.get(u).add(i);
                indegree[i]++;
            }
        }

        topologicalSort();
        print(result, buildCost);
        br.close();
    }
}