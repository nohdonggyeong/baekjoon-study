package sw검정.advanced_기출문제.ad_230712_그릇_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main2 {
     
    static int T,N,M;;
    static String[] material;
    static List<String> result;
     
    public static void main(String[] args) throws Exception {
        System.out.println(LocalDateTime.now());
        System.setIn(new FileInputStream("input7.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        T = Integer.parseInt(br.readLine());
         
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            material = new String[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                /*시험 당시에는 대문자 알파뱃 배열을 미리 선언하여 사용하였습니다.
                static String[] alphabet = {"A","B","C",....,"Z"};
                material[i] = alphabet[Integer.parseInt(st.nextToken())-1];*/
                material[i] = String.valueOf((char)(64 + Integer.parseInt(st.nextToken()))); //재료를 알파벳으로 치환 ASCII 로 65~91 이 A~Z이다.
            }
 
            result = new ArrayList<>();
             
            dfs(0,0, new ArrayList<>());
             
            bw.write("#" + test_case + " " + result.size() + "\n");
        }
         
        bw.flush();
        br.close();
        System.out.println(LocalDateTime.now());
    }
 
    public static void dfs(int start, int count, List<String> mix) {
        if(count == M) { //모든 재료수를 조합했을 경우
            String mixed = "";
            for (int i = 0; i < M; i++) {
                mixed += mix.get(i); //그릇 생성...
            }
             
            if(!result.contains(mixed)) {
                result.add(mixed);
            }
        } else {
            for (int i = start; i < N; i++) {
                List<String> temp = new ArrayList<>();
                temp.addAll(mix);
                temp.add(material[i]);
                Collections.sort(temp); //같은 조합인지 확인하기위해 정렬을 통하여 형식을 통일한다.
                dfs(i+1, count+1, temp);
            }
        }
    }
}