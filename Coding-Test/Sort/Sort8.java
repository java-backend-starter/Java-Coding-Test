import java.io.*;

public class Sort8{
    /*
     * 작성일시 : 2025-03-03
     * 작성시간 : 17:22
     * 작성자 : 장성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 10989
     * 문제 이름 : 수 정렬하기 3
     * 문제 난이도 : 브론즈 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    /*
     * 기수 정렬은 정렬해야 할 모든 요소가 특정 자리수 이하일 때 사용하는 정렬 알고리즘
     * radix : 기수(특정 진수)를 지정
     * digit : 자릿수를 지정
     */
    static void radixSort(int[] values, int radix, int digit) {
        // 정렬된 값을 저장할 output 배열
        int[] output = new int[values.length];
        // 각 자리에 대한 요소의 개수를 저장할 버킷 배열
        int[] bucket;
        // 현재 자릿수를 추출하기 위한 factor (처음에는 1로 시작)
        int factor = 1;

        // 각 자릿수에 대해 반복 (가장 낮은 자릿수부터 가장 높은 자릿수까지)
        for (int i = 0; i < digit; i++) {
            // radix (기수) 크기의 버킷 배열 초기화
            bucket = new int[radix];

            // 각 자릿수에 대한 요소의 개수를 셈
            for (int j = 0; j < values.length; j++) {
                // 현재 자릿수를 추출하고 해당 자릿수에 맞는 버킷의 카운트를 증가시킴
                bucket[(values[j] / factor) % radix]++;
            }

            // 버킷 배열을 누적합으로 변경 (현재 자릿수에 해당하는 위치를 계산)
            for (int j = 1; j < bucket.length; j++) {
                bucket[j] += bucket[j - 1];
            }

            // output 배열에 값을 위치시키기 위해 뒤에서부터 처리 (안정성 유지)
            for (int j = values.length - 1; j >= 0; j--) {
                // 현재 자릿수에 맞는 버킷 인덱스를 찾아 output 배열에 값 배치
                output[bucket[(values[j] / factor) % radix] - 1] = values[j];
                // 해당 버킷의 카운트를 감소시킴
                bucket[(values[j] / factor) % radix]--;
            }

            // output 배열의 값을 다시 values 배열로 복사 (다음 자릿수를 처리하기 위해)
            for (int j = 0; j < values.length; j++) {
                values[j] = output[j];
            }

            // 다음 자릿수를 처리하기 위해 factor를 radix로 곱함
            factor *= radix;
        }
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        int [] values = new int[size];
        for(int i = 0; i < size; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        radixSort(values, 10, 5);

        for(int value : values){
            bw.append(value + "\n");
        }
        bw.flush();
    }
}