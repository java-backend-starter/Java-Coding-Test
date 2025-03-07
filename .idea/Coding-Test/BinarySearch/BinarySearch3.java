import java.io.*;

public class BinarySearch2 {
    /*
     * 최초 작성일시 : 2025-03-07
     * 최초 작성시간 : 11:18
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 2343
     * 문제 이름 : 평균
     * 문제 난이도 : 브론즈 Ⅰ
     *
     * 작성 목적
     *
     * 책에 있는 문제 복습
     */

    static int binarySearch(int n, int k){
        int start = 1, end = k, answer = 0;
        while(start <= end){
            int mid = (start+end);
            int count = 0;
            for(int i = 0; i < n; i++){
                count += Math.min(mid/i, n);
            }
            if(count > mid){
                end = mid + 1;
                answer = mid;
            }
            else {
                start = mid - 1;
            }
        }
        return start;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(n, k));
    }
}