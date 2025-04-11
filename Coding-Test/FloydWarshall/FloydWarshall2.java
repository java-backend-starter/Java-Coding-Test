import java.io.*;
import java.util.*;

public class FloydWarshall2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-09
     * ���� �ۼ��ð� : 15:02
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11403
     * ���� �̸� : ��� ã��
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
                    if(graph[i][k] == 1 && graph[k][j] == 1){
                        graph[i][j] = 1;
                    }
                }
            }
        }
    }

    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());

        graph = new int[node+1][node+1];

        for(int i = 1; i <= node; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= node; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        floydWarshall(graph, node);

        print(graph, node);

    }
}