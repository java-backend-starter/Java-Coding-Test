import java.io.*;
import java.util.*;

public class LCA2 {
    /*
     * 최초 작성일시 : 2025-04-17
     * 최초 작성시간 : 13:14
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11438
     * 문제 이름 : LCA2
     * 문제 난이도 : 플레티넘 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    /*
     * max : 최대 2^max 깊이까지 조상을 저장할 수 있도록 하는 변수 (log2(node) + 1)
     * depth[i] : i번 노드의 깊이
     * parent[i][j] : i번 노드의 2^j번째 조상
     * tree : 트리 구조를 인접 리스트 형태로 저장
     */
    static int max;
    static int[] depth;
    static int[][] parent;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    /**
     * DFS를 통해 각 노드의 깊이와 2^0번째 부모(즉, 바로 위 부모)를 설정
     * @param node 현재 노드
     * @param par 현재 노드의 부모 노드
     * @param dep 현재 노드의 깊이
     */
    static void DFS(int node, int par, int dep) {
        depth[node] = dep;          // 현재 노드의 깊이 설정
        parent[node][0] = par;      // 바로 위 부모 저장

        for (int next : tree.get(node)) {
            if (next != par) {
                DFS(next, node, dep + 1); // 자식 노드로 DFS 재귀 호출
            }
        }
    }

    /**
     * 이진 리프팅을 위한 parent 배열 초기화
     * parent[i][j] = i번 노드의 2^j번째 조상
     * @param size 노드 수
     */
    static void init(int size) {
        for (int j = 1; j < max; j++) {
            for (int i = 1; i <= size; i++) {
                if (parent[i][j - 1] != -1) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    /**
     * 두 노드의 최소 공통 조상(LCA)을 구하는 함수
     * @param u 노드 u
     * @param v 노드 v
     * @return u와 v의 최소 공통 조상
     */
    static int LCA(int u, int v) {
        // 항상 u가 더 깊은 노드가 되도록 설정
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // u를 v의 깊이까지 끌어올림
        for (int i = max - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        // 이미 공통 조상인 경우
        if (u == v) return u;

        // 공통 조상이 될 때까지 이진 리프팅
        for (int i = max - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        // 최종적으로 공통 조상의 바로 아래에 위치한 두 노드의 부모가 LCA
        return parent[u][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine()); // 노드 개수 입력

        // 최대 log2(node) 만큼의 조상 저장 필요
        max = (int) Math.ceil(Math.log(node) / Math.log(2)) + 1;

        // 배열 초기화 (1번 노드부터 사용)
        depth = new int[node + 1];
        parent = new int[node + 1][max];
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int query = Integer.parseInt(br.readLine()); // 쿼리 수 입력

        DFS(1, -1, 0); // 루트 노드를 기준으로 DFS 수행

        init(node); // 이진 리프팅을 위한 parent 테이블 초기화

        // 쿼리 처리
        for (int i = 0; i < query; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(LCA(a, b) + "\n"); // 최소 공통 조상 출력
        }

        bw.flush(); // 출력 버퍼 비우기
    }
}