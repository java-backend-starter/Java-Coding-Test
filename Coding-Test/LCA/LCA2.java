import java.io.*;
import java.util.*;

public class LCA2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-17
     * ���� �ۼ��ð� : 13:14
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 11438
     * ���� �̸� : LCA2
     * ���� ���̵� : �÷�Ƽ�� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    static int max;
    static int [] depth;
    static int [][] parent;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    static void DFS(int node, int par, int dep){
        depth[node] = dep;
        parent[node][0] = par;

        for(int next : tree.get(node)){
            if(next != par){
                DFS(next, node, dep+1);
            }
        }
    }

    static void init(int size){
        for(int j = 1; j < max; j++){
            for(int i = 1; i <= size; i++){
                if(parent[i][j-1] != -1){
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    static int LCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = max - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        if (u == v) return u;

        for (int i = max - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());
        max = (int) Math.ceil(Math.log(node)/Math.log(2)) + 1;
        depth = new int[node+1];
        parent = new int[node+1][max];
        for(int i = 0; i <= node; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < node-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int query = Integer.parseInt(br.readLine());

        DFS(1, -1, 0);

        init(node);

        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(LCA(a, b) + "\n");
        }
        bw.flush();
    }
}