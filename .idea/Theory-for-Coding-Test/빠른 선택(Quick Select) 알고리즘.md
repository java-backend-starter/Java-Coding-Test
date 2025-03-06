# 세그먼트 트리

### 최초 작성일 : 2025-03-01
### 최초 작성시간 : 23:29
### 최초 작성자 : 장성환
### 작성 목적 : 빠른 선택 알고리즘 개념 정리

---

### 마지막 수정일 : 2025-03-06
### 마지막 수정시간 : 21:50
### 마지막 작성자 : 장성환

---

### 수정이력

2024-03-06
1. 수식 입력 방식 변경

---

### 자료 출처

[빠른 선택 자료1](https://yoeubi.github.io/data-structure/11-index)

[빠른 선택 자료2](https://www.daleseo.com/quick-select/)

---
### 1. 빠른 선택(Quick Select)이란?

* 주어진 배열에서 k번째로 작은 값을 효율적으로 찾기 위한 검색 알고리즘  
* 빠른 선택 알고리즘은 배열을 완전히 정렬하지 않고도 원하는 값을 찾을 수 있다.

### 2. 핵심 아이디어

* 퀵 정렬(Quick Sort)과 유사하게 피벗(pivot)을 선택하여 배열을 분할한다.
  * 예시 : [1, 3, 2, 5, 7, 6, 4] 배열에서 2번째로 작은 값을 찾고자 할 때 
  1. 마지막 값인 4를 피벗으로 선택하여 배열을 4보다 작은 값과 큰 값으로 나눈다.([1, 3, 2] < 4 < [7, 6, 5])
  2. 이때, 피벗 4는 전체 배열에서 4번째로 작은 값이며, 2번째로 작은 값은 왼쪽 부분 배열 [1, 3, 2]에 존재함을 알 수 있다.
  3. 1과 2를 반복하여 원하는 값을 찾는다.

### 3. 시간 복잡도

* 이상적인 경우는 매번 배열이 절반으로 나누어지는 경우로 시간 복잡도는 $O(n)$이다.
* 최악의 경우는 피벗 선택이 계속해서 한쪽으로 치우지는 경우로 시간 복잡도는 $O(n^2)$이다.
* 배열의 크기가 커질 수록 최악의 경우는 거의 발생하지 않기 때문에 평균적으로는 $O(n)$의 시간 복잡도를 가진다.

### 4. 구현

* 자바로 직접 구현한 코드를 찾지 못해서 다른 언어로 작성된 코드를 보고 직접 구현해봤다.
* partition 함수에서 피봇은 마지막 요소로 정한 뒤에 올바른 위치로 변경했다.
```java
int quickSelect(int [] values, int start, int end, int k){
    if(start == end){
        return values[end];
    }
    int pivot = partition(values, start, end);
    if(k == pivot){
        return values[pivot];
    }
    else if(k < pivot){
        return quickSelect(values, start, pivot-1, k);
    }
    else {
        return quickSelect(values, pivot+1, end, k);
    }
}
int partition(int[] values, int left, int right) {
  int pivot = values[right]; // 마지막 요소를 피벗으로 설정
  int i = left; // 작은 값이 들어갈 위치

  for (int j = left; j < right; j++) {
    if (values[j] < pivot) {
      swap(values, i, j);
      i++;
    }
  }
  swap(values, i, right); // 피벗을 올바른 위치로 이동
  return i; // 피벗의 최종 위치 반환
}

// 배열 원소 교환 함수
void swap(int[] values, int i, int j) {
  int temp = values[i];
  values[i] = values[j];
  values[j] = temp;
}
```