package no2206벽부수고이동하기;

import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


class Position{
	int row,col,count,which; // 행,렬,이동횟수, which = 벽을 부수기 전인지(0) 후인지(1) 나타내는 변수 
	
	Position(int row, int col, int count, int which) {
		this.row = row;
		this.col = col;
		this.count = count;
		this.which = which;
	}
}


public class solve1 {

	static int row, col;
	static boolean[][][] visit;
	static int[][] map;
	static Queue<Position> q;
	static int[] vectorRow = {0,-1,0,1};
	static int[] vectorCol = {1,0,-1,0};

	static void bfs() {
		
		while(!q.isEmpty()) {
			
			Position temp = q.poll();
			
			int nowrow = temp.row;
			int nowcol = temp.col;
			int nowcount = temp.count;
			int nowwhich = temp.which;
			
			//목적지 도착시 출력 후 메소드 종료
			if(nowrow==row-1 && nowcol==col-1) {
				System.out.println(nowcount); return;
			}
			
			//4방향탐색
			int nextrow, nextcol;
			for(int i=0; i<4; i++) {
				
				nextrow = nowrow + vectorRow[i];
				nextcol = nowcol + vectorCol[i];
				
				if(rgck(nextrow, nextcol)) {
					
					//Case 1 : 다음칸이 벽이 아니며, 해당칸을 방문하지 않았을때, 이때, nowwhich변수로 인해 벽을 부수기 전인지(0) 후인지(1) 알 수 있음
					if(map[nextrow][nextcol]==0 && !visit[nextrow][nextcol][nowwhich]) {
						
						q.add(new Position(nextrow,nextcol,nowcount+1,nowwhich)); //다음칸 Q에 넣기
						visit[nextrow][nextcol][nowwhich]=true;  //다음칸 방문 true
						
						if(nowwhich==0) {  //이때, 다음칸이 아직 벽이 부수기 전(0)이라면 벽을 부순후의 방문배열도 true로 만들어야 벽을 부순 후 사용할 방문배열에서 왔던길을 다시 가지 않는다
							visit[nextrow][nextcol][nowwhich+1]=true;
						}
						
					}
					//Case 2 : 다음칸이 벽이라면 
					else if(map[nextrow][nextcol]==1) {
						//벽인데, 벽을 부수기 전이라면 부수고 진행
						if(nowwhich == 0) {
							q.add(new Position(nextrow, nextcol, nowcount+1, nowwhich+1)); //nowwhich=1(벽을부순후)로 갱신
							visit[nextrow][nextcol][nowwhich+1]=true;
						}
					}
				}
			}
			
			
		}
		
		//불가능할 경우
		System.out.println(-1);
		
		
		
	}
	
	//범위체크
	static boolean rgck(int r, int c) {
		if(r>=0 && r<row && c>=0 && c<col) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] headline = br.readLine().split(" ");
		
		row = Integer.parseInt(headline[0]);
		col = Integer.parseInt(headline[1]);
		
		visit = new boolean[row][col][2];
		map = new int[row][col];
		
		for(int r=0; r<row; r++) {
			String inner = br.readLine();
			
			for(int c=0; c<col; c++) {
				map[r][c] = inner.charAt(c) - '0';
			}
		}
		
		q = new LinkedList<>();
		
		q.add(new Position(0,0,1,0));
		visit[0][0][0] = true;
		visit[0][0][1] = true;
		bfs();
		
	}

}
