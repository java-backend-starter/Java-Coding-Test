import java.io.*;
import java.util.*;

public class PrefixSum4 {
    /* 
    * 작성일시 : 2025-02-18
    * 작성시간 : 19:20
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2042
    * 문제 이름 : 구간 합 구하기
    * 문제 난이도 : 골드 Ⅰ
    *
    * 작성 목적
    *  
    * 백준에 있는 문제 풀이
    */
    /*
     * datas : 원본 배열
     * sum : 구간 합을 구하기 위한 세그먼트 트리
     */
    static long [] datas;
    static long [] sum;

    /*
     * getTreeSize : 세그먼트 트리의 크기를 구하는 함수
     * init : 세그먼트 트리를 초기화하는 함수, 리프노드는 원본 데이터, 루트 노드와 내부 노드는 구간 합 정보가 있다.
     * update : 특정 인덱스의 숫자를 변경, 세그먼트 트리의 구간 합 정보도 수정한다
     * prefixSum : 특정 구간의 구간 합을 구하는 함수
     */
    static int getTreeSize(int size){
        int h = (int) Math.ceil(Math.log(size) / Math.log(2));
        int treeSize = (int) Math.pow(2, (h+1));
        return treeSize;
    }
    static void init(long [] values, long [] tree, int node, int start, int end) {
        /*
         * 리프 노드인 경우
         * 원본 배열의 값을 저장
         */
        if(start == end) {
            tree[node] = values[start];
        }
        /*
         * 리프 노드가 아닌 경우
         * 자식 노드의 값을 먼저 구하고 node가 가져야 할 구간 합을 구한다.
         */
        else {
            init(values, tree, node*2, start, (start+end)/2);
            init(values, tree, node*2+1, (start+end)/2+1, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    static void update(long [] values, long [] tree, int node, int start, int end, int index, long value){
        /*
         * [start, end]에 index가 포함되지 않은 경우
         * 해당 구간의 자식을 더 이상 탐색할 필요가 없다.
         */
        if (index < start || index > end) {
            return;
        }

        /*
         * 리프 노드인 경우
         * 원본과 세그먼트 트리의 리프 노드의 값을 변경한다.
         */
        if (start == end) {
            values[index] = value;
            tree[node] = value;
            return;
        }

        /*
         * [start, end]에 index가 포함된 경우
         * 세그먼트 트리의 자식 노드의 값을 변경하고 본인이 가져야 할 값을 변경한다.
         */
        update(values, tree, node*2, start, (start+end)/2, index, value);
        update(values, tree, node*2+1, (start+end)/2+1, end, index, value);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    static long prefixSum(long [] tree, int node, int start, int end, int left, int right){
        
        /*
         * [left, right]와 [start, end]가 곂치지 않는 경우
         * 이 구간의 값을 더할 필요가 없으므로 0을 반환
         */
        if(left > end || right < start) {
            return 0;
        }

        /*
         * [left, right]가 [start, end]를 완전히 포함하는 경우
         * 이 구간의 합은 [start, end]의 일부이기 때문에 그대로 반환한다.
         */
        if(left <= start && end <= right) {
            return tree[node];
        }

        /*
         * [start, end]가 [left, right]를 완전히 포함하는 경우와
         * [left, right]와 [start, end]가 겹쳐져 있는 경우
         * 위의 두 조건문에 따른 값들을 구해야 하기 때문에 자식 노드들을 탐색해서 값을 더한다.
         */
        return prefixSum(tree, node * 2, start, (start + end) / 2, left, right)
            + prefixSum(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 입력받을 요소의 개수
         * change : 변경할 요소의 개수
         * query : 질의 개수
         */
        int size = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        int query = Integer.parseInt(st.nextToken());

        datas = new long[size+1];
        sum = new long[getTreeSize(size)];

        for(int i = 1; i <= size; i++){
            datas[i] = Long.parseLong(br.readLine());
        }

        /*
         * 세그먼트 트리 초기화
         */
        init(datas, sum, 1, 1, size);

        /*
         * 질의와 변경이 구분되지 않고 입력되기에 (change + query)번 반복한다.
         * command의 값에 따라서 특정 요소의 값과 구간 합을 변경하거나 구간 합을 계산한다.
         */
        for(int i = 0; i < (change + query); i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command == 1){
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                update(datas, sum, 1, 1, size, index, value);
            }
            else if(command == 2){
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                bw.append(prefixSum(sum, 1, 1, size, start, end) + "\n");
            }
            else {
                continue;
            }
        }

        bw.flush();
    }
}