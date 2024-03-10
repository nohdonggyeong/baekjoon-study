package backjoon.유형별로_풀어보기.그리디.회의실_배정;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
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

public class Main {
	static int N;
	static Meeting[] meetings;
	static int count;
	
	public static void main(String[] args) {
		try (BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			meetings = new Meeting[N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				meetings[n] = new Meeting(start, end);
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
