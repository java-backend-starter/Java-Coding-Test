import java.io.*;
import java.util.*;

public class BFS2{
    /*
     * 작성일시 : 2025-03-05
     * 작성시간 : 12:32
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2178
     * 문제 이름 : 미로 찾기
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * dx, dy : 상하좌우로 이동하기 위한 배열
     * 순서는 상, 하, 좌, 우
     * 상과 좌에 1, 하와 우에 -1을 대입
     */
    static int [] dx = new int[] { 0, 0, 1, -1};
    static int [] dy = new int[] { 1, -1, 0, 0};

    /*
     * graph : 미로를 탐색하기 위한 인접 배열
     * visited : 방문 여부를 체크하기 위한 배열
     */
    static int [][] graph;
    static boolean [][] visited;

    static void BFS(int sX, int sY, int row, int col){
        /*
         * queue에는 int [] 타입을 저장
         * 이유 : x 좌표와 y좌표를 저장하기 위함
         */
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] { sX, sY });
        visited[sX][sY] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            /*
             * for문 : 상하좌우 중에서 이동할 수 있는 위치를 탐색하기 위해 4번 반복 
             * 검사해야 할 조건
             * 1. 유효한 좌표일 것(바깥쪽 if문)
             * 2. 아직 방문하지 않은 곳일 것(!visited[nX][nY])
             * 3. 이동할 수 있는 곳일 것(graph[nX][nY] != 0)
             * 
             * 위의 3개 조건을 모두 충족해야 다음으로 이동이 가능하다.
             * 
             * 이 알고리즘을 모두 수행하면 graph[row-1][col-1]에 미로의 경로 중 최단 거리를 기록
             */
            for(int i = 0; i < 4; i++){
                int nX = now[0] + dx[i];
                int nY = now[1] + dy[i];
                if(nX >= 0 && nY >= 0 && nX < row && nY < col){
                    if(!visited[nX][nY] && graph[nX][nY] != 0){
                        queue.add(new int [] {nX, nY});
                        visited[nX][nY] = true;
                        graph[nX][nY] = graph[now[0]][now[1]] + 1;
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        graph = new int[row][col];
        visited = new boolean[row][col];

        String line;
        for(int i = 0; i < row; i++){
            line = br.readLine();
            for(int j = 0; j < col; j++){
                graph[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }
        BFS(0, 0, row, col);
        System.out.println(graph[row-1][col-1]);
    }
}