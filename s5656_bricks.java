import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class s5656_bricks {
	
	static int map[][],N,W,H, min;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ;i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map= new int[H][W];
			min = Integer.MAX_VALUE;
			
			for (int j = 0; j < H; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0 ; k < W; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			drop(0,0);
//			destroy(4,2,map[4][2]);
//			tidy();
//			destroy(5,2,map[5][2]);
//			tidy();
			
//			print();
//			System.out.println(min);
			sb.append("#"+i + " " + min + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void tidy() {
		int val = 0;
		int streak = 0;
		
		for (int i= 0; i < W; i++) {
			streak = 0;
			for (int j = H-1; j >=0; j--) {
				if (map[j][i] == 0) {
					streak++;
				} else if (streak > 0 && map[j][i] != 0) {
					map[j+streak][i] = map[j][i];
					map[j][i] = 0;
					j += streak+1;
					streak = 0;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < H;i++) {
			for(int j =0; j<W; j++) {
				if (map[i][j] != 0) count++;
			}
		}
		min = Math.min(min, count);
	}
	
	static void destroy(int x, int y, int len) {
		LinkedList<point> q = new LinkedList<point>();
		map[x][y] = 0;
		
		if (len > 1) {
		for (int i =x; i < x+len; i++) {
			if (i >= H) continue;
			if (map[i][y] > 1) {
				q.add(new point(i,y, map[i][y]));
			}
			map[i][y] = 0;
		}
		
		for (int i = x-len+1; i < x; i++) {
			if (i < 0) continue;
			if (map[i][y] > 1) {
				q.add(new point(i,y, map[i][y]));
			}
			map[i][y] = 0;
		}
		
		for (int i =y; i < y+len; i++) {
			if (i >= W) continue;
			if (map[x][i] > 1) {
				q.add(new point(x,i, map[x][i]));
			}
			map[x][i] = 0;
		}
		for (int i = y-len+1; i < y; i++) {
			if (i < 0) continue;
			if (map[x][i] > 1) {
				q.add(new point(x,i, map[x][i]));
			}
			map[x][i] = 0;
		}
		int plen = q.size();
			for (int i = 0; i < plen; i++) {
				point p = q.poll();
				destroy(p.x,p.y, p.val);
			}
		}
	}
	
	static void print() {
		System.out.println("_________________");
		for (int i = 0; i < H;i++) {
			for(int j =0; j<W; j++) {
				System.out.format("%d ", map[i][j]);
			}
			System.out.println("");
		}
	}
	
	static void drop(int n, int count) {
		if (count >= N || n >= W || min == 0) return;
//		System.out.format("%d %d\n", N, count);
		int temp[][] = new int[H][W];
		
		
		drop(n+1, count);
		
		int next = 0;
		for (int i =0; i < H; i++) {
			if (map[i][n] != 0) {
				next = i;
				break;
			}
		}
		
		copymap(temp,map);
		destroy(next, n, map[next][n]);
		tidy();
//		System.out.format("Destroyed [%d,%d] \n", next,n);
//		print();
		drop(0, count+1);
		
		copymap(map,temp); // restore
		
	}
	
	
	static void copymap(int map[][], int map2[][]) {
		for (int i =0; i <H; i++) {
			for (int j = 0 ; j < W; j++) {
				map[i][j] = map2[i][j];
			}
		}
	}
	
	static class point{
		int x, y, val;
			public point(int x, int y, int val) {
				this.x =x;
				this.y =y;
				this.val = val;
			}
	}
	
}