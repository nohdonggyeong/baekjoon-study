package backjoon.자료구조.boj_20920_영단어_암기는_괴로워;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Word implements Comparable<Word> {
		int count;
		int length;
		String spelling;
		
		Word(int count, int length, String spelling) {
			this.count = count;
			this.length = length;
			this.spelling = spelling;
		}
		
		@Override
		public int compareTo(Word o) {
			if (this.count == o.count) {
				if (this.length == o.length) {
					return this.spelling.compareTo(o.spelling);
				}
				return Integer.compare(o.length, this.length);
			}
			return Integer.compare(o.count, this.count);
		}
	}
	
	static int N, M;
	static Map<String, Integer> wordMap;
	static List<Word> wordList;
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			wordMap = new HashMap<>();
			String str;
			for (int i = 0; i < N; i++) {
				str = br.readLine();
				if (str.length() >= M) {
					if (wordMap.containsKey(str)) {
						wordMap.put(str, wordMap.get(str) + 1);
					} else {
						wordMap.put(str, 1);
					}
				}
			}

			wordList = new ArrayList<>();
			for (String word : wordMap.keySet()) {
				wordList.add(new Word(wordMap.get(word), word.length(), word));
			}
			
			Collections.sort(wordList);
			
			StringBuilder sb = new StringBuilder();
			for (Word word : wordList) {
				sb.append(word.spelling).append("\n");
			}
			bw.write(sb.toString().trim());
			bw.flush();
		}
	}

}
