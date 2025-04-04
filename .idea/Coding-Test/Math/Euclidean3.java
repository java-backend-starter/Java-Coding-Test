import java.io.*;
import java.util.*;

public class Euclidean3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-03-30
     * ���� �ۼ��ð� : 00:02
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1033
     * ���� �̸� : Ĭ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    // �׷����� ���� ����Ʈ ���·� ����
    static ArrayList<ArrayList<Node>> values = new ArrayList<>();
    static long lcm = 1; // ��� �Ÿ����� �ּҰ���� (LCM)
    static boolean[] visited; // �湮 ���� üũ �迭
    static long[] distance; // �� ����� �Ÿ��� ���� �迭

    // �ִ�����(GCD) ��� (��Ŭ���� ȣ���� ���)
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // ���� �켱 Ž��(DFS) ���� -> �Ÿ��� ���
    static void DFS(int node) {
        visited[node] = true; // ���� ��� �湮 ǥ��
        for (Node next : values.get(node)) {
            if (!visited[next.getB()]) { // �湮���� ���� ����� �Ÿ� ��� �� DFS ����
                distance[next.getB()] = (distance[node] * next.getQ()) / next.getP();
                DFS(next.getB());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine()); // ��� ���� �Է�
        for (int i = 0; i < size; i++) {
            values.add(new ArrayList<>()); // ���� ����Ʈ �ʱ�ȭ
        }
        visited = new boolean[size]; // �湮 �迭 �ʱ�ȭ
        distance = new long[size]; // �Ÿ��� �迭 �ʱ�ȭ

        // ���� ���� �Է� �� �ּҰ����(LCM) ���
        for (int i = 0; i < size - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // ��� A
            int b = Integer.parseInt(st.nextToken()); // ��� B
            int p = Integer.parseInt(st.nextToken()); // ���� P
            int q = Integer.parseInt(st.nextToken()); // ���� Q

            // ����� ���� (A-B�� B-A ���� ������ �ݴ�)
            values.get(a).add(new Node(b, p, q));
            values.get(b).add(new Node(a, q, p));

            // �ּҰ���� ���� ��� (�� ������ �ּҰ���� ����)
            lcm = lcm * (p * q / gcd(p, q));
        }

        distance[0] = lcm; // ���� ����� �Ÿ����� �ּҰ������ ����
        Arrays.fill(visited, false); // �湮 �迭 �ʱ�ȭ
        DFS(0); // DFS �����Ͽ� ��� ����� �Ÿ��� ���

        // ��� �Ÿ������� �ִ�����(GCD) ���ϱ�
        long mgcd = distance[0];
        for (int i = 1; i < size; i++) {
            mgcd = gcd(mgcd, distance[i]);
        }

        // ��� �Ÿ����� �ִ������� ������ ���
        for (int i = 0; i < size; i++) {
            bw.write((distance[i] / mgcd) + " ");
        }

        bw.flush(); // ��� ���� ����
        bw.close(); // ��Ʈ�� �ݱ�
    }
}

// �׷����� ������ ��Ÿ���� Ŭ����
class Node {
    private int b; // ����� ���
    private int p; // ���� P
    private int q; // ���� Q

    Node(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() { return b; }
    public int getP() { return p; }
    public int getQ() { return q; }
}