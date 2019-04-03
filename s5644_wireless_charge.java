import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class s5644_wireless_charge {

	static PriorityQueue<access> pq1 = new PriorityQueue<access>();
	static PriorityQueue<access> pq2 = new PriorityQueue<access>();
	
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ;  i <=T; i++) {
			int dx[] = {0,-1,0,1,0};
			int dy[] = {0,0,1,0,-1};
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int UA[] = new int[M];
			int UB[] = new int[M];
			
			
			int A = Integer.parseInt(st.nextToken());
			access AP[] = new access[A];
			
			st = new StringTokenizer(br.readLine());
			for (int j =0;j < M; j++) 
				UA[j] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int j =0;j < M; j++) 
				UB[j] = Integer.parseInt(st.nextToken());
		
			for (int j =0; j < A; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				AP[j] = new access(y,x,c,p);
			}
			
			user one = new user(1,1);
			user two = new user(10,10);
			
			
			
			int a = 0;
			for (int j=0;j <=M; j++) {
				for (int k =0; k < A; k++) {
					int a1 = Math.abs(one.x - AP[k].x) + Math.abs(one.y - AP[k].y);
					int b1 = Math.abs(two.x - AP[k].x) + Math.abs(two.y - AP[k].y);
					if (a1 <= AP[k].c) {
						pq1.add(AP[k]);
					}
					if (b1 <= AP[k].c) {
						pq2.add(AP[k]);
					}
				}
				int get = sorter();
				a += get;
				if (j != M) {
					one.x += dx[UA[j]];
					one.y += dy[UA[j]];
					two.x += dx[UB[j]];
					two.y += dy[UB[j]];
				}
				pq1.clear();
				pq2.clear();
			}
			sb.append("#"+i+" " +a + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static int sorter() {
		int len1 = pq1.size();
		int len2 = pq2.size();
		if (len1 == 0 && len2 > 0) return pq2.peek().p;
		if (len1 > 0 && len2 == 0) return pq1.peek().p; 
		
		access p1 = pq1.poll();
		access p2 = pq2.poll();
		if (len1 == 1 && len2 == 1) {
			if (p1.x == p2.x && p1.y == p2.y && p1.p == p2.p) {
				return (p1.p);
			} else {
				return (p1.p + p2.p);
			}
		}
		
		if (len1 > 1 && len2 == 1) {
			if (p1.x == p2.x && p1.y == p2.y && p1.p == p2.p) {
				int max = pq1.poll().p + p2.p;
				return max;
			} else {
				return (p1.p+p2.p);
			}
		}
		
		if (len1 == 1 && len2 > 1) {
			if (p1.x == p2.x && p1.y == p2.y && p1.p == p2.p) {
				int max = pq2.poll().p + p1.p;
				return max;
			} else {
				return (p1.p+p2.p);
			}
		}
		
		if (len1 > 1 && len2 > 1) {
			int one = 0;
			if (p1.x != p2.x || p1.y != p2.y || p1.p != p2.p) {
				return (p1.p + p2.p);
			} else if (p1.x == p2.x && p1.y == p2.y && p1.p == p2.p){
				one = p1.p;
			}
			int two = p1.p + pq2.peek().p;
			int three = p2.p + pq1.peek().p;
			one = Math.max(one, two);
			one = Math.max(one, three);
			return one;
			
		}
		
		
		return 0;
	}
	
	static class access implements Comparable<access>{
		int x,y,c,p;
			public access(int x, int y, int c, int p) {
				this.x = x;
				this.y = y;
				this.c = c;
				this.p = p;
			}
			
			@Override
			public int compareTo(access a) {
				return this.p <= a.p ? 1: -1;
			}
	}
	
	
	static class user{
		int x,y;
			public user(int x, int y) {
				this.x =x;
				this.y =y;
			}
	}
	
}