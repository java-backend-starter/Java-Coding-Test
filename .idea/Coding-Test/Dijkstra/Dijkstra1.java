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
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean [] visited;
    static int [] distance;

    static void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        distance[start] = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int now = node.getVertex();
            if(!visited[now]){
                visited[now] = true;
                for(Node next : graph.get(now)){
                    if(!visited[next.getVertex()]){
                        if(distance[next.getVertex()] > distance[now] + next.getWeight()){
                            distance[next.getVertex()] = distance[now] + next.getWeight();
                            queue.add(new Node(next.getVertex(), distance[next.getVertex()]));
                        }
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i <= node; i++){
            graph.add(new ArrayList<>());
        }
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