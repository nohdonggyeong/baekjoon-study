package backjoon.알고리즘_분류.시뮬레이션.boj_21944_문제_추천_시스템_Version_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Problem implements Comparable<Problem> {
		int P;
		int L;
		String G;
		
		Problem(int P, int L, String G) {
			this.P = P;
			this.L = L;
			this.G = G;
		}
		
		@Override
		public int compareTo(Problem o) {
			if (this.L == o.L) {
				return Integer.compare(this.P, o.P);
			}
			return Integer.compare(this.L, o.L);
		}
	}
	
	static TreeSet<Problem> problemSet = new TreeSet<>();
	static HashMap<String, TreeSet<Problem>> GMap = new HashMap<>();
	static HashMap<Integer, Problem> PMap = new HashMap<>();
	
	static int recommend(String G, int x) {
		if (x == 1) {
			return GMap.get(G).last().P;
		} else if (x == -1) {
			return GMap.get(G).first().P;
		}
		
		return -1;
	}
	
	static int recommend2(int x) {
		if (x == 1) {
			return problemSet.last().P;
		} else if (x == -1) {
			return problemSet.first().P;
		}
		
		return -1;
	}
	
	static int recommend3(int x, int L) {
		Problem problem = null;
		if (x == 1) {
			problem = problemSet.higher(new Problem(0, L, null));
		} else if (x == -1) {
			problem = problemSet.lower(new Problem(0, L, null));
		}
		
		return problem == null ? -1 : problem.P;
	}
	
	static void add(int P, int L, String G) {
		Problem newProblem = new Problem(P, L, G);
		
		problemSet.add(newProblem);
		
		if (GMap.get(G) == null) {
			GMap.put(G, new TreeSet<Problem>());
		}
		GMap.get(G).add(newProblem);

		PMap.put(P, newProblem);
	}
	
	static void solved(int P) {
		Problem solved = PMap.remove(P);
		GMap.get(solved.G).remove(solved);
		problemSet.remove(solved);
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				switch (st.nextToken()) {
				case "recommend":
					sb.append(recommend(st.nextToken(), Integer.parseInt(st.nextToken()))).append("\n");
					break;
				case "recommend2":
					sb.append(recommend2(Integer.parseInt(st.nextToken()))).append("\n");
					break;
				case "recommend3":
					sb.append(recommend3(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
					break;
				case "add":
					add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());
					break;
				case "solved":
					solved(Integer.parseInt(st.nextToken()));
					break;
				default:
					break;
				}
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}