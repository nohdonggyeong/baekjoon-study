package 알고리즘.백트래킹.boj_2580_스도쿠;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] sudoku = new int[9][9];
	
	static void dfs(int row, int col) throws IOException {
		if (col == 9) {
			dfs(row + 1, 0);
			return;
		}
		
		if (row == 9) {
			StringBuilder sb = new StringBuilder();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(String.valueOf(sudoku[i][j]));
					sb.append(" ");
				}
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			System.exit(0);
		}
		
		if (sudoku[row][col] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (isPossible(row, col, i)) {
					sudoku[row][col] = i;
					dfs(row, col + 1);
				}
			}
			sudoku[row][col] = 0;
			return;
		}
		dfs(row, col + 1);
	}
	
	static boolean isPossible(int row, int col, int val) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] == val) {
				return false;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][col] == val) {
				return false;
			}
		}
		
		int set_row = (row / 3) * 3;
		int set_col = (col / 3) * 3;
		for (int i = set_row; i < set_row + 3; i++) {
			for (int j = set_col; j < set_col + 3; j++) {
				if (sudoku[i][j] == val) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Process
		dfs(0, 0);
		
		br.close();
	}
}
