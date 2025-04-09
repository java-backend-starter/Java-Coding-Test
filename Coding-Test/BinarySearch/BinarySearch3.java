import java.io.*;

public class BinarySearch2 {
    /*
     * 최초 작성일시 : 2025-03-07
     * 최초 작성시간 : 11:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1300
     * 문제 이름 : 평균
     * 문제 난이도 : 골드 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    static int binarySearch(int n, int k) {
        /*
         * start, end : 탐색 범위를 1부터 k까지 설정
         * answer : 정답을 저장할 변수
         */
        int start = 1, end = k, answer = 0;

        while (start <= end) { // 이진 탐색 수행
            /*
             * mid : 중간값(mid) 계산
             * count : mid 이하의 숫자가 몇 개인지 세는 변수
             */
            int mid = (start + end) / 2;
            int count = 0;

            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n); // 각 행에서 mid 이하인 원소 개수 합산
            }

            if (count >= k) { // k번째 원소보다 크거나 같은 개수라면
                end = mid - 1; // 더 작은 값이 있을 수 있으므로 왼쪽 범위를 탐색
                answer = mid; // 현재 mid를 정답 후보로 저장
            }
            else { // count가 k보다 작다면
                start = mid + 1; // 오른쪽 범위를 탐색
            }
        }

        return answer; // 최종적으로 찾은 k번째 원소 반환
    }


    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(n, k));
    }
}