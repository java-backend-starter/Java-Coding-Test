import java.io.*;
import java.util.*;

public class DynamicProgramming6 {
    /*
     * 최초 작성일시 : 2025-04-26
     * 최초 작성시간 : 15:12
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 13398
     * 문제 이름 : 연속 합
     * 문제 난이도 : 골드 Ⅴ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     *
     */
    /*
     * 문제 설명:
     * 정수 수열이 주어질 때, 연속된 몇 개의 수를 선택하여 얻을 수 있는 최대 합을 구한다.
     * 단, 수열에서 "한 개의 원소를 제거할 수 있음".
     *
     * 예:
     * 입력: 10 -4 3 1 5 6 -35 12 21 -1
     * 출력: 54 (6을 제거한 후 12 + 21 + (-1) = 32, 또는 -35를 제거 등)
     *
     * 해결 전략:
     * - 일반적인 연속 합(DP)처럼 접근하되,
     * - 각 인덱스를 제거했을 때를 고려하여 양쪽 방향의 최대 연속 합을 따로 저장
     *
     * 점화식:
     * - leftSum[i]  = max(values[i], values[i] + leftSum[i-1])
     *   → 0부터 i까지의 구간에서 i를 끝으로 하는 최대 연속 합
     * - rightSum[i] = max(values[i], values[i] + rightSum[i+1])
     *   → i부터 끝까지 구간에서 i를 시작으로 하는 최대 연속 합
     *
     * - 어떤 하나의 인덱스 i를 제거했을 때,
     *   최대 합은 leftSum[i-1] + rightSum[i+1]
     *
     * 최종 정답은:
     * 1. 아무것도 제거하지 않고 얻은 최대 합
     * 2. 하나의 인덱스를 제거해서 얻는 최대 합
     * 이 두 가지 중 큰 값
     */

    // 입력된 수열
    static int[] values;

    // 각 위치에서 왼쪽에서부터의 최대 연속 합
    static int[] leftSum;

    // 각 위치에서 오른쪽에서부터의 최대 연속 합
    static int[] rightSum;

    // 왼쪽/오른쪽 누적합 배열을 채우고, 제거하지 않았을 때의 최대 연속 합을 계산
    static int mamoize(int size) {
        leftSum = new int[size];
        rightSum = new int[size];

        // 시작값 초기화
        leftSum[0] = values[0];
        rightSum[size - 1] = values[size - 1];

        // 제거하지 않았을 때 최대 연속 합 (초깃값)
        int result = leftSum[0];

        // 왼쪽에서 오른쪽으로 최대 연속 합 계산 (Kadane's 알고리즘)
        for (int i = 1; i < size; i++) {
            leftSum[i] = Math.max(values[i], values[i] + leftSum[i - 1]);
            result = Math.max(result, leftSum[i]); // 중간중간 최댓값 갱신
        }

        // 오른쪽에서 왼쪽으로 최대 연속 합 계산
        for (int i = size - 2; i >= 0; i--) {
            rightSum[i] = Math.max(values[i], rightSum[i + 1] + values[i]);
        }

        return result;
    }

    // 하나의 원소를 제거했을 때의 최대 합을 계산
    static int max(int size, int result) {
        for (int i = 1; i < size - 1; i++) {
            // i번째 원소를 제거한 경우: i-1까지의 최대 연속 합 + i+1부터의 최대 연속 합
            int temp = leftSum[i - 1] + rightSum[i + 1];
            result = Math.max(result, temp); // 기존 최댓값과 비교하여 갱신
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 수열의 길이
        int size = Integer.parseInt(br.readLine());
        values = new int[size];

        // 수열 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 원소를 제거하지 않은 경우 최대 합 계산
        int result = mamoize(size);

        // 2. 하나의 원소를 제거했을 때 가능한 최대 합 계산 후 최댓값 비교
        System.out.println(max(size, result));
    }
}
