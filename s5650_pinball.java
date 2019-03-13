import java.util.Scanner;

public class pinball {
	
	static int N, map[][], sx, sy;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int ans[] = new int[T];
		for (int i = 0 ; i < T ; i++) {
			N = sc.nextInt();
			map = new int[N][N];
			int max = 0;
			for (int j = 0 ; j < N ; j++) {
				for (int k = 0 ; k < N ; k++) {
					map[j][k] = sc.nextInt();
				}
			}
			
			for (int j = 0 ; j < N ; j++) {
				for (int k = 0 ; k < N ; k++) {
					if (map[j][k] == 0) {
						for (int p = 0; p < 4; p++) {
							max = Math.max(max, play(j,k,p));
						}
					}
				}
			}
			ans[i] = max;
		}
		
		for (int i =0 ; i < T;i++) {
			System.out.format("#%d %d\n", i+1, ans[i]);
		}
		
	}
	
	static int play(int x, int y, int d) {
//		System.out.format("start %d %d\n", x,y);
		int score = 0;
		
		int dx[] = {0,-1,0,1}; //1-аб 2-╩С 3-го 4-©Л
		int dy[] = {-1,0,1,0};
		
			int i = d;
			int mx = x;
			int my = y;
//			System.out.println("x y d");
			int l = i;
			wloop: while (true) {
				mx += dx[l];
				my += dy[l];
//				System.out.format("%d %d -> %d\n", mx,my,l);
				if (mx >= 0 && my>= 0 && mx < N && my < N) {
					if (x == mx && y == my) return score;
					if (map[mx][my] == -1) return score;
					if (map[mx][my] == 0) continue wloop;
					if (map[mx][my] >= 1 && map[mx][my] <=5) {
						l = crash(mx-dx[l],my-dy[l],mx,my,l);
						score++;
					} else if (map[mx][my] >= 6 && map[mx][my] <=10) {
//						System.out.println("INTO THE HOLE");
						floop: for (int k = 0 ; k < N; k++) {
							for (int m = 0 ; m < N ; m++) {
								if (map[mx][my] == map[k][m] && (mx !=k || my != m)) {
									mx = k;
									my = m;
									break floop;
								}
							}
						}
					}
				} else {
					l = reverse(l);
					score++;
				}
			}
	}
	
	static int reverse(int d) {
		switch (d) {
			case 0:	d = 2; break;
			case 1:	d = 3; break;
			case 2:	d = 0; break;
			case 3:	d = 1; break;
		}
		return d;
	}
	
	static int turn(int d, int val) {
		d = d + val;
		if (d < 0) {
			d = 3;
		} else if (d > 3) {
			d = 0;
		}
		return d;
	}
	
	static int crash(int x, int y, int mx, int my, int d) {
		switch(map[mx][my]) {
		case 1: { //аб
			if (x > mx || y < my) {
				return reverse(d);
			} else if (x < mx) {
				return turn(d, -1);
			} else if (y > my) {
				return turn(d, 1);
			}
		}
		case 2: { //╩С
			if (x < mx || y < my) {
				return reverse(d);
			} else if (x > mx) {
				return turn(d, 1);
			} else if (y > my) {
				return turn(d, -1);
			}
		}
		case 3: { //©Л
			if (x < mx || y > my) {
				return reverse(d);
			} else if (x > mx) {
				return turn(d, -1);
			} else if (y < my) {
				return turn(d, 1);
			}
		}
		case 4: { //го
			if (x > mx || y > my) {
				return reverse(d);
			} else if (x < mx) {
				return turn(d, 1);
			} else if (y < my) {
				return turn(d, -1);
			}
		}
		case 5: return reverse(d);
		}
		return -1;
	}
	
}
