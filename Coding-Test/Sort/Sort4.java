import java.io.*;
import java.util.StringTokenizer;

public class Sort4 {
    /*
     * 작성일시 : 2025-03-01
     * 작성시간 : 22:48
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11399
     * 문제 이름 : ATM
     * 문제 난이도 : 실버 Ⅳ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static void insertionSort(int [] values) {
        int location;
        int data;
        for(int i = 1; i < values.length; i++){
            location = i-1;
            data = values[i];
            while(location >= 0 && values[location] > data){
                values[location+1] = values[location];
                location--;
            }
            values[location+1] = data;
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size+1];
        int [] sum = new int[size+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= size; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        insertionSort(values);

        for(int i = 1; i <= size; i++){
            sum[i] = values[i] + sum[i-1];
        }

        int answer = 0;
        for(int i = 1; i <= size; i++){
            answer += sum[i];
        }

        System.out.println(answer);
    }

}