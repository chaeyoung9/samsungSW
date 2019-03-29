import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class s1952_under_arrest {

	static boolean vis[][];
	static int map[][];
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= T ; i++) {
			
			//입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			vis = new boolean[N][M];
			
			for (int j = 0 ;j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0 ; k < M; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int count = 1;
			Queue<point2> q = new LinkedList<point2>();
			Queue<point> n = new LinkedList<point>();
			vis[R][C] = true;
			q.add(new point2(R,C,1));
			wloop1: while(!q.isEmpty()) {
				point2 p = q.poll();
				if (p.time == L) continue wloop1;
				
				switch (map[p.x][p.y]) {
				case 1: {
					n.add(new point (p.x-1, p.y));
					n.add(new point (p.x+1, p.y));
					n.add(new point (p.x, p.y+1));
					n.add(new point (p.x, p.y-1));
					break;
				}
				case 2: {
					n.add(new point(p.x-1, p.y));
					n.add(new point(p.x+1, p.y));
					break;
				}
				case 3: {
					n.add(new point(p.x, p.y-1));
					n.add(new point(p.x, p.y+1));
					break;
				}
				case 4: {
					n.add(new point(p.x-1,p.y));
					n.add(new point(p.x, p.y+1));
					break;
				}
				case 5: {
					n.add(new point(p.x+1, p.y));
					n.add(new point(p.x, p.y+1));
					break;
				}
				case 6: {
					n.add(new point(p.x, p.y-1));
					n.add(new point(p.x+1, p.y));
					break;
				}
				case 7: {
					n.add(new point(p.x-1, p.y));
					n.add(new point(p.x, p.y-1));
					break;
				}
				}
				
				//n에 들어간것 처리
				wloop2: while(!n.isEmpty()) {
					point p2 = n.poll();
					int X = p2.x;
					int Y = p2.y;
					
					if (X >= N || Y>= M || X < 0 || Y < 0 || vis[X][Y] || vis[X][Y] == true || map[X][Y] == 0) continue wloop2;

					if (pass(p.x,p.y, X,Y, map[X][Y]) && p.time+1 <= L) {
						vis[X][Y] = true;
						count++;
						q.add(new point2(X,Y, p.time+1));
					}
					
				}
			}
			sb.append("#"+i + " " +count + "\n");
		}//Tloop
		System.out.println(sb.toString());
	}
	
		
	
	static boolean pass(int x, int y, int mx, int my, int d) {
		switch (d) {
			case 1: return true;
			case 2: if (y == my) return true; break;		
			case 3: if (x == mx) return true; break;
			case 4: if ((x == mx && y > my) || (y ==my && x < mx)) return true; break;
			case 5: if ((y == my && x > mx) || (x == mx && y > my)) return true; break;
			case 6: if ((x == mx && y < my) || (y == my && x > mx)) return true; break;
			case 7: if ((y == my && x < mx) || (x == mx && y < my)) return true; break;
		}
		return false;
	}

	static class point{
		int x, y;
			public point(int x, int y) {
				this.x =x;
				this.y = y;
			}
	}
	static class point2{
		int x, y, time;
			public point2(int x, int y, int time) {
				this.x =x;
				this.y = y;
				this.time = time;
			}
	}
}
