import java.io.*;
import java.util.*;

public class Tree2 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-14
     * ���� �ۼ��ð� : 14:04
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1068
     * ���� �̸� : Ʈ��
     * ���� ���̵� : ��� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
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