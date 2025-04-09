/* �ʿ��� ���̺귯�� import */
import java.io.*;
import java.util.*;

public class Graph4 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-04
     * ���� �ۼ��ð� : 14:41
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 2251
     * ���� �̸� : ����
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * å�� �ִ� ���� ����
     */

    /*
     * ���� �״� 6���� ��츦 ���� sender, receiver �迭
     * 0 -> A, 1 -> B, 2 -> C ������ �ǹ�
     */
    static int[] sender = { 0, 0, 1, 1, 2, 2 };
    static int[] receiver = { 1, 2, 0, 2, 0, 1 };

    /* visited[a][b] : A, B ������ ���¸� �湮�ߴ��� ���� (C�� ��ü �� �� - A - B�� ���� ����) */
    static boolean[][] visited = new boolean[201][201];

    /* result[i] : A�� ����� ��, C�� i��ŭ ���� ���� �� �ִ����� ���� */
    static boolean[] result = new boolean[201];

    /* now[0] = A, now[1] = B, now[2] = C ������ ���� �뷮 ���� */
    static int[] now = new int[3];

    /* BFS�� ��� ���� ���� Ž�� */
    static void BFS() {
        Queue<AB> queue = new LinkedList<>();

        /* ó�� ���´� A�� B�� ��� �ְ�, C�� ���� �� ���� */
        queue.add(new AB(0, 0));
        visited[0][0] = true;
        result[now[2]] = true;  // A�� �����, C�� ���� �����̹Ƿ� ���

        while (!queue.isEmpty()) {
            AB ab = queue.poll();
            int A = ab.getA();
            int B = ab.getB();
            int C = now[2] - A - B;  // �� �� �� - A - B = C

            /* 6���� �� �״� ��� ��� �õ� */
            for (int i = 0; i < 6; i++) {
                int[] next = { A, B, C };

                /* ���� �ױ�: sender�� ���� receiver�� �̵� */
                next[receiver[i]] += next[sender[i]];
                next[sender[i]] = 0;

                /* receiver�� ��ġ�� ��� ���� */
                if (next[receiver[i]] > now[receiver[i]]) {
                    next[sender[i]] = next[receiver[i]] - now[receiver[i]];
                    next[receiver[i]] = now[receiver[i]];
                }

                /* ���ο� ���¶�� ť�� �߰��ϰ� �湮 ó�� */
                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));

                    /* A�� ��� ���� �� C�� �� �� ��� */
                    if (next[0] == 0) {
                        result[next[2]] = true;
                    }
                }
            }
        }
    }

    /* ���� �Լ� */
    public static void main(String[] args) throws IOException {
        /* �Է��� ���� BufferedReader, ����� ���� BufferedWriter */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* A, B, C�� ���� �뷮 �Է� */
        now[0] = Integer.parseInt(st.nextToken());
        now[1] = Integer.parseInt(st.nextToken());
        now[2] = Integer.parseInt(st.nextToken());

        /* BFS ���� */
        BFS();

        /* ������ C�� �� �� ��� ��� (A�� ����� ����) */
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                bw.append(i + " ");
            }
        }
        bw.flush();  // ��� ���� ����
    }
}

/* A, B ���� ���¸� �����ϴ� Ŭ���� */
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
