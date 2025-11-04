import java.io.*;
import java.util.*;

public class MinimumSpanningTree2 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 13:37
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 17472
     * 문제 이름 : 다리 만들기 2
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 사방 탐색을 위한 방향 벡터 (오, 왼, 위, 아래)
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int[][] map; // 입력받은 맵 (섬과 바다)
    static boolean[][] visited; // BFS를 위한 방문 배열
    static int N, M; // 맵의 세로(N), 가로(M)
    static ArrayList<ArrayList<int[]>> islands = new ArrayList<>(); // 섬 좌표 리스트
    static ArrayList<int[]> island; // 하나의 섬을 구성하는 좌표들
    static int[] parent; // 크루스칼 알고리즘용 유니온 파인드 부모 배열
    static PriorityQueue<Node> edges = new PriorityQueue<>(); // 간선(다리) 리스트 (가중치 오름차순)

    // 섬을 식별하고 islands 리스트를 초기화
    static void init() {
        int num = 2; // 섬 번호는 2부터 시작 (0: 바다, 1: 미방문 섬)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    island = new ArrayList<>();
                    BFS(i, j, num); // BFS로 섬 탐색 및 번호 부여
                    islands.add(island); // 식별된 섬 리스트에 추가
                    num++;
                }
            }
        }
    }

    // BFS를 통해 섬 번호를 부여하고 해당 섬을 island 리스트에 저장
    static void BFS(int r, int c, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = num; // 섬 번호 지정
        island.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int row = now[0], col = now[1];

            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    map[nr][nc] = num;
                    queue.add(new int[]{nr, nc});
                    island.add(new int[]{nr, nc});
                }
            }
        }
    }

    // 모든 섬에서 만들 수 있는 다리를 탐색하고 edges 리스트에 저장
    static void makeEdgeList() {
        for (int i = 0; i < islands.size(); i++) {
            for (int[] start : islands.get(i)) {
                int r = start[0];
                int c = start[1];
                int startIsland = map[r][c];

                // 4방향으로 가능한 다리를 탐색
                for (int d = 0; d < 4; d++) {
                    int length = 0;
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        if (map[nr][nc] == 0) {
                            length++; // 바다이면 다리 길이 증가
                        } else {
                            if (map[nr][nc] != startIsland && length >= 2) {
                                // 다른 섬에 도달했고 길이가 2 이상이면 유효한 다리
                                edges.add(new Node(startIsland - 2, map[nr][nc] - 2, length));
                            }
                            break; // 같은 섬이거나 섬 도달 시 중단
                        }
                        nr += dr[d];
                        nc += dc[d];
                    }
                }
            }
        }
    }

    // 크루스칼 알고리즘으로 MST 구하고 다리 길이 총합 반환
    static int kruskal(int islandCount) {
        parent = new int[islandCount];
        for (int i = 0; i < islandCount; i++) {
            parent[i] = i; // 각 섬을 독립 집합으로 초기화
        }

        int useEdge = 0; // 사용한 간선 수
        int result = 0; // 총 다리 길이

        while (!edges.isEmpty()) {
            Node now = edges.poll();
            if (find(now.getStart()) != find(now.getEnd())) {
                union(now.getStart(), now.getEnd());
                result += now.getWeight();
                useEdge++;
            }
        }

        // 모든 섬이 연결되었는지 확인 (MST는 V-1개의 간선)
        return useEdge == islandCount - 1 ? result : -1;
    }

    // 유니온 파인드: 대표 노드 찾기
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    // 유니온 파인드: 두 집합을 합치기
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    // 입력 및 처리 메인 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        // 지도 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(); // 섬 식별 및 번호 부여
        makeEdgeList(); // 다리 후보 간선 생성
        int result = kruskal(islands.size()); // MST로 최소 다리 길이 계산

        System.out.println(result);
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