import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SlidingWindow1 {
    /* 
    * 작성일시 : 2025-02-14
    * 작성시간 : 20:39
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 12891
    * 문제 이름 : DNA 비밀번호
    * 문제 난이도 : 실버 Ⅴ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    /*
     * check : 필요한 A, C, G, T의 최소 개수를 저장하는 배열
     * myArray : 현재 슬라이딩 윈도우에서 A, C, G, T의 개수를 저장하는 배열
     * checked : 현재까지 조건을 만족하는 문자 개수
     * count : 조건을 만족하는 부분 문자열의 개수
     */
    static int[] check = new int[4];
    static int[] myArray = new int[4];
    static int checked = 0;
    static int count = 0;

    /*
     * 슬라이딩 윈도우에 문자를 추가하는 메서드
     * 각 문자(A, C, G, T)가 요구되는 최소한의 개수를 충족하면 checked 값이 증가
     */
    static void add(char c) {
        switch (c) {
            case 'A' -> {
                myArray[0]++;
                if (myArray[0] == check[0]) { 
                    checked++;
                }
            }
            case 'C' -> {
                myArray[1]++;
                if (myArray[1] == check[1]) { 
                    checked++;
                }
            }
            case 'G' -> {
                myArray[2]++;
                if (myArray[2] == check[2]) { 
                    checked++;
                }
            }
            case 'T' -> {
                myArray[3]++;
                if (myArray[3] == check[3]) { 
                    checked++;
                }
            }
        }
    }

    
    /*
     * 슬라이딩 윈도우에 문자를 제거하는 메서드
     * 문자를 제거하기 전에 각 문자(A, C, G, T)가 요구되는 최소한의 개수를 충족하면 checked 값이 감소
     * 문자를 제거하기 전에 checked를 감소시키는 이유는 조건을 충족시킨 상태에서 조건을 충족하지 못하는 상태로 바뀌기 때문
     */
    static void remove(char c) {
        switch (c) {
            case 'A' -> {
                if (myArray[0] == check[0]) { 
                    checked--;
                }
                myArray[0]--;
            }
            case 'C' -> {
                if (myArray[1] == check[1]) { 
                    checked--;
                }
                myArray[1]--;
            }
            case 'G' -> {
                if (myArray[2] == check[2]) { 
                    checked--;
                }
                myArray[2]--;
            }
            case 'T' -> {
                if (myArray[3] == check[3]) { 
                    checked--;
                }
                myArray[3]--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * size : 문자열 길이 
         * partial : 부분 문자열 길이
         */
        int size = Integer.parseInt(st.nextToken());
        int partial = Integer.parseInt(st.nextToken());

        // password : DNA 문자열
        char[] password = br.readLine().toCharArray();

        // A, C, G, T 각각의 최소 개수 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            check[i] = Integer.parseInt(st.nextToken());
            if (check[i] == 0) { 
                checked++; // 해당 문자가 필요 개수가 0이면 조건 자동 만족
            }
        }

        // 초기 윈도우 설정 (첫 번째 부분 문자열)
        for (int i = 0; i < partial; i++) {
            add(password[i]); // 처음 partial 길이만큼 문자 추가
        }
        if (checked == 4) { 
            count++; // 모든 조건이 만족되면 count 증가
        }

        // 슬라이딩 윈도우 실행
        for (int i = partial; i < size; i++) {
            add(password[i]); // 새로운 문자 추가
            remove(password[i - partial]); // 앞쪽 문자 제거
            if (checked == 4) { 
                count++; // 모든 조건이 만족되면 count 증가
            }
        }

        System.out.println(count);
    }
}
