import java.io.*;
import java.util.*;

public class SegmentTree2 {
    /*
     * 작성일시 : 2025-02-28
     * 작성시간 : 18:21
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2357
     * 문제 이름 : 최솟값과 최댓값
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

        int size = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        int [] values = new int[size+1];
        for(int i = 1; i <= size; i++){
            values[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree tree = new SegmentTree(values, size);

        for(int i = 0; i < query; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.append(tree.min(1, 1, size, start, end) + " " + tree.max(1, 1, size, start, end) + "\n");
        }
        bw.flush();
    }

}

class SegmentTree {
    private int [] values;
    private int [] minTree;
    private int [] maxTree;
    private int treeSize;

    public SegmentTree(int [] values, int size){
        int height = (int) Math.ceil(Math.log(size)/Math.log(2));
        this.treeSize = (int) Math.pow(2, (height + 1));
        this.values = values;
        this.minTree = new int[this.treeSize];
        this.maxTree = new int[this.treeSize];
        init(1, 1, values.length-1);
    }

    private void init(int node, int start, int end){
        if(start == end){
            minTree[node] = values[start];
            maxTree[node] = values[start];
        }
        else {
            init(node*2, start, (start+end)/2);
            init(node*2+1, (start+end)/2+1, end);
            minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
            maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2+1]);
        }
    }

    public void update(int node, int start, int end, int index, int value){
        if(index < start || index > end){
            return;
        }

        if(start == end){
            values[index] = value;
            minTree[node] = value;
            maxTree[node] = value;
            return;
        }

        update(node*2, start, (start+end)/2, index, value);
        update(node*2+1, (start+end)/2+1, end, index, value);
        minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
        maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2+1]);
    }

    public int min(int node, int start, int end, int left, int right){
        if(end < left || start > right){
            return 1000000001;
        }

        if(left <= start && end <= right) {
            return minTree[node];
        }

        return Math.min(min(node*2, start, (start+end)/2, left, right), min(node*2+1, (start+end)/2+1, end, left, right));

    }

    public int max(int node, int start, int end, int left, int right){
        if(end < left || start > right) {
            return 0;
        }

        if(left <= start && end <= right){
            return maxTree[node];
        }

        return Math.max(max(node*2, start, (start+end)/2, left, right), max(node*2+1, (start+end)/2+1, end, left, right));
    }

}