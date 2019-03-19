import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s7088_calf_counting {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		int calf[] = new int[100001];
		int one[] = new int[100001];
		int two[] = new int[100001];
		int three[] = new int[100001];
		
		for (int i = 1; i <= T; i++) {
			sb.append("#"+i+"\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			
			
			for (int j = 1; j <=N; j++) {
				calf[j] = Integer.parseInt(br.readLine());
			}
			
			int o = 0;
			int tw = 0;
			int t = 0;
			
			for (int j = 1; j  <= N; j++) {
				switch(calf[j]) {
					case 1: o++; break;
					case 2: tw++; break;
					case 3: t++; break;
				}
				one[j] = o;
				two[j] = tw;
				three[j] = t;
			}
			
			for (int j = 0; j < Q; j++) {
				st = new StringTokenizer (br.readLine());
				int L = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				
				int a1 = one[R] - one[L-1];
				int a2 = two[R] - two[L-1];
				int a3 = three[R] - three[L-1];
				
				sb.append(a1 + " " + a2+ " " + a3 + "\n");
			}
		}
		System.out.println(sb);
	}
}
