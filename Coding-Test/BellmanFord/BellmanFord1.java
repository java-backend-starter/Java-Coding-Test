import java.io.*;
import java.util.*;

public class BellmanFord1 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 10:55
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11657
     * 문제 이름 : 타임머신
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     * 벨만포드 알고리즘을 이용한 최단 거리 계산 및 음수 사이클 판별
     */

    static Node[] graph;           // 간선 정보를 저장하는 배열
    static long[] distance;        // 시작 노드로부터의 최단 거리 배열

    // 벨만포드 알고리즘 구현
    static void bellmanFord(int node, int edge, int start){
        distance[start] = 0; // 시작 노드의 거리는 0

        // N-1번 반복하여 최단 거리 갱신
        for(int i = 1; i < node; i++){
            for(int j = 0; j < edge; j++){
                Node next = graph[j];

                // 출발 노드가 도달 가능한 경우만 처리
                if(distance[next.getStart()] != Long.MAX_VALUE){
                    // 더 짧은 경로가 존재하면 갱신
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

        int node = Integer.parseInt(st.nextToken()); // 노드 수
        int edge = Integer.parseInt(st.nextToken()); // 간선 수

        graph = new Node[edge + 1];           // 간선 리스트 초기화
        distance = new long[node + 1];        // 거리 배열 초기화 (1번부터 사용)
        Arrays.fill(distance, Long.MAX_VALUE); // 무한대로 초기화

        // 간선 정보 입력
        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());      // 출발 노드
            int v = Integer.parseInt(st.nextToken());      // 도착 노드
            long w = Long.parseLong(st.nextToken());       // 가중치

            graph[i] = new Node(u, v, w);                  // 간선 객체 저장
        }

        bellmanFord(node, edge, 1); // 1번 노드에서 시작하여 최단 거리 계산

        boolean isCycle = false;

        // N번째 순회 시에도 거리 갱신이 일어나면 음수 사이클 존재
        for(int i = 0; i < edge; i++){
            Node now = graph[i];
            if(distance[now.getStart()] != Long.MAX_VALUE){
                if(distance[now.getEnd()] > distance[now.getStart()] + now.getWeight()){
                    isCycle = true;
                }
            }
        }

        // 출력 처리
        if(!isCycle){
            // 1번 노드를 제외한 다른 노드까지의 최단 거리 출력
            for(int i = 2; i <= node; i++){
                if(distance[i] == Long.MAX_VALUE){
                    bw.write("-1\n"); // 도달할 수 없는 경우
                }
                else{
                    bw.write(distance[i] + "\n"); // 도달 가능한 경우 거리 출력
                }
            }
        }
        else {
            // 음수 사이클이 존재하면 -1 출력
            bw.write("-1\n");
        }

        bw.flush(); // 출력 버퍼 비우기
    }
}

// 간선 정보를 저장하는 클래스
class Node {
    private int start;    // 출발 노드
    private int end;      // 도착 노드
    private long weight;  // 가중치 (음수 가능)

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

