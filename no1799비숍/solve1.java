package no1799비숍;

/*dfs+백트래킹을 활용한 문제
 * 
 * 체스판을 2개의 영역으로 분리해서 생각
 * 1 2 1 2
 * 2 1 2 1
 * 1 2 1 2
 * 2 1 2 1
 * 이런식으로 나눠서 1에서 시작한 dfs는 1에서만 탐색을 계속하고 2는 2끼리만
 * 이렇게 하지 않으면 시간초과남
 * 
 * 
 */




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class solve1 {
	
	static int size;//맵크기
	static int map[][]; //0,1,2가 들어갈 맵
	static boolean visit[][]; //방문배열
	static int vecR[] = {1,1,-1,-1}; //대각선 벡터(행)
	static int vecC[] = {1,-1,-1,1}; //대각선 벡터(열)
	static int counts[]; //최대비숍의 갯수 저장배열 counts[1] -> index 1의 최대 비숍갯수
	
	static void dfs(int start, int index, int count) {
		
		if(counts[index]<count) {
			counts[index] = count;
		}
		
		// dfs탑색을 진행할때 dfs로 들어간 다음의 인덱스부터 시작하도록 start+1
		for(int i = start+1; i<size*size; i++) {
			int r = i/size;
			int c = i%size;
			
			//맵의 인덱스와 현재 dfs의 인덱스가 같고 방문하지 않았을때,
			if(map[r][c] == index && !visit[r][c]) {
				
				//비숍을 놓을 수 있다면 dfs진행
				if(isPossible(r,c)) {
					visit[r][c] = true;
					dfs(i,index, count+1);
					visit[r][c] = false;
				}
			}
			
		}
		
	}
	
	//대각선에 비숍을 놓을 수 있는지 확인하는 메소드
	static boolean isPossible(int r, int c) {
		
		boolean temp = true;
		
		for(int i=0; i<4 ; i++) {
			
			int tempr = r; int tempc = c;
			
			while(true) {
				tempr = tempr + vecR[i];
				tempc = tempc + vecC[i];
				
				if(rangeCheck(tempr,tempc)) {
					if(visit[tempr][tempc]) {
						temp=false;
					}
				}
				else {
					break; //범위를 벗어나면 브레이크
				}
			}
			
		}
		
		return temp;
		
	}
	
	//범위체크
	static boolean rangeCheck(int r, int c) {
		if(r>=0 && r<size && c>=0 && c<size) {return true;}
		return false;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		
		map = new int[size][size];
		visit = new boolean[size][size];
		
		for(int r=0; r<size; r++) {
			
			String templine[] = br.readLine().split(" ");
			for(int c=0; c<size; c++) {
				map[r][c] = Integer.parseInt(templine[c]);
			}
		}
		
		
		// 인덱스 1과 2로 나누는 작업
		for(int r=0;  r<size; r++) {
			
			if(r%2==0) {
				for(int c=1; c<size; c=c+2) {
					if(map[r][c]==0) {continue;}
					map[r][c] = 2;
				}
			}
			else {
				for(int c=0; c<size; c=c+2) {
					if(map[r][c]==0) {continue;}
					map[r][c]=2;
				}
			}
		}
		
		
		counts = new int[3];
		counts[1] = 0; counts[2] = 0;
		
		dfs(-1,1,0); //인덱스 1의 경우 진행
		dfs(-1,2,0); //인덱스 2의 경우 진행

		System.out.println(counts[1] + counts[2]);
	}

}
