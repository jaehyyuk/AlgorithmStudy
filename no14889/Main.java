package no14889;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * 1�� : dfs All���� -> �ð��ʰ�
 * 2�� : dfs���� ������������ �湮�� �ߺ� ����(start�� �����ε������� for���� ������ ������) ex, 123 - 213�� ��� ���� --> �ð��ʰ�;
 * 3�� : dfs���迡�� Ʋ���ſ���;
 * dfs ��ͽ� start�� depth�� ���� �����ؼ� �߻��� ����;; �ֱ׷���;;
 * depth�� 3���� �ۿ� �Ȱ��°� start�� 1~6�� ���� �� --> �и��ؼ� �����ؾ� ��!!!!!!
 * 
 */

public class Main {

	static int N;
	static int[][] capa;
	static boolean[] visit;
	static int min_diff = Integer.MAX_VALUE;
	
	static void dfs(int start, int depth) {
		if(depth>=N/2) {
			int[] temp1 = new int[N/2];
			int[] temp2 = new int[N/2];
			
			int t1=0;
			int t2=0;
			for(int i=1; i<=N; i++) {
				if(visit[i]) {
					temp1[t1] = i; t1++;
				}
				else {
					temp2[t2] = i; t2++;
				}
			}
			/*
			for(int i=0; i<N/2; i++) {
				System.out.print(temp1[i]+" ");
			}
			System.out.println();
			*/
			t1 = 0;
			t2 = 0;
			for(int i=0; i<N/2; i++) {
				
				for(int j=i+1; j<N/2; j++) {
					
					t1 = t1 + capa[temp1[i]][temp1[j]] + capa[temp1[j]][temp1[i]];
					t2 = t2 + capa[temp2[i]][temp2[j]] + capa[temp2[j]][temp2[i]];
				}
			}
			
			if(Math.abs(t1-t2)<min_diff) {min_diff = Math.abs(t1-t2);}
			
			return;
		}
		
		for(int i=start+1; i<=N; i++) {
	
				visit[i] = true;
				dfs(i,depth+1);
				visit[i] = false;
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		capa = new int[N+1][N+1];
		visit = new boolean[N+1];
		StringTokenizer st;
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int c=1; c<=N; c++) {
				capa[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		dfs(0,0);
		
		System.out.println(min_diff);
	}

}
