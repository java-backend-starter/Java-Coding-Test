# 정렬

### 최초 작성일시 : 2025-02-18
### 최초 작성시간 : 19:38
### 최초 작성자 : 장성환
### 작성 목적 : 정렬개념 정리

---

### 마지막 수정일자 :
### 마지막 수정일시 :
### 마지막 작성자 :

---
### 수정이력


---
### 자료 출처

[C로 배우는 쉬운 자료구조](https://search.shopping.naver.com/book/catalog/32436279809?cat_id=50010920&frm=PBOKPRO&query=C%EB%A1%9C+%EB%B0%B0%EC%9A%B0%EB%8A%94+%EC%89%AC%EC%9A%B4+%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0&NaPm=ct%3Dm7bsyx7s%7Cci%3D62de1a30c048ad3fa67230d29716f34f4e5288a3%7Ctr%3Dboknx%7Csn%3D95694%7Chk%3D17f33feb0d102088663db0ef9aaa53fc070ec5f3)

[쉽게 배우는 알고리즘](https://search.shopping.naver.com/book/catalog/32476025996?cat_id=50010921&frm=PBOKPRO&query=%EC%89%BD%EA%B2%8C+%EB%B0%B0%EC%9A%B0%EB%8A%94+%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98&NaPm=ct%3Dm7bsxq08%7Cci%3D094090b68874c0eb47e64d781eb20ee156e48e3c%7Ctr%3Dboknx%7Csn%3D95694%7Chk%3D6317e827ffff0b8af1cc8d9c885beb603b7a7609)

---

## 1. 정렬(Sort)이란?

### 개념

* 순서 없이 배열된 자료를 일정한 순서로 재배열하는 것이다.
* 대표적인 정렬 방법에는 오름차순과 내림차순 정렬이 있다.
* 자료를 정렬하는 데 사용하는 기준이 되는 특정 값을 키(key)라고 한다.

### 정렬 방식의 종류

* 비교식 정렬(Comparative Sort)
  * 비교할 각 키값을 한 번에 두 개씩 비교하여 교환하는 방식으로 진행되는 정렬
  * 비교식 정렬에서 정렬하는 방식에 따라 교환 방식, 삽입 방식, 병합 방식, 선택 방식으로 나누어진다.
* 분배식 정렬(Distribute Sort)
* 내부 정렬(Internal Sort)
  * 컴퓨터 메모리 내부에서 정렬
  * 정렬 속도는 빠르지만 정렬할 수 있는 자료의 양이 메모리 용량에 따라 제한된다.
* 외부 정렬(External Sort)
  * 보조 기억 장치에서 정렬하는 것으로 파일을 부분 파일로 분리하여 각각의 부분 파일을 내부 정렬로 정렬하여 병합하는 방식
  * 내부 정렬에 비해 정렬 속도는 느리지만 대용량의 데이터를 정렬할 수 있다.
  * 몇 개의 부분 파일로 나눌 것인지에 따라 2-way 병합, n-way 병합이 있다.
* 여러 정렬 방법 중 어떤 방법을 사용할 것인지는 다음과 같은 것을 고려하여 정한다
  * 사용하는 시스템의 특성
  * 정렬할 자료의 양
  * 자료들의 정렬 전 상태
  * 정렬에 사용하는 기억 공간과 실행 시간
* 같은 조건에서 정렬 방법의 효율성을 비교하는 기준은 다음과 같아
  * 정렬에 필요한 비교 횟수와 이동 횟수

## 2. 선택 정렬(Selection Sort)

* 전체 원소 중에서 기준 위치에 맞는 원소를 선택해 자리를 교환하는 방식을 사용하는 정렬
* 오름차순으로 정렬할 때 선택 정렬의 알고리즘은 다음과 같다.
``` java
selectionSort(A[], n) {
    for last ← downto 2 {
        k ← theLargest(A, last) // 가장 큰 수 찾기
        A[k] ↔ A[last]; // A[k]와 A[last]의 값을 교환
    }
}

theLargest(A[], last) {
    largest ← 1;
    for i ← 2 to last
        if(A[i] > A[largest]) then largest ← i;
    return largest;
}
```
* 이를 구현하면 다음과 같다. 요소의 타입은 int로 가정한다.
```java
void selectionSort(int [] values){
    for(int i = values.length-1; i > 0; i--){
        int index = largest(values, i);
        int temp = values[i];
        values[i] = values[index];
        values[index] = temp;
    }
}
int largest(int [] values, int last){
    int max = 0;
    for(int i = 1; i <= last; i++){
        if(values[i] > values[max]){
            max = i;
        }
    }
    return max;
}
```
* 선택 정렬의 시간복잡도는 모든 경우에서 Θ(n^2)이다.
  * largest 함수에서 가장 큰 값을 찾기 위해 n - i번(i번째 원소를 기준으로) 반복되고 selectionSort에 있는 for문으로 largest 함수가 n번 실행되기 때문이다.
  * 따라서 전체 비교 횟수는 다음과 같다.
    * (n-1) + (n-2) + ... + 2 + 1 = (n(n-1))/2

## 3. 버블 정렬(Bubble Sort)

* 인접한 원소 두 개를 비교하여 자리를 교환하는 방식을 반복하는 정렬
* 버블 정렬의 알고리즘은 다음과 같다.
``` java
bubbleSort(A [], n){
    for last ← n downto 2
        for i ← 1 to last - 1
            if (A[i] > A[i+1]) then A[i] ↔ A[i+1];
}
```
* 이를 구현하면 다음과 같다.
```java 
void bubbleSort(int [] values){
    for(int i = values.length-1; i > 0; i--){
        for(int j = 0; j < i; j++){
            if(values[j] > values[j+1]){
                int temp = values[j];
                values[j] = values[j+1];
                values[j+1] = temp;
            }
        }
    }
}
```
* 버블 정렬은 정렬하는 방식에서 차이가 있을 뿐 선택 정렬과 같은 수행횟수를 가진다. 
* 따라서 시간 복잡도는 모든 경우에서 Θ(n^2)이다.
  * 전체 비교 횟수 : (n-1) + (n-2) + ... + 2 + 1 = (n(n-1))/2
* 위 알고리즘은 알고리즘이 수행하기 시작하거나 수행 중에 요소들의 정렬이 끝나는 경우에도 끝까지 수행된다.
* 따라서 무의미한 반복을 제거하기 위해 다음 알고리즘을 쓴다.
``` java
bubbleSort(A [], n){
    for last ← n downto 2 {
        sorted ← TRUE;
        for i ← 1 to last - 1 {
            if (A[i] > A[i+1]) then { 
                A[i] ↔ A[i+1];
                sorted ← FALSE;
            }
        }
        if(sorted = TRUE) then return;
    }
}
```
* 이를 구현하면 다음과 같다. 위에 구현한 코드에서 정렬 여부를 확인하는 코드만 추가되었다.
```java 
void bubbleSort(int [] values){
    for(int i = values.length-1; i > 0; i--){
        boolean sorted = true;
        for(int j = 0; j < i; j++){
            if(values[j] > values[j+1]){
                int temp = values[j];
                values[j] = values[j+1];
                values[j+1] = temp;
                sorted = false;
            }
        }
        if(sorted) return;
    }
}
```

## 4. 삽입 정렬(Insertion Sort)

* 정렬되어 있는 부분집합에 정렬할 원소의 위치를 찾아 삽입하는 방식을 사용하는 정렬
* 삽입 정렬은 정렬된 부분집합의 크기를 늘려가는 방식의 정렬이다.
  * 선택 정렬과 버블 정렬은 정렬되지 않은 배열의 크기를 줄여가는 방식의 정렬
* 삽입 정렬의 알고리즘은 다음과 같다.
``` java
insertionSort(A[], n){
    for i ← 2 to n {
        loc ← i - 1;
        item ← A[i]; // A[1 ... i-1]은 정렬되어 있는 상태
        while(loc ≥ 1 and item < A[loc]) {
            A[loc+1] ← A[loc];
            loc--;
        }
        A[loc+1] ← item; 
    }
}
```
* 이를 구현하면 다음과 같다
```java
void insertionSort(int [] values){
    for(int i = 1; i < values.length; i++){
        int location = i - 1;
        int item = values[i];
        while(location >= 0 && item < values[location]){
            values[location+1] = values[location];
            location--;
        }
        values[location+1] = item;
    }
}
```
* 삽입 정렬의 수행 시간을 계산하면 다음과 같다.
  * 바깥쪽 for문 : n번 수행
  * 안쪽 while문
    * 최선의 경우 : A[i]가 제자리에 있는 경우(자리 이동이 없음)
    * 최악의 경우 : A[i]가 A[1] 자리에 가야 하는 경우(자리 이동이 i번)
    * 평균적인 경우 : A[i]가 A[i/2]번째 자리에 가는 경우(자리 이동이 i/2번)
  * 따라서 최악의 경우의 수행 시간은 (n-1) + (n-2) + ... + 2 + 1 = (n(n-1))/2이기 때문에 시간 복잡도는 Θ(n^2)이다.
* 삽입 정렬은 특정 조건에서는 Θ(n)에 가까운 시간 복잡도를 가진다.
  * 특정 조건 : 배열이 거의 정렬되어 있는 경우
  * 사유 : while문이 거의 실행되지 않기 때문에 사실상 for문의 n번 반복의 영향을 받기 때문

## 5. 병합 정렬(Merge Sort)

* 여러 개의 정렬된 부분집합을 병합하여 하나의 정렬된 집합을 만드는 정렬
* 병합 정렬은 분할과 정복이라는 원리를 사용하여 정렬하기에 다음의 과정을 거친다.
  * 집합을 2개 이상의 부분집합으로 나눈다.
  * 부분 집합에 대해서 정렬 작업을 진행한다.
  * 정렬된 부분 집합을 결합한다.
* 이때 몇 개의 부분집합을 결합해서 하나의 집합을 만드는지에 따라 2-way 병합과 n-way 병합으로 나누어진다.
* 병합 정렬의 알고리즘은 다음과 같다.
``` java
// p : 시작 지점, r : 마지막 지점
mergeSort(A [], p, r) {
  if(p < r) then {
    q ← ⌊(p+r)/2⌋;
    // 집합을 분할해서 정렬
    mergeSort(A, p, q);
    mergeSort(A, q+1, r);
    // 병합
    merge(A, p, q, r);
  }
}
// p : 시작 지점, q : 중간 지점, r : 마지막 지점
merge(A[], p, q, r){
  i ← p;
  j ← q + 1;
  t ← 1;
 
  while(i ≤ q and j ≤ r) {
    if(A[i] ≤ A[j]) then tmp[t++] ← A[i++];
    else tmp[t++] ← A[j++];
  }
  
  while(i ≤ q) {
    tmp[t++] ← A[i++];
  }
  
  while(j ≤ r) {
    tmp[t++] ← A[j++];
  }
  
  i ← p;
  t ← 1;
  while(i ≤ r) {
    A[i++] ← tmp[t++];
  }
}
```
* 이를 구현하면 다음과 같다.
```java
void mergeSort(int [] values, int start, int end){
    if(start < end){
        mid = (start+end)/2;
        mergeSort(values, start, mid);
        mergeSort(values. mid+1, end);
        merge(values, start, mid, end);
    }
}

void merge(int [] values, int start, int middle, int end){
    int left = start, right = middle + 1, t = start;
    int [] sorted = new int[values.length];
    while(left <= middle && right <= end){
        if(values[i] <= values[j]){
            sorted[t++] = values[left++];
        }
        else {
            sorted[t++] = values[right++];
        }
    }
    
    while(left <= middle){
        sorted[t++] = values[left++];
    }
    
    while(right <= end){
        sorted[t++] = values[right++];
    }
    
    left = start; t = start;
    while(left <= end){
        values[left++] = sorted[t++];
    }
}
```
* 병합 정렬의 수행 시간을 계산할 때 두 가지 경우로 나누어서 본다
  * 정렬할 요소의 개수가 1개일 때
  * 정렬할 요소의 개수가 2개 이상일 때
  * ![시간복잡도 식](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%201.png?raw=true)
* 정렬할 요소의 개수가 1개일 때에는 상수 시간이 들고 정렬할 요소의 개수가 2개 이상일 때는 다음의 식으로 시간 복잡도가 계산된다.
  * ![시간복잡도 계산](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%202(%EC%88%98%EC%A0%95).png?raw=true) 
  * T(n/2) : 두 개의 부분 집합을 처리하는 비용
  * c(상수) : 병합에 드는 시간(선형 시간)
  * 아래는 n = 2^k로 가정하여 계산한 것
* 따라서 병합 정렬은 최악의 경우에도 Θ(n log n)이라는 시간 복잡도를 가진다.

## 6. 퀵 정렬(Quick Sort)

* 기준값을 이용해서 부분집합을 만들어서 정렬된 부분집합을 병합하는 방식의 정렬 알고리즘
* 퀵정렬은 다음의 방법으로 정렬한다.
  * 정렬할 집합에 대해서 기준값을 기준으로 왼쪽 부분집합과 오른쪽 부분집합으로 나눈다.
  * 왼쪽 부분집합에는 기준값보다 작은 요소들, 오른쪽 부분집합에는 기준값보다 큰 요소들을 이동시킨다.
  * 왼쪽 부분집합과 오른쪽 부분집합을 독립적으로 정렬한다.
* 퀵정렬에서 사용하는 기준값을 피봇이라고 한다. 피봇을 정하는 기준은 다음과 같다.
  * 첫 번째 요소 또는 마지막 요소
  * 전체 원소 중 가운데에 위치한 요소
  * 별도의 수식으로 계산한 위치에 있는 요소
* 퀵 정렬의 알고리즘은 다음과 같다. 피봇은 마지막 요소로 정했다.
``` java
quickSort(A[], p, r){
  if(p < r) {
    q ← partition(A, p, r);
    quickSort(A, p, q-1);
    quickSort(A, q+1, r);
  }
}

partition(A[], p, r) {
  x ← A[r];
  i ← p-1;
  for j ← p to r - 1 {
    if(A[j] ≤ x) then A[++i] ↔ A[j];
  }
  A[i+1] ↔ A[r];
  return i+1;
    
}
```
* 이를 구현하면 다음과 같다.
```java
void quickSort(int [] values, int start, int end){
    if(start < end){
        int mid = partition(values, start, end);
        quickSort(values, start, mid-1);
        quickSort(values, mid+1, end);
    }
}

int partition(int [] values, int start, int end){
    int pivot = values[end];
    int left = start-1;
    for(int right = start; right < end; right++){
        if(values[right] <= pivot){
            int temp = values[++left];
            values[left] = values[right];
            values[right] = temp;
        }
    }
    int tmp = values[left+1];
    values[left+1] = values[end];
    values[end] = tmp;
    return left+1;
}
```
* 퀵 정렬의 수행 시간은 다음과 같다.
  * 분할
    * 분할은 집합의 모든 요소를 탐색하는 과정으로 Θ(n)이 소요된다.
  * 병합
    * 이상적인 경우
      * 이상적인 경우는 분할이 항상 균등하게 될 때로 병합 정렬과 같은 모양이 되어서 다음의 수식에 따라 Θ(n log n)이 된다.
![이상적인 경우](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%203.png?raw=true)
    * 최악의 경우
      * 최악의 경우는 계속해서 한쪽은 하나도 없고 다른 쪽에 다 몰리도록 분할되는 경우로 다음의 수식에 따라 Θ(n^2)이 된다.
![최악의 경우](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%204.png?raw=true)
    * 평균적인 경우
      * 평균적인 경우는 피봇을 랜덤으로 정하는 경우로 모든 가능한 경우의 수를 구해 평균을 낸 것이다.
      * 어떠한 경우의 수행 시간은 다음과 같다.(T(i-1)와 T(n-i)는 재귀호출 비용, Θ(n)는 분할 비용)
![평균적인 경우의 어떤 분할에 대한 식](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%205.png?raw=true)
      * 이 수식을 평균내면 다음의 수식이 나오고 이를 계산하면 Θ(n log n)이다.
![평균적인 경우](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%206.png?raw=true)

## 7. 힙 정렬

* 힙 자료구조를 사용하여 정렬을 수행하는 방식의 알고리즘
* 힙은 다음의 특징을 가지고 있다.
  * 힙은 완전 이진 트리이다.
  * 힙은 최소 힙과 최대 힙이 있다. 최소 힙을 기준으로 다음의 성질을 만족한다.
    * 각 노드의 값은 자기 자식의 값보다 작거나 같다.(최대 힙은 반대)
    * 따라서 최소 힙의 루트 노드는 최솟값, 최대 힙의 루트 노드는 최대값이 있다.
  * 힙은 완전 이진 트리이므로 배열로도 표현이 가능하다.
  * 루트 노드는 1부터 시작하고 부모와 자식의 관계는 다음과 같다.
    * 부모 노드가 i번째일 때
      * 왼쪽 자식 : (2 * i)번째
      * 오른쪽 자식 : [(2 * i) + 1]번째
    * 자식 노드가 i번째일 때
      * 부모 노드 : ⌊(i/2)⌋번째
* 힙 정렬을 하기 위해서 요소들의 집합을 힙으로 만드는 과정과 요소 하나를 제거하고 힙 성질을 만족하도록 수선하는 과정이 필요하다.
* 힙을 만드는 것에 대한 알고리즘은 다음과 같다.
``` java
buildHeap(A[], n) {
  for i ← ⌊(n/2)⌋ downto 1 {
    heapify(A, i, n);
  }
}

heapify(A[], k, n) {
  // A[k]를 루트로 하는 트리가 힙성질을 만족하도록 수선
  // A[k]의 두 자식을 루트로 하는 서브 트리는 힙성질을 이미 만족
  // n : 최대 인댁서(전체 배열의 크기)
  left ← 2k; right = 2k+1;
  if(right ≤ n) then { // 두 자식을 가지는 있는 경우
    if(A[left] < A[right] then smaller ← left;
    else smaller ← right;
  }
  else if(left ≤ n) then smaller ← left; // k의 왼쪽 자식만 있는 경우
  else return; // A[k]가 리프 노드인 경우
  
  // 최소 힙이나 최대 힙의 성질에 반할 경우 smaller를 기준으로 재수선
  if(A[smaller] < A[k]) then {
    A[k] ↔ A[smaller];
    heapify(A, smaller, n);
  }
}
```
* 이를 구현하면 다음과 같다.
```java
void buildHeap(int [] values, int size){
    for(int i = values.length/2; i > 0; i--){
        heapify(values, i, size);
    }
}

void heapify(int [] values, int root, int size){
    int left = 2 * root, right = 2 * root + 1;
    int smaller;
    if(right < size){
        if(values[left] < values[right]){
            smaller = left;
        }
        else {
            smaller = right;
        }
    }
    else if(left <= size){
        smaller = left;
    }
    else {
        return;
    }
    if(values[smaller] < values[root]){
        int temp = values[root];
        values[root] = values[smaller];
        values[smaller] = temp;
        heapify(values, smaller);
    }
}
```
* 힙을 만든 다음에는 정렬 작업을 진행한다. 이를 알고리즘으로 표현하면 다음과 같다.
``` java
heapSort(A[], n) {
  buildHeap(A, n);
  // 원소를 교환한 후 힙 성질을 만족하도록 수선
  for i ← n downto 2 {
    A[1] ↔ A[i];
    heapify(A, 1, i-1);
  }
}
```
* 이를 구현하면 다음과 같다.
```java
void heapSort(int [] values){
    buildHeap(values);
    for(int i = values.length-1; i > 1; i--){
        int temp = values[1];
        values[1] = values[i];
        values[i] = temp;
        heapify(values, 1, i-1);
    }
}
```
* 힙 정렬의 시간 복잡도는 다음과 같다.
  * buildHeap의 경우
    * buildHeap은 ⌊(i/2)⌋의 heapify를 수행한다.
    * 리프 노드에 대해서 heapify를 수행하지 않기 때문에 다음의 식에 따라 Θ(n)의 수행 시간이 소요된다.
![buildHeap 수행시간](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%207(%EC%88%98%EC%A0%95).png?raw=true)
      * 안의 등비급수에 대해서 계산하면 다음과 같은 식과 결과가 나온다. 
![buildHeap 수행시간](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%208(%EC%88%98%EC%A0%95).png?raw=true)
![bulidHeap 수행시간](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%209(%EC%88%98%EC%A0%95).png?raw=true)
      * 여기서 h/(2^h)는 x가 1/2인 경우이다. 따라서 상한은 2다. 가장 위의 식에 대입하면 Ο(n)이 된다.
![bulidHeap 수행시간](https://github.com/seonghwanJang/Java-Coding-Test/blob/main/.idea/Images/%EC%A0%95%EB%A0%AC/%EA%B3%B5%EC%8B%9D%2010(%EC%88%98%EC%A0%95).png?raw=true)
  * heapify의 경우
    * buildHeap에서 계산한 식 중 O(h)가 heapify를 수행하는데 소요되는 시간이다.
    * h는 트리의 높이이고 h = log n(log의 밑은 2)이기 때문에 최악의 경우를 계산하면 O(log n)이다.
  * heapSort의 경우
    * heapSort는 buildHeap을 한 번 수행하고 n번의 heapify를 수행한다.
    * 따라서 buildHeap과 n번의 heapify를 수행하는데 Θ(n log n)이 든다.(비교 정렬의 경우 최악의 경우에 Θ(n log n)의 시간이 든다.)

## 8. 기수 정렬(Radix Sort)

* 