import java.io.*;
import java.util.*;

public class Prime2 {
    /*
     * 최초 작성일시 : 2025-03-25
     * 최초 작성시간 : 12:09
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1456
     * 문제 이름 : 거의 소수
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 에라토스테네스의 체를 구현
     * 백준 1456번 문제에서 주어진 값의 범위는 10^14 이하이므로 values 배열의 크기는 10^7(10^14의 제곱근)로 고정된다.
     * 따라서 Math.sqrt() 함수를 사용하지 않고 소수를 구한다.
     */
    static void eratosthenes(long [] values){
        for(int i = 2; i < values.length; i++){
            if(values[i] == 0){
                continue;
            }
            for(int j = i+i; j < values.length; j += i){
                values[j] = 0;
            }
        }
    }
    /*
     * 거의 소수를 구하는 알고리즘
     * 문제에서 거의 소수는 소수의 거듭제곱 형태를 의미한다.
     * 그래서 문제에서 주어진 범위(min 이상 max 이하)에서 거의 소수의 개수를 구한다.
     */
    static int counter(long [] values, long min, long max) {
        long temp; // 소수의 거듭제곱을 저장할 변수
        int count = 0; // 거의 소수 개수를 저장할 변수
        /*
         * 배열 values에는 소수가 저장되어 있으며,
         * 이를 이용해 소수의 거듭제곱 형태인 숫자(거의 소수)를 찾는다.
         */
        for(int i = 2; i < values.length; i++) {
            if(values[i] != 0) { // 소수인 경우
                temp = values[i]; // 소수의 첫 번째 거듭제곱은 자기 자신
                /*
                 * 소수 values[i]의 거듭제곱을 계산하면서 min과 max 범위 내에 있는지 확인한다.
                 * temp가 max를 초과하기 전까지 계속 거듭제곱을 증가시킨다.
                 */
                while((double) values[i] <= (double) max / (double) temp) {
                    // 현재 거듭제곱(temp)이 min 이상이면 거의 소수로 카운트
                    if((double) values[i] >= (double) min / (double) temp) {
                        count++;
                    }
                    temp *= values[i]; // 다음 거듭제곱 계산
                }
            }
        }
        return count; // 최종적으로 찾은 거의 소수 개수를 반환
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * min, max : 문제에서 주어진 범위
         */
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        /*
         * 소수를 저장하고 있는 배열
         * 10^14까지의 범위에서 소수를 저장한다.
         */
        long [] values = new long[10000001];
        for(int i = 2; i < values.length; i++){
            values[i] = i;
        }
        eratosthenes(values);

        System.out.println(counter(values, min, max));
    }
}