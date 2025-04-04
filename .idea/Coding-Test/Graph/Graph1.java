import java.io.*;
import java.util.*;

public class Graph1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-04
     * ���� �ۼ��ð� : 12:33
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 18352
     * ���� �̸� : Ư�� �Ÿ��� ���� ã��
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int [] visited;
    static ArrayList<Integer> result = new ArrayList<>();

    import java.io.*;
import java.util.*;

    public class Main {
        // �׷����� ������ ���� ����Ʈ (2���� ArrayList)
        static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // �湮 ���� �� �ִ� �Ÿ� ���� �迭 (-1�� �ʱ�ȭ)
        static int[] visited;

        // Ư�� �Ÿ�(K)�� �ش��ϴ� ���ø� ������ ����Ʈ
        static ArrayList<Integer> result = new ArrayList<>();

        // �ʺ� �켱 Ž�� (BFS)
        static void BFS(int node) {
            Queue<Integer> queue = new LinkedList<>(); // BFS�� ���� ť ����
            visited[node] = 0; // ���� ����� �Ÿ��� 0���� ����
            queue.add(node); // ť�� ���� ��� ����

            while (!queue.isEmpty()) {
                int now = queue.poll(); // ���� ��带 ����

                // ���� ���� ����� ��� ���� ��带 Ȯ��
                for (int next : graph.get(now)) {
                    // �湮���� ���� �����
                    if (visited[next] == -1) {
                        visited[next] = visited[now] + 1; // �ִ� �Ÿ� ����
                        queue.add(next); // ť�� �߰�
                    }
                }
            }
        }

        public static void main(String[] args) throws IOException {
            // �Է��� ������ �ޱ� ���� BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // ����� ������ �ϱ� ���� BufferedWriter
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // ù ��° �Է� ���� �о�ͼ� ���� �������� �и�
            StringTokenizer st = new StringTokenizer(br.readLine());

            // �Է°�: ���� ��, ���� ��, ��ǥ �Ÿ� K, ��� ���� ��ȣ X
            int city = Integer.parseInt(st.nextToken());
            int load = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            // �湮 �迭 �ʱ�ȭ (��� ���� -1�� ����)
            visited = new int[city + 1];
            Arrays.fill(visited, -1);

            // �׷��� �ʱ�ȭ (���� ������ŭ ����Ʈ ����)
            for (int i = 0; i <= city; i++) {
                graph.add(new ArrayList<>());
            }

            // ���� ���� �Է� �ޱ� (�ܹ��� �׷���)
            for (int i = 0; i < load; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
            }

            // BFS ���� (��� ���ú��� Ž�� ����)
            BFS(start);

            // �ִ� �Ÿ��� K�� ���ø� ��� ����Ʈ�� �߰�
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == distance) {
                    result.add(i);
                }
            }

            // ��� ����Ʈ�� �������� ����
            Collections.sort(result);

            // ��� ��� (�Ÿ��� K�� ���ð� ���ٸ� -1 ���)
            if (result.isEmpty()) {
                bw.append("-1\n");
            } else {
                for (int value : result) {
                    bw.append(value + "\n");
                }
            }

            // ��� ���� ���� (flush)
            bw.flush();
        }
    }
}