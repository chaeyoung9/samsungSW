import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class s5648_atom{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4001][4001]; 
        Queue<point> q = new LinkedList<>();
        int T = Integer.parseInt(br.readLine().trim());
        int fin[] = new int[T+1];
        
        for(int i=1;i<=T;i++) {
            int N = Integer.parseInt(br.readLine().trim());
            int zero = 0;
            int ans = 0;
            
            for(int j=0;j<N;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken())+1000)*2;
                int y = (1000- Integer.parseInt(st.nextToken()))*2;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                if (k==0) k = 1001;
                q.add(new point(x,y,d,k));
                map[y][x] = k;
             }
            
            while(!q.isEmpty()) {
                point p = q.poll();
                int x = p.x; 
                int y = p.y;
                if(map[y][x] > p.k) {
                    if(p.k==101) ans-=101;
                    ans+=map[y][x];
                    map[y][x] = 0;
                    continue;
                }
                
                switch(p.d) {
    	            case 0: y--; break;
    	            case 1: y++; break;
    	            case 2: x--; break;
    	            case 3: x++; break;
                }
                
                if(x < 0 || y < 0 || x > 4000 || y > 4000) {
                    map[p.y][p.x] = 0;
                    continue;
                }
                
                if(map[y][x]!=0) {
                    if(p.k==101) zero++;
                    map[y][x] += p.k;
                    map[p.y][p.x] = 0;
                } else {
                    map[y][x] = p.k;
                    map[p.y][p.x] = 0;
                    q.add(new point(x, y, p.d, p.k));
                }
            }
            ans -= zero*101;
            fin[i] = ans;
        }        
        for (int i =1; i <= T; i++) {
        	System.out.format("#%d %d\n", i, fin[i]);
        }
    }
    
    static class point{
        private int x,y,k,d;
        public point(int x,int y,int d,int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }
}
