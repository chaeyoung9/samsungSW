	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.StringTokenizer;
	
	
	public class s5648_atom {
		
		public static void main(String args[]) throws NumberFormatException, IOException {
			Queue<point> que = new LinkedList<point>();
			int[][] map = new int[4001][4001];
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(br.readLine().trim());
			StringBuilder sb = new StringBuilder();
			
			for (int i = 1; i <= T; i++) {
				int N = Integer.parseInt(br.readLine().trim());
				
				for (int j = 0; j < N; j++) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					int x = (Integer.parseInt(st.nextToken()) + 1000)*2;
					int y = (1000- Integer.parseInt(st.nextToken())) *2;
					int d = Integer.parseInt(st.nextToken());
					int K = Integer.parseInt(st.nextToken());
					if (K ==0) {K = 1234;}
					map[y][x] = K;
					que.add(new point(x,y,d, K));
				}
				
				int E = 0;
				int zero = 0;
				
				while(!que.isEmpty()) {
						point p = que.poll();
						
						int d = p.direction;
						int x = p.x;
						int y = p.y;

						if (map[y][x] > p.energy) {
							if (p.energy == 1234) E -= 1234;
							E += map[y][x];
							map[y][x] = 0;
							continue;
						}
						
						switch(d) {
							case 0 : y--; break;
							case 1 : y++; break;
							case 2 : x--; break;
							case 3 : x++; break;
						}
						
						if (x <0 || y <0 || x > 4000 || y > 4000) {
							map[p.y][p.x]= 0;
							continue;
						}
						
						if (map[y][x] != 0) {
							if (map[p.y][p.x]== 1234) { zero++;}
							if (map[y][x] == 1234) {zero++;}
							map[y][x] += map[p.y][p.x];
							map[p.y][p.x]= 0; 
						} else {
							map[y][x] = map[p.y][p.x];
							map[p.y][p.x] = 0; 
							que.add(new point(x,y, p.direction, p.energy));
						}
				}
				E -= (1234 * zero);
				sb.append("#" + i+ " " + E + "\n");
			}
			
			System.out.println(sb);
		}
		
		static class point{
			int x;
			int y;
			int direction;
			int energy;
			
				public point(int x, int y, int direction, int energy) {
					this.x =x;
					this.y =y;
					this.direction = direction;
					this.energy = energy;
				}
		}
	}
