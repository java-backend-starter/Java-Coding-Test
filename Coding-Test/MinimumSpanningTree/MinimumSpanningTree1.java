import java.io.*;
import java.util.*;

public class MinimumSpanningTree1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 12:44
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1197
     * ���� �̸� : �ּ� ���д� Ʈ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // �ּ� ���� Ʈ���� ���� ���Ͽ� ���ε� �ڷᱸ��
    static int [] parent;
    // ���� ������ �����ϴ� �켱���� ť (����ġ�� ���� ������ ���ĵ�)
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    // find �Լ�: ��ǥ ��带 ã�� �Լ� (��� ���� ��� ���)
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    // union �Լ�: �� ��带 ���� �������� ��ħ
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b){
            parent[b] = a;
        }
        else{
            parent[a] = b;
        }
    }

    // ũ�罺Į �˰����� �����Ͽ� �ּ� ���� Ʈ���� ����ġ ���� ����
    static int kruskal(){
        int total = 0;

        // �켱���� ť�� �� ������ �ݺ�
        while(!queue.isEmpty()){
            Node now = queue.poll();
            // ����Ŭ�� ������ �ʴ� ��쿡�� ������ ����
            if(find(now.getStart()) != find(now.getEnd())){
                union(now.getStart(), now.getEnd());
                total += now.getWeight(); // ������ ����ġ�� �ջ�
            }
        }
        return total;
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken()); // ��� ����
        int edge = Integer.parseInt(st.nextToken()); // ���� ����

        // �� ����� �θ� �ڱ� �ڽ����� �ʱ�ȭ
        parent = new int[node + 1];
        for(int i = 1; i <= node; i++){
            parent[i] = i;
        }

        // ���� ������ �Է¹޾� �켱���� ť�� ����
        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // ���� ���
            int v = Integer.parseInt(st.nextToken()); // �� ���
            int w = Integer.parseInt(st.nextToken()); // ����ġ

            queue.add(new Node(u, v, w));
        }

        // �ּ� ���� Ʈ���� ����ġ �� ���
        System.out.println(kruskal());
    }

    // ���� ������ �����ϴ� Ŭ���� (�켱���� ť���� ����ϱ� ���� Comparable �������̽� ����)
    class Node implements Comparable<Node>{
        private int start;
        private int end;
        private int weight;

        Node(int start, int end, int weight){
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

        public int getWeight() {
            return weight;
        }

        // ����ġ�� �������� �������� �����ϱ� ���� compareTo �޼��� �������̵�
        @Override
        public int compareTo(Node obj){
            return this.weight - obj.weight;
        }
    }

}