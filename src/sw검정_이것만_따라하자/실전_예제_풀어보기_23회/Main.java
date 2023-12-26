package sw검정_이것만_따라하자.실전_예제_풀어보기_23회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[] months = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int year = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			
			int diff = 0;
			for (int y = 1900; y < year; y++) {
				if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
					diff += 1;
				}
			}
			diff = diff + (year - 1900) * 365;
			
			for (int m = 0; m < month - 1; m++) {
				diff = diff + months[m];
			}
			if (month > 2 && (year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				diff += 1;
			}
			
			diff += day;
			
			sb.append("#").append(t).append(" ");
			int days = (diff % 7);
			switch (days) {
			case 0:
				sb.append("SUN").append("\n");
				break;
			case 1:
				sb.append("MON").append("\n");
				break;
			case 2:
				sb.append("TUE").append("\n");
				break;
			case 3:
				sb.append("WED").append("\n");
				break;
			case 4:
				sb.append("THU").append("\n");
				break;
			case 5:
				sb.append("FRI").append("\n");
				break;
			case 6:
				sb.append("SAT").append("\n");
				break;
			default:
				break;
			}
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
