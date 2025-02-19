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
* 