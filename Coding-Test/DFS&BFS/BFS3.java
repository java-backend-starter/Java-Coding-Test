import java.io.*;
import java.util.*;

public class BFS3{
    /*
     * 작성일시 : 2025-03-06
     * 작성시간 : 19:45
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1167
     * 문제 이름 : 트리의 지름
     * 문제 난이도 : 골드 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * graph : 그래프 자료구조
     * visited : 방문 여부를 저장하는 배열
     * distance : 가중치를 저장하는 배열
     */
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean [] visited;
    static long [] distance;

    /*
     * 너비 우선 탐색 알고리즘
     * 인접 노드를 방문하면서 가중치를 업데이트(distance[next.getNode()] = distance[now] + next.getWeight())
     * 시작 노드는 가중치가 0.
     */
    static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(Node next : graph.get(now)){
                if(!visited[next.getNode()]){
                    visited[next.getNode()] = true;
                    queue.add(next.getNode());
                    distance[next.getNode()] = distance[now] + next.getWeight();
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * node : 노드의 개수
         */
        int node = Integer.parseInt(br.readLine());
        visited = new boolean[node + 1];
        distance = new long[node + 1];
        for(int i = 0; i <= node; i++){
            graph.add(new ArrayList<>());
        }

        /*
         * u : 노드 번호
         * vertex : u와 인접한 노드
         * weight : u와 vertx 사이의 거리
         * u와 연결된 노드들을 저장하는 반복문으로 -1이 나올 때까지 반복
         */
        for(int i = 0; i < node; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            while(true){
                int vertex = Integer.parseInt(st.nextToken());
                if(vertex == -1){
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Node(vertex, weight));
            }
        }

        /*
         * BFS(1) : distance 배열로 가장 멀리 떨어져 있는 노드를 찾음
         * max : BFS(1)을 수행했을 때 가장 멀리 떨어져 있는 노드
         */
        BFS(1);
        int max = 1;
        for(int i = 2; i <= node; i++){
            if(distance[max] < distance[i]){
                max = i;
            }
        }

        /*
         * 임의의 노드에서 가장 멀리 떨어져 있는 노드를 시작 정점으로 해서 BFS 수핸
         * Arrays.sort는 distance 배열에서 마지막 인덱스로 가장 큰 수에 접근하기 위해서 실행
         */
        visited = new boolean[node+1];
        distance = new long[node+1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[node]);
    }
}

/*
 * 인접 노드에 대한 정보를 저장하는 클래스
 * node : 인접 노드의 번호
 * weight : 인접 노드와 떨어진 거리
 */
class Node implements Comparable<Node> {
    private int node;
    private int weight;

    Node(int node, int weight){
        this.node = node;
        this.weight = weight;
    }

    public int getNode(){
        return this.node;
    }

    public int getWeight(){
        return this.weight;
    }

    @Override
    public int compareTo(Node anather){
        return this.node - anather.getNode();
    }
}