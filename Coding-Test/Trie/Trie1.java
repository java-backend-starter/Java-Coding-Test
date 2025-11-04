import java.io.*;
import java.util.*;

public class Trie1 {
    /*
     * 최초 작성일시 : 2025-04-15
     * 최초 작성시간 : 13:43
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 14425
     * 문제 이름 : 문자열 집합
     * 문제 난이도 : 실버 Ⅳ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * strs : 입력할 문자열 개수
         * checks : 문자열 검색 횟수
         */
        int strs = Integer.parseInt(st.nextToken());
        int checks = Integer.parseInt(st.nextToken());

        /*
         * 문자열 입력
         */
        Trie trie = new Trie();
        for(int i = 0; i < strs; i++){
            trie.insert(br.readLine());
        }

        /*
         * 문자열 검색
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
 * Trie 자료구조 클래스
 * 트라이(Trie)는 문자열을 효율적으로 저장하고 검색할 수 있는 자료구조
 * 사전 찾기나 자동 완성 등의 기능에 유용
 */
class Trie {
    private Node root; // 트리의 루트 노드

    // 생성자: 트라이를 초기화하며 루트 노드를 생성
    Trie(){
        root = new Node('\0', false); // 루트 노드는 특별한 값(빈 문자)과 끝이 아닌 상태로 초기화
    }

    // 문자열을 트라이에 삽입하는 메서드
    public void insert(String str){
        int size = str.length();
        if(size == 0){
            return; // 빈 문자열은 삽입하지 않음
        }
        char key = str.charAt(0); // 문자열의 첫 문자를 가져옴
        insertProcess(root.add(key, false), str, size, 1); // 루트에서 시작하여 문자열을 삽입하는 재귀 호출
    }

    // 문자열 삽입을 실제로 처리하는 재귀 메서드
    private void insertProcess(Node node, String str, int size, int index){
        if(size == index){ // 문자열 끝에 도달한 경우
            node.setData(str); // 해당 노드에 문자열 데이터를 저장
            node.setEnd(true); // 끝 노드임을 표시
            return;
        }
        char key = str.charAt(index); // 현재 문자
        insertProcess(node.add(key, false), str, size, index+1); // 자식 노드로 이동하여 재귀 호출
    }

    // 트라이에서 문자열을 검색하는 메서드
    public boolean search(String str){
        if(str.length() == 0) {
            return false; // 빈 문자열은 검색하지 않음
        }
        return searchProcess(root, str, str.length(), 0); // 루트에서 시작하여 문자열을 검색하는 재귀 호출
    }

    // 문자열 검색을 실제로 처리하는 재귀 메서드
    private boolean searchProcess(Node node, String str, int size, int index){
        if(size == index) { // 문자열 끝에 도달한 경우
            return node.isEnd(); // 해당 노드가 끝 노드인지 여부를 반환
        }
        Node child = node.find(str.charAt(index)); // 현재 문자의 자식 노드를 찾음
        if(child == null) return false; // 자식 노드가 없으면 검색 실패
        return searchProcess(child, str, size, index+1); // 자식 노드에서 재귀적으로 검색
    }
}

// Trie에 저장되는 노드 클래스
class Node {
    private char key; // 노드의 문자 값
    private String data; // 문자열 데이터 (단어가 끝날 때 저장)
    private Node [] child; // 자식 노드 배열 (26개의 자식 노드, 알파벳 소문자 기준)
    private boolean isEnd; // 단어의 끝을 나타내는 플래그

    // 생성자: key 값과 끝 여부를 초기화
    Node(char key, boolean isEnd){
        this.key = key;
        this.isEnd = isEnd;
        child = new Node[26]; // 알파벳 소문자 26개를 위한 배열
    }

    // 자식 노드를 추가하는 메서드
    public Node add(char key, boolean isEnd){
        if(this.child[key - 'a'] == null){ // 해당 문자의 자식 노드가 없으면
            child[key - 'a'] = new Node(key, isEnd); // 새 노드를 생성
        }
        return child[key - 'a']; // 해당 문자의 자식 노드를 반환
    }

    // 자식 노드를 찾는 메서드
    public Node find(char key){
        return child[key - 'a']; // 해당 문자의 자식 노드를 반환
    }

    // 데이터(단어) 설정
    public void setData(String data) {
        this.data = data;
    }

    // 끝 노드 설정
    public void setEnd(boolean end) {
        isEnd = end;
    }

    // 데이터(단어) 반환
    public String getData() {
        return data;
    }

    // 끝 노드 여부 반환
    public boolean isEnd() {
        return isEnd;
    }
}
