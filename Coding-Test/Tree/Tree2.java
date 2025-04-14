import java.io.*;
import java.util.*;

public class Tree2 {
    /*
     * 최초 작성일시 : 2025-04-14
     * 최초 작성시간 : 14:04
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1068
     * 문제 이름 : 트리
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    /*
     * tree: 트리 구조를 저장하는 인접 리스트
     * visited: 노드 방문 여부 확인 배열
     * node: 전체 노드 수
     * root: 루트 노드 번호
     * deleted: 삭제할 노드 번호
     * answer: 리프 노드 개수 결과
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;

    static int node;
    static int root;
    static int deleted;
    static int answer = 0;

    /*
     * DFS(깊이 우선 탐색)
     * 목적: 삭제된 노드를 제외한 트리에서 리프 노드 개수를 계산
     * 방법: 자식 노드를 탐색하며, 자식이 없는 경우(=child == 0)에 리프 노드로 간주하여 answer 증가
     * 조건: 삭제된 노드는 탐색하지 않음 (next != deleted)
     */
    static void DFS(int node) {
        visited[node] = true;
        int child = 0;

        for (int next : tree.get(node)) {
            if (!visited[next] && next != deleted) {
                child++;
                DFS(next);
            }
        }

        // 자식 노드가 없으면 리프 노드로 판단
        if (child == 0) {
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드 개수 입력
        node = Integer.parseInt(br.readLine());
        visited = new boolean[node];

        // 트리 초기화 (노드 수만큼 리스트 생성)
        for (int i = 0; i < node; i++) {
            tree.add(new ArrayList<>());
        }

        // 각 노드의 부모 정보 입력
        // -1이면 루트 노드
        // 무방향 그래프처럼 양방향 연결 (자식-부모 관계 모두 저장)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < node; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(i).add(parent);
                tree.get(parent).add(i);
            }
        }

        // 삭제할 노드 번호 입력
        deleted = Integer.parseInt(br.readLine());

        // 삭제할 노드가 루트일 경우 전체 트리가 삭제되므로 리프 노드는 0개
        if (deleted == root) {
            System.out.println(0);
        } else {
            // DFS 실행 후 리프 노드 개수 출력
            DFS(root);
            System.out.println(answer);
        }
    }
}