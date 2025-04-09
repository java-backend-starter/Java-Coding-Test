import java.io.*;
public class SumNumbers{
     /* 
    * 작성일시 : 2025-01-24
    * 작성시간 : 00:45
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11720
    * 문제 이름 : 숫자의 합
    * 문제 난이도 : 브론즈 Ⅲ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * size : 입력받을 숫자의 개수
         * data : 공백 없이 주어진 size 개의 숫자
         */
        int size = Integer.parseInt(br.readLine());
        String data = br.readLine();

        /*
         * 이 문제는 문자열 형식의 숫자를 정수 타입으로 변환하는 법을 다룬다
         * 자바에서는 toCharArray() 함수로 char 배열로 바꾼 뒤에 숫자의 합을 구하거나
         * 문자열에서 charAt() 함수를 활용하여 숫자의 합을 구한다.
         */
        
        int sum = 0;

        // charAt()을 사용하는 경우
        for(int i = 0; i < size; i++){
            sum += (data.charAt(i) - '0'); 
            // sum += (data.charAt(i) - 48); // '0'은 ASCII 코드에서 48이다.
        }
        System.out.println("charAt() 을 사용하는 경우: " + sum);

        sum = 0;
        // charAt() 함수를 사용하지 않는 경우, 이떄는 String 타입의 data를 char 타입의 배열에 저장한 다음에 계산한다.
        char [] datas = data.toCharArray();
        for(int i = 0; i < datas.length; i++){
            sum += (datas[i] - '0');
            // sum += (datas[i] - 48);
        }
        System.out.println("char array를 사용하는 경우 : " + sum);
    }
}