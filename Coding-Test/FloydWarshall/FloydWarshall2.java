import java.io.*;
import java.util.*;

public class FloydWarshall2 {
    /*
     * 최초 작성일시 : 2025-04-09
     * 최초 작성시간 : 15:02
     * 최초 작성자 : 정성환
     *
     * 문제 출처 : 백준
     * 문제 번호 : 11403
     * 문제 이름 : 경로 찾기
     * 문제 난이도 : 실버 Ⅰ
     *
     * 작성 목적
     *
     * 백준에 있는 문제 풀이
     *
     */
    // 그래프를 나타내는 2차원 배열
    static int [][] graph;

    // 플로이드-워셜 알고리즘을 이용한 경로 존재 여부 계산
    static void floydWarshall(int [][] graph, int node){
        for(int k = 1; k <= node; k++){ // 경유 노드
            for(int i = 1; i <= node; i++){ // 출발 노드
                for(int j = 1; j <= node; j++){ // 도착 노드
                    // i -> k, k -> j 경로가 모두 존재하면 i -> j도 경로 존재
                    if(graph[i][k] == 1 && graph[k][j] == 1){
                        graph[i][j] = 1;
                    }
                }
            }
        }
    }

    // 결과 출력 함수
    static void print(int [][] graph, int node) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= node; i++){
            for(int j = 1; j <= node; j++){
                // 경로 존재 여부 출력 (1: 존재, 0: 없음)
                bw.write(graph[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush(); // 출력 버퍼 비우기
        bw.close(); // 출력 스트림 닫기
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int node = Integer.parseInt(br.readLine()); // 노드 수 입력

        // 그래프 초기화 (1번 인덱스부터 사용)
        graph = new int[node+1][node+1];

        // 인접 행렬 입력 받기 (0: 경로 없음, 1: 경로 있음)
        for(int i = 1; i <= node; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= node; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close(); // 입력 스트림 닫기

        // 경로 존재 여부 계산
        floydWarshall(graph, node);

        // 결과 출력
        print(graph, node);
    }
}