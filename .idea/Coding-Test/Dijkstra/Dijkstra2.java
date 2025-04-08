import java.io.*;
import java.util.*;

public class Dijkstra12{
    /*
     * 최초 작성일시 : 2025-04-08
     * 최초 작성시간 : 10:32
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1916
     * 문제 이름 : 최소비용 구하기
     * 문제 난이도 : 골드 Ⅴ
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

    // 다익스트라 알고리즘: 시작 정점 start로부터 모든 정점까지의 최단 거리 계산
    static void dijkstra(int start) {
        // 최소 거리를 기준으로 정점을 꺼내기 위한 우선순위 큐 생성
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // 시작 정점을 큐에 넣고, 시작 정점까지의 거리 0으로 초기화
        queue.offer(new Node(start, 0));
        distance[start] = 0;

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            // 현재까지 가장 가까운 정점을 꺼냄
            Node node = queue.poll();
            int now = node.getVertex();

            // 아직 방문하지 않았다면 처리 시작
            if (!visited[now]) {
                visited[now] = true; // 방문 체크

                // 현재 정점과 연결된 모든 인접 정점 확인
                for (Node n : graph.get(now)) {
                    int next = n.getVertex();    // 다음 정점
                    int weight = n.getWeight();  // 간선 가중치

                    // 더 짧은 경로가 발견되었을 경우 갱신
                    if (distance[next] > distance[now] + weight) {
                        distance[next] = distance[now] + weight;
                        queue.add(new Node(next, distance[next])); // 갱신된 거리로 큐에 추가
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * node : 노드 수
         * edge : 간선 수
         */
        int node = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());

        for(int i = 0; i <= node; i++){
            graph.add(new ArrayList<>());
        }
        
        /*
         * 초기화
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
        /*
         * start : 출방 도시
         * end : 도착 도시
         */
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        /*
         * start에서 출발해서 end에 도착했을 때 비용 출력
         */
        System.out.println(distance[end]);
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