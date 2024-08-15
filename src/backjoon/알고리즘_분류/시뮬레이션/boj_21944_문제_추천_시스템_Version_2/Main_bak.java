package backjoon.알고리즘_분류.시뮬레이션.boj_21944_문제_추천_시스템_Version_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_bak {
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Problem problem = (Problem) obj;
            return P == problem.P;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(P);
        }
    }

    static int N, M;
    static HashMap<Integer, Problem> PHm = new HashMap<>();
    static HashMap<Integer, TreeSet<Problem>> LHm = new HashMap<>();
    static HashMap<String, TreeSet<Problem>> GHm = new HashMap<>();
    static TreeSet<Problem> problemTs = new TreeSet<>();
    static TreeSet<Integer> LTs = new TreeSet<>();
    static StringBuilder sb = new StringBuilder();

    static void add(int P, int L, String G) {
        Problem problem = new Problem(P, L, G);
        PHm.put(P, problem);
        LHm.computeIfAbsent(L, k -> new TreeSet<>()).add(problem);
        GHm.computeIfAbsent(G, k -> new TreeSet<>()).add(problem);
        problemTs.add(problem);
        LTs.add(L);
    }

    static void solved(int P) {
        Problem problem = PHm.remove(P);
        if (problem != null) {
            TreeSet<Problem> levelSet = LHm.get(problem.L);
            levelSet.remove(problem);
            if (levelSet.isEmpty()) {
                LHm.remove(problem.L);
                LTs.remove(problem.L);
            }

            TreeSet<Problem> groupSet = GHm.get(problem.G);
            groupSet.remove(problem);
            if (groupSet.isEmpty()) {
                GHm.remove(problem.G);
            }

            problemTs.remove(problem);
        }
    }

    static void recommend(String G, int x) {
        TreeSet<Problem> groupSet = GHm.get(G);
        if (groupSet != null && !groupSet.isEmpty()) {
            if (x == 1) {
                sb.append(groupSet.last().P).append("\n");
            } else if (x == -1) {
                sb.append(groupSet.first().P).append("\n");
            }
        } else {
            sb.append(-1).append("\n");
        }
    }

    static void recommend2(int x) {
        if (!problemTs.isEmpty()) {
            if (x == 1) {
                sb.append(problemTs.last().P).append("\n");
            } else if (x == -1) {
                sb.append(problemTs.first().P).append("\n");
            }
        } else {
            sb.append(-1).append("\n");
        }
    }

    static void recommend3(int x, int L) {
        Integer level = (x == 1) ? LTs.ceiling(L) : LTs.floor(L);
        if (level != null) {
            TreeSet<Problem> treeSet = LHm.get(level);
            if (x == 1) {
                sb.append(treeSet.first().P).append("\n");
            } else if (x == -1) {
                sb.append(treeSet.last().P).append("\n");
            }
        } else {
            sb.append(-1).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                String G = st.nextToken();
                add(P, L, G);
            }

            M = Integer.parseInt(br.readLine());
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                switch (command) {
                    case "recommend":
                        recommend(st.nextToken(), Integer.parseInt(st.nextToken()));
                        break;
                    case "recommend2":
                        recommend2(Integer.parseInt(st.nextToken()));
                        break;
                    case "recommend3":
                        recommend3(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
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

            bw.write(sb.toString());
            bw.flush();
        }
    }
}