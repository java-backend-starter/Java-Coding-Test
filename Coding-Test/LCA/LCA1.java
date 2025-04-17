import java.io.*;
import java.util.*;

public class LCA1 {
    /*
     * 최초 작성일시 : 2025-04-17
     * 최초 작성시간 : 11:41
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11437
     * 문제 이름 : LCA
     * 문제 난이도 : 골드 Ⅲ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int [] depth;
    static int [] parent;
    static boolean [] visited;

    static int lca(int a, int b){
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }
        while(depth[a] != depth[b]){
            a = parent[a];
        }
        while(a != b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

    static void BFS(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        int level = 1;
        int nowSize = 1;
        int count = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next : tree.get(now)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    parent[next] = now;
                    depth[next] = level;
                }
            }
            count++;
            if(count == nowSize){
                count = 0;
                nowSize = queue.size();
                level++;
            }
        }
    }
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine());
        depth = new int[node+1];
        parent = new int[node+1];
        visited = new boolean[node+1];
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

        BFS(1);

        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b) + "\n");
        }
        bw.flush();
    }
}