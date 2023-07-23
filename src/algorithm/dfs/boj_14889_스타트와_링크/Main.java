package algorithm.dfs.boj_14889_스타트와_링크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] spec;
	
	static int combN, combR;
	static int[] combInput;
	static boolean[] combVisited;
	static int[] combTemp;
	static List<int[]> combOutput;
	
	static int permN, permR;
	static int[] startTeam, linkTeam;
	static boolean[] permVisited;
	static int[] permTemp;
	static List<int[]> permOutput;
	
	static int minResult;
	
	static void combination(int start, int depth) {
		if (depth == combR) {
			int index = 0;
			combTemp = new int[combR];
			for (int i = 0; i < combN; i++) {
				if (combVisited[i]) {
					combTemp[index++] = combInput[i];
				}
			}
			combOutput.add(combTemp.clone());
			return;
		}
		
		 for (int i = start; i < combN; i++) {
			 if (!combVisited[i]) {
				 combVisited[i] = true;
				 combination(i + 1, depth + 1);
				 combVisited[i] = false;
			 }
		 }
	}
	
	static void permutation(int depth, int[] team) {
		if (depth == permR) {
			permOutput.add(permTemp.clone());
			return;
		}
		
		for (int i = 0; i < permN; i++) {
			if (!permVisited[i]) {
				permVisited[i] = true;
				permTemp[depth] = team[i];
				permutation(depth + 1, team);
				permVisited[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		spec = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				spec[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		combN = N;
		combR = N / 2;
		combInput = new int[combN];
		combVisited = new boolean[combN];
		
		for (int i = 0; i < N; i++) {
			combInput[i] = i;
		}
		
		combOutput = new ArrayList<>();
		combination(0, 0);
		
		minResult = Integer.MAX_VALUE;
		for (int[] el : combOutput) {
			startTeam = el.clone();
			linkTeam = new int[N / 2];
			
			int startTeamIndex = 0;
			int linkTeamIndex = 0;
			for (int i = 0; i < N; i++) {
				if (startTeam[startTeamIndex] != combInput[i]) {
					linkTeam[linkTeamIndex++] = combInput[i];
				} else {
					if (startTeamIndex < N / 2 - 1) {
						startTeamIndex++;
					}
				}
			}
			
			permN = N / 2;
			permR = 2;
			
			permVisited = new boolean[permN];
			permTemp = new int[permR];
			permOutput = new ArrayList<>();
			permutation(0, startTeam);
			
			int startTeamSum = 0;
			for (int[] e : permOutput) {
				startTeamSum += spec[e[0]][e[1]];
			}
			
			permVisited = new boolean[permN];
			permTemp = new int[permR];
			permOutput = new ArrayList<>();
			permutation(0, linkTeam);
			
			int linkTeamSum = 0;
			for (int[] e : permOutput) {
				linkTeamSum += spec[e[0]][e[1]];
			}
			
			minResult = Math.min(minResult, Math.abs(startTeamSum - linkTeamSum));
		}
		
		bw.write(String.valueOf(minResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
