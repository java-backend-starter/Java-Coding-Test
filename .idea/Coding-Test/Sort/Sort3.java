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

    /*
     * 내림차순으로 정렬하는 선택 정렬 알고리즘
     * 가장 큰 값을 찾아서 i번째 요소와 자리를 변경
     * 백준 1427번 문제는 최대 10개의 요소들을 정렬하기 때문에 어떤 정렬 알고리즘을 사용해도 시간 내에 풀 수 있음.
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

        /*
         * value : 입력받은 숫자를 문자열로 받음
         */
        String value = br.readLine();

        /*
         * 입력받은 숫자에서 각 자리를 저장
         */
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