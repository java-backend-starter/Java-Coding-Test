import java.io.*;
import java.util.*;

public class Dijkstra1 {
    /*
     * 최초 작성일시 : 2025-04-08
     * 최초 작성시간 : 10:01
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1753
     * 문제 이름 : 최단경로
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    /*
     * graph : 입력받을 그래프
     * visited : 방문 여부를 저장하는 배열
     * distance : 가중치 배열
     */
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean [] visited;
    static int [] distance;

    /*
     * 다익스트라 알고리즘
     * - 하나의 시작 정점에서 다른 모든 정점까지의 최단 경로를 구함
     * - 방향/무방향 그래프 모두 가능 (단, 음의 가중치는 허용되지 않음)
     * - 우선순위 큐를 사용해 가장 가까운 정점부터 처리
     * - 가중치가 있는 그래프에 BFS 아이디어를 확장한 방식
     */
    static void dijkstra(int start) {
        // 최소 거리 우선순위로 정점 처리
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0)); // 시작 정점을 큐에 삽입
        distance[start] = 0; // 시작 정점까지의 거리는 0

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // 현재까지 가장 짧은 거리의 정점 꺼냄
            int now = node.getVertex();

            // 방문하지 않은 정점이라면 처리
            if (!visited[now]) {
                visited[now] = true;

                // 현재 정점의 모든 인접 정점 확인
                for (Node next : graph.get(now)) {
                    int nextVertex = next.getVertex();
                    int newDist = distance[now] + next.getWeight();

                    // 더 짧은 경로가 발견되면 갱신
                    if (!visited[nextVertex] && distance[nextVertex] > newDist) {
                        distance[nextVertex] = newDist;
                        queue.add(new Node(nextVertex, newDist));
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node : 노드 개수
         * edge : 간선 개수
         * start : 시작 노드
         */
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i <= node; i++){
            graph.add(new ArrayList<>());
        }
        /*
         * 초기화
         * distance는 무한대로 초기화해야 하기때문에 Integer.MAX_VALUE로 값을 채움
         */
        visited = new boolean[node + 1];
        distance = new int[node + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dijkstra(start);

        for(int i = 1; i <= node; i++){
            bw.write((visited[i] ? distance[i] : "INF") + "\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}

/*
 * 인접 노드를 표현한 클래스
 * 노드 번호와 가중치 저장
 */
class Node implements Comparable<Node>{
    private int vertex;
    private int weight;

    Node(int vertex, int weight){
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
    public int compareTo(Node n){
        if(this.weight > n.getWeight()){
            return 1;
        }
        else {
            return -1;
        }
    }
}