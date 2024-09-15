package backjoon.자료구조.트리셋.boj_5670_휴대폰_자판;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static class TrieNode{
		Map<Character, TrieNode> children;
		Boolean isEndOfWord;
		
		public TrieNode(){
			children = new HashMap<>();
			isEndOfWord = false;
		}
	}
	
	static class Trie {
		TrieNode root;
		
		Trie() {
			root = new TrieNode();
		}
		
		public void insert(String word) {
			TrieNode current = root;
			
			for (char c : word.toCharArray()) {
				current.children.putIfAbsent(c, new TrieNode());
				current = current.children.get(c);
			}
			
			current.isEndOfWord = true;
		}
		
		public int getCount(String word) { 
			TrieNode current = root;
			
			int count = 0;
			for(int i = 0; i < word.length(); i++) {
				if(i == 0 || current.children.size() > 1 || current.isEndOfWord) {
					++count;
				}
				current = current.children.get(word.charAt(i));
			}
			
			return count;
		}
	}
	
	public static void main(String[] args) throws IOException{
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			StringBuilder sb = new StringBuilder();

			String input;
			while((input = br.readLine()) != null && !input.isEmpty()) {
				List<String> words = new ArrayList<>();
				Trie trie = new Trie();
				int n = Integer.parseInt(input);
				for(int i = 0; i < n; i++) {
					input = br.readLine();
					words.add(input);
					trie.insert(input);
				}
				
				double sum = 0;
				for (String word : words) {
					sum += trie.getCount(word);	
				}
				
				sb.append(String.format("%.2f", sum / words.size())).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}