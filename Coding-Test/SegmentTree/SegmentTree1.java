import java.io.*;
import java.util.*;

public class SegmentTree1 {
    /*
     * 작성일시 : 2025-02-28
     * 작성시간 : 18:04
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 10868
     * 문제 이름 : 최솟값
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 세그먼트 트리 관련 백준 문제 풀이
     */

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 입력받을 요소의 개수
         * query : 질의의 개수
         */
        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        /*
         * values : 원본 배열
         * tree : 세그먼트 트리 객체
         */
        int [] values = new int[size+1];
        for(int i = 1; i <= size; i++){
            values[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree tree = new SegmentTree(values, size);

        /*
         * 특정 구간에서의 최솟값을 찾는 질의에 대한 응답을 찾음
         */
        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.append(tree.min(1, 1, size, start, end) + "\n");
        }
        bw.flush();
    }

}

// 세그먼트 트리
class SegmentTree {
    /*
     * values : 원본 배열
     * tree : 최솟값을 저장하는 트리
     * treeSize : 트리에 저장할 수 있는 요소의 개수
     */
    private int [] values;
    private int [] tree;
    private int treeSize;

    /*
     * 초기화 과정
     * 1. 세그먼트 트리의 크기를 구한다
     * 2. 원본 배열을 저장한다.
     * 3. 트리에 데이터를 저장(리프노트 : 원본 배열에서 요소를 저장, 루트 노트 : 요소 집합에서 최솟값을 저장, 내부 노드 : 특정 구간의 최솟값을 저장)
     */
    public SegmentTree(int [] values, int size){
        int height = (int) Math.ceil(Math.log(size)/Math.log(2));
        this.treeSize = (int) Math.pow(2, (height + 1));
        this.values = values;
        this.tree = new int[this.treeSize];
        init(1, 1, values.length-1);
    }

    /*
     * 트리를 초기화하는 함수, 1번만 실행
     */
    private void init(int node, int start, int end){
        /*
         * 1. 리프 노드에는 요소값을 저장
         * 2. 내부 노드에는 특정 구간의 최솟값을, 루트 노드에는 전체 구간의 최솟값을 저장
         */
        if(start == end){
            tree[node] = values[start];
        }
        else {
            init(node*2, start, (start+end)/2);
            init(node*2+1, (start+end)/2+1, end);
            tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        }
    }

    /*
     * 요소를 변경하는 함수.
     * 이 문제에서는 사용하지 않는다.
     */
    public void update(int node, int start, int end, int index, int value){
        /*
         * 탐색 범위를 벗어난 경우로 더이상 탐색하지 않는다.
         */
        if(index < start || index > end){
            return;
        }


        /*
         * 리프 노드인 경우에 원본 배열과 트리의 리프노드의 값을 변경한다.
         */
        if(start == end){
            values[index] = value;
            tree[node] = value;
            return;
        }

        /*
         * 탐색 범위에 완전히 포함되거나 일부가 곂쳐져 있는 경우에 자식 노드들을 업데이트하고 자신을 업데이트한다.
         */
        update(node*2, start, (start+end)/2, index, value);
        update(node*2+1, (start+end)/2+1, end, index, value);
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }

    /*
     * 특정 구간의 최솟값을 찾는 경우
     * [start, end] : 현재 탐색하고 있는 범위
     * [left, right] : 최솟값을 찾을 때 사용할 구간
     */
    public int min(int node, int start, int end, int left, int right){
        /*
         * 탐색 범위([start, end])를 벗어난 경우로 입력받는 수의 범위를 벗어나는 값을 반환
         * 이유 : 여기서 반환되는 값이 최솟값이 되거나 최솟값을 구하는 연산에 영향을 주면 안되기 때문
         */
        if(end < left || start > right){
            return 1000000001;
        }

        /*
         * 탐색 범위([start, end]) 안에 완전히 포함된 경우로 해당 노드의 값을 반환
         */
        if(left <= start && end <= right) {
            return tree[node];
        }

        /*
         * [start, end]가 [left, right]를 포함하거나 [left, right]의 일부 구간을 포함하는 경우
         * 이 경우에는 [start, end]의 범위에 속한 두 자식 노드들 중 최솟값을 가진 노드를 반환
         */
        return Math.min(min(node*2, start, (start+end)/2, left, right), min(node*2+1, (start+end)/2+1, end, left, right));

    }

}