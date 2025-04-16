import java.io.*;
import java.util.*;

public class SegmentTree3 {
    /*
     * 작성일시 : 2025-04-16
     * 작성시간 : 16:49
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2042
     * 문제 이름 : 구간 합 구하기
     * 문제 난이도 : 골드 I
     *
     * 작성 목적 :
     * 세그먼트 트리 관련 백준 문제 풀이
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * node   : 입력받을 데이터 개수
         * change : 값 변경 횟수
         * sum    : 구간합 구하기 횟수
         * count  : 총 질의 개수
         */
        int node = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());
        int count = change + sum;

        /*
         * values : 입력된 원본 배열 (1-indexed)
         */
        long[] values = new long[node + 1];
        for (int i = 1; i <= node; i++) {
            values[i] = Long.parseLong(br.readLine());
        }

        /*
         * 세그먼트 트리 생성
         */
        SegmentTree tree = new SegmentTree(values, node);

        /*
         * 질의 처리
         * 1. query == 1 : 특정 인덱스의 값을 수정
         * 2. query == 2 : 특정 구간의 합을 구함
         */
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if (query == 1) {
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                tree.update(1, 1, node, index, value);
            } else if (query == 2) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                bw.write(tree.prefixSum(1, 1, node, left, right) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class SegmentTree {
    /*
     * values : 원본 배열
     * tree   : 세그먼트 트리 배열 (구간합 저장)
     * size   : 원본 배열의 크기
     */
    private long[] values;
    private long[] tree;
    private int size;

    /*
     * 생성자 역할:
     * 1. 트리 높이를 계산하고 tree 배열을 초기화함
     * 2. 원본 배열 복사
     * 3. 세그먼트 트리 초기화 수행
     */
    SegmentTree(long[] values, int size) {
        this.size = size;
        int height = (int) Math.ceil(Math.log(size) / Math.log(2));
        int treeSize = (int) Math.pow(2, height + 1);
        tree = new long[treeSize];
        this.values = values;
        init(1, 1, size);
    }

    /*
     * 세그먼트 트리 초기화
     * 리프 노드: 원본 값 저장
     * 내부 노드: 좌/우 자식의 합 저장
     */
    private long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = values[start];
        } else {
            int mid = (start + end) / 2;
            return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
        }
    }

    /*
     * 값 수정
     * index 위치의 값을 value로 변경하고 관련된 구간합 갱신
     */
    public void update(int node, int start, int end, int index, long value) {
        // 범위 밖인 경우 무시
        if (index < start || index > end) return;

        // 리프 노드: 실제 값 갱신
        if (start == end) {
            values[index] = value;
            tree[node] = value;
            return;
        }

        // 내부 노드: 하위 트리 재귀적으로 갱신 후 구간합 갱신
        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, value);
        update(node * 2 + 1, mid + 1, end, index, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    /*
     * 구간합 계산
     * 구간 [left, right]에 대한 합을 반환
     */
    public long prefixSum(int node, int start, int end, int left, int right) {
        // 구간이 전혀 겹치지 않으면 0 반환
        if (end < left || start > right) return 0;

        // 완전히 포함되는 구간이면 바로 반환
        if (left <= start && end <= right) return tree[node];

        // 일부만 포함될 경우 좌/우 자식 노드 재귀 탐색하여 합산
        int mid = (start + end) / 2;
        return prefixSum(node * 2, start, mid, left, right)
                + prefixSum(node * 2 + 1, mid + 1, end, left, right);
    }
}