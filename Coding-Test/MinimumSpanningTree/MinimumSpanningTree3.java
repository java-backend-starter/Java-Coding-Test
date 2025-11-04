import java.io.*;
import java.util.*;

public class MinimumSpanningTree3 {
    /*
     * 최초 작성일시 : 2025-04-14
     * 최초 작성시간 : 13:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1414
     * 문제 이름 : 불우이웃돕기
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 부모 노드를 관리하는 배열
    static int [] parent;

    // 간선들을 저장할 우선순위 큐
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    // 노드의 개수
    static int node;

    // 간선 가중치의 합
    static int sum = 0;

    // 부모를 찾는 함수(경로 압축 기법 사용)
    static int find(int a){
        if(parent[a] == a){  // a의 부모가 자신이면 자신을 반환
            return parent[a];
        }
        // 경로 압축: 부모를 재귀적으로 찾아가면서 최적화
        return parent[a] = find(parent[a]);
    }

    // 두 집합을 합치는 함수(Union-Find에서 사용)
    static void union(int a, int b){
        a = find(a);  // a의 루트 찾기
        b = find(b);  // b의 루트 찾기

        // a와 b가 서로 다르면 두 집합을 합침
        if(a != b){
            parent[b] = a;  // b의 부모를 a로 설정하여 합침
        }
    }

    // Kruskal 알고리즘을 이용해 최소 신장 트리를 구하는 함수
    static int kruskal(int node){
        int used = 0;  // 사용된 간선의 수
        int result = 0;  // 최소 신장 트리의 가중치 합

        // 우선순위 큐에서 간선을 하나씩 꺼내면서 처리
        while(!queue.isEmpty()){
            Node now = queue.poll();  // 현재 간선 꺼내기
            // 현재 간선이 연결하는 두 노드가 서로 다른 집합에 속한다면
            if(find(now.getStart()) != find(now.getEnd())){
                // 두 집합을 합침
                union(now.getStart(), now.getEnd());
                used++;  // 간선 사용
                result += now.getWeight();  // 간선의 가중치를 결과에 더함
            }
        }

        // 신장 트리가 완성되었으면 가중치 합을 반환, 아니면 -1을 반환
        return used == node - 1 ? result : -1;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 노드의 수 입력
        node = Integer.parseInt(br.readLine());

        // 부모 배열 초기화
        parent = new int[node];
        for(int i = 0; i < node; i++){
            parent[i] = i;  // 각 노드의 부모는 자신
        }

        // 간선 정보를 입력받아 우선순위 큐에 추가
        // 간선의 가중치를 알파벳 대소문자로 주어지는 방식이 독특하여 처리하기 까다로웠음.
        for(int i = 0; i < node; i++){
            char [] tArray = br.readLine().toCharArray();  // 간선의 가중치를 담은 행 입력
            for(int j = 0; j < node; j++){
                int temp = 0;
                // 소문자일 경우 a=1, b=2, ..., z=26
                if(tArray[j] >= 'a' && tArray[j] <= 'z'){
                    temp = tArray[j] - 'a' + 1;
                }
                // 대문자일 경우 A=27, B=28, ..., Z=52
                else if(tArray[j] >= 'A' && tArray[j] <= 'Z'){
                    temp = tArray[j] - 'A' + 27;
                }
                sum += temp;  // 모든 가중치의 합을 구함
                // i와 j가 같지 않고, 간선이 존재하면 큐에 추가
                if(i != j && temp != 0){
                    queue.add(new Node(i, j, temp));  // 간선의 시작, 끝, 가중치 저장
                }
            }
        }

        // Kruskal 알고리즘을 이용해 최소 신장 트리의 가중치 합을 구함
        int result = kruskal(node);

        // 결과가 -1이면 최소 신장 트리가 불가능한 경우이고, 그렇지 않으면 전체 가중치에서 최소 신장 트리의 가중치 합을 빼서 출력
        // 이유: 최소 신장 트리가 완성되면, 자신이 사용할 만큼만 남기고 나머지는 기부되는 형태이기 때문
        System.out.println(result != -1 ? sum - result : result);

    }
}
// 간선 클래스 (섬과 섬을 연결하는 다리 정보 저장)
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
        return this.weight - o.weight; // 가중치 기준 오름차순 정렬
    }
}