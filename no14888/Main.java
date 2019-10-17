package no14888;
/*
 * 
 * DFS 브루트포스 문제
 * 초기시작값을 처음 들어오는 숫자
 * 각 연산자의 카운트에 따라서 dfs진입여부를 점검한다(visit배열과 같은 역할)
 * depth가 N과 같을때, 즉 모든 연산을 다 끝낸 마지막에 max와 min을 비교 갱신 한다
 * 
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
public class Main {

	
	static int N;
	static int[] nums;
	static int[] operator;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	static void dfs(int depth, int calculated) {
		
		
		if(depth>=N) {
			
			if(calculated>max) {max = calculated;}
			if(calculated<min) {min = calculated;}
			
			return;}
		
		int num = nums[depth];
		
		for(int i=0; i<4; i++) {
			if(operator[i]>0) {
				if(i==0) {
					operator[i]--;
					dfs(depth+1, calculated+num);
					operator[i]++;
					
				}else if(i==1) {
					operator[i]--;
					dfs(depth+1, calculated-num);
					operator[i]++;
				}else if(i==2){
					operator[i]--;
					dfs(depth+1, calculated*num);
					operator[i]++;
				}else {
					operator[i]--;
					dfs(depth+1, calculated/num);
					operator[i]++;
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operator = new int[4];
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");	
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1,nums[0]);
		System.out.println(max);
		System.out.println(min);
	}

}
