import java.io.*;
import java.util.*;

public class Euclidean3 {
    /*
     * 최초 작성일시 : 2025-03-30
     * 최초 작성시간 : 00:02
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1033
     * 문제 이름 : 칵테일
     * 문제 난이도 : 골드 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    // 그래프를 인접 리스트 형태로 저장
    static ArrayList<ArrayList<Node>> values = new ArrayList<>();
    static long lcm = 1; // 모든 거리들의 최소공배수 (LCM)
    static boolean[] visited; // 방문 여부 체크 배열
    static long[] distance; // 각 노드의 거리값 저장 배열

    // 최대공약수(GCD) 계산 (유클리드 호제법 사용)
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 깊이 우선 탐색(DFS) 실행 -> 거리값 계산
    static void DFS(int node) {
        visited[node] = true; // 현재 노드 방문 표시
        for (Node next : values.get(node)) {
            if (!visited[next.getB()]) { // 방문하지 않은 노드라면 거리 계산 후 DFS 진행
                distance[next.getB()] = (distance[node] * next.getQ()) / next.getP();
                DFS(next.getB());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine()); // 노드 개수 입력
        for (int i = 0; i < size; i++) {
            values.add(new ArrayList<>()); // 인접 리스트 초기화
        }
        visited = new boolean[size]; // 방문 배열 초기화
        distance = new long[size]; // 거리값 배열 초기화

        // 간선 정보 입력 및 최소공배수(LCM) 계산
        for (int i = 0; i < size - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 노드 A
            int b = Integer.parseInt(st.nextToken()); // 노드 B
            int p = Integer.parseInt(st.nextToken()); // 비율 P
            int q = Integer.parseInt(st.nextToken()); // 비율 Q

            // 양방향 연결 (A-B와 B-A 간의 비율이 반대)
            values.get(a).add(new Node(b, p, q));
            values.get(b).add(new Node(a, q, p));

            // 최소공배수 누적 계산 (각 비율의 최소공배수 유지)
            lcm = lcm * (p * q / gcd(p, q));
        }

        distance[0] = lcm; // 시작 노드의 거리값을 최소공배수로 설정
        Arrays.fill(visited, false); // 방문 배열 초기화
        DFS(0); // DFS 실행하여 모든 노드의 거리값 계산

        // 모든 거리값들의 최대공약수(GCD) 구하기
        long mgcd = distance[0];
        for (int i = 1; i < size; i++) {
            mgcd = gcd(mgcd, distance[i]);
        }

        // 모든 거리값을 최대공약수로 나눠서 출력
        for (int i = 0; i < size; i++) {
            bw.write((distance[i] / mgcd) + " ");
        }

        bw.flush(); // 출력 버퍼 비우기
        bw.close(); // 스트림 닫기
    }
}

// 그래프의 간선을 나타내는 클래스
class Node {
    private int b; // 연결된 노드
    private int p; // 비율 P
    private int q; // 비율 Q

    Node(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() { return b; }
    public int getP() { return p; }
    public int getQ() { return q; }
}