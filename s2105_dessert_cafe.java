import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class s2105_dessert_cafe {

	static HashSet<Integer> hs = new HashSet<Integer>();
	static int map[][], inx, iny, N, max;
	static int dx[] = {0,1,1,-1,-1};
	static int dy[] = {0,1,-1,-1,1};
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			max = -1;
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0 ; k < N ;k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for (int j = 0; j < N; j++) {
				for (int k = 1; k < N; k++) {
					inx = j; iny = k;
					hs.add(map[j][k]);
					search(j,k,0);
					hs.clear();
				}
			}
			sb.append("#" +i + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void search(int x, int y, int d) {
		if (d>4) return;
		if (d == 0) d++;
		
		
		int mx = x + dx[d];
		int my = y+ dy[d];
		
		if (mx >=0 && mx < N && my>=0 && my< N) {
			if (mx == inx && my == iny) {
				if (hs.size() < 4) {
					return;
				}
				max = Math.max(hs.size(), max);
				return;
			}
			
			if (!hs.contains(map[mx][my])) {
				hs.add(map[mx][my]);
				search(mx,my, d);
				hs.remove(map[mx][my]);
			}
		}
		search(x,y,d+1);
	}
}
