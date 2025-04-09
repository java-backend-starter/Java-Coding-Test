import java.io.*;
import java.util.Stack;

public class Stack1 {
    /* 
    * 작성일시 : 2025-02-17
    * 작성시간 : 18:12
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1874
    * 문제 이름 : 스택 수열
    * 문제 난이도 : 실버 Ⅱ
    *
    * 작성 목적
    *  
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        /*
         * BufferedWriter를 써봤지만 백준에서 출력 초과 이슈가 있어서 StringBuilder로 변경
         * NO가 정답인 테스트케이스에서 BufferedWriter에 저장된 데이터가 같이 출력되는 현상 등이 있다고 하지만 찾아볼 예정
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        /*
         * size : 수열에 들어갈 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());
        int [] datas = new int[size];
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(br.readLine());
        }

        /*
         * number : stack에 들어갈 숫자
         * result : 답이 NO인 경우와 NO가 아닌 경우를 구분하기 위한 변수
         */
        int number = 1;
        boolean result = true;

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < size; i++){
            int now = datas[i];
            /*
             * if문 : now가 스택에서 top보다 크거나 같은 경우
             * 이때는 오름차순으로 출력이 가능하므로 스택에 now까지 삽입하고
             * 스택에서 now를 꺼내는 연산을 수행
             */
            if(now >= number){
                while(now >= number){
                    stack.add(number++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            }
            /*
             * else문 : now가 스택에서 top보다 작은 경우
             * 이때는 now == top인 경우와 now < top인 경우가 있다.
             * 전자는 스택에서 숫자 하나만 빠지는 경우로 오름차순 출력에 지장이 없다.
             * 하지만 후자는 다른 숫자들도 같이 빼야 하기 때문에 후입선출로 오름차순 출력이 불가능하다.
             */
            else{
                int top = stack.pop();
                if(top > now){
                    System.out.println("NO");
                    result = false;
                    break;
                }
                else{
                    sb.append("-\n");
                }
            }
        }

        if(result){
            System.out.println(sb.toString());
        }
    }    
}
