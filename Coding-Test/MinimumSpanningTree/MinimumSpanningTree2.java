import java.io.*;
import java.util.*;

public class MinimumSpanningTree2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 13:37
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 17472
     * ���� �̸� : �ٸ� ����� 2
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    // ��� Ž���� ���� ���� ���� (��, ��, ��, �Ʒ�)
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int[][] map; // �Է¹��� �� (���� �ٴ�)
    static boolean[][] visited; // BFS�� ���� �湮 �迭
    static int N, M; // ���� ����(N), ����(M)
    static ArrayList<ArrayList<int[]>> islands = new ArrayList<>(); // �� ��ǥ ����Ʈ
    static ArrayList<int[]> island; // �ϳ��� ���� �����ϴ� ��ǥ��
    static int[] parent; // ũ�罺Į �˰���� ���Ͽ� ���ε� �θ� �迭
    static PriorityQueue<Node> edges = new PriorityQueue<>(); // ����(�ٸ�) ����Ʈ (����ġ ��������)

    // ���� �ĺ��ϰ� islands ����Ʈ�� �ʱ�ȭ
    static void init() {
        int num = 2; // �� ��ȣ�� 2���� ���� (0: �ٴ�, 1: �̹湮 ��)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    island = new ArrayList<>();
                    BFS(i, j, num); // BFS�� �� Ž�� �� ��ȣ �ο�
                    islands.add(island); // �ĺ��� �� ����Ʈ�� �߰�
                    num++;
                }
            }
        }
    }

    // BFS�� ���� �� ��ȣ�� �ο��ϰ� �ش� ���� island ����Ʈ�� ����
    static void BFS(int r, int c, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = num; // �� ��ȣ ����
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

    // ��� ������ ���� �� �ִ� �ٸ��� Ž���ϰ� edges ����Ʈ�� ����
    static void makeEdgeList() {
        for (int i = 0; i < islands.size(); i++) {
            for (int[] start : islands.get(i)) {
                int r = start[0];
                int c = start[1];
                int startIsland = map[r][c];

                // 4�������� ������ �ٸ��� Ž��
                for (int d = 0; d < 4; d++) {
                    int length = 0;
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        if (map[nr][nc] == 0) {
                            length++; // �ٴ��̸� �ٸ� ���� ����
                        } else {
                            if (map[nr][nc] != startIsland && length >= 2) {
                                // �ٸ� ���� �����߰� ���̰� 2 �̻��̸� ��ȿ�� �ٸ�
                                edges.add(new Node(startIsland - 2, map[nr][nc] - 2, length));
                            }
                            break; // ���� ���̰ų� �� ���� �� �ߴ�
                        }
                        nr += dr[d];
                        nc += dc[d];
                    }
                }
            }
        }
    }

    // ũ�罺Į �˰������� MST ���ϰ� �ٸ� ���� ���� ��ȯ
    static int kruskal(int islandCount) {
        parent = new int[islandCount];
        for (int i = 0; i < islandCount; i++) {
            parent[i] = i; // �� ���� ���� �������� �ʱ�ȭ
        }

        int useEdge = 0; // ����� ���� ��
        int result = 0; // �� �ٸ� ����

        while (!edges.isEmpty()) {
            Node now = edges.poll();
            if (find(now.getStart()) != find(now.getEnd())) {
                union(now.getStart(), now.getEnd());
                result += now.getWeight();
                useEdge++;
            }
        }

        // ��� ���� ����Ǿ����� Ȯ�� (MST�� V-1���� ����)
        return useEdge == islandCount - 1 ? result : -1;
    }

    // ���Ͽ� ���ε�: ��ǥ ��� ã��
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    // ���Ͽ� ���ε�: �� ������ ��ġ��
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    // �Է� �� ó�� ���� �Լ�
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        // ���� ���� �Է�
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(); // �� �ĺ� �� ��ȣ �ο�
        makeEdgeList(); // �ٸ� �ĺ� ���� ����
        int result = kruskal(islands.size()); // MST�� �ּ� �ٸ� ���� ���

        System.out.println(result);
    }
}

// ���� Ŭ���� (���� ���� �����ϴ� �ٸ� ���� ����)
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
        return this.weight - o.weight; // ����ġ ���� �������� ����
    }
}