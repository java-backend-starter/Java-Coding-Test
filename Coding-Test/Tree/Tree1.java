import java.io.*;
import java.util.*;

public class Tree1 {
    /*
     * 최초 작성일시 : 2025-04-14
     * 최초 작성시간 : 13:40
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11725
     * 문제 이름 : 트리의 부모 찾기
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    /*
     * tree: 트리 구조를 저장하는 인접 리스트
     * visited: 노드 방문 여부 확인 배열
     * result: 각 노드의 부모 노드를 저장하는 배열
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[] result;

    /*
     * DFS(깊이 우선 탐색)
     * 목적: 각 노드의 부모 노드를 찾아 result 배열에 저장
     * 방법: 자식 노드를 방문할 때 result[next] = node로 부모 저장 (next: 자식, node가 부모)
     * 즉, 현재 노드를 부모로 두고 자식 노드를 탐색하며 기록함
     */
    static void DFS(int node) {
        visited[node] = true;
        for (int next : tree.get(node)) {
            if (!visited[next]) {
                result[next] = node;
                DFS(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 노드 개수 입력
        int node = Integer.parseInt(br.readLine());

        visited = new boolean[node + 1];
        result = new int[node + 1];

        // 트리 초기화
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 간선 정보 입력 (무방향 그래프)
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree.get(s).add(e);
            tree.get(e).add(s);
        }

        // DFS를 통해 부모 노드 기록 (루트는 1번 노드)
        DFS(1);

        // 루트(1번)를 제외한 각 노드의 부모 노드 출력
        for (int i = 2; i <= node; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }
}