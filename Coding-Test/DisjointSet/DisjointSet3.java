import java.io.*;
import java.util.*;

public class DisjointSet3 {
    /*
     * 최초 작성일시 : 2025-04-05
     * 최초 작성시간 : 12:32
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1043
     * 문제 이름 : 여행 가자
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * set : 각 사람의 대표 노드를 저장하는 배열 (유니온 파인드용)
     * know : 진실을 아는 사람들의 번호
     * party : 각 파티에 참여하는 사람들의 명단 (2차원 리스트)
     * count : 거짓말을 해도 되는 파티의 개수
     */
    static int[] set;
    static int[] know;
    static ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    static int count = 0;

    /*
     * makeSet: 유니온 파인드를 위한 초기 세팅
     * 각 사람을 자신을 대표자로 가지도록 설정
     */
    static int[] makeSet(int size) {
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    /*
     * findSet: 해당 원소의 루트(대표자)를 찾음
     * 경로 압축(Path Compression) 기법을 사용하여 트리 높이를 낮춤
     */
    static int findSet(int x) {
        if (set[x] != x) {
            set[x] = findSet(set[x]);  // 재귀적으로 루트를 찾아 바로 연결
        }
        return set[x];
    }

    /*
     * unionSet: 두 원소가 속한 집합을 하나로 합침
     * 대표자가 다르면 하나로 연결
     */
    static void unionSet(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (x != y) {
            set[y] = x;
        }
    }

    /*
     * checkSameSet: 두 사람이 같은 집합(대표자 공유)인지 확인
     */
    static boolean checkSameSet(int x, int y) {
        return findSet(x) == findSet(y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int people = Integer.parseInt(st.nextToken());       // 전체 사람 수
        int partiesCount = Integer.parseInt(st.nextToken()); // 파티 개수

        set = makeSet(people + 1); // 1번부터 people번까지, 0은 사용 안 함

        st = new StringTokenizer(br.readLine());
        int knowCount = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

        know = new int[knowCount];
        for (int i = 0; i < knowCount; i++) {
            know[i] = Integer.parseInt(st.nextToken());
        }

        // 각 파티의 참석자 목록 저장
        for (int i = 0; i < partiesCount; i++) {
            st = new StringTokenizer(br.readLine());
            int memberCount = Integer.parseInt(st.nextToken());

            ArrayList<Integer> members = new ArrayList<>();
            for (int j = 0; j < memberCount; j++) {
                members.add(Integer.parseInt(st.nextToken()));
            }
            party.add(members);
        }

        // 파티에 속한 사람들을 같은 집합으로 묶기 (유니온)
        for (ArrayList<Integer> members : party) {
            for (int j = 1; j < members.size(); j++) {
                unionSet(members.get(0), members.get(j)); // 첫 사람을 기준으로 모두 연결
            }
        }

        // 각 파티마다 진실을 아는 사람과 연결되어 있는지 확인
        for (ArrayList<Integer> members : party) {
            boolean possible = true;
            int rep = members.get(0); // 해당 파티의 대표자

            for (int person : know) {
                if (checkSameSet(rep, person)) {
                    // 이 파티에 진실을 아는 사람과 연결된 사람이 있으면 거짓말 불가
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++; // 거짓말이 가능한 파티
            }
        }

        System.out.println(count); // 가능한 파티 수 출력
    }
}