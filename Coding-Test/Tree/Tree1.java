import java.io.*;
import java.util.*;

public class Tree1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-14
     * ���� �ۼ��ð� : 13:40
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11725
     * ���� �̸� : Ʈ���� �θ� ã��
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean [] visited;
    static int [] result;

    static void DFS(int node){
        visited[node] = true;
        for(int next : tree.get(node)){
            if(!visited[next]){
                result[next] = node;
                DFS(next);
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());

        visited = new boolean[node + 1];
        result = new int[node + 1];

        for(int i = 0; i <= node; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < node-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree.get(s).add(e);
            tree.get(e).add(s);
        }

        DFS(1);

        for(int i = 2; i <= node; i++){
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }
}