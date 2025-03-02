import java.io.*;

public class Sort6 {
    /*
     * 작성일시 : 2025-03-02
     * 작성시간 : 09:34
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11004
     * 문제 이름 : k번째 수
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static void mergeSort(int [] values, int start, int end){
        if(start < end){
            int mid = (start+end)/2;
            mergeSort(values, start, mid);
            mergeSort(values, mid+1, end);
            merge(values, start, mid, end);
        }
    }

    static void merge(int [] values, int start, int middle, int end){
        int left = start, right = middle+1, t = start;
        int [] sorted = new int[values.length];
        while(left <= middle && right <= end){
            if(values[left] < values[right]){
                sorted[t++] = values[left++];
            }
            else {
                sorted[t++] = values[right++];
            }
        }

        while(left <= middle){
            sorted[t++] = values[left++];
        }

        while(right <= end){
            sorted[t++] = values[right++];
        }

        left = start;
        t = start;

        while(left <= end){
            values[left++] = sorted[t++];
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int [] values = new int[size];
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(values, 0, values.length-1);

        for(int v : values){
            System.out.println(v);
        }
    }
}