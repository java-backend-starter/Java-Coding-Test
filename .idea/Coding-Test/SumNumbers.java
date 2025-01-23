import java.io.*;
public class SumNumbers{
    /* 
    * 최초 작성일시 : 2025-01-24
    * 최초 작성시간 : 00:45
    * 최초 작성자 : 정성환
    * 
    * 마지막 작성일시 : 2025-01-24
    * 마지막 작성시간 : 01:43
    * 마지막 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 11720
    * 문제 이름 : 숫자의 합
    * 문제 난이도 : 브론즈 Ⅳ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * size : 더할 숫자의 개수
         * data : 입력받은 숫자(각 자리수의 숫자들을 더하는 것)
         */
        int size = Integer.parseInt(br.readLine());
        String data = br.readLine();

        /*
         * 데이터를 문자열로 받고 해당 문자열을 숫자로 변환해서 합을 구한다.
         * 문자열을 숫자로 변환할 때 문자 하나씩 숫자로 바꾼다.
         * 문자로 된 숫자들(예 : '0', '1')은 아스키코드에서 48부터 시작하므로
         * 48이나 '0'을 빼야 합을 정확하게 구할 수 있다. 
         */
        
        int sum = 0;

        // charAt() 함수를 사용허는 방법
        for(int i = 0; i < size; i++){
            sum += (data.charAt(i) - '0'); 
            // sum += (data.charAt(i) - 48); // '0' 대신 48이 들어가도 결과는 같다.
        }
        System.out.println("charAt() 함수를 이용한 경우 : " + sum);

        sum = 0;
        // char 타입의 array를 사용하는 방법
        char [] datas = data.toCharArray();
        for(int i = 0; i < datas.length; i++){
            sum += (datas[i] - '0');
            // sum += (datas[i] - 48);
        }
        System.out.println("char 배열을 이용한 경우 : " + sum);
    }
}