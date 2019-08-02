package no14499주사위굴리기;
import java.io.*;
import java.util.*;

//시뮬레이션문제
//각각의 케이스별로 해당하는 시뮬레이션을 구현
// 입력값 제대로 맞는지 확인하자!

public class solve1 {
	
	static int map[][];
	static int maprow, mapcol;
	static int order[];
	static int vecR[] = {0,0,0,-1,1};
	static int vecC[] = {0,1,-1,0,0};
	static int square[];
	
	static void simulator(int r, int c) {
		int nowr = r; int nowc = c;
		
		for(int i=0; i<order.length; i++) {
			
			int nextr, nextc;
			
			int or = order[i];   // 바닥초기화 필요
			switch(or) {
				case 1 :
					nextr = nowr + vecR[or]; nextc = nowc + vecC[or];
					//System.out.println(nextr + " " +nextc);
					if(rgck(nextr,nextc)) {
						int temp = square[6];
						
						square[6] = square[3];
						square[3] = square[1];
						square[1] = square[4];
						square[4] = temp;
						
						
						
						if(map[nextr][nextc]==0) {
							map[nextr][nextc] = square[6];
						}
						else {
							square[6] = map[nextr][nextc];
							map[nextr][nextc] = 0;
						}
						
						
						
						
						
						
						
						System.out.println(square[1]);
						
						nowr = nextr; nowc = nextc;
					}
					break;
					
				case 2:
					nextr = nowr + vecR[or]; nextc = nowc + vecC[or];
					//System.out.println(nextr + " " +nextc);
					if(rgck(nextr,nextc)) {
						int temp = square[6];
						
						square[6] = square[4];
						square[4] = square[1];
						square[1] = square[3];
						square[3] = temp;
						
						if(map[nextr][nextc]==0) {
							map[nextr][nextc] = square[6];
						}
						else {
							square[6] = map[nextr][nextc];
							map[nextr][nextc] = 0;
						}
						System.out.println(square[1]);
						nowr = nextr; nowc = nextc;
					}					
					break;
					
				case 3:
					nextr = nowr + vecR[or]; nextc = nowc + vecC[or];
					//System.out.println(nextr + " " +nextc);
					if(rgck(nextr,nextc)) {
						int temp = square[6];
						
						square[6] = square[2];
						square[2] = square[1];
						square[1] = square[5];
						square[5] = temp;
						
						if(map[nextr][nextc]==0) {
							map[nextr][nextc] = square[6];
						}
						else {
							square[6] = map[nextr][nextc];
							map[nextr][nextc] = 0;
						}
						
						System.out.println(square[1]);
						nowr = nextr; nowc = nextc;
					}					
					break;
					
				case 4:
					nextr = nowr + vecR[or]; nextc = nowc + vecC[or];
					//System.out.println(nextr + " " +nextc);
					if(rgck(nextr,nextc)) {
						int temp = square[6];
						
						square[6] = square[5];
						square[5] = square[1];
						square[1] = square[2];
						square[2] = temp;
						
						if(map[nextr][nextc]==0) {
							map[nextr][nextc] = square[6];
						}
						else {
							square[6] = map[nextr][nextc];
							map[nextr][nextc] = 0;
						}
						
						System.out.println(square[1]);
						nowr = nextr; nowc = nextc;
					}					
					break;
					
			
			}
			
			
		}
	}
	
	static boolean rgck(int row, int col) {
		if(row>=0 && row < maprow && col>=0 && col < mapcol) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st[] = br.readLine().split(" ");
		maprow = Integer.parseInt(st[0]);
		mapcol = Integer.parseInt(st[1]);
		
		map = new int[maprow][mapcol];
		
		int tr,tc;
		tr = Integer.parseInt(st[2]);
		tc = Integer.parseInt(st[3]);
		
		order = new int[Integer.parseInt(st[4])];
		
		for(int r=0; r<maprow; r++) {
			
			String st1[] = br.readLine().split(" ");
			for(int c=0; c<mapcol ; c++) {
				map[r][c] = Integer.parseInt(st1[c]);
			}
		}
		
		
		String st2[] = br.readLine().split(" ");
		
		for(int i=0; i<st2.length; i++) {
			order[i] = Integer.parseInt(st2[i]);
		}
		
		square = new int[7];
		simulator(tr,tc);
		
	}

}
