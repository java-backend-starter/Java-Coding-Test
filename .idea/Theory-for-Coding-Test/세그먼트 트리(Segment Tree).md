# 세그먼트 트리

### 최초 작성일시 : 2025-02-18
### 최초 작성일자 : 19:56
### 최초 작성자 : 장성환
### 작성 목적 : 세그먼트 트리 개념 정리
---

### 마지막 수정일자 : 2025-02-18
### 마지막 수정일시 : 22:34
### 마지막 작성자 : 장성환

---
### 수정이력

2025-02-18
1. 코드 가독성 향상

---
### 자료 출처

[세그먼트 트리 자료1](https://cano721.tistory.com/38)

[세그먼트 트리 자료2](https://velog.io/@jeongbeom4693/Java-%EC%84%B8%EA%B7%B8%EB%A8%BC%ED%8A%B8-%ED%8A%B8%EB%A6%ACSegment-Tree)

---

## 1. 세그먼트 트리 개념

* 특정 구간 내의 데이터들에 대한 연산을 빠르게 구할 수 있는 트리
* 세그먼트 트리를 활용하는 연산 : 특전 구간의 합, 최소값, 최대값, 평균값 등
* 시간 복잡도
    * 데이터 변경: O(logN)
    * 연산: O(logN)
    * 데이터 변경할 때마다 M번 연산: O((logN +logN)*M) = O(MlogN)

---

## 2. 세그먼트 트리 구조

* 세그먼트 트리는 포화 이진 트리(Full Binary Tree)로 구현된다. 따라서 이진 트리의 특성을 가진다.

![세그먼트 트리 구조](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%84%B8%EA%B7%B8%EB%A8%BC%ED%8A%B8%20%ED%8A%B8%EB%A6%AC/%EC%84%B8%EA%B7%B8%EB%A8%BC%ED%8A%B8%20%ED%8A%B8%EB%A6%AC%20%EA%B5%AC%EC%A1%B0.png?raw=true)

* 세그먼트 트리에서 노드의 의미는 다음과 같다.
    * 리프 노드 : 원본 데이터
    * 리프가 아닌 노드 : 왼쪽 자식 노드와 오른쪽 자식 노드의 합
* 세그먼트 트리는 포화 이진 트리이므로 리프 노드가 아닌 노드들은 항상 2개의 자식을 가진다.
    * 리프 노드가 N개면 리프가 아닌 노드의 개수는 N-1개
    * 따라서 필요한 노드의 개수는 2N - 1개

---

## 3. 세그먼트 트리 구현 방법

### 세그먼트 트리 만드는 법

#### 배열의 크기 구하기

* 트리의 높이를 구한 다음에 2의 거듭제곱 꼴로 만든다.
* 2의 거듭제곱 꼴로 만드는 이유는 세그먼트 트리가 완전 이진트리가 아니라서 배열의 순서대로 채워지지 않기 떄문이다.

``` java
    /*
     * n : 요소의 개수
     * h : 트리의 높이
     * treeSize : 배열의 크기
     */
    int h = (int) Math.ceil(Math.log(n) / Math.log(2));
    int treeSize = (int) Math.pow(2, h+1);
```

#### 세그먼트 트리 초기화 방법

``` java
    /*
     * values : 원본 배열
     * tree : 세그먼트 트리
     * node : 현재 노드의 번호
     * start : 구간 합 시작 요소 번호
     * end : 구간 합 마지막 요소 번호
     */
    void init(long [] values, long [] tree, int node, int start, int end) {
        // 리프 노드인 경우
        if(start == end) {
            tree[node] = values[start];
        }
        // 리프 노드가 아닌 경우
        else {
            init(values, tree, node*2, start, (start+end)/2);
            init(values, tree, node*2, (start+end)/2, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }
```

* start == end인 경우는 리프 노드인 경우로 원본 배열의 값을 저장한다.
* 그 외의 경우에는 node의 자식 노드들의 값을 구하고 node에 node의 자식 노드들이 가진 값의 합을 저장한다.
* init 함수를 먼저 실행하는 이유는 자식 노드의 값이 있어야 자신 node가 가질 값을 저장할 수 있기 때문이다.

#### 수 변경하기

``` java
    void update(long [] values, long [] tree, int node, int start, int end, int index, int value){
        // [start, end]에 index가 포함되지 않은 경우
        if (index < start || index > end) {
            return;
        }

        // 리프 노드인 경우
        if (start == end) {
            a[index] = value;
            tree[node] = value;
            return;
        }

        // [start, end]에 index가 포함된 경우
        update(a, tree,node*2, start, (start+end)/2, index, value);
        update(a, tree,node*2+1, (start+end)/2+1, end, index, value);
        tree[node] = tree[node*2] + tree[node*2+1];
    }
```

* i번째 수를 value로 변경하면 i번째 수를 포함하는 구간의 합도 변경해야 한다.
* 이 때 두 가지 경우를 봐야 한다.
    1. [start, end]에 index가 포함된 경우
    2. [start, end]에 index가 포함되지 않은 경우
* 1의 경우에는 리프 노드를 찾을 때까지 재귀 호출이 진행된다. 리프 노드에 도착하면 리프 노드의 값을 변경하고 변경된 값을 리턴한다. 그리고 변경된 값이 리턴될 때마다 구간 합를 다시 구한다.
* 2의 경우에는 index의 자식들도 범위에 포함되지 않으므로 자식 노드의 탐색을 중단한다.

#### 구간 합

``` java
    /* 
     * tree : 세그먼트 트리
     * node : 현재 노드
     * start, end : node에 저장된 구간
     * left, right : 구해야 하는 구간 합 범위 
     */
    long prefixSum(long [] tree, int node, int start, int end, int left, int right){
        // [left, right]와 [start, end]가 곂치지 않는 경우
        if(left > end || right < start) {
            return 0;
        }

        // [left, right]가 [start, end]를 완전히 포함하는 경우
        if(left <= start && end <= right) {
            return tree[node];
        }

        /*
         * [start, end]가 [left, right]를 완전히 포함하는 경우와
         * [left, right]와 [start, end]가 겹쳐져 있는 경우
         */
        return prefixSume(tree, node * 2, start, (start + end) / 2, left, right)
            + prefixSume(tree, node * 2, (start + end) / 2 + 1, end, left, right);
    }
```
* 구간 합을 구하기 위해서 다음의 경우를 확인해야 한다.
    * start와 end : 특정 노드가 저장하고 있는 구간 합 정보
    * left와 right : 구해야 하는 구간 합 정보
    1. [left, right]와 [start, end]가 곂치지 않는 경우
    2. [left, right]가 [start, end]를 완전히 포함하는 경우
    3. [start, end]가 [left, right]를 완전히 포함하는 경우
    4. [left, right]와 [start, end]가 겹쳐져 있는 경우
* 1의 경우는 left > end || right < start인 경우로 곂치지 않기 때문에 더 이상 탐색할 필요가 없어서 0을 반환한다.
* 2의 경우는 left <= start && end <= right인 경우로 1의 경우와 같이 더 이상 탐색할 필요가 없지만 [start, end] 구간이 포함되어 있으므로 tree[node]를 반환한다.
* 3과 4의 경우에는 왼쪽 자식과 오른쪽 자식을 탐색해서 1 또는 2의 경우를 충족시키는 구간을 찾아서 구간 합을 구한다.

### 시간 복잡도

#### 구간 합 구하기

* 최악의 경우는 리프 노드까지 탐색하는 경우로 시간 복잡도는 Ο(logN)이다.
* 세그먼트 트리의 각 레벨에서 방문하는 노드의 개수는 4개 이하이다.
    * 특정 레벨에서 방문한 노드의 순서가 a, b, c, d일 때 b와 c는 구간 합의 중간 부분이기 때문에 다음 레벨에서는 b와 c의 자식 노드들을 탐색하지 않기 때문이다.
* 따라서 트리의 모든 노드를 탐색하지 않아도 구간 합을 구할 수 있다.

#### 수 변경하기

* 수를 변경하는 경우에는 리프 노드까지 내려가서 값을 변경하고 자신의 부모 노드의 값도 변경한다.
* 특정 구간을 저장하고 있는 노드만 탐색해서 값을 변경하기 때문에 각 레벨에서 방문하는 노드의 수는 2개를 넘지 않는다.
* 따라서 시간 복잡도는 Ο(logN)이다.

### 기타

* 세그먼트 트리는 구간 합을 구하는 것 외에도 최솟값, 최댓값, 특정 구간의 숫자들의 곱, XOR 연산 등도 구할 수 있다.
* 구간 합 이외의 다른 것을 구할 때에는 return되는 값의 수식을 변경하면 된다.
