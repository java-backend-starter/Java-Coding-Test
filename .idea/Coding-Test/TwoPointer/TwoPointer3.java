import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class TwoPointer3 {
    /* 
    * 작성일시 : 2025-02-11
    * 작성시간 : 21:46
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1253
    * 문제 이름 : 좋다
    * 문제 난이도 : 골드 Ⅲ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력받을 수의 개수
         */
        int size = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int [] datas = new int[size];
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * count는 문제의 조건인 어떤 수가 다른 두 수의 합으로 표현될 수 있는 경우를 만족시키는 수의 개수를 저장
         * 이 문제도 배열을 정렬한 상태에서 알고리즘을 수행해야 효율적으로 답을 구할 수 있다.
         */
        int count = 0;
        Arrays.sort(datas);
        /*
         * 이 문제도 기본적으로 3가지 경우를 충족해야 한다.
         * 1. 어떤 수가 두 개의 수의 합과 같은 경우(문제의 조건)
         * 2. 어떤 수가 두 개의 수의 합보다 작은 경우
         * 3. 어떤 수가 두 개의 수의 합보다 큰 경우
         * 
         * 어떤 수가 두 개의 수의 합과 같은 경우일 때에는
         * 두 개의 수에 어떤 수가 포함되지 않아야 한다는 추가 조건도 충족시켜야 한다.
         */
        for(int i = 0; i < size; i++){
            int number = datas[i];
            int start = 0;
            int end = size - 1;
            while(start < end){
                if(datas[start] + datas[end] == number){
                    /*
                     * 어떤 숫자가 다른 두 수의 합으로 표현된 경우
                     * 이때 어떤 숫자가 다른 두 수에 포함되지 않아야 한다.
                     * 한 번이라도 다른 두 수의 합으로 표현되기만 하면 조건을 충족하기 때문에 break 문이 존재
                     */
                    if(start != i && end != i){
                        count++;
                        break;
                    }
                    else if(start == i){
                        start++;
                    }
                    else if(end == i){
                        end--;
                    }
                }
                else if(datas[start] + datas[end] < number){
                    start++;
                }
                else {
                    end--;
                }
            }
        }

        System.out.println(count);
    }
}
