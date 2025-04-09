import java.io.*;
import java.util.*;

public class BellmanFord1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 10:55
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11657
     * ���� �̸� : Ÿ�Ӹӽ�
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     * �������� �˰����� �̿��� �ִ� �Ÿ� ��� �� ���� ����Ŭ �Ǻ�
     */

    static Node[] graph;           // ���� ������ �����ϴ� �迭
    static long[] distance;        // ���� ���κ����� �ִ� �Ÿ� �迭

    // �������� �˰��� ����
    static void bellmanFord(int node, int edge, int start){
        distance[start] = 0; // ���� ����� �Ÿ��� 0

        // N-1�� �ݺ��Ͽ� �ִ� �Ÿ� ����
        for(int i = 1; i < node; i++){
            for(int j = 0; j < edge; j++){
                Node next = graph[j];

                // ��� ��尡 ���� ������ ��츸 ó��
                if(distance[next.getStart()] != Long.MAX_VALUE){
                    // �� ª�� ��ΰ� �����ϸ� ����
                    if(distance[next.getEnd()] > distance[next.getStart()] + next.getWeight()){
                        distance[next.getEnd()] = distance[next.getStart()] + next.getWeight();
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken()); // ��� ��
        int edge = Integer.parseInt(st.nextToken()); // ���� ��

        graph = new Node[edge + 1];           // ���� ����Ʈ �ʱ�ȭ
        distance = new long[node + 1];        // �Ÿ� �迭 �ʱ�ȭ (1������ ���)
        Arrays.fill(distance, Long.MAX_VALUE); // ���Ѵ�� �ʱ�ȭ

        // ���� ���� �Է�
        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());      // ��� ���
            int v = Integer.parseInt(st.nextToken());      // ���� ���
            long w = Long.parseLong(st.nextToken());       // ����ġ

            graph[i] = new Node(u, v, w);                  // ���� ��ü ����
        }

        bellmanFord(node, edge, 1); // 1�� ��忡�� �����Ͽ� �ִ� �Ÿ� ���

        boolean isCycle = false;

        // N��° ��ȸ �ÿ��� �Ÿ� ������ �Ͼ�� ���� ����Ŭ ����
        for(int i = 0; i < edge; i++){
            Node now = graph[i];
            if(distance[now.getStart()] != Long.MAX_VALUE){
                if(distance[now.getEnd()] > distance[now.getStart()] + now.getWeight()){
                    isCycle = true;
                }
            }
        }

        // ��� ó��
        if(!isCycle){
            // 1�� ��带 ������ �ٸ� �������� �ִ� �Ÿ� ���
            for(int i = 2; i <= node; i++){
                if(distance[i] == Long.MAX_VALUE){
                    bw.write("-1\n"); // ������ �� ���� ���
                }
                else{
                    bw.write(distance[i] + "\n"); // ���� ������ ��� �Ÿ� ���
                }
            }
        }
        else {
            // ���� ����Ŭ�� �����ϸ� -1 ���
            bw.write("-1\n");
        }

        bw.flush(); // ��� ���� ����
    }
}

// ���� ������ �����ϴ� Ŭ����
class Node {
    private int start;    // ��� ���
    private int end;      // ���� ���
    private long weight;  // ����ġ (���� ����)

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

