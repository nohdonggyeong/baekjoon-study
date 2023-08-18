package 알고리즘.브루트포스.boj_3085_사탕_게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    // value setting
    private static int size;
    private static char[][] arr;

    // value swap
    public static void swap(int x1, int y1, int x2, int y2) {
        char temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    public static int check() {
        int cnt, max = 1;

        // row check
        for (int j = 0; j < size; j++) {
            cnt = 1;
            for (int i = 1; i < size; i++) {
                if (arr[i][j] == arr[i - 1][j]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
            }
            if (max < cnt) {
                max = cnt;
            }
        }

        // column check
        for (int i = 0; i < size; i++) {
            cnt = 1;
            for (int j = 1; j < size; j++) {
                if (arr[i][j] == arr[i][j - 1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
            }
            if (max < cnt) {
                max = cnt;
            }
        }
        return max;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        
        // arr input
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (String el : br.readLine().split("")) {
                arr[i][j++] = el.charAt(0);
            }
        }

        int resultMax = 0;

        // row swap and check
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n - 1; i++) {
                swap(i, j, i + 1, j);
                resultMax = Math.max(resultMax, check());
                swap(i, j, i + 1, j);
            }
        }

        // column swap and check
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                swap(i, j, i, j + 1);
                resultMax = Math.max(resultMax, check());
                swap(i, j, i, j + 1);
            }
        }

        bw.write(String.valueOf(resultMax > 1 ? resultMax : 0));
        bw.flush();
        bw.close();
        br.close();
    }
}
