package no14502연구소;

//1.무작위로 벽 3개를 세움
//2.bfs 후 안전영역 갯수 구함
//3.계속 비교 후 최대값 출력

import java.io.*;
import java.util.*;

class Loc{
	int row, col;
	Loc(int row, int col){
		this.row = row;
		this.col = col;
	}
}

public class solve1 {
	
	static int row, col;
	static int[][] matrix;
	static boolean[][] visit;
	static int count;
	static Queue<Integer> q1;
	static Queue<Integer> q2;
	static int max = 0;
	static int[] vecR= {0,1,0,-1};
	static int[] vecC= {1,0,-1,0};
	
	static boolean check(int r, int c) {
		if(r>=0 && r<row && c>=0 && c<col) {return true;}
		return false;
	}
	
	static void bfs() {
		
		while(!q1.isEmpty()) {
			//Loc temp = q.poll();
			
			int temprow = q1.poll();
			int tempcol = q2.poll();
			
			for(int i=0; i<4; i++) {
				int nextrow = temprow + vecR[i];
				int nextcol = tempcol + vecC[i];
				if(check(nextrow,nextcol)) {
					if(matrix[nextrow][nextcol]==0 && !visit[nextrow][nextcol]) {
						q1.add(nextrow);
						q2.add(nextcol);
						visit[nextrow][nextcol] = true;
						count--;
					}
					
				}

			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//StringTokenizer st = new StringTokenizer(br.readLine());
		String st[] = br.readLine().split(" ");
		
		
		//row = Integer.parseInt(st.nextToken());
		//col = Integer.parseInt(st.nextToken());

		row = Integer.parseInt(st[0]);
		col = Integer.parseInt(st[1]);
		
		matrix = new int[row][col];
		visit = new boolean[row][col];
		
		int initialzero = 0;
		ArrayList<Loc> zq = new ArrayList<>();
		
		for(int i=0; i<row; i++) {
			//StringTokenizer st1 = new StringTokenizer(br.readLine());
			st = br.readLine().split(" ");
			for(int j=0; j<col; j++) {
				//int temp = Integer.parseInt(st1.nextToken());

				int temp = Integer.parseInt(st[j]);
				matrix[i][j] = temp;
				if(temp == 0) {
					initialzero++;
				}
				if(temp == 2) {
					zq.add(new Loc(i, j));
				}
				
			}
		}
		////// 여기까지입력 //////

		//무작위로 3개 추출, (가로x세로)C3 의 경우의수 생성 ,최대 64C3의 경우 생성 (가로 세로 모두 8일때)
		int tot = row*col;
		for(int i=0; i<tot; i++) {
			int oneR = i/col;
			int oneC = i%col;
			if(matrix[oneR][oneC] != 0) {continue;}
			matrix[oneR][oneC] = 1;
			
			for(int j=i+1; j<tot; j++) {
				int twoR = j/col;
				int twoC = j%col;
				if(matrix[twoR][twoC] != 0 ) {continue;}
				matrix[twoR][twoC] = 1;
				
				for(int k=j+1; k<tot; k++) {
					int threeR = k/col;
					int threeC = k%col;
					if(matrix[threeR][threeC]!=0) {continue;}
					matrix[threeR][threeC] = 1;
					
					q1 = new LinkedList<>();
					q2 = new LinkedList<>();
					for(int z = 0; z<zq.size(); z++) {
						Loc te = zq.get(z);
						q1.add(te.row);
						q2.add(te.col);
					}
					visit = new boolean[row][col];
					count = initialzero - 3;
					
					bfs();
					
					if(max<count) {max = count;} //최대값 비교
					matrix[threeR][threeC] = 0;
					
					
					
				}
				
				matrix[twoR][twoC] = 0;
			}
			matrix[oneR][oneC] = 0;
		}
		
		
		System.out.println(max);

	}

}
