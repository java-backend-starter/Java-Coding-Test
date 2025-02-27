import java.io.*;

public class Sort1 {
    /* 
    * 작성일시 : 2025-02-27
    * 작성시간 : 20:04
    * 작성자 : 장성환
    *
    * 문제 출처 : 백준
    * 문제 번호 : 2750
    * 문제 이름 : 수 정렬하기
    * 문제 난이도 : 브론즈 Ⅱ
    *
    * 작성 목적
    *  
    * 책에 있는 문제 복습
    */
    /*
     * 이 문제에서 입력되는 요소들의 개수는 최대 1000개이다. 
     * 따라서 Ο(n^2)이 걸리는 정렬 알고리즘을 사용하더라도 2초안에 풀 수 있다.
     * 아래는 Ο(n^2)이 걸리는 대표적인 정렬 알고리즘인 선택 정렬, 버블 정렬, 삽입 정렬을 구현한 것이다.
     */

    /*
     * 선택 정렬 알고리즘
     * 정렬되지 않은 부분에서 가장 작은 값을 찾아 위치를 변경한다.
     */
    static void selectionSort(int [] values){
        int smallest;
        for(int i = 0; i < values.length; i++){
            smallest = values.length-1;
            for(int j = values.length-2; j > i; j--){
                if(values[j] < values[smallest]){
                    smallest = j;
                }
            }
            if(values[smallest] < values[i]){
                int temp = values[smallest];
                values[smallest] = values[i];
                values[i] = temp;
            }
        }
    }

    /*
     * 버블 정렬 알고리즘
     * 옆에 있는 수와 비교해서 정렬한다.
     * sorted는 이미 정렬되었을 때 무의미한 반복을 하지 않기 위해 추가
     */
    static void bubbleSort(int [] values){
        for(int i = 0; i < values.length; i++){
            boolean sorted = true;
            for(int j = values.length-1; j > i; j--){
                if(values[j-1] > values[j]){
                    sorted = false;
                    int temp = values[j-1];
                    values[j-1] = values[j];
                    values[j] = temp;
                }
            }
            if(sorted){
                return;
            }
        }
    }

    /*
     * 삽입 정렬 알고리즘
     * 정렬된 부분 집합에서 어떠한 값이 들어가야 할 위치를 찾아 삽입해서 정렬하는 방식
     */
    static void insertionSort(int [] values){
        for(int i = 1; i < values.length; i++){
            int j = i - 1;
            int item = values[i];
            while(j >= 0 && values[j] > item){
                values[j+1] = values[j];
                j--;
            }
            values[j+1] = item;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
         * size : 입력받을 데이터의 개수
         */
        int size = Integer.parseInt(br.readLine());

        int [] values = new int[size];
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        /*
         * 정렬 알고리즘 실행.
         * 어떤 알고리즘을 쓰더라도 문제를 풀 수 있다.
         */
        // selectionSort(values);
        // bubbleSort(values);
        insertionSort(values);
        for(int value : values){
            bw.append(value + "\n");
        }
        bw.flush();
    }
}
