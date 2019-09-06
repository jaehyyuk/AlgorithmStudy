package no16236아기상어;

/*
 * 
 * 
 * 
 * 
 * 
 * 
 */






import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Loc{
	int r,c,t;
	Loc(int r, int c, int t){
		this.r = r; this.c = c; this.t = t;
	}
	
}

public class Main {

	static int mapsize;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<Loc> fish; //같은 layer안에 먹을 수 있는 물고기가 여러마리면 여기에 해당 좌표를 저장, 후에 우선순위 물고기를 탐색함
	static Queue<Loc> q;
	static int[] vecC = {1,0, -1, 0};
	static int[] vecR = {0,1,0,-1};
	static int sharksize; //상어크기
	static int eating; //상어가 먹은 물고기 수
	
	
	public static void bfs(){
		
		boolean find = false; //먹을 수 있는 물고기를 찾았다면 true
		
		while(!q.isEmpty()) {
			if(find) {return;} //물고기찾음! bfs를 끝낸다
			
			int qsize = q.size(); //같은 layer에서 (같은 시간안에) 먹을 수 있는 물고기가 여러마리 일 수 있기 때문에
								  //큐의 사이즈만큼 돌리는 것, 즉 다음 time을 탐색할 모든 큐 속의 자료는 봐야 한다는 것.
			
			for(int rotate_samelayer=0; rotate_samelayer<qsize; rotate_samelayer++) {
				
				Loc temp = q.poll();
				int nowrow = temp.r;
				int nowcol = temp.c;
				int nowtime = temp.t;
				
				int nextrow, nextcol;
				
				for(int i=0; i<4; i++) {
					
					nextrow = nowrow + vecR[i];
					nextcol = nowcol + vecC[i];
					
					//범위를 넘지 않고 방문을 하지 않았다면
					if(rgck(nextrow, nextcol) && !visit[nextrow][nextcol]) {
						//빈 공간(0) 이거나 사이즈가 같은 물고기라면 그냥 지나쳐 간다
						if(map[nextrow][nextcol]==0 || map[nextrow][nextcol]==sharksize) {
							q.add(new Loc(nextrow, nextcol, nowtime+1));
							visit[nextrow][nextcol] = true;
						}
						//작은 물고기 발견! 먹는다!
						else if(map[nextrow][nextcol]<sharksize) {
							fish.add(new Loc(nextrow,nextcol,nowtime+1));//fish어레이에 넣어준다
							find=true; //먹이를 찾음을 표시
						}
		
					}
						
				}
	
			}

		}
		
	}
	
	public static boolean rgck(int r, int c) {
		if(r>=0 && r<mapsize && c>=0 && c<mapsize) {return true;}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		mapsize = Integer.parseInt(br.readLine());
		
		map = new int[mapsize][mapsize];
		visit = new boolean[mapsize][mapsize];
		
		q = new LinkedList<>();
		fish = new ArrayList<>();
		
		for(int r=0; r<mapsize ; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			for(int c=0; c<mapsize; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					q.add(new Loc(r,c,0));
					map[r][c]=0; //중요! 9는 위치이므로 초기값을 0으로 만들어 줘야함;;
				}
			}
		}
		
		sharksize = 2;
		eating = 0;
		
		int time=0; //걸린 시간 저장
		
		//반복문을 통해 더이상 먹이를 찾지 못할때까지 반복해준다
		while(true) {

			visit = new boolean[mapsize][mapsize];
			bfs();
			
			if(fish.size()==0) { //fish어레이에 값이 없을 경우 먹이를 못 찾은 경우다
				System.out.println(time); break;
			}	
			
			//다음 먹이의 위치를 기억하는 변수
			int startrow, startcol;
			startrow = fish.get(0).r;
			startcol = fish.get(0).c;
			time = fish.get(0).t;
			
			//fish가 2개 이상이라면 우선순위의 먹이를 찾는다
			for(int i=1; i<fish.size(); i++) {
				Loc temp = fish.get(i);
				if(startrow>temp.r) {startrow = temp.r; startcol = temp.c;}
				else if(startrow == temp.r) {
					if(startcol>temp.c) {startrow = temp.r; startcol = temp.c;}
				}
			}
			
			//bfs를 다시 돌리기 위해 모든것을 초기화 해준다.
			q = new LinkedList<>();
			fish = new ArrayList<>();
			map[startrow][startcol] = 0;
			
			//다음 먹이를 먹고 시작할 위치를 넣어준다
			q.add(new Loc(startrow,startcol,time));
			
			//먹이를 먹고 먹은수와 사이즈업그레이드 진행
			eating++;
			if(sharksize==eating) {
				sharksize++;
				eating=0;
			}
			
		}
		
		
		
		
	}

}
