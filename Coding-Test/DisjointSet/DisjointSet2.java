import java.io.*;
import java.util.*;

public class DisjointSet2 {
    /*
     * 최초 작성일시 : 2025-04-05
     * 최초 작성시간 : 11:40
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1976
     * 문제 이름 : 여행 가자
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 상호배타적 집합(Disjoint Set Union - DSU) 자료구조를 이용한 풀이
     * 핵심 연산: makeSet (초기화), findSet (부모 찾기, 경로 압축), unionSet (합치기)
     */
    static int[] makeSet(int size) {
        // 각 원소를 자기 자신으로 초기화 (자기 자신이 대표자)
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    static int findSet(int[] set, int x) {
        // 경로 압축을 이용해 루트(대표자)를 찾는 함수
        if (set[x] != x) {
            set[x] = findSet(set, set[x]); // 부모를 루트로 갱신
        }
        return set[x];
    }

    static void unionSet(int[] set, int x, int y) {
        // 두 원소의 집합을 합치는 연산 (루트를 기준으로)
        x = findSet(set, x);
        y = findSet(set, y);
        if (x != y) {
            set[y] = x; // y의 루트를 x로 연결
        }
    }

    /*
     * 여행 계획(route)에 있는 모든 도시들이
     * 같은 집합에 속하는지(=같은 루트를 가지는지) 확인하는 함수
     */
    static boolean check(int[] set, int[] route) {
        int root = findSet(set, route[1]); // 첫 도시의 루트를 기준으로
        for (int i = 2; i < route.length; i++) {
            if (root != findSet(set, route[i])) {
                // 하나라도 다른 루트가 있다면 연결 불가
                return false;
            }
        }
        return true; // 모두 같은 집합에 속함
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cities = Integer.parseInt(br.readLine()); // 도시 수 (N)
        int routes = Integer.parseInt(br.readLine()); // 여행 경로에 있는 도시 수 (M)

        // 상호배타적 집합 초기화 (도시 번호 1~N이므로 N+1 크기)
        int[] set = makeSet(cities + 1);

        // 도시 연결 정보 입력 (인접 행렬)
        int[][] city = new int[cities + 1][cities + 1];
        for (int i = 1; i <= cities; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= cities; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    // 연결된 도시라면 같은 집합으로 묶기
                    unionSet(set, i, j);
                }
            }
        }

        // 여행 경로 입력
        int[] route = new int[routes + 1]; // 1-based 인덱스 사용
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= routes; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 도시가 같은 집합(연결됨)에 속해 있다면 "YES", 아니면 "NO"
        System.out.println(check(set, route) ? "YES" : "NO");
    }

}