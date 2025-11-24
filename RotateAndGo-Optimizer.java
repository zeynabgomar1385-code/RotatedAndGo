import java.util.*;

public class LibrarySpell {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String T = sc.nextLine();
        String U = sc.nextLine();
        int K = sc.nextInt();
        
        int[] required = new int[256];
        int uniqueChars = 0;
        for (char c : T.toCharArray()) {
            if (required[c] == 0) uniqueChars++;
            required[c]++;
        }
        
        boolean[] forbidden = new boolean[256];
        for (char c : U.toCharArray()) {
            forbidden[c] = true;
        }
        
        String result = null;
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < S.length(); i++) {
            if (forbidden[S.charAt(i)]) continue;
            
            int[] current = new int[256];
            int formed = 0;
            List<Integer> requiredIndices = new ArrayList<>();
            
            for (int j = i; j < S.length(); j++) {
                char c = S.charAt(j);
                
                if (forbidden[c]) break;
                
                if (required[c] > 0) {
                    current[c]++;
                    if (current[c] == required[c]) formed++;
                    requiredIndices.add(j);
                }
                
                if (formed == uniqueChars) {
                    boolean validDistance = true;
                    for (int k = 1; k < requiredIndices.size(); k++) {
                        if (requiredIndices.get(k) - requiredIndices.get(k-1) > K) {
                            validDistance = false;
                            break;
                        }
                    }
                    
                    if (validDistance) {
                        String candidate = S.substring(i, j + 1);
                        
                        switch (Integer.compare(candidate.length(), minLen)) {
                            case -1: 
                                result = candidate;
                                minLen = candidate.length();
                                break;
                            case 0: 
                                switch (candidate.compareTo(result)) {
                                    case -1: 
                                        result = candidate;
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
        }
        
        switch (result != null ? 1 : 0) {
            case 1:
                int start = S.indexOf(result);
                System.out.println(start + " " + (start + result.length() - 1));
                System.out.println(result);
                break;
            case 0:
                System.out.println(-1);
                break;
        }
    }
}