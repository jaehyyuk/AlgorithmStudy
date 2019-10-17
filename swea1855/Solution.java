package swea1855;

/*
 * 
 * 자료구조 BFS순회를 위한 배열 - 링크드리스트
 * 각 노드의 부모를 적은 배열
 * 
 * 비포노드와 나우노드
 * 비포노드의 현재부터 부모까지 주르륵 비포어레이리스트에 넣음 (가는 횟수까지)
 * 나우노드의 현재부터 부모까지 주르륵 나우어레이이스트에 넣음
 * 
 * 나우노드의 첫번째부터 비포노드의 부모들과 비교
 * 일치하는것이 나오면 해당 노드들의 횟수들을 더해줌
 * 
 * 
 * 2번째, depth차이이용
 * 층차이가 나지 않는다면? 결국 올라가는 수는 똑같음 --> 동시비교 가능하다는 소리
 * 층차이가 난다면? 1임 now가 1더 많음
 * 따라서 now를 한번 해주고 비교 --> 층차이가 나지 않는 경우와 같이 처리
 * 
 * dp[before][now] = dp[now][before]  --> 디피배열의 최대가 100000*100000 -> 불가능
 * 이때 비포와 나우를 각기 다른 temp변수에 저장하고 진행
 * 
 * 나우와 비포를 계산하고 디피배열을 본다 -1이라면 계산하고 디피배열에 넣어준다
 * 계산할때 올라가면서 계속 디피를 확인한다 이때 디피가 -1이라면 cnt +2
 * 디피가 값이 있다면 cnt+dp값
 *
 * 
 */


import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

/*
class NodeAndMove{
	int node;
	int move;
	
	NodeAndMove(int node, int move){
		this.node = node;
		this.move = move;
	}
}*/

class Solution {

	static ArrayList<Integer>[] nodes;
	//static ArrayList<NodeAndMove> beforeArr;
	
	//static ArrayList<Integer> beforeArrnode;
	//static ArrayList<Integer> beforeArrmove;
	
	
	
	//static ArrayList<NodeAndMove> nowArr;
	

	//static ArrayList<Integer> nowArrnode;
	//static ArrayList<Integer> nowArrmove;
	
	static int[] parents;
	static Queue<Integer> q;
	static Queue<Integer> depthq;
	static int before;
	static int beforedepth;
	static int now;
	static int nowdepth;
	static boolean visit[];
	static int totalmove;
	static int dp[][];
	
