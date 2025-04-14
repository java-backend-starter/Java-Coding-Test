import java.io.*;
import java.util.*;

public class MinimumSpanningTree3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 13:18
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1414
     * ���� �̸� : �ҿ��̿�����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // �θ� ��带 �����ϴ� �迭
    static int [] parent;

    // �������� ������ �켱���� ť
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    // ����� ����
    static int node;

    // ���� ����ġ�� ��
    static int sum = 0;

    // �θ� ã�� �Լ�(��� ���� ��� ���)
    static int find(int a){
        if(parent[a] == a){  // a�� �θ� �ڽ��̸� �ڽ��� ��ȯ
            return parent[a];
        }
        // ��� ����: �θ� ��������� ã�ư��鼭 ����ȭ
        return parent[a] = find(parent[a]);
    }

    // �� ������ ��ġ�� �Լ�(Union-Find���� ���)
    static void union(int a, int b){
        a = find(a);  // a�� ��Ʈ ã��
        b = find(b);  // b�� ��Ʈ ã��

        // a�� b�� ���� �ٸ��� �� ������ ��ħ
        if(a != b){
            parent[b] = a;  // b�� �θ� a�� �����Ͽ� ��ħ
        }
    }

    // Kruskal �˰����� �̿��� �ּ� ���� Ʈ���� ���ϴ� �Լ�
    static int kruskal(int node){
        int used = 0;  // ���� ������ ��
        int result = 0;  // �ּ� ���� Ʈ���� ����ġ ��

        // �켱���� ť���� ������ �ϳ��� �����鼭 ó��
        while(!queue.isEmpty()){
            Node now = queue.poll();  // ���� ���� ������
            // ���� ������ �����ϴ� �� ��尡 ���� �ٸ� ���տ� ���Ѵٸ�
            if(find(now.getStart()) != find(now.getEnd())){
                // �� ������ ��ħ
                union(now.getStart(), now.getEnd());
                used++;  // ���� ���
                result += now.getWeight();  // ������ ����ġ�� ����� ����
            }
        }

        // ���� Ʈ���� �ϼ��Ǿ����� ����ġ ���� ��ȯ, �ƴϸ� -1�� ��ȯ
        return used == node - 1 ? result : -1;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // ����� �� �Է�
        node = Integer.parseInt(br.readLine());

        // �θ� �迭 �ʱ�ȭ
        parent = new int[node];
        for(int i = 0; i < node; i++){
            parent[i] = i;  // �� ����� �θ�� �ڽ�
        }

        // ���� ������ �Է¹޾� �켱���� ť�� �߰�
        // ������ ����ġ�� ���ĺ� ��ҹ��ڷ� �־����� ����� ��Ư�Ͽ� ó���ϱ� ��ٷο���.
        for(int i = 0; i < node; i++){
            char [] tArray = br.readLine().toCharArray();  // ������ ����ġ�� ���� �� �Է�
            for(int j = 0; j < node; j++){
                int temp = 0;
                // �ҹ����� ��� a=1, b=2, ..., z=26
                if(tArray[j] >= 'a' && tArray[j] <= 'z'){
                    temp = tArray[j] - 'a' + 1;
                }
                // �빮���� ��� A=27, B=28, ..., Z=52
                else if(tArray[j] >= 'A' && tArray[j] <= 'Z'){
                    temp = tArray[j] - 'A' + 27;
                }
                sum += temp;  // ��� ����ġ�� ���� ����
                // i�� j�� ���� �ʰ�, ������ �����ϸ� ť�� �߰�
                if(i != j && temp != 0){
                    queue.add(new Node(i, j, temp));  // ������ ����, ��, ����ġ ����
                }
            }
        }

        // Kruskal �˰����� �̿��� �ּ� ���� Ʈ���� ����ġ ���� ����
        int result = kruskal(node);

        // ����� -1�̸� �ּ� ���� Ʈ���� �Ұ����� ����̰�, �׷��� ������ ��ü ����ġ���� �ּ� ���� Ʈ���� ����ġ ���� ���� ���
        // ����: �ּ� ���� Ʈ���� �ϼ��Ǹ�, �ڽ��� ����� ��ŭ�� ����� �������� ��εǴ� �����̱� ����
        System.out.println(result != -1 ? sum - result : result);

    }
}
// ���� Ŭ���� (���� ���� �����ϴ� �ٸ� ���� ����)
class Node implements Comparable<Node> {
    private int start;
    private int end;
    private int weight;

    Node(int start, int end, int weight) {
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

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // ����ġ ���� �������� ����
    }
}