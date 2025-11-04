/* 필요한 라이브러리 import */
import java.io.*;
import java.util.*;

public class Graph4 {
    /*
     * 최초 작성일시 : 2025-04-04
     * 최초 작성시간 : 14:41
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2251
     * 문제 이름 : 물통
     * 문제 난이도 : 골드 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * 물을 붓는 6가지 경우를 위한 sender, receiver 배열
     * 0 -> A, 1 -> B, 2 -> C 물통을 의미
     */
    static int[] sender = { 0, 0, 1, 1, 2, 2 };
    static int[] receiver = { 1, 2, 0, 2, 0, 1 };

    /* visited[a][b] : A, B 물통의 상태를 방문했는지 여부 (C는 전체 물 양 - A - B로 유추 가능) */
    static boolean[][] visited = new boolean[201][201];

    /* result[i] : A가 비었을 때, C에 i만큼 물이 있을 수 있는지를 저장 */
    static boolean[] result = new boolean[201];

    /* now[0] = A, now[1] = B, now[2] = C 각각의 물통 용량 저장 */
    static int[] now = new int[3];

    /* BFS로 모든 물통 상태 탐색 */
    static void BFS() {
        Queue<AB> queue = new LinkedList<>();

        /* 처음 상태는 A와 B가 비어 있고, C는 가득 찬 상태 */
        queue.add(new AB(0, 0));
        visited[0][0] = true;
        result[now[2]] = true;  // A가 비었고, C의 물이 전부이므로 기록

        while (!queue.isEmpty()) {
            AB ab = queue.poll();
            int A = ab.getA();
            int B = ab.getB();
            int C = now[2] - A - B;  // 총 물 양 - A - B = C

            /* 6가지 물 붓는 경우 모두 시도 */
            for (int i = 0; i < 6; i++) {
                int[] next = { A, B, C };

                /* 물을 붓기: sender의 물을 receiver로 이동 */
                next[receiver[i]] += next[sender[i]];
                next[sender[i]] = 0;

                /* receiver가 넘치는 경우 조정 */
                if (next[receiver[i]] > now[receiver[i]]) {
                    next[sender[i]] = next[receiver[i]] - now[receiver[i]];
                    next[receiver[i]] = now[receiver[i]];
                }

                /* 새로운 상태라면 큐에 추가하고 방문 처리 */
                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));

                    /* A가 비어 있을 때 C의 물 양 기록 */
                    if (next[0] == 0) {
                        result[next[2]] = true;
                    }
                }
            }
        }
    }

    /* 메인 함수 */
    public static void main(String[] args) throws IOException {
        /* 입력을 위한 BufferedReader, 출력을 위한 BufferedWriter */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* A, B, C의 물통 용량 입력 */
        now[0] = Integer.parseInt(st.nextToken());
        now[1] = Integer.parseInt(st.nextToken());
        now[2] = Integer.parseInt(st.nextToken());

        /* BFS 실행 */
        BFS();

        /* 가능한 C의 물 양 결과 출력 (A가 비었을 때만) */
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                bw.append(i + " ");
            }
        }
        bw.flush();  // 출력 버퍼 비우기
    }
}

/* A, B 물통 상태를 저장하는 클래스 */
class AB {
    private int A;
    private int B;

    AB(int A, int B) {
        this.A = A;
        this.B = B;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }
}
