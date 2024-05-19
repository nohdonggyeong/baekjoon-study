package backjoon.알고리즘_분류.그리디.회의실_배정;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end) {
				return Integer.compare(this.start,  o.start);
			}
			return Integer.compare(this.end, o.end);
		}
	}
	
	static int N;
	static Meeting[] meetings;
	static int count;
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			N = Integer.parseInt(br.readLine());
			
			meetings = new Meeting[N];
			StringTokenizer st;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				meetings[n] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(meetings);
			
			int end = count = 0;
			for (int n = 0; n < N; n++) {
				if (end <= meetings[n].start) {
					end = meetings[n].end;
					count += 1;
				}
			}
			
			bw.write(String.valueOf(count));
			bw.flush();
		}
	}
}
