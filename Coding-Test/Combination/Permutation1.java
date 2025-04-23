import java.io.*;
import java.util.*;

public class Permutation1 {
    /*
     * 최초 작성일시 : 2025-04-21
     * 최초 작성시간 : 12:58
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 1722
     * 문제 이름 : 순열의 순서
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */
    /*
     * factorial : 각 숫자 개수(n)에 대해 만들 수 있는 순열의 총 경우의 수 저장
     *             예: factorial[3] = 6 (3! = 3×2×1)
     *
     * permutation : 순열 정보를 담는 배열 (1-based index 사용)
     *
     * visited : 순열 생성/탐색 중 이미 사용된 숫자인지 여부를 체크하기 위한 배열
     */
    static long[] factorial;
    static int[] permutation;
    static boolean[] visited;

    /*
     * k번째 순열을 구하는 함수
     *
     * 입력:
     * - size : 순열의 크기 (요소 개수)
     * - k    : 몇 번째 순열인지 (1-based index)
     *
     * 결과:
     * - permutation 배열에 k번째 순열 결과가 저장됨
     */
    static void findPermutation(int size, long k) {
        // 각 자리(position i)에 들어갈 수를 결정
        for (int i = 1; i <= size; i++) {
            long count = 0; // 가능한 수 중 아직 사용되지 않은 수의 위치를 세기 위한 변수

            for (int j = 1; j <= size; j++) {
                if (visited[j]) continue; // 이미 사용된 수는 건너뜀

                // (size - i)! 만큼의 경우의 수가 한 숫자마다 반복됨
                // 이 범위 안에 현재 k가 들어온다면 j를 현재 위치(i)에 배정
                if (k <= factorial[size - i] * (count + 1)) {
                    permutation[i] = j;       // 현재 자리(i)에 j를 배정
                    visited[j] = true;       // 해당 수는 이제 사용되었음
                    k -= factorial[size - i] * count; // 남은 k값 갱신
                    break;
                }
                count++; // 다음 후보 숫자로 이동
            }
        }
    }

    /*
     * 현재 입력된 순열이 사전 순으로 몇 번째 순열인지 구하는 함수
     *
     * 입력:
     * - size : 순열의 크기 (요소 개수)
     * - st   : 순열이 담긴 StringTokenizer (공백으로 분리된 숫자들)
     *
     * 결과:
     * - 해당 순열이 사전 순에서 몇 번째 순열인지 반환 (1-based index)
     */
    static long findK(int size, StringTokenizer st) {
        long k = 1; // 1부터 시작 (1번째 순열)

        for (int i = 1; i <= size; i++) {
            // i번째 숫자를 입력받아 permutation 배열에 저장
            permutation[i] = Integer.parseInt(st.nextToken());
            long count = 0; // permutation[i]보다 작은 수 중 사용되지 않은 수 개수

            // permutation[i]보다 작은 수 중 아직 방문하지 않은 수 개수 계산
            for (int j = 1; j < permutation[i]; j++) {
                if (!visited[j]) {
                    count++;
                }
            }

            // count개의 수가 i번째 자리에 올 수 있었으므로, 그만큼 순열 수 증가
            k += count * factorial[size - i];

            // 현재 수는 사용 처리
            visited[permutation[i]] = true;
        }

        return k; // 최종적으로 계산된 순열 번호 반환
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());

        factorial = new long[size+1];
        permutation = new int[size+1];
        visited = new boolean[size+1];

        // 경우의 수 초기화
        factorial[0] = 1;
        for(int i = 1; i <= size; i++){
            factorial[i] = factorial[i-1] * i;
        }

        st = new StringTokenizer(br.readLine());

        /*
         * query : 사용자로부터 입력받는 질의 유형
         * - 1인 경우: k번째 순열을 구하여 출력함
         * - 2인 경우: 주어진 순열이 사전 순으로 몇 번째인지 계산하여 출력함
         */
        int query = Integer.parseInt(st.nextToken());
        if(query == 1){
            long k = Long.parseLong(st.nextToken());
            findPermutation(size, k);
            for(int i = 1; i <= size; i++){
                bw.write(permutation[i] + " ");
            }
            bw.flush();
        }
        else if(query == 2){
            long k = findK(size, st);
            System.out.println(k);
        }
    }
}