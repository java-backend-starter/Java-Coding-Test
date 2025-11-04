import java.io.*;
import java.util.*;

public class LCA1 {
    /*
     * 최초 작성일시 : 2025-04-17
     * 최초 작성시간 : 11:41
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11437
     * 문제 이름 : LCA
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 트리를 인접 리스트 형태로 저장
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    /*
     * depth[i] : i번 노드의 깊이 (루트는 0)
     * parent[i] : i번 노드의 부모 노드 번호
     * visited[i] : BFS 수행 중 방문 여부를 체크
     */
    static int[] depth;
    static int[] parent;
    static boolean[] visited;

    /**
     * 두 노드의 최소 공통 조상(LCA: Lowest Common Ancestor)을 구하는 함수
     * @param a 노드 a
     * @param b 노드 b
     * @return 노드 a와 b의 최소 공통 조상
     */
    static int lca(int a, int b) {
        // 항상 a가 더 깊은 노드가 되도록 설정
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이를 맞추기 위해 a를 부모 방향으로 이동
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        // 두 노드가 같아질 때까지 동시에 부모를 따라 이동
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        // 결국 a와 b가 만나는 지점이 최소 공통 조상
        return a;
    }

    /**
     * BFS(너비 우선 탐색)를 통해 각 노드의 깊이(depth)와 부모(parent)를 설정하는 함수
     * @param node 탐색 시작 노드 (보통 루트 노드 1번)
     */
    static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);         // 시작 노드를 큐에 추가
        visited[node] = true;    // 시작 노드를 방문 처리

        /*
         * level : 현재 탐색 중인 깊이
         * nowSize : 현재 레벨에서의 노드 수
         * count : 현재 깊이에서 처리한 노드 수
         */
        int level = 1;
        int nowSize = 1;
        int count = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();  // 큐에서 노드를 꺼냄

            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;     // 방문 처리
                    queue.add(next);          // 자식 노드 큐에 추가
                    parent[next] = now;       // 현재 노드를 자식의 부모로 저장
                    depth[next] = level;      // 자식 노드의 깊이를 현재 레벨로 저장
                }
            }

            count++;  // 현재 깊이에서 처리한 노드 수 증가

            // 현재 깊이의 모든 노드를 처리했으면 다음 깊이로 이동
            if (count == nowSize) {
                count = 0;
                nowSize = queue.size(); // 다음 깊이에 있는 노드 수
                level++;                // 깊이 증가
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine()); // 노드의 개수 입력

        /*
         * 트리 정보 초기화 (1번 노드부터 시작하기 위해 node+1 크기로 배열 생성)
         */
        depth = new int[node + 1];
        parent = new int[node + 1];
        visited = new boolean[node + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 정보 입력 (트리는 node - 1개의 간선으로 구성)
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v); // u와 v는 양방향 연결
            tree.get(v).add(u);
        }

        int query = Integer.parseInt(br.readLine()); // 최소 공통 조상을 구할 쿼리 수

        BFS(1); // 루트 노드(1번)를 기준으로 트리 탐색하여 depth와 parent 설정

        // 각 쿼리마다 최소 공통 조상 출력
        for (int i = 0; i < query; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b) + "\n"); // 최소 공통 조상을 출력
        }

        bw.flush(); // 출력 버퍼 비우기
    }
}