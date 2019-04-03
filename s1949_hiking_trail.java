import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class s1949_hiking_trail {
	
	static int map[][],N, K , max;
	static boolean vis[][];
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = 0;
			map = new int[N][N];
			vis = new boolean[N][N];
			LinkedList<point> L = new LinkedList<point>();
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k= 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					if (L.isEmpty()) L.add(new point(j,k,map[j][k]));
					if (!L.isEmpty() && L.peek().val < map[j][k]) {
						L.clear();
						L.add(new point(j,k, map[j][k]));
					} else if (L.peek().val == map[j][k]) {
						L.add(new point(j,k,map[j][k]));
					}
				}
			} //ÀÔ·Â ³¡
			
			while(!L.isEmpty()) {
				point p = L.poll();
				vis[p.x][p.y]= true; 
				search(p.x,p.y,1, false);
				vis[p.x][p.y]= false; 
			}
			
			sb.append("#"+i + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void search(int x, int y, int count, boolean cut) {
		if (count > max) {
			max = count;
		}
		
		
		int dx[] = {1,0,-1,0};
		int dy[] = {0,1,0,-1};
		
		for (int i = 0; i < 4; i++) {
			int mx = x+dx[i];
			int my = y+dy[i];
			if (mx >= 0 && my>=0 && mx < N && my < N && vis[mx][my] == false) {
				vis[mx][my] = true;
				
				for (int k = 1; k <= K; k++) {
					if (map[mx][my] - k < map[x][y] && cut == false) {
						map[mx][my] -= k;
						cut = true;
						search(mx,my, count+1, cut);
						map[mx][my] += k;
						cut = false;
					}	
				}
				
				if (map[mx][my] < map[x][y]) {
					search(mx,my,count+1, cut);
				}
				vis[mx][my] = false;
				
			}
		}
		
	}
	
	static class point{
		int x,y,val;
			public point(int x, int y, int val) {
				this.x = x;
				this.y = y;
				this.val = val;
			}
	}
}
