import java.util.*;

public class Prime3 {
    /*
     * 최초 작성일시 : 2025-03-25
     * 최초 작성시간 : 12:56
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1747
     * 문제 이름 : 소수&팰린드롬
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    /*
     * 에라토스테네스의 체를 이용한 소수 구하기
     * 일정한 범위의 숫자들에 대해서 소수들을 구한다.
     */
    static void eratosthenes(boolean [] primes){
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for(int i = 2; i < Math.sqrt(primes.length); i++){
            if(primes[i]){
                for(int j = i+i; j < primes.length; j += i){
                    primes[j] = false;
                }
            }
        }
    }
    /*
     * 팰린드롬 수를 식별하는 알고리즘
     * 팰린드롬은 어떤 수와 그 수의 숫자 순서를 뒤집은 수가 일치하는 수이다.(예시 : 131, 13531)
     * 따라서 숫자 1자리씩 저장하는 char 배열을 만들어서 양쪽 끝에서부터 숫자를 비교한다.
     */
    static boolean palindrome(int n){
        char [] temp = String.valueOf(n).toCharArray();
        int start = 0;
        int end = temp.length-1;
        while(start < end){
            char sc = temp[start];
            char ec = temp[end];
            if(sc != ec){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        /*
         * value : 소수와 팰린드룸 수를 비교할 때 시작할 숫자
         */
        int value = sc.nextInt();

        boolean [] primes = new boolean[10000001];
        eratosthenes(primes);

        while(true){
            /*
             * 소수이면서 펠린드롬 숫자인지 판별
             * break;가 있는 이유는 value 이상의 수들 중에서 소수이면서 팰린드롬 숫자인 최솟값만 찾으면 되기 때문
             */
            if(primes[value] && palindrome(value)){
                System.out.println(value);
                break;
            }
            value++;
        }
    }
}