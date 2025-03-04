# 너비 우선 탐색과 깊이 우선 탐색

### 최초 작성일시 : 2025-03-04
### 최초 작성시간 : 10:42
### 최초 작성자 : 장성환
### 작성 목적 : 너비 우선 탐색과 깊이 우선 탐색 개념 정리

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

### 1. 깊이 우선 탐색(Depth First Search)

* 그래프에서 특정 정점에서 갈 수 있는 노드까지 탐색하고 더 이상 갈 수 없으면 이전 노드로 되돌아가는 방식의 그래프 순회 방법
* 노드를 탐색하다가 다른 노드로 갈 수 없으면 이전 노드로 돌아가서 깊이 우선 탐색을 수행해야 하기 때문에 스택을 사용
* 깊이 우선 탐색의 수행 과정은 다음과 같다.
  1. 시작 정점 v를 결정해서 방문
  2. 정점 v와 인접한 정점 중에서 
     1. 방문하지 않은 정점 w가 있으면 정점 v를 스택에 push하고 w를 방문 -> w를 v로 해서 2를 반복
     2. 방문하지 않은 정점 w가 없으면 스택을 pop하여 받은 가장 마지막에 방문한 정점을 v로 하여 2을 반복
  3. 스택이 공백이 될 떄까지 반복
* 깊이 우선 탐색의 알고리즘은 다음과 같다.
``` java
// 재귀 호출 버전
DFS(v) {
    visited[v] = YES;
    for each x ∈ L(v) // L(v) : 정점 v의 인접 정점 집합
        if(visited[x] = NO) then DFS(v);
}
```
``` java
// 스택 사용 버전
DFS(v) {
    visited[v] = YES;
    while(stack is not empty) {
        if(visited[w] = NO) then { // w : v의 인접 정점
            stack.push(v);
            visited[w] = YES;
            v ← w;
        }
        else {
            v ← stack.pop();    
        }
    }
}
```
* 이를 구현하면 다음과 같다.

```java
// 재귀 호출 버전
import java.util.ArrayList;

static ArrayList<ArrayList<Integer>> graph;
static boolean [] visited;

// 중략 : 그래프 생성 등의 코드

void DFS(int vertex) {
    visited[vertex] = true;
    for(int v : graph[vertex]){
        if(!visited[v]){
            DFS(v);
        }
    }
}
```

```java
// 스택 사용 버전
import java.util.ArrayList;
import java.util.Stack;

static ArrayList<ArrayList<Integer>> graph;
static boolean[] visited;

// 중략 : 그래프 생성 등의 코드

void DFS(int vertex) {
    Stack<Integer> stack = new Stack<>();
    stack.push(vertex);
    visited[vertex] = true;

    while (!stack.isEmpty()) {
        int v = stack.pop();
        for (int w : graph.get(v)) {
            if (!visited[w]) {
                stack.push(v); // 백트래킹을 위해 현재 정점 다시 넣음
                stack.push(w);
                visited[w] = true;
                break;
            }
        }
    }
}
```

### 2. 너비 우선 탐색(Breadth First Search)

* 그래프에서 특정 정점과 인접한 정점들을 모두 방문하고 방문했던 정점을 시작으로 다시 인접한 정점들을 차례로 방문하는 방식의 순회 방법
* 인접한 정점에서 다시 너비 우선 탐색을 진행하기 때문에 탐색 과정에서 정점 순서를 관리하기 위해 큐를 사용
* 너비 우선 탐색은 다음과 같이 수행된다.
  1. 시작 정점 v를 결정하여 방문
  2. 정점 v에 인접한 정점 중에서 방문하지 않은 정점을 차례로 큐에 삽입
  3. 방문하지 않은 인접 정점이 없으면 방문했던 정점에서 인접한 정점을 다시 차례로 방문하기 위해 큐에서 정점 하나를 빼서 2를 반복
  4. 스택이 공백이 될 때까지 2와 3을 반복
* 너비 우선 탐색의 알고리즘은 다음과 같다.
``` java
    for each v ∈ V - |s|
        visited[v] ← NO;
    visited[s] ← YES;
    Q.enqueue(s);
    while(Q is not empty) {
        u ← Q.dequeue();
        for each v ∈ L(u) // L(u) : 정점 u의 인접 정점 집합
            if(visited[v] = NO) then {
                visited[v] ← YES;
                Q.enqueue(v);
            }
    }
}
```