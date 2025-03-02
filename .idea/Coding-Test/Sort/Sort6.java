import java.io.*;

public class Sort6 {
    /*
     * 작성일시 : 2025-03-02
     * 작성시간 : 09:34
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2751
     * 문제 이름 : 수 정렬하기 2
     * 문제 난이도 : 실버 Ⅴ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */
    static void mergeSort(int[] values, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            // 왼쪽 부분 정렬
            mergeSort(values, start, mid);
            // 오른쪽 부분 정렬
            mergeSort(values, mid + 1, end);
            // 정렬된 두 부분을 병합
            merge(values, start, mid, end);
        }
    }

    static void merge(int[] values, int start, int middle, int end) {
        int left = start, right = middle + 1;

        // ⚠️ 필요할 만큼만 크기를 가지는 임시 배열 생성
        int[] sorted = new int[end - start + 1];
        int t = 0;

        // 두 부분 배열을 비교하면서 작은 값을 sorted 배열에 저장
        while (left <= middle && right <= end) {
            if (values[left] < values[right]) {
                sorted[t++] = values[left++];
            } else {
                sorted[t++] = values[right++];
            }
        }

        // 왼쪽 배열의 남은 요소를 추가
        while (left <= middle) {
            sorted[t++] = values[left++];
        }

        // 오른쪽 배열의 남은 요소를 추가
        while (right <= end) {
            sorted[t++] = values[right++];
        }

        // 정렬된 값을 원본 배열에 복사
        for (int i = 0; i < sorted.length; i++) {
            values[start + i] = sorted[i];
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // size : 입력받을 요소의 개수
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