import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s4014_runway {
	
	static int map[][], N, X;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int i = 1 ; i <=T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			int map2[][] = new int[N+1][N+1];
			for (int j =0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0 ; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					map2[k][j] = map[j][k];
				}
			}
			
			int ans = search(map) + search(map2);
			sb.append("#"+i+" " + ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int search(int[][] map) {
		int count = 0;
		for (int i =0; i < N; i++) {
			int val = 1;

			int j;
			for (j = 0; j < N-1; j++) {
				if (map[j][i] == map[j+1][i]) {
					val++;
				} else if (map[j][i] == map[j+1][i]-1 && val >= X) { //오르막
					val = 1;
				} else if (map[j][i] == map[j+1][i]+1 && val >= 0) { //내리막
					val = 1 - X;
				} else break;
			}
			if (j == N-1 && val >= 0) {
				count++;
			}
		}
		return count;
	}
}
