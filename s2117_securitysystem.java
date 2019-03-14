import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class s2117_securitysystem {
	
	static Queue<house> q = new LinkedList<house>();
	static int N, M , max;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int ans[] = new int[T];
		for (int i = 0 ; i < T; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			max = 0;
			int map[][] = new int[N][N];
			for (int j = 0 ; j < N; j++) {
				for (int k = 0; k < N; k++) {
					map[j][k] = sc.nextInt();
					if (map[j][k] == 1) {
						q.add(new house(j,k));
					}
				}
			}
			
			house[] hs = new house[q.size()];
			int num =0;
			while(!q.isEmpty()) {
				house h = q.poll();
				hs[num++] = new house(h.x, h.y);
			}
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					search(j,k, hs);
				}
			}
			ans[i] = max;
		}
		for (int i =0; i<T;i++) {
			System.out.format("#%d %d\n", i+1, ans[i]);
		}
		
	}
	
	static void search(int x, int y, house[] h) {
//		System.out.println("START");
//		System.out.println("______________");
		
		int houses = h.length;
		int count = 0;
		int till = N+2;
		int price = 0;
		for (int o = 1; o < till; o++) {
			count = 0;
			price = (o*o) +(o-1)*(o-1);
			for (int i = 0; i < houses; i++) {
				
				int len = Math.abs(h[i].x - x) + Math.abs(h[i].y - y);
				if (len <= o-1) {
//					System.out.format("#%d) size:%d [%d,%d] -> [%d,%d] maxC:%d\n", i+1, o, x,y, h[i].x, h[i].y, max); 
					count++;
				}
				
			}
			if (count*M >= price)  {
//				System.out.format("count=%d price=%d\n", count, price);
				max = Math.max(max, count);
			}
		}
	}
	
	
	static class house{
		int x;
		int y;
			public house(int x, int y) {
				this.x =x;
				this.y =y;
			}
	}
	
}
