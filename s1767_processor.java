package samsungSW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s1767_processor {
	
	static int N, map[][], maxCore, min, cores;
	static point p[];
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine().trim());
		int ans[] = new int[C];
		
		for (int k = 0; k < C; k++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			p = new point[12];
			maxCore = 0;
			min = 12*12;
			cores = 0;
			Arrays.fill(p, new point(0,0));
			
			for (int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int j = 0;
				while(st.hasMoreTokens()) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (!(i ==0 || j ==0 || i == N-1 || j == N-1)) {
							p[cores++] = new point(i,j);
						}
					}
					j++;
				}
			}
//			for (int i = 0 ; i < cores; i++) {
//				System.out.println(p[i].x + " " + p[i].y);
//			}
			
			DFS(0, 0);
			ans[k] = min;
		}
		
		for (int i = 0 ; i < C; i++) {
			System.out.format("#%d %d\n", i+1, ans[i]);
		}
		
	}
	
	static void DFS(int coreNum, int coreC) {
		int mx[] = {0,0,1,-1};
		int my[] = {1,-1,0,0};
		
		if (coreNum > cores+1) return;
		
		if (coreNum == cores) {
			if (coreC == maxCore) {
				min = Math.min(min, count());
				print();
				System.out.format("maxcore: %d core: %d count: %d\n", maxCore, coreC, count());
				return;
			} else if (coreC > maxCore) {
				maxCore = coreC;
				min = count();
				print();
				System.out.format("maxcore: %d core: %d count: %d\n", maxCore, coreC, count());
				return;
			}
		}
		
		int x = p[coreNum].x;
		int y = p[coreNum].y;
		
		int block = 0;
		loop1: for (int i = 0 ; i < 4 ; i++) {
			int nx = x + mx[i];
			int ny = y + my[i];
			
			
			loop2: while(true) {
				if  (nx < 0 || ny <0 || nx > N-1 || ny>N-1) break loop2;
				if (map[nx][ny] == 1 || map[nx][ny] == 2) {
					block++;
					if (block ==4) {
						DFS(coreNum+1, coreC);
						return;
					}
					if (i == 3 ) {
						DFS(coreNum+1, coreC);
						return;
					}
					continue loop1;
				}
				nx += mx[i];
				ny += my[i];
			}
			nx -= mx[i];
			ny -= my[i];
			
			while(nx!= x || ny!= y) {
				map[nx][ny] = 2;
				nx -= mx[i];
				ny -= my[i];
			}
			DFS(coreNum+1, coreC+1);
			
			nx = x;
			ny = y;
			while(nx !=0 && ny!=0 && nx != N-1 && ny != N-1) { //백트래킹
				nx += mx[i];
				ny += my[i];
				map[nx][ny] = 0;
			}
		}
		
	}
	
	
	static void print() {
		System.out.println("__________________________");
		for (int i = 0 ; i < N ; i ++) {
			for (int j =0 ; j < N ; j++) {
				System.out.format("%d ", map[i][j]);
			}
			System.out.println("");
		}
	}
	
	static int count() {
		int count = 0;
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < N ; j++) {
				if (map[i][j] == 2) {
					count++;
				}
			}
		}
		return count;
	}
	
	static class point {
		int x;
		int y;
			public point(int x, int y) {
				this.x = x;
				this.y = y;
			}
	}
}
