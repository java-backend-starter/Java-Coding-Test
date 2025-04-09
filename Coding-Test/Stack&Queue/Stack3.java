import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Stack3 {
    /* 
    * 작성일시 : 2025-02-17
    * 작성시간 : 19:34
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 17298
    * 문제 이름 : 오큰수
    * 문제 난이도 : 골드 Ⅳ
    *
    * 작성 목적
    *  
    * 백준에 있는 문제 풀이
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * size : 입력받을 요소의 개수
         * datas : 요소가 들어있는 배열
         * answer : 정답 배열(오큰수가 있는 배열)
         */
        int size = Integer.parseInt(br.readLine());
        int [] datas = new int[size];
        int [] answer = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * 오큰수를 구하는 과정
         * stack에 넣을 값은 인덱스로 stack.push(0)는 다른 수와 비교를 위해 첫 번째 요소의 인덱스 값을 미리 저장
         */
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < size; i++){
            /*
             * i번째 요소보다 왼쪽에 있는 요소들 중에서 i번째 요소보다 작은 값이 있다면 정답 배열에 i번째 요소의 값을 저장
             * stack.push(i)는 i번째 요소에 대한 처리가 끝났다는 것을 의미
             */
            while(!stack.isEmpty() && datas[stack.peek()] < datas[i]){
                answer[stack.pop()] = datas[i];
            }
            stack.push(i);
        }
        /*
         * 아래의 while문은 오큰수를 구하지 못한 요소들에 대한 처리
         * 마지막 요소는 오큰수를 구할 수 없기 때문에 항상 -1이다.
         */
        while(!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }

        for(int data : answer){
            bw.append(data + " ");
        }
        bw.flush();
    }
}
