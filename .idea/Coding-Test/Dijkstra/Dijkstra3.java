import java.io.*;
import java.util.*;

public class Dijkstra13 {
    /*
     * 최초 작성일시 : 2025-04-08
     * 최초 작성시간 : 14:51
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1854
     * 문제 이름 : K번째 최단 경로
     * 문제 난이도 : 플레티넘 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     */
    static int[][] graph = new int[1001][1001];
    static ArrayList<PriorityQueue<Integer>> distance = new ArrayList<>();

    static void dijkstra(int start, int node, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        distance.get(1).add(0);

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int next = 1; next <= node; next++) {
                if (graph[now.getVertex()][next] != 0) {
                    int newDist = now.getWeight() + graph[now.getVertex()][next];

                    if (distance.get(next).size() < k) {
                        distance.get(next).add(newDist);
                        queue.add(new Node(next, newDist));
                    } else if (distance.get(next).peek() > newDist) {
                        distance.get(next).poll(); // 가장 큰 값 제거
                        distance.get(next).add(newDist);
                        queue.add(new Node(next, newDist));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 거리 배열 초기화 (Max Heap)
        for (int i = 0; i <= node; i++) {
            distance.add(new PriorityQueue<>(k, (o1, o2) -> o2 - o1)); // 내림차순 = Max Heap
        }

        // 간선 정보 입력
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
        }

        dijkstra(1, node, k); // 시작 정점 1

        // 출력
        for (int i = 1; i <= node; i++) {
            if (distance.get(i).size() == k) {
                bw.write(distance.get(i).peek() + "\n");
            } else {
                bw.write("-1\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Node implements Comparable<Node> {
    private int vertex;
    private int weight;

    Node(int vertex, int weight) {
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
    public int compareTo(Node n) {
        return Integer.compare(this.weight, n.getWeight());
    }
}
