import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class TwoPointer2 {
    /* 
    * 작성일시 : 2025-02-11
    * 작성시간 : 21:14
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1940
    * 문제 이름 : 주몽
    * 문제 난이도 : 실버 Ⅳ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 갑옷을 만드는데 사용할 재료의 개수
         * number : 갑옷을 만들 수 있는 고유번호의 합
         */
        int size = Integer.parseInt(br.readLine());
        int number = Integer.parseInt(br.readLine());

        int [] datas = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * 이 문제는 두 개의 포인터가 양쪽 끝을 향해야 한다.
         * 따라서 start는 첫 번째 요소를 가리키고 end는 마지막 요소를 가리킬 수 있도록 초기화한다.
         * count는 갑옷을 만들 수 있는 재료의 조합의 개수를 저장하는 변수이다.
         */
        int start = 0;
        int end = size - 1;
        int count = 0;

        /*
         * 재료의 고유 번호를 정렬하면 효율적으로 문제의 답을 구할 수 있어서 Arrays.sort() 함수를 사용했다.
         */
        Arrays.sort(datas);
        /*
         * 백준의 2018번 문제(수들의 합 5)와 같이 3가지 경우의 수로 나누어서 본다.
         * 1. 두 재료의 고유 번호의 합이 갑온을 만들 수 있는 번호와 동일할 때(문제에서 요구하는 조건)
         * 2. 두 재료의 고유 번호의 합이 갑온을 만들 수 있는 번호보다 작을 때
         * 3. 두 재료의 고유 번호의 합이 갑온을 만들 수 있는 번호보다 클 때
         */
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
