import java.io.*;
import java.util.*;

public class Greedy1 {
    /*
     * 최초 작성일시 : 2025-03-11
     * 최초 작성시간 : 17:30
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11047
     * 문제 이름 : 동전 0
     * 문제 난이도 : 실버 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * minCoins : money를 만들기 위한 최소한의 동전 개수를 반환하는 함수
     * 단위가 가장 큰 동전부터 개수를 세면 최소한의 동전 개수를 반환이 가능
     * 문제는 coins에 있는 동전 단위가 오름차순으로 정렬된 상태로 입력되기에 마지막 인덱스부터 첫 번째 인덱스 방향으로 진행하면서 동전 개수를 센다.
     */
    static int minCoins(int money, int [] coins){
        int count = 0;
        for(int i = coins.length-1; i >= 0; i--){
            if(money >= coins[i]){
                count += money / coins[i];
                money %= coins[i];
            }
        }
        return count;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 입력받을 동전의 개수
         * money : 목표 금액
         */
        int size = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        int [] coins = new int[size];
        for(int i = 0; i < size; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        /*
         * coins 배열에 입력된 데이터는 오름차순으로 정렬된 상태로 입력되기에 따로 정렬할 필요는 없다.
         */
        System.out.println(minCoins(money, coins));
    }
}