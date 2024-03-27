package sw_expert_academy._sea_1225_암호생성기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer> list;
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			for (int t = 1; t <= 10; t++) {
				br.readLine();
				
				list = new ArrayList<Integer>();
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < 8; i++) {
					list.add(Integer.parseInt(st.nextToken()));
				}
				
				int removed;
				loop:
				while (true) {
					for (int i = 1; i <= 5; i++) {
						removed = list.remove(0) - i;
						if (removed <= 0) {
							list.add(0);
							break loop;
						} else {
							list.add(removed);
						}
					}
				}
				
				sb.append("#").append(t).append(" ");
				for (int i = 0; i < 8; i++) {
					sb.append(list.get(i)).append(" ");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		}
	}
}
