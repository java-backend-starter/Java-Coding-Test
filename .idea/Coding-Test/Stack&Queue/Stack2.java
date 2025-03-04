import java.io.*;
import java.util.Stack;
public class Stack2 {
    /* 
    * 작성일시 : 2025-02-17
    * 작성시간 : 18:53
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1918
    * 문제 이름 : 후위 표기식
    * 문제 난이도 : 골드 Ⅱ
    *
    * 작성 목적
    *  
    * 백준에 있는 문제 풀이
    */
    /*
     * precedence : 연산자 우선순위를 정하는 함수
     * *, /는 1순위, +, -는 2순위를 표현한 것으로 숫자가 클수록 우선순위가 높다.
     */
    public static int precedence(char symbol){
        switch (symbol) {
            case '+', '-' -> {
                return 1;
            }
            case '*', '/', '%' -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char [] express = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        /*
         * 후위 표현식으로 변경하는 과정
         * 1. '('인 경우 : stack에 삽입
         * 2. ')'인 경우 : 연산자들을 출력, stack.pop()은 stack에 있는 '('를 빼기 위해 존재
         * 3. '+', '-', '*', '/', '%'인 경우 : 연산자 우선순위에 따라 연산사들을 출력
         * 4. 피연산자인 경우(default) : 그대로 출력
         */
        for(int i = 0; i < express.length; i++){
            char symbol = express[i];
            switch (symbol) {
                case '(' -> {
                    stack.push(symbol);
                }
                case ')' -> {
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                case '+', '-', '*', '/', '%' -> {
                    while(!stack.isEmpty() && (precedence(stack.peek()) >= precedence(symbol))){
                        sb.append(stack.pop());
                    }
                    stack.push(symbol);
                }
                default -> {
                    sb.append(symbol);
                }
            }
        }
        /*
         * 스택에 남아있는 연산자를 일괄 출력
         * 위의 for문으로는 모든 연산자를 출력할 수 없는 경우가 있기 때문에 반드시 거쳐야 하는 과정
         */
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
}
