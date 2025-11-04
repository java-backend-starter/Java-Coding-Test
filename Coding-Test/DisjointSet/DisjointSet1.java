import java.io.*;
import java.util.*;

public class DisjointSet1 {
    /*
     * 최초 작성일시 : 2025-04-05
     * 최초 작성시간 : 11:13
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1717
     * 문제 이름 : 집합의 표현
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 상호배타적 집합(Disjoint Set) 구현
     * 주요 연산: makeSet, findSet (with path compression), union
     */
    static int[] makeSet(int size) {
        // 각 원소가 자기 자신을 대표로 가지는 집합 초기화
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    static int findSet(int[] set, int x) {
        // 경로 압축을 적용하여 대표 노드를 찾음
        if (set[x] != x) {
            set[x] = findSet(set, set[x]);
        }
        return set[x];
    }

    static void union(int[] set, int x, int y) {
        // x와 y가 속한 집합의 대표 노드를 찾고, 서로 다르면 합침
        int rootX = findSet(set, x);
        int rootY = findSet(set, y);
        if (rootX != rootY) {
            set[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 원소 개수와 질의 수 입력
        int size = Integer.parseInt(st.nextToken());
        int queries = Integer.parseInt(st.nextToken());

        // 1번부터 size번까지 사용할 수 있도록 size + 1 크기의 배열 생성
        int[] set = makeSet(size + 1);

        for (int i = 0; i < queries; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (query == 0) {
                // 합집합 연산
                union(set, x, y);
            } else if (query == 1) {
                // 같은 집합에 속하는지 여부 출력
                bw.write(findSet(set, x) == findSet(set, y) ? "YES\n" : "NO\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}