import java.io.*;
import java.util.*;

public class Tree2 {
    /*
     * 최초 작성일시 : 2025-04-14
     * 최초 작성시간 : 14:04
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1068
     * 문제 이름 : 트리
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean [] visited;

    static int node;
    static int root;
    static int deleted;
    static int answer = 0;

    static void DFS(int node){
        visited[node] = true;
        int child = 0;
        for(int next : tree.get(node)){
            if(!visited[next] && next != deleted){
                child++;
                DFS(next);
            }
        }
        if(child == 0){
            answer++;
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        node = Integer.parseInt(br.readLine());
        visited = new boolean[node];
        for(int i = 0; i < node; i++){
            tree.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < node; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1){
                root = i;
            }
            else {
                tree.get(i).add(parent);
                tree.get(parent).add(i);
            }
        }

        deleted = Integer.parseInt(br.readLine());
        if(deleted == root){
            System.out.println(0);
        }
        else {
            DFS(root);
            System.out.println(answer);
        }
    }
}