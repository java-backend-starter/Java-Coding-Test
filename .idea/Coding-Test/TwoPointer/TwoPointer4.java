import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoPointer4 {
    /* 
    * 작성일시 : 2025-02-13
    * 작성시간 : 23:52
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 3273
    * 문제 이름 : 두 수의 합
    * 문제 난이도 : 실버 Ⅲ
    *
    * 작성 목적
    * 
    * 백준에 있는 투 포인터 문제 풀이
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력받을 데이터의 요소의 개수
         */
        int size = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int [] datas = new int[size];
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * number : 두 요소의 합으로 나와야 할 값
         * start, end : 투 포인터를 위한 변수
         * count : datas[start] + datas[end] == number를 만족시키는 두 요소의 개수를 저장
         */
        int number = Integer.parseInt(br.readLine());
        int start = 0;
        int end = size-1;
        int count = 0;

        /*
         * 이 문제는 두 요소의 합 == 특정한 값인지 여부를 확인해야 하기 때문에
         * 정렬을 수행한 다음에 투 포인터 알고리즘을 사용해야 효율적으로 답을 구할 수 있다.
         * 
         * 이 문제의 경우는 다음과 같다.
         * 1. 두 수의 합 == number
         * 2. 두 수의 합 < number
         * 3. 두 수의 합 > number
         * 
         * 이 중 1의 경우를 찾는 것이다.
         */
        Arrays.sort(datas);
        while(start < end){
            if(datas[start] + datas[end] < number){
                start++;
            }
            else if(datas[start] + datas[end] > number){
                end--;
            }
            else {
                count++;
                start++;
                end--;
            }
        }
        System.out.println(count);
    }
}
