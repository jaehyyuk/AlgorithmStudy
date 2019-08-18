package no15686치킨배달;

/*
 * dfs(백트래킹)과 완전탐색 활용 문제
 * 어떤 경우들을 선택할지 말지 조합하는 문제에 있어 dfs로 해결이 가능하다 (그 깊이가 크지 않을때)
 * 이 경우 백트래킹을 통해 원하는 조건을 모두 탐색할 수 있도록 해야한다 (입력받은 M을 만족하는 모든 경우)
 * 치킨거리를 구하는 경우는 각 집에서 각 치킨집들의 모든 거리들을 구하고 비교해서 최소값을 찾는다(완전탐색)
 * 처음에는 bfs를 써야하나 했지만 굳이 그럴필요 없이 for문으로 구하면 된다
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Location{
	
	int row, col;
	Location(int r, int c){
		this.row = r; this.col = c;
	}
}

public class solve1 {
	
	static int m;					//입력 변수 m
	static Location[] chickenLoc;	//치킨집의 위치들을 저장할 Location 배열
	static Location[] house;		//집들의 위치들을 저장할 Location 배열	
	static boolean[] chickenSel;	//치킨집의 선택 유무배열
	static int housecount, chickencount;	//집의 수, 치킨집의 수
	static int chickenDist = Integer.MAX_VALUE;	//치킨거리저장 변수
	
	
	static void dfs(int layer, int select) {
		//루트노드가 -1깊이(layer)부터 시작,  0깊이 일때가 첫번째 치킨집을 선택하느냐 마느냐의 경우
		
		if(select == m) {
			
			
			int dist=0; //해당 dfs경로의 경우, 치킨거리 저장
			
			for(int i=0; i<housecount; i++) {
				
				int tempdist = Integer.MAX_VALUE; // 지정한 집에서 각 치킨집까지의 거리저장
				for(int j=0;  j<chickencount; j++) {
					
					if(chickenSel[j]) { //선택한 치킨집만 계산
						tempdist = Math.min(tempdist, distance(house[i], chickenLoc[j]));
					}
					
				}
				dist += tempdist;
			}
			
			//기존의 치킨거리보다 개선되면 갱신
			if(chickenDist>dist) {chickenDist = dist;}
			
			return;
		}
		
		//dfs깊이 들어가기
		layer++;
		if(layer >= chickencount) {return;} //트리의 깊이가 선택할 수 있는 치킨집의 수보다 많아지는 경우 불가능한 깊이이므로 스킵
		
		//Case 1 : 해당 치킨집을 살려둔다
		chickenSel[layer] = true;
		dfs(layer, select+1);
		chickenSel[layer] = false; //나오면서 Select해제
		
		//Case 2 : 해당 치킨집을 없앤다.
		dfs(layer, select);
		
		
	}
	
	//거리계산 메소드
	static int distance(Location a, Location b) {
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputone = br.readLine().split(" ");
		
		int n = Integer.parseInt(inputone[0]);
		m = Integer.parseInt(inputone[1]);
		
		chickenLoc = new Location[13];
		chickenSel = new boolean[13];
		house = new Location[2*n];
		housecount = 0; chickencount = 0;
		
		for(int r=0; r<n; r++) {
			
			String[] inputtwo = br.readLine().split(" ");
			
			for(int c=0; c<n; c++) {
				int temp = Integer.parseInt(inputtwo[c]);
				if(temp == 1) {
					house[housecount] = new Location(r,c);
					housecount++;
				}
				else if(temp == 2) {
					chickenLoc[chickencount] = new Location(r,c);
					chickencount++;
				}
			}
		}
		// ... 입력
		
		dfs(-1,0);
		
		System.out.println(chickenDist);
		
	}

}
