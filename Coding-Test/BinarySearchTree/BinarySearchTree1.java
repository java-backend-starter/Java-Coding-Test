import java.io.*;
import java.util.*;

public class BinarySearchTree1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-15
     * ���� �ۼ��ð� : 14:54
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 1991
     * ���� �̸� : Ʈ�� ��ȸ
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    /*
     * Ʈ��
     * 2���� �迭�� ����
     * �� : �θ� ���
     * �� : �ڽ� ���(�ڽ��� ������ .���� ó��)
     */
    static char [][] tree;

    /*
     * ���� ��ȸ
     * �θ� ��� -> ���� �ڽ� -> ������ �ڽ� ������ Ž��
     */
    static void preorder(char now){
        if(now != '.'){
            System.out.print(now);
            preorder(tree[now-'A'][0]);
            preorder(tree[now-'A'][1]);
        }
    }

    /*
     * ���� ��ȸ
     * ���� �ڽ� -> �θ� ��� -> ������ �ڽ� ������ Ž��
     */
    static void inorder(char now){
        if(now != '.'){
            inorder(tree[now-'A'][0]);
            System.out.print(now);
            inorder(tree[now-'A'][1]);
        }
    }

    /*
     * ���� ��ȸ
     *  ���� �ڽ� -> ������ �ڽ� -> �θ� ��� ������ Ž��
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
         * Ʈ���� ��� ����
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
         * ������ȸ, ������ȸ, ������ȸ ������ ��ȸ
         */
        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');
        System.out.println();
    }
}