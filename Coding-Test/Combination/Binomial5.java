import java.io.*;
import java.util.*;

public class Binomial5 {
    /*
     * 최초 작성일시 : 2025-04-19
     * 최초 작성시간 : 10:30
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 13251
     * 문제 이름 : 조약돌 꺼내기
     * 문제 난이도 : 실버 Ⅲ
     *
     * 작성 목적 : 백준에 있는 문제 풀이
     */

    static int[] marbleCounts;                   // 각 색깔 조약돌 개수
    static double[] colorProbabilities;          // 각 색이 단독으로 선택될 확률

    static int totalMarbles = 0;                 // 전체 조약돌 수
    static int colorCount;                       // 색깔 종류 수
    static int drawCount;                        // 뽑을 조약돌 수

    static double totalProbability = 0.0;        // 결과 확률

    // 특정 색상의 조약돌만 뽑힐 확률 계산
    static double calculate(int index) {
        if (marbleCounts[index] >= drawCount) {
            colorProbabilities[index] = 1.0;

            for (int i = 0; i < drawCount; i++) {
                colorProbabilities[index] *=
                        (double)(marbleCounts[index] - i) / (totalMarbles - i);
            }
        }
        return colorProbabilities[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        colorCount = Integer.parseInt(br.readLine());
        marbleCounts = new int[colorCount];
        colorProbabilities = new double[colorCount];

        // 각 색의 조약돌 수 입력받고 전체 개수 계산
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < colorCount; i++) {
            marbleCounts[i] = Integer.parseInt(st.nextToken());
            totalMarbles += marbleCounts[i];
        }

        drawCount = Integer.parseInt(br.readLine());

        // 확률 계산
        for (int i = 0; i < colorCount; i++) {
            totalProbability += calculate(i);
        }

        System.out.println(totalProbability);
    }
}