	static void bfs() {

		
		while(!q.isEmpty()) {
			//beforeArr = new ArrayList<>();
			//nowArr = new ArrayList<>();
			/*
			beforeArrnode = new ArrayList<>();
			beforeArrmove = new ArrayList<>();
			nowArrnode = new ArrayList<>();
			nowArrmove = new ArrayList<>();
			*/
			
			now = q.poll();
			nowdepth = depthq.poll();
			int t = now;
			int b = before;
			
			//add child nodes into q
			if(!(nodes[now]==null)) {
				for(int i=0; i<nodes[now].size(); i++) {
				//if(!visit[nodes[now].get(i)]) {
					q.add(nodes[now].get(i));
					depthq.add(nowdepth+1);
					//visit[nodes[now].get(i)] = true;
				//}
			}
			}
			
			
			int cnt = 0;
			//if(dp[now][before]<0) {
				
				if(nowdepth == beforedepth) {
					
					while(true) {
						now = parents[now];
						cnt++;
						before = parents[before];
						cnt++;
						if(dp[now][before]>-1) {
							cnt += dp[now][before];
							break;
						}
					}
					
					dp[t][b] = cnt;
					dp[b][t] = cnt;
					totalmove += cnt;
					
					
				}
				
				
				else {
					
					
					cnt++;
					now = parents[now];
					if(dp[now][before]>-1) {
						cnt += dp[now][before];
					}
					else {
						
						while(true) {
							now = parents[now];
							cnt++;
							before = parents[before];
							cnt++;
							if(dp[now][before]>-1) {
								cnt += dp[now][before];
								break;
							}
						}
						
						
					}
					
					dp[t][b] = cnt;
					dp[b][t] = cnt;
					totalmove += cnt;

				}
				
				
			//}
			//else {
				
			//}
			
			/*
			if(nowdepth == beforedepth) {
				
				int cnt = 0;
				while(true) {
					
					now = parents[now];
					before = parents[before];
					cnt++;
					if(now==before) {
						totalmove += cnt*2;
						break;
					}
				}
				
				
				
				
			}
			
			else {
				//now가 1층 더 깊다
				int cnt=1;
				//now만 먼저 1층 올려줌
				now = parents[now];
				
				if(now == before) {
					totalmove += cnt;
				}
				else {
					totalmove += cnt;
					cnt=0;
					while(true) {
						now = parents[now];
						before = parents[before];
						cnt++;
						if(now==before) {
							totalmove += cnt*2;
							break;
						}
					}
				}
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			int cnt = 0;
			while(true) { //calculate before
				
				//beforeArr.add(new NodeAndMove(before,cnt));//
				
				
				beforeArrnode.add(before);
				beforeArrmove.add(cnt);
				
				before = parents[before];
				cnt++;
				if(before==0) {break;}
			}
			
			cnt = 0;
			while(true) { //calculate now
				now = parents[now];
				cnt++;
				//nowArr.add(new NodeAndMove(now,cnt));//
				nowArrnode.add(now);
				nowArrmove.add(cnt);
				if(now==1) {break;}
			}*/
			
			
			
			/*
			System.out.println("***");
			for(int i=0; i<beforeArr.size(); i++) {
				System.out.print("node = " + beforeArr.get(i).node +" move = " +beforeArr.get(i).move+" ");
			}
			
			System.out.println();
			
			for(int i=0; i<nowArr.size(); i++) {
				System.out.print("node = " + nowArr.get(i).node +" move = " +nowArr.get(i).move+" ");
			}
			System.out.println();*/
			
			/*
			boolean flag = false;
			for(int i=0; i<nowArrnode.size(); i++) {
				//int nowN = nowArr.get(i).node;
				//int nowM = nowArr.get(i).move;
				int nowN = nowArrnode.get(i);
				int nowM = nowArrmove.get(i);
				int beN, beM;
				
				for(int j=0; j<beforeArrnode.size(); j++) {
					//beN = beforeArr.get(j).node;
					beN = beforeArrnode.get(j);
					if(nowN==beN) {
						//beM = beforeArr.get(j).move;
						beM = beforeArrmove.get(j);
						totalmove = totalmove + nowM + beM;
						flag = true;
						break;
					}
					
				}
				
				if(flag) {break;}
				
			}
			
			*/
			
			before = t;
			beforedepth = nowdepth;
			
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<testcase; tc++) {
			totalmove=0;
			int N = Integer.parseInt(br.readLine());
			
			//visit = new boolean[N+1];
			
			dp = new int[N+1][N+1];
			for(int r=1; r<=N; r++) {
				
				for(int c=1; c<=N; c++) {
					dp[r][c] = -1;
				}
			}
			for(int i=1; i<=N; i++) {
				dp[i][i] = 0;
			}
			
			nodes = new ArrayList[N+1];
			/*
			beforeArr = new ArrayList<>();
			nowArr = new ArrayList<>();
			
			beforeArrnode = new ArrayList<>();
			beforeArrmove = new ArrayList<>();
			nowArrnode = new ArrayList<>();
			nowArrmove = new ArrayList<>();
			*/
			/*
			for(int i=1; i<=N; i++) {
				nodes[i] = new ArrayList<>();
			}*/
			
			
			parents = new int[N+1];
			parents[1] = 1;
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			for(int i=2; i<=N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				parents[i] = temp;
				if(nodes[temp]==null) {nodes[temp]= new ArrayList<>();}
				nodes[temp].add(i);
			}
			
			q = new LinkedList<>();
			depthq = new LinkedList<>();
			
			before = 1;
			beforedepth = 0;
			//visit[1] = true;
			for(int i=0; i<nodes[1].size(); i++) {
				q.add(nodes[1].get(i));
				depthq.add(1);
				//visit[nodes[1].get(i)] = true;
			}
					
			bfs();
			System.out.println("#"+(tc+1)+" "+totalmove);
			
			
			
			
			
			
			
			
		}
	}

}
