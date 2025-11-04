import java.io.*;
import java.util.*;

public class Dijkstra13 {
    /*
     * 최초 작성일시 : 2025-04-08
     * 최초 작성시간 : 14:51
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1854
     * 문제 이름 : K번째 최단 경로
     * 문제 난이도 : 플레티넘 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    /*
     * graph : 입력받은 그래프
     * distance : 각 노드별 거리 데이터 저장 배열
     */
    static int[][] graph = new int[1001][1001];
    static ArrayList<PriorityQueue<Integer>> distance = new ArrayList<>();

    // k번째 최단 경로를 구하는 다익스트라 변형 알고리즘
// start: 시작 정점, node: 총 정점 수, k: 각 정점까지 최대 몇 개의 최단 경로를 저장할지
    static void dijkstra(int start, int node, int k) {
        // 최소 거리 기준으로 정점을 처리하기 위한 우선순위 큐
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // 시작 정점에서의 거리 0으로 초기화하고 큐에 삽입
        queue.add(new Node(start, 0));
        distance.get(1).add(0); // 시작 정점 1번에 대한 거리 리스트에 0 추가

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node now = queue.poll(); // 현재까지 가장 짧은 거리의 정점 꺼냄

            // 모든 다른 정점에 대해 확인
            for (int next = 1; next <= node; next++) {
                // 현재 정점에서 next 정점으로 가는 간선이 존재할 경우
                if (graph[now.getVertex()][next] != 0) {
                    // 현재 거리 + 간선 가중치
                    int newDist = now.getWeight() + graph[now.getVertex()][next];

                    // 아직 k개의 경로가 저장되지 않았으면 그냥 추가
                    if (distance.get(next).size() < k) {
                        distance.get(next).add(newDist);
                        queue.add(new Node(next, newDist));

                        // 이미 k개가 찼지만, 현재 거리보다 큰 값이 있다면 교체
                    } else if (distance.get(next).peek() > newDist) {
                        distance.get(next).poll(); // 가장 큰 값 제거 (우선순위 큐가 max-heap일 경우)
                        distance.get(next).add(newDist); // 새로운 더 짧은 거리 추가
                        queue.add(new Node(next, newDist));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 거리 배열 초기화 (Max Heap)
        for (int i = 0; i <= node; i++) {
            distance.add(new PriorityQueue<>(k, (o1, o2) -> o2 - o1)); // 내림차순 = Max Heap
        }

        // 간선 정보 입력
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
        }

        dijkstra(1, node, k); // 시작 정점 1

        // 출력
        for (int i = 1; i <= node; i++) {
            if (distance.get(i).size() == k) {
                bw.write(distance.get(i).peek() + "\n");
            } else {
                bw.write("-1\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

/*
 * 인접 노드를 표현한 클래스
 * 노드 번호와 가중치 저장
 */
class Node implements Comparable<Node> {
    private int vertex;
    private int weight;

    Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.weight, n.getWeight());
    }
}
