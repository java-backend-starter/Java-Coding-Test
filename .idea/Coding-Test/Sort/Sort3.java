import java.io.*;

public class Sort3 {
    /*
     * 작성일시 : 2025-02-28
     * 작성시간 : 18:04
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1427
     * 문제 이름 : 소트 인사이트
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static void selectionSort(int [] values){
        int max;
        for(int i = 0; i < values.length; i++){
            max = i;
            for(int j = values.length-1; j > i; j--){
                if(values[max] < values[j]){
                    max = j;
                }
            }
            if(values[max] > values[i]){
                int temp = values[max];
                values[max] = values[i];
                values[i] = temp;
            }
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();

        int [] values = new int[value.length()];
        for(int i = 0; i < values.length; i++){
            values[i] = Integer.parseInt(value.substring(i, i+1));
        }

        selectionSort(values);

        for(int v : values){
            System.out.print(v);
        }
    }

}