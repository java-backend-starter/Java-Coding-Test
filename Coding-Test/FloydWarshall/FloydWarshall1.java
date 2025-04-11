import java.io.*;
import java.util.*;

public class FloydWarshall {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 12:05
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11404
     * ���� �̸� : �÷��̵�
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    static int [][] graph;
    final static int INF = Integer.MAX_VALUE;

    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){
            for(int i = 1; i <= node; i++){
                for(int j = 1; j <= node; j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                if(graph[i][j] == INF){
                    bw.write("0 ");
                }
                else{
                    bw.write(graph[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int city = Integer.parseInt(br.readLine());
        int line = Integer.parseInt(br.readLine());

        graph = new int[city+1][city+1];

        for(int i = 1; i <= city; i++){
            for(int j = 1; j <= city; j++){
                if(i == j){
                    graph[i][j] = 0;
                }
                else {
                    graph[i][j] = INF;
                }
            }
        }

        for(int i = 0; i < line; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(w < graph[u][v]) {
                graph[u][v] = w;
            }
        }

        br.close();

        floydWarshall(graph, city);

        print(graph, city);

    }
}