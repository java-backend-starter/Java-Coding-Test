import java.io.*;
import java.util.*;

public class Prime1 {
    /*
     * 최초 작성일시 : 2025-03-25
     * 최초 작성시간 : 00:05
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1929
     * 문제 이름 : 소수 구하기
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * 에라토스테네스의 체를 이용한 소수 구하는 알고리즘
     * 1. boolean 배열을 생성한다.
     * 2. 0과 1은 소수에서 제외한다.
     * 3. 2부터 n까지의 수들 중 소수를 구한다.
     */
    static boolean [] eratosthenes(int n){
        boolean [] primes = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for(int i = 2; i <= (int) Math.sqrt(n); i++){
            if(primes[i]){
                for(int j = i + i; j <= n; j += i){
                    primes[j] = false;
                }
            }
        }
        return primes;
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * min, max : 소수를 구할 때의 범위, min 이상 max 이하에서 구한다.
         */
        int min = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        /*
         * 에라토스테네스의 체를 이용해서 소수들을 구한다.
         * 그 후 소수들을 모아서 출력한다.
         */
        boolean [] primes = eratostenes(max);

        for(int i = min; i <= max; i++){
            if(primes[i]){
                bw.append(i + "\n");
            }
        }
        bw.flush();
    }
}