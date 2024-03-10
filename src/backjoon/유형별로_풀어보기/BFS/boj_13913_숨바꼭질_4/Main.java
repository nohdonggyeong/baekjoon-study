package backjoon.유형별로_풀어보기.BFS.boj_13913_숨바꼭질_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    private static int N; // 수빈 처음 위치
    private static int K; // 동생 처음 위치
    private static int[] visitCheck = new int[100001]; // 수빈이 방문한 위치로 시간 저장
    private static int[] beforeVisitCheck = new int[100001]; // 수빈이 방문한 위치의 이전 위치 저장

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>(); // 작업 명령이 순서대로 담길 큐
        int index; // 큐에서 뽑아낸 이번 차례 작업 위치
        visitCheck[N] = 0; // 수빈 처음 위치는 0초로 저장

        queue.add(N); // 첫 작업 명령 큐로 담기
        while (!queue.isEmpty()) { // 작업 명령이 더 없을 때까지 반복
            index = queue.remove();
            if (index == K) {
                return visitCheck[index];
            }

            // 처음 와본 곳이면 해당 위치 인덱스로 이전 위치 인덱스, 방문시간을 저장
            // 작업 명령 큐로 위치 인덱스 추가
            if (index - 1 >= 0 && visitCheck[index - 1] == 0) {
                beforeVisitCheck[index - 1] = index;
                visitCheck[index - 1] = visitCheck[index] + 1;
                queue.add(index - 1);
            }
            if (index + 1 <= 100000 && visitCheck[index + 1] == 0) {
                beforeVisitCheck[index + 1] = index;
                visitCheck[index + 1] = visitCheck[index] + 1;
                queue.add(index + 1);
            }
            if (index * 2 <= 100000 && visitCheck[index * 2] == 0) {
                beforeVisitCheck[index * 2] = index;
                visitCheck[index * 2] = visitCheck[index] + 1;
                queue.add(index * 2);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputArr = br.readLine().split(" ");
        N = Integer.parseInt(inputArr[0]);
        K = Integer.parseInt(inputArr[1]);

        int ouputInt = bfs();
        bw.write(String.valueOf(ouputInt));
        bw.newLine();

        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int i = K;
        while (i != N) {
            stack.push(beforeVisitCheck[i]);
            i = beforeVisitCheck[i];
        }
        StringBuilder sb  = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(String.valueOf(stack.pop()));
            sb.append(" ");
        }
        bw.write(sb.toString().trim());
        bw.flush();

        bw.close();
        br.close();
    }
}
