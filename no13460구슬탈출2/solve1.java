package no13460구슬탈출2;
import java.io.*;
import java.util.*;


//BFS로 풀이 , 코드가 비효율적인듯 .. ㅠ
//예외처리를 잘 해주어야 하는 문제!
//문제를 풀기전에 어떤경우에 어떤 상황이 나오는지 case별로 잘 정리하고 코드를 짜야할듯하다


// 위치와 이동횟수를 저장할 클래스 Loc
class Loc{
	int row, col , count;
	Loc(int r, int c, int co){
		this.row = r; this.col = c; this.count = co;
	}
}

public class solve1 {

	static int row, col; //N,M
	static char map[][]; //지도
	static Queue<Loc> rQ; //빨간구슬의 Q
	static Queue<Loc> bQ; //파란구슬의 Q

	
	static void bfs() {
		//구슬이 'O'에 들어갔나 나타내는 변수
		boolean rflag=false, bflag=false;
		
		while(!rQ.isEmpty()) {
			
			Loc rtemp = rQ.poll();
			Loc btemp = bQ.poll();

			int rrow = rtemp.row;	// Red의 행
			int rcol = rtemp.col;	// Red 열
			int brow = btemp.row;	// Blue 행
			int bcol = btemp.col; 	// Blue 열
			int bco = btemp.count;	// 이동횟수
			int rco = rtemp.count;
			
			
			int nextrr, nextrc, nextbr, nextbc; //Red Blue의 다음 좌표 Row(r),Col(c)
			boolean temprflag, tempbflag; // temprflag = true -> red 의 이동경로에 blue가 있다 
			

			//아래방향
			nextrr = rrow; nextrc = rcol; nextbr = brow; nextbc = bcol;
			temprflag = false; tempbflag = false;
			rflag = false; bflag = false;
			
			//이동방향의 끝부분 찾기, 'O'가 있는지 찾기(rflag), 이동경로중 R또는 B가 있는지 찾기 (temprflag)
			while(true) {
				
				nextrr = nextrr + 1;
				
				if(rgck(nextrr, nextrc)) {
					if(nextrr == brow && nextrc == bcol) {temprflag = true;}
					else if(map[nextrr][nextrc] == 'O') {rflag = true;}
					else if(map[nextrr][nextrc]=='#') {break;}
				}
						
			}
			
			while(true) {
				
				nextbr = nextbr + 1;
				
				if(rgck(nextbr, nextbc)) {
					if(nextbr == rrow && nextbc == rcol) {tempbflag = true;}
					else if(map[nextbr][nextbc] == 'O') {bflag = true;}
					else if(map[nextbr][nextbc]=='#') {break;}					
				}

			}			
			//이동방향의 끝부분찾기 //// 여기서 next값들은 Red,Blue 각각의 이동경로 끝에 있는  '#'좌표다
			
			//다음 이동횟수가 10 미만이면 진행
			if(rco+1<10) {

				//Case1 : 이동경로중 Blue가  있으며, Blue가 'O'에 빠지지 않았다
				if(temprflag && !tempbflag && !bflag) {

					// 이동하려는 곳이 기존의 위치와 같으면 Q에 넣지 않는다
					if(nextrr-2==rrow && nextrc == rcol && nextbr-1 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-2,nextrc, rco+1)); // Red앞에 Blue가 있으므로  #BR 순이 되야함
						bQ.add(new Loc(nextbr-1,nextbc, bco+1));
					}
				}

				//Case2 : 이동경로중 Red가 있으며, Blue가 'O'에 빠지지 않았다
				else if(!temprflag && tempbflag && !bflag) {

					// 이동하려는 곳이 기존의 위치와 같으면 Q에 넣지 않는다
					if(nextrr-1==rrow && nextrc == rcol && nextbr-2 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-1,nextrc, rco+1)); 
						bQ.add(new Loc(nextbr-2, nextrc, bco +1)); //Blue앞에 Red가 있으므로 #RB순이 되야함
					}
				}

				//Case3 : 이동경로중 RorB 어느것도 있지 않으며, Blue가 'O'에 빠지지 않았다
				else if(!temprflag && !tempbflag && !bflag) {

					// 이동하려는 곳이 기존의 위치와 같으면 Q에 넣지 않는다
					if(nextrr-1==rrow && nextrc == rcol && nextbr-1 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-1, nextrc, rco + 1)); //Red, Blue각각 앞에 가로막는 다른 구슬이 없으므로 #R, #B순이 됨
						bQ.add(new Loc(nextbr-1, nextbc, bco+1));
					}
				}

			}

			//이동을 마치고 Red는  'O'로 탈출하고 Blue는 탈출을 못했을경우 우리가 원하는 경우이므로 출력하고 메소드 종료
			if(rflag && !bflag) {System.out.println(rco+1); return;}


			// 나머지 방향에 대해서 동일하게 구현, 단 이동방향에 따른 좌표의 변화에 주의
			
			//우측방향
			nextrr = rrow; nextrc = rcol; nextbr = brow; nextbc = bcol;
			temprflag = false; tempbflag = false;
			rflag = false; bflag = false;
			
			while(true) {
				nextrc = nextrc + 1;
				
				if(rgck(nextrr, nextrc)) {
					if(nextrr == brow && nextrc == bcol) {temprflag = true;}
					else if(map[nextrr][nextrc] == 'O') {rflag = true;}
					else if(map[nextrr][nextrc]=='#') {break;}
				}
						
			}
			
			while(true) {
				
				nextbc = nextbc + 1;
				
				if(rgck(nextbr, nextbc)) {
					if(nextbr == rrow && nextbc == rcol) {tempbflag = true;}
					else if(map[nextbr][nextbc] == 'O') {bflag = true;}
					else if(map[nextbr][nextbc]=='#') {break;}					
				}

			}			


			if(rco+1<10) {

				if(temprflag && !tempbflag&& !bflag) {

					if(nextrr==rrow && nextrc-2 == rcol && nextbr == brow && nextbc-1 == bcol) {}
					else {
						rQ.add(new Loc(nextrr,nextrc-2,rco+1)); 
						bQ.add(new Loc(nextbr,nextbc-1,bco+1));	
					}
				}

				else if(!temprflag && tempbflag&& !bflag) {

					if(nextrr==rrow && nextrc-1 == rcol && nextbr == brow && nextbc-2 == bcol) {}
					else {
						rQ.add(new Loc(nextrr,nextrc-1,rco+1)); 
						bQ.add(new Loc(nextbr, nextrc-2,bco+1));
					}	
				}

				else if(!temprflag && !tempbflag && !bflag) {

					if(nextrr==rrow && nextrc-1 == rcol && nextbr == brow && nextbc-1 == bcol) {}
					else {
						rQ.add(new Loc(nextrr, nextrc-1,rco+1)); 
						bQ.add(new Loc(nextbr, nextbc-1,bco+1));

					}

				}		

			}
			
			if(rflag && !bflag) {System.out.println(rco+1); return;}

			
			//위방향
			nextrr = rrow; nextrc = rcol; nextbr = brow; nextbc = bcol;
			temprflag = false; tempbflag = false;
			rflag = false; bflag = false;
			
			while(true) {
				nextrr = nextrr - 1;
				
				if(rgck(nextrr, nextrc)) {
					if(nextrr == brow && nextrc == bcol) {temprflag = true;}
					else if(map[nextrr][nextrc] == 'O') {rflag = true;}
					else if(map[nextrr][nextrc]=='#') {break;}
				}
						
			}
			
			while(true) {
				
				nextbr = nextbr - 1;
				
				if(rgck(nextbr, nextbc)) {
					if(nextbr == rrow && nextbc == rcol) {tempbflag = true;}
					else if(map[nextbr][nextbc] == 'O') {bflag = true;}
					else if(map[nextbr][nextbc]=='#') {break;}					
				}

			}			
			
			if(rco+1<10) {

				if(temprflag && !tempbflag&& !bflag) {

					if(nextrr+2==rrow && nextrc == rcol && nextbr+1 == brow && nextbc == bcol) {}
					else {
						rQ.add(new Loc(nextrr+2,nextrc,rco+1)); 
						bQ.add(new Loc(nextbr+1,nextbc,bco+1));

					}

				}

				else if(!temprflag && tempbflag&& !bflag) {

					if(nextrr+1==rrow && nextrc == rcol && nextbr+2 == brow && nextbc == bcol) {}
					else {
						rQ.add(new Loc(nextrr+1,nextrc,rco+1)); 
						bQ.add(new Loc(nextbr+2, nextrc,bco+1));
					}		
				}

				else if (!temprflag && !tempbflag && !bflag){

					if(nextrr+1==rrow && nextrc == rcol && nextbr+1 == brow && nextbc == bcol) {}
					else {
						rQ.add(new Loc(nextrr+1, nextrc,rco+1)); 
						bQ.add(new Loc(nextbr+1, nextbc,bco+1));	
					}
				}

			}
			
			if(rflag && !bflag) {System.out.println(rco+1); return;}
	
			
			
			//왼쪽방향
			nextrr = rrow; nextrc = rcol; nextbr = brow; nextbc = bcol;
			temprflag = false; tempbflag = false;
			rflag = false; bflag = false;
			
			while(true) {
				nextrc = nextrc - 1;
				
				if(rgck(nextrr, nextrc)) {
					if(nextrr == brow && nextrc == bcol) {temprflag = true;}
					else if(map[nextrr][nextrc] == 'O') {rflag = true;}
					else if(map[nextrr][nextrc]=='#') {break;}
				}
						
			}
			
			while(true) {
				
				nextbc = nextbc - 1;
				
				if(rgck(nextbr, nextbc)) {
					if(nextbr == rrow && nextbc == rcol) {tempbflag = true;}
					else if(map[nextbr][nextbc] == 'O') {bflag = true;}
					else if(map[nextbr][nextbc]=='#') {break;}					
				}

			}			

			if(rco+1<10) {


				if(temprflag && !tempbflag&& !bflag) {

					if(nextrr==rrow && nextrc+2 == rcol && nextbr == brow && nextbc+1 == bcol) {}
					else {
						rQ.add(new Loc(nextrr,nextrc+2,rco+1)); 
						bQ.add(new Loc(nextbr,nextbc+1,bco+1));
					}

				}
				else if(!temprflag && tempbflag&& !bflag) {

					if(nextrr==rrow && nextrc+1 == rcol && nextbr == brow && nextbc+2 == bcol) {}
					else {
						rQ.add(new Loc(nextrr,nextrc+1,rco+1)); 
						bQ.add(new Loc(nextbr, nextrc+2,bco+1));
					}

				}
				else if(!temprflag && !tempbflag && !bflag){

					if(nextrr==rrow && nextrc+1 == rcol && nextbr == brow && nextbc+1 == bcol) {}
					else {
						rQ.add(new Loc(nextrr, nextrc+1,rco+1)); 
						bQ.add(new Loc(nextbr, nextbc+1,bco+1));
					}	
				}				

			}

			if(rflag && !bflag) {System.out.println(rco+1); return;}
			
			
		}
		
		System.out.println(-1);
		
		
		
	}
	
	static boolean rgck(int r, int c) {
		if(r>=0 && r<row && c>=0 && c<col) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String st[] = br.readLine().split(" ");
		
		row = Integer.parseInt(st[0]);
		col = Integer.parseInt(st[1]);
		
		map = new char[row][col];

		
		rQ = new LinkedList<>();
		bQ = new LinkedList<>();
		
		for(int r=0; r<row; r++) {
			
			String st1 = br.readLine();
			
			for(int c=0; c<col; c++) {
				map[r][c] = st1.charAt(c);
				if(map[r][c] == 'R') {
					rQ.add(new Loc(r,c,0));
				}
				if(map[r][c] == 'B') {
					bQ.add(new Loc(r,c,0));
				}
			}
		}
		
		bfs();

	}

}
