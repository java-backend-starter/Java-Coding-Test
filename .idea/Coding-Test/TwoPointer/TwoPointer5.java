import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoPointer5 {
    /* 
    * 작성일시 : 2025-02-14
    * 작성시간 : 00:17
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2470
    * 문제 이름 : 두 용액
    * 문제 난이도 : 골드 Ⅴ
    *
    * 작성 목적
    * 
    * 백준에 있는 투 포인터 문제 풀이
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * size : 입력 받을 용액의 특성값의 개수
         */
        int size = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        /*
         * datas 배열 : 용액의 특성값을 저장
         * 각 요소의 특성값의 범위가 -10억 이상 10억 이하이기 때문에 long 타입으로 선언
         * 하지만 int의 범위가 대략적으로 -21억 이상 21억 이하이기 때문에 두 특성값의 합은
         * int 값의 범위를 넘지 않을 것 같다.
         */
        long [] datas = new long[size];
        for(int i = 0; i < size; i++){
            datas[i] = Long.parseLong(st.nextToken());
        }

        /*
         * start, end : 투 포인터 알고리즘에 사용할 변수
         * answer : 조건을 만족시키는 두 용액을 저장하는 배열 
         * min : 특성값의 합을 저장
         */
        int start = 0;
        int end = size - 1;
        int [] answer = new int[2];
        long min = 2000000001L;

        /*
         * 투 포인터 알고리즘을 효율적으로 적용하기 위해 정렬한다.
         * 이 문제는 지금까지 풀었던 투 포인터 문제들과 다른 알고리즘이 사용된다.
         * 
         * while문이 진행되는 과정으로는 다음과 같다.
         * 1. 두 용액을 섞어서 나오는 특성값(sum)을 구한다.
         * 2. 1에서 구한 특성값이 지금까지 구한 특성값의 합의 최솟값보다 작은지 체크
         * 2-1. 작으면 min 값을 갱신
         * 3. 두 용액을 섞어서 나온 특성값이 양수인지 여부를 확인해서 두 개의 포인터 변수 중 하나를 이동 
         */
        Arrays.sort(datas);
        while(start < end){
            long sum = datas[start] + datas[end];
            if(Math.abs(sum) < Math.abs(min)){
                answer[0] = start;
                answer[1] = end;
                min = sum;
            }
            
            if(sum > 0){
                end--;
            }
            else {
                start++;
            }
        }
        System.out.println(datas[answer[0]] + " " + datas[answer[1]]);
    }
}