import java.io.*;
import java.util.*;

public class BinarySearchTree1 {
    /*
     * 최초 작성일시 : 2025-04-15
     * 최초 작성시간 : 14:54
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1991
     * 문제 이름 : 트리 순회
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    /*
     * 트리
     * 2차원 배열로 구현
     * 행 : 부모 노드
     * 열 : 자식 노드(자식이 없으면 .으로 처리)
     */
    static char [][] tree;

    /*
     * 전위 순회
     * 부모 노드 -> 왼쪽 자식 -> 오른쪽 자식 순으로 탐색
     */
    static void preorder(char now){
        if(now != '.'){
            System.out.print(now);
            preorder(tree[now-'A'][0]);
            preorder(tree[now-'A'][1]);
        }
    }

    /*
     * 중위 순회
     * 왼쪽 자식 -> 부모 노드 -> 오른쪽 자식 순으로 탐색
     */
    static void inorder(char now){
        if(now != '.'){
            inorder(tree[now-'A'][0]);
            System.out.print(now);
            inorder(tree[now-'A'][1]);
        }
    }

    /*
     * 후위 순회
     *  왼쪽 자식 -> 오른쪽 자식 -> 부모 노드 순으로 탐색
     */
    static void postorder(char now){
        if(now != '.'){
            postorder(tree[now-'A'][0]);
            postorder(tree[now-'A'][1]);
            System.out.print(now);
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * 트리의 요소 개수
         */
        int size = Integer.parseInt(br.readLine());

        tree = new char[size][2];

        for(int i = 0; i < size; i++){
            String [] values = br.readLine().split(" ");
            char parent = values[0].charAt(0);
            char left = values[1].charAt(0);
            char right = values[2].charAt(0);

            tree[(int) parent - 'A'][0] = left;
            tree[(int) parent - 'A'][1] = right;
        }

        /*
         * 전위순회, 중위순회, 후위순회 순으로 순회
         */
        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');
        System.out.println();
    }
}