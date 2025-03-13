import java.io.*;

public class Greedy5 {
    /*
     * 최초 작성일시 : 2025-03-13
     * 최초 작성시간 : 21:29
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1541
     * 문제 이름 : 잃어버린 괄호
     * 문제 난이도 : 실버 Ⅱ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String calculate = br.readLine();
        String [] minus = calculate.split("-");

        int sum = 0;
        String [] plus;
        for(int i = 0; i < minus.length; i++){
            int sub = 0;
            plus = minus[i].split("[+]");
            for(int j = 0; j < plus.length; j++){
                sub += Integer.parseInt(plus[j]);
            }
            sum = i == 0 ? sub : sum - sub;
        }

        System.out.println(sum);
    }
}