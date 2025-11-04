import java.io.*;
import java.util.*;

public class BellmanFord2 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 12:05
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11657
     * 문제 이름 : 오민식의 고민
     * 문제 난이도 : 플레티넘 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    /*
     * Bellman-Ford 변형 알고리즘을 이용해
     * - 최대 수익 계산
     * - 도달 불가능한 경우 처리
     * - 양의 사이클(무한 수익) 탐지
     */

    /*
     * graph     : 간선 정보 저장 배열 (출발, 도착, 비용)
     * distance  : 각 도시에 도달했을 때의 최대 수익
     * prices    : 각 도시에 도착하면 벌 수 있는 수익
     */
    static Node[] graph;
    static long[] distance;
    static long[] prices;

    static void bellmanFord(int node, int edge, int start) {
        // 시작 노드의 초기 수익은 해당 도시 도착 시 얻는 수익
        distance[start] = prices[start];

        // node + 100회 반복: 충분히 큰 반복으로 양의 사이클을 감지
        for (int i = 0; i < node + 100; i++) {
            for (int j = 0; j < edge; j++) {
                Node now = graph[j];
                int s = now.getStart();      // 간선의 시작 노드
                int e = now.getEnd();        // 간선의 끝 노드
                long price = now.getWeight(); // 간선의 이동 비용

                // 출발 노드에 도달할 수 없다면 건너뜀
                if (distance[s] == Long.MIN_VALUE) continue;

                    // 출발 노드가 이미 무한 수익이라면 도착 노드도 무한 수익이 됨
                else if (distance[s] == Long.MAX_VALUE) {
                    distance[e] = Long.MAX_VALUE;
                }

                // 더 큰 수익이 가능한 경우
                else if (distance[e] < distance[s] + prices[e] - price) {
                    distance[e] = distance[s] + prices[e] - price;

                    // N번째 반복 이후 수익이 갱신되면 양의 사이클로 판단 → 무한 수익 처리
                    if (i >= node - 1) {
                        distance[e] = Long.MAX_VALUE;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());  // 도시 수 (노드 수)
        int start = Integer.parseInt(st.nextToken()); // 시작 도시
        int end = Integer.parseInt(st.nextToken());   // 도착 도시
        int edge = Integer.parseInt(st.nextToken());  // 간선 수

        graph = new Node[edge];             // 간선 정보를 담을 배열
        distance = new long[node];          // 수익 계산용 배열
        prices = new long[node];            // 각 도시 수익

        Arrays.fill(distance, Long.MIN_VALUE); // 수익 초기값은 도달 불가능 상태

        // 간선 정보 입력 (u → v, 비용 w)
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            graph[i] = new Node(u, v, w);
        }

        // 각 도시 도착 시 얻는 수익 정보 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < node; i++) {
            prices[i] = Long.parseLong(st.nextToken());
        }

        // 벨만 포드 알고리즘 실행
        bellmanFord(node, edge, start);

        // 결과 출력
        if (distance[end] == Long.MIN_VALUE) {
            // 도착 도시에 도달할 수 없음
            System.out.println("gg");
        } else if (distance[end] == Long.MAX_VALUE) {
            // 무한 수익 가능한 양의 사이클 존재
            System.out.println("Gee");
        } else {
            // 정상적인 최대 수익 출력
            System.out.println(distance[end]);
        }
    }
}

// 간선 정보를 담는 클래스
class Node {
    private int start;     // 간선의 시작 노드
    private int end;       // 간선의 끝 노드
    private long weight;   // 간선의 이동 비용

    Node(int start, int end, long weight) {
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