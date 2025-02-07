import java.io.*;
import java.util.StringTokenizer;

public class AverageNumbers {
    /* 
    * 최초 작성일시 : 2025-02-07
    * 최초 작성시간 : 20:18
    * 최초 작성자 : 정성환
    * 
    * 마지막 작성일시 : 
    * 마지막 작성시간 : 
    * 마지막 작성자 : 
    *
    * 문제 출처 : 백준
    * 문제 번호 : 1546
    * 문제 이름 : 평균
    * 문제 난이도 : 브론즈 Ⅰ
    *
    * 작성 목적
    * 
    * 책에 있는 문제 복습
    */
    
    /*
     * max(int [] data) 함수
     * 최댓값을 구하는 함수, 
     * n개의 시험 점수 중에서 최댓값을 구해야 하기 때문에 구현 
     */
    public static int max(int [] data){
        int max = 0;
        for(int i = 0; i < data.length; i++){
            if(max < data[i]){
                max = data[i];
            }
        }
        return max;
    }
    /*
     * 시험 점수들을 조작하고 평균을 구하는 함수
     * 리턴 값이 double인 이유는 평균을 구해야 하기 때문에 실수값을 반환해야 하기 때문
     * 
     * 시험 점수를 조작하고 평균을 구하는 방법(m은 가장 높은 시험 점수)은
     * 
     * ((A * 100) / m +(B * 100) / m + (C * 100) / m + ...) / 시험을 본 과목의 개수
     * 
     * 이고 이를 정리하면
     * 
     * (A + B + C + ...) * 100 / m / 시험을 본 과목의 개수 
     * 
     * 이다.
     */
    public static double changeAverageScore(int [] data){
        int sum = 0;
        int max = max(data);
        for(int i = 0; i < data.length; i++){
            sum += data[i];
        }
        return (double) ((sum * 100) / max) / data.length;
    }

    /*
     * 다른 방법
     * 합을 구하면서 최댓값을 구하는 법이다.
     * 위의 두 함수와 시간 복잡도가 동일하지만
     * 자바 표준 라이브러리를 이용해서 코드를 간결하게 쓸 수 있다.
     */
    public static double changeAverageScore2(int [] data){
        int sum = 0;
        int max = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i];
            max = Math.max(max, data[i]);
        }
        return (double) ((sum * 100) / max) / data.length;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int [] datas = new int[size];
        for(int i = 0; i < size; i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println("changeAverageScore 함수를 쓴 경우 : " + changeAverageScore(datas));
        System.out.println("changeAverageScore2 함수를 쓴 경우 : " + changeAverageScore2(datas));
    }

}
