import java.io.*;
import java.util.*;

public class TopologicalSort3 {
    /*
     * 최초 작성일시 : 2025-04-07
     * 최초 작성시간 : 13:01
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1948
     * 문제 이름 : 임계 경로
     * 문제 난이도 : 플레티넘 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    // graph: 정방향 그래프 (도로 정보 저장)
    // reverse: 역방향 그래프 (임계 경로 추적용)
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Node>> reverse = new ArrayList<>();

    static int[] indegree;    // 각 노드의 진입 차수
    static boolean[] visited; // 역방향 탐색 시 방문 여부
    static int[] result;      // 출발지에서 각 노드까지 도달하는 데 걸리는 최대 시간
    static int count = 0;     // 임계 경로에 포함된 간선 수

    /*
     * 위상 정렬을 수행하며 각 노드까지 도달하는 최대 시간을 계산
     * 출발 도시에서 시작하여 가능한 모든 경로를 따라가며 최대값 갱신
     */
    static void topologicalSort(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Node next : graph.get(now)) {
                int nextNode = next.getNode();
                int time = next.getWeight();

                // 현재 경로를 통한 시간 갱신
                result[nextNode] = Math.max(result[nextNode], result[now] + time);

                // 진입 차수 감소
                indegree[nextNode]--;
                if (indegree[nextNode] == 0) {
                    queue.offer(nextNode);
                }
            }
        }
    }

    /*
     * 역방향 BFS를 통해 최장 경로에 포함된 간선의 수를 세는 과정
     * 도착 도시부터 시작해서 출발 도시까지 거꾸로 추적
     * 조건: 해당 간선이 최장 경로의 일부인지 확인(result[now] == result[prevNode] + time)
     */
    static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Node prev : reverse.get(now)) {
                int prevNode = prev.getNode();
                int time = prev.getWeight();

                // 임계 경로 조건 만족 시 간선 수 증가
                if (result[now] == result[prevNode] + time) {
                    count++;

                    // 방문하지 않은 노드는 큐에 추가
                    if (!visited[prevNode]) {
                        visited[prevNode] = true;
                        queue.offer(prevNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int city = Integer.parseInt(br.readLine());  // 도시 수
        int load = Integer.parseInt(br.readLine());  // 도로 수

        indegree = new int[city + 1];
        visited = new boolean[city + 1];
        result = new int[city + 1];

        // 그래프 초기화
        for (int i = 0; i <= city; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for (int i = 0; i < load; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발 도시
            int v = Integer.parseInt(st.nextToken()); // 도착 도시
            int w = Integer.parseInt(st.nextToken()); // 소요 시간

            graph.get(u).add(new Node(v, w));
            reverse.get(v).add(new Node(u, w));
            indegree[v]++;  // 도착 도시의 진입 차수 증가
        }

        // 출발 도시와 도착 도시 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 위상 정렬을 통해 최장 경로 계산
        topologicalSort(start);

        // 최장 경로에 포함된 간선 수 계산
        BFS(end);

        // 결과 출력: 총 소요 시간, 임계 경로 간선 수
        System.out.println(result[end]);
        System.out.println(count);
    }
}

/*
 * Node: 연결된 도시와 소요 시간 정보를 담는 클래스
 */
class Node {
    private int node;
    private int weight;

    Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public int getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}