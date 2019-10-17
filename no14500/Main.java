package no14500;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 각 도형의 베리에이션의 진행방향의 좌표들을 구한다
 * 해당 좌표들의 값들을 합한다
 * 모든 경우를 둘러볼 때가지 반복한다
 * 
 * 베리에이션의 경우를 각 도형의 진행방향 벡터를 입력으로 맞아서 푸는 함수를 구현함
 * 이때, ㅗ,ㅜ의 경우 진행방향이 매끄럽게이어지지 않으므로 다른 함수로 품
 * 
 */
public class Main {

	static int N,M; //n=row, m=col
	static int[][] map;
	static int[] vecr = {0,-1,0,1};
	static int[] vecc = {1,0,-1,0};
	static int max = 0;
	
	//ㅡ,ㅁ,ㄴ,ㄴㄱ 의 진행에 따른 함수
	//예를 들어, ㅡ의 경우 r,c가 첫번째칸, r1,c1이 두번째칸 그리고 v1은 r,c에서 r1,c1에 가기위한 방향
	static void cal(int v1, int v2, int v3) {
		int r1,r2,r3,c1,c2,c3;
		int sum;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				sum = 0;
				//각 칸의 좌표를 구함
				r1 = r+vecr[v1];
				c1 = c+vecc[v1];
				r2 = r1+vecr[v2];
				c2 = c1+vecc[v2];
				r3 = r2+vecr[v3];
				c3 = c2+vecc[v3];
				
				//범위를 초과하면 다음것
				if(!(r1>=0 && r1<N && c1>=0 && c1<M)) {continue;}
				if(!(r2>=0 && r2<N && c2>=0 && c2<M)) {continue;}
				if(!(r3>=0 && r3<N && c3>=0 && c3<M)) {continue;}
				
				sum = sum + map[r][c] + map[r1][c1] + map[r2][c2] + map[r3][c3];
				if(sum>max) {max = sum;}
				
			}
		}
	}
	
	//ㅗ의 베리에이션의 경우 v1은 ㅡorㅣ의 방향 v2는 ㅡ에서 위or아래 ㅣ에서 좌or우의 방향
	static void cal2(int v1, int v2) {
		int r1,r2,r3,c1,c2,c3;
		int sum;
		
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					sum = 0;
					r1 = r + vecr[v1];
					c1 = c + vecc[v1];
					r2 = r1 + vecr[v1];
					c2 = c1 + vecc[v1];
					r3 = r1 + vecr[v2];
					c3 = c1 + vecc[v2];
					if(!(r1>=0 && r1<N && c1>=0 && c1<M)) {continue;}
					if(!(r2>=0 && r2<N && c2>=0 && c2<M)) {continue;}
					if(!(r3>=0 && r3<N && c3>=0 && c3<M)) {continue;}
					
					sum = sum + map[r][c] + map[r1][c1] + map[r2][c2] + map[r3][c3];
					if(sum>max) {max = sum;}
				}
			}
		
		
		
	}
	

	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] st = br.readLine().split(" ");
		N = Integer.parseInt(st[0]);
		M = Integer.parseInt(st[1]);
		map = new int[N][M];
		
		StringTokenizer st1;
		for(int r=0; r<N; r++) {
			st1 = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st1.nextToken());
			}
		}

		
		//총 19개의 베리에이션이 있음
		cal(0,0,0);
		cal(3,3,3);
		cal(0,3,2);
		cal(3,3,0);
		cal(1,0,0);
		cal(0,3,3);
		cal(0,0,1);
		cal(3,3,2);
		cal(0,0,3);
		cal(2,3,3);
		cal(3,0,0);
		cal(3,0,3);
		cal(0,1,0);
		cal(3,2,3);
		cal(0,3,0);
		
		cal2(0,3);
		cal2(0,1);
		cal2(3,2);
		cal2(3,0);
		
		System.out.println(max);
		

	}

}
