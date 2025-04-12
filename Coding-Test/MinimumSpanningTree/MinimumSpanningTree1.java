import java.io.*;
import java.util.*;

public class MinimumSpanningTree1 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 12:44
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1197
     * 문제 이름 : 최소 스패닝 트리
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 최소 신장 트리를 위한 유니온 파인드 자료구조
    static int [] parent;
    // 간선 정보를 저장하는 우선순위 큐 (가중치가 작은 순으로 정렬됨)
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    // find 함수: 대표 노드를 찾는 함수 (경로 압축 기법 사용)
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    // union 함수: 두 노드를 같은 집합으로 합침
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

    // 크루스칼 알고리즘을 수행하여 최소 신장 트리의 가중치 합을 구함
    static int kruskal(){
        int total = 0;

        // 우선순위 큐가 빌 때까지 반복
        while(!queue.isEmpty()){
            Node now = queue.poll();
            // 사이클이 생기지 않는 경우에만 간선을 선택
            if(find(now.getStart()) != find(now.getEnd())){
                union(now.getStart(), now.getEnd());
                total += now.getWeight(); // 간선의 가중치를 합산
            }
        }
        return total;
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken()); // 노드 개수
        int edge = Integer.parseInt(st.nextToken()); // 간선 개수

        // 각 노드의 부모를 자기 자신으로 초기화
        parent = new int[node + 1];
        for(int i = 1; i <= node; i++){
            parent[i] = i;
        }

        // 간선 정보를 입력받아 우선순위 큐에 저장
        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작 노드
            int v = Integer.parseInt(st.nextToken()); // 끝 노드
            int w = Integer.parseInt(st.nextToken()); // 가중치

            queue.add(new Node(u, v, w));
        }

        // 최소 신장 트리의 가중치 합 출력
        System.out.println(kruskal());
    }

    // 간선 정보를 저장하는 클래스 (우선순위 큐에서 사용하기 위해 Comparable 인터페이스 구현)
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

        // 가중치를 기준으로 오름차순 정렬하기 위한 compareTo 메서드 오버라이딩
        @Override
        public int compareTo(Node obj){
            return this.weight - obj.weight;
        }
    }

}