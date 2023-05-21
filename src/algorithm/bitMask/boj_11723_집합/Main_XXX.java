package algorithm.bitMask.boj_11723_집합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_XXX {
	private static class MySet {
		private Set<Integer> set;
		
		public MySet() {
			this.set = new HashSet<>();
		}
		
		public void add(int x) {
			this.set.add(x);
		}
		
		public void remove(int x) {
			this.set.remove(x);
		}
		
		public int check(int x) {
			return this.set.contains(x) ? 1 : 0;
		}
		
		public void toggle(int x) {
			if (this.set.contains(x)) {
				this.set.remove(x);
			} else {
				this.set.add(x);
			}
		}
		
		public void all() {
			this.set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
		}
		
		public void empty() {
			this.set = new HashSet<>();
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int M = Integer.parseInt(br.readLine());
		
		MySet mySet = new MySet();
		for (int i = 0; i < M; i++) {
			String command = br.readLine();
			if (command.contains("add")) {
				mySet.add(Integer.parseInt(command.split(" ")[1]));
			} else if (command.contains("remove")) {
				mySet.remove(Integer.parseInt(command.split(" ")[1]));
			} else if (command.contains("check")) {
				bw.write(String.valueOf(mySet.check(Integer.parseInt(command.split(" ")[1]))));
				bw.newLine();
			} else if (command.contains("toggle")) {
				mySet.toggle(Integer.parseInt(command.split(" ")[1]));
			} else if (command.contains("all")) {
				mySet.all();
			} else if (command.contains("empty")) {
				mySet.empty();
			}
		}
		
		// Output
		bw.flush();
		bw.close();
		br.close();
	}
}
