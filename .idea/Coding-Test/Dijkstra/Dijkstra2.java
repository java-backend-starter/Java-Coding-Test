import java.io.*;
import java.util.*;

public class Dijkstra12{
    /*
     * ���� �ۼ��Ͻ� : 2025-04-08
     * ���� �ۼ��ð� : 10:32
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1916
     * ���� �̸� : �ּҺ�� ���ϱ�
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     */
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean [] visited;
    static int [] distance;

    static void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        distance[start] = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int now = node.getVertex();
            if(!visited[now]){
                visited[now] = true;
                for(Node n : graph.get(now)){
                    int next = n.getVertex();
                    int weight = n.getWeight();
                    if(distance[next] > distance[now] + weight){
                        distance[next] = distance[now] + weight;
                        queue.add(new Node(next, distance[next]));
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());

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
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(distance[end]);
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