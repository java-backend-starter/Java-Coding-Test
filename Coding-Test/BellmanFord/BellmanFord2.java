import java.io.*;
import java.util.*;

public class BellmanFord2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 12:05
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11657
     * ���� �̸� : ���ν��� ���
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     * ��� : 4% ����
     */
    static Node [] graph;
    static long [] distance;
    static long [] prices;

    static void bellmanFord(int node, int edge, int start){
        distance[start] = prices[start];
        for(int i = 0; i < node + 100; i++){
            for(int j = 0; j < edge; j++){
                Node now = graph[j];
                int s = now.getStart();
                int e = now.getEnd();
                long price = now.getWeight();
                if(distance[s] == Long.MIN_VALUE){
                    continue;
                }
                else if(distance[s] == Long.MAX_VALUE){
                    distance[e] = Long.MAX_VALUE;
                }
                else if(distance[e] < distance[s] + prices[e] - price){
                    distance[e] = distance[s] + prices[e] - price;
                    if(i >= node - 1){
                        distance[e] = Long.MAX_VALUE;
                    }
                }

            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        graph = new Node[edge];
        distance = new long[node];
        prices = new long[node];
        Arrays.fill(distance, Long.MIN_VALUE);

        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());  // weight�� long���� ����

            graph[i] = new Node(u, v, w);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < node; i++){
            prices[i] = Long.parseLong(st.nextToken());
        }

        bellmanFord(node, edge, start);

        if(distance[end] == Long.MIN_VALUE){
            System.out.println("GG");
        }
        else if(distance[end] == Long.MAX_VALUE){
            System.out.println("Gee");
        }
        else {
            System.out.println(distance[end]);
        }
    }
}
class Node {
    private int start;
    private int end;
    private long weight;

    Node(int start, int end, long weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public long getWeight() {
        return weight;
    }
}