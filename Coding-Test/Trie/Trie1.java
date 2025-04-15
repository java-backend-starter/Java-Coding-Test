import java.io.*;
import java.util.*;

public class Trie1 {
    /*
     * ���� �ۼ��Ͻ� : 2025-04-15
     * ���� �ۼ��ð� : 13:43
     * ���� �ۼ��� : ����ȯ
     *
     * ���� ��ó : ����
     * ���� ��ȣ : 14425
     * ���� �̸� : ���ڿ� ����
     * ���� ���̵� : �ǹ� ��
     *
     * �ۼ� ����
     *
     * ���ؿ� �ִ� ���� Ǯ��
     *
     */
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * strs : �Է��� ���ڿ� ����
         * checks : ���ڿ� �˻� Ƚ��
         */
        int strs = Integer.parseInt(st.nextToken());
        int checks = Integer.parseInt(st.nextToken());

        /*
         * ���ڿ� �Է�
         */
        Trie trie = new Trie();
        for(int i = 0; i < strs; i++){
            trie.insert(br.readLine());
        }

        /*
         * ���ڿ� �˻�
         */
        int count = 0;
        for(int i = 0; i < checks; i++){
            if(trie.search(br.readLine())){
                count++;
            }
        }

        System.out.println(count);
    }
}

/*
 * Trie �ڷᱸ�� Ŭ����
 * Ʈ����(Trie)�� ���ڿ��� ȿ�������� �����ϰ� �˻��� �� �ִ� �ڷᱸ��
 * ���� ã�⳪ �ڵ� �ϼ� ���� ��ɿ� ����
 */
class Trie {
    private Node root; // Ʈ���� ��Ʈ ���

    // ������: Ʈ���̸� �ʱ�ȭ�ϸ� ��Ʈ ��带 ����
    Trie(){
        root = new Node('\0', false); // ��Ʈ ���� Ư���� ��(�� ����)�� ���� �ƴ� ���·� �ʱ�ȭ
    }

    // ���ڿ��� Ʈ���̿� �����ϴ� �޼���
    public void insert(String str){
        int size = str.length();
        if(size == 0){
            return; // �� ���ڿ��� �������� ����
        }
        char key = str.charAt(0); // ���ڿ��� ù ���ڸ� ������
        insertProcess(root.add(key, false), str, size, 1); // ��Ʈ���� �����Ͽ� ���ڿ��� �����ϴ� ��� ȣ��
    }

    // ���ڿ� ������ ������ ó���ϴ� ��� �޼���
    private void insertProcess(Node node, String str, int size, int index){
        if(size == index){ // ���ڿ� ���� ������ ���
            node.setData(str); // �ش� ��忡 ���ڿ� �����͸� ����
            node.setEnd(true); // �� ������� ǥ��
            return;
        }
        char key = str.charAt(index); // ���� ����
        insertProcess(node.add(key, false), str, size, index+1); // �ڽ� ���� �̵��Ͽ� ��� ȣ��
    }

    // Ʈ���̿��� ���ڿ��� �˻��ϴ� �޼���
    public boolean search(String str){
        if(str.length() == 0) {
            return false; // �� ���ڿ��� �˻����� ����
        }
        return searchProcess(root, str, str.length(), 0); // ��Ʈ���� �����Ͽ� ���ڿ��� �˻��ϴ� ��� ȣ��
    }

    // ���ڿ� �˻��� ������ ó���ϴ� ��� �޼���
    private boolean searchProcess(Node node, String str, int size, int index){
        if(size == index) { // ���ڿ� ���� ������ ���
            return node.isEnd(); // �ش� ��尡 �� ������� ���θ� ��ȯ
        }
        Node child = node.find(str.charAt(index)); // ���� ������ �ڽ� ��带 ã��
        if(child == null) return false; // �ڽ� ��尡 ������ �˻� ����
        return searchProcess(child, str, size, index+1); // �ڽ� ��忡�� ��������� �˻�
    }
}

// Trie�� ����Ǵ� ��� Ŭ����
class Node {
    private char key; // ����� ���� ��
    private String data; // ���ڿ� ������ (�ܾ ���� �� ����)
    private Node [] child; // �ڽ� ��� �迭 (26���� �ڽ� ���, ���ĺ� �ҹ��� ����)
    private boolean isEnd; // �ܾ��� ���� ��Ÿ���� �÷���

    // ������: key ���� �� ���θ� �ʱ�ȭ
    Node(char key, boolean isEnd){
        this.key = key;
        this.isEnd = isEnd;
        child = new Node[26]; // ���ĺ� �ҹ��� 26���� ���� �迭
    }

    // �ڽ� ��带 �߰��ϴ� �޼���
    public Node add(char key, boolean isEnd){
        if(this.child[key - 'a'] == null){ // �ش� ������ �ڽ� ��尡 ������
            child[key - 'a'] = new Node(key, isEnd); // �� ��带 ����
        }
        return child[key - 'a']; // �ش� ������ �ڽ� ��带 ��ȯ
    }

    // �ڽ� ��带 ã�� �޼���
    public Node find(char key){
        return child[key - 'a']; // �ش� ������ �ڽ� ��带 ��ȯ
    }

    // ������(�ܾ�) ����
    public void setData(String data) {
        this.data = data;
    }

    // �� ��� ����
    public void setEnd(boolean end) {
        isEnd = end;
    }

    // ������(�ܾ�) ��ȯ
    public String getData() {
        return data;
    }

    // �� ��� ���� ��ȯ
    public boolean isEnd() {
        return isEnd;
    }
}
