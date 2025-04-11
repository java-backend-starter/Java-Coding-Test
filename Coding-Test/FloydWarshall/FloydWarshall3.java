import java.io.*;
import java.util.*;

public class FloydWarshall3 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 15:28
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1389
     * ���� �̸� : �ɺ� �������� 6�ܰ� ��Ģ
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    static int [][] graph;

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

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int node = Integer.parseInt(st.nextToken());
        int friend = Integer.parseInt(st.nextToken());

        graph = new int[node+1][node+1];

        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                if(i == j){
                    graph[i][j] = 0;
                }
                else {
                    graph[i][j] = 10000001;
                }
            }
        }

        for(int i = 0; i < friend; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        br.close();

        floydWarshall(graph, node);

        int min = Integer.MAX_VALUE;
        int answer = -1;

        for(int i = 1; i <= node; i++){
            int temp = 0;
            for(int j = 1; j <= node; j++){
                temp += graph[i][j];
            }
            if(min > temp){
                min = temp;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}