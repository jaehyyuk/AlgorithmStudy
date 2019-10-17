
import java.util.*;
import java.io.*;
import java.io.FileInputStream;


class Sol {
	
	static int start, end;
	static int[] rangestart;
	static int[] rangeend;
	static boolean[] visit;
	static Queue<Integer> q;
	static Queue<Integer> time;
	static int total;
	
	static int bfs() {
		
		while(!q.isEmpty()) {
			
			int templocation = q.poll();
			int temptime = time.poll();
			
			if(templocation==end) {
				return temptime;
			}
			
			if(templocation == 1) {
				if(!visit[2]) {
					visit[2] = true;
					q.add(2); time.add(temptime+1);
				}
				if(!visit[3]) {
					visit[3]=true;
					q.add(3); time.add(temptime+1);
				}
			}
			
			else {
				int idx = 0;
				int where = -1;
				for (int i = 1; i < rangestart.length; i++) {
					if (rangestart[i] <= templocation && rangeend[i] >= templocation) {
						if (templocation == rangestart[i]) {
							idx = i; where = 0;
						}
						else if(templocation == rangeend[i]) {
							idx = i; where = 2;
						}
						else {
							idx = i; where = 1;
						}
					}
				}
				
				
				
				if(where == 0) {
					int[] next = new int[4];
					next[0] = templocation - (idx-1);
					next[1] = templocation + 1;
					next[2] = templocation + idx;
					next[3] = templocation + (idx+1);
					
					for(int i=0; i<4; i++) {
						if(next[i]>=1 && next[i]<total) {
							if(!visit[next[i]]) {
								visit[next[i]]=true;
								q.add(next[i]);
								time.add(temptime+1);
							}
							
						}
					}
				}
				else if (where == 1){
					int[] next = new int[6];
					next[0] = templocation - (idx-1);
					next[1] = templocation + 1;
					next[2] = templocation + idx;
					next[3] = templocation + (idx+1);
					next[4] = templocation - idx;
					next[5] = templocation - 1 ;
					
					for(int i=0; i<6; i++) {
						if(next[i]>=1 && next[i]<total) {
							if(!visit[next[i]]) {
								visit[next[i]]=true;
								q.add(next[i]);
								time.add(temptime+1);
							}
							
						}
					}
				}
				
				else if(where == 2) {
					int[] next = new int[4];
					next[0] = templocation - (idx);
					next[1] = templocation - 1;
					next[2] = templocation + idx;
					next[3] = templocation + (idx+1);
					
					for(int i=0; i<4; i++) {
						if(next[i]>=1 && next[i]<total) {
							if(!visit[next[i]]) {
								visit[next[i]]=true;
								q.add(next[i]);
								time.add(temptime+1);
							}
							
						}
					}
				}
	

			}
			
			
			
			
		}
		
		return 0;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("C:\\eclipse\\temo\\src\\swea4112\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		
		rangestart = new int[144];
		rangeend = new int[144];
		
		rangestart[1] = 1;
		rangeend[1] = 1;
		for(int i=2; i<rangestart.length; i++) {
			rangestart[i] = rangestart[i-1]+i-1;
			rangeend[i] = rangestart[i] + i-1;
			
		}
		
		
		total = 143*144/2;
		for(int tc=0; tc<testcase; tc++) {
			String[] starr = br.readLine().split(" ");
			
			start = Integer.parseInt(starr[0]);
			end = Integer.parseInt(starr[1]);
			
			if(start==end) {
				System.out.println("#"+(tc+1)+" "+0);
			}
			else {
				visit = new boolean[total];
				q = new LinkedList<>();
				time = new LinkedList<>();
				
				q.add(start);
				time.add(0);
				visit[start]=true;
				int ans = bfs();
				System.out.println("#"+(tc+1)+" "+ans);
				
			}
					
			
		}
	}

}
