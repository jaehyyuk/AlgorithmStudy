

import java.io.*;

/*

BFS 으로 풀 수 있지만 
단순한 수학계산으로도 풀 수 있음!

1. start와 end 를 무조건 start가 작게끔 세팅
2. start와 end의 속한 층(layer)를 구함
3. 그 층 내부에서의 인덱스를 각각 구함
4. start와 end의 range가 이루는 삼각형의 마지막 라인에 속한다면 층의 차이만큼만 움직이면 됨
5. 삼각형 마지막 라인을 벗어난 인덱스의 경우 해당 인덱스와 그 라인의 range까지의 거리만큼 더 움직임
6. 또한, 층이 같을 경우 예외처리 해줌

*/

public class Sol2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		
		int[] rangestart = new int[144];
		
		
		rangestart[1] = 1;
		
		for(int i=2; i<rangestart.length; i++) {
			rangestart[i] = rangestart[i-1]+i-1;
		}
		//System.out.println(rangestart[142]);
		
		for(int tc=0; tc<testcase; tc++) {
			
			String st[] = br.readLine().split(" ");
			int start = Integer.parseInt(st[0]);
			int end = Integer.parseInt(st[1]);
			
			
			
			if(start==end) {
				System.out.println("#"+(tc+1)+" "+0); continue;
			}
			
			else {
				if (start > end) {
					int temp = start;
					start = end;
					end = temp;
				}
				
				int start_layer=0;
				int end_layer=0;
				
				for(int i=1; i<rangestart.length-1; i++) {
					if(rangestart[i]<=start && rangestart[i+1]>=start) {
						start_layer = i;
					}
				}
				
				for(int i=1; i<rangestart.length-1; i++) {
					if(rangestart[i]<=end && rangestart[i+1]>=end) {
						end_layer = i;
					}
				}
				
				
				
				int start_idx = start - rangestart[start_layer] + 1;
				int end_idx = end - rangestart[end_layer] + 1;
				
				
				int ans = 0;
				int temp = end_layer - start_layer;
				
				if(temp==0) {
					
					ans = Math.abs(start_idx - end_idx);
					
				}
				else {
					if (end_idx >= start_idx && end_idx <= start_idx + (temp)) {
						ans = temp;
					} else {
						if (end_idx < start_idx) {
							ans = start_idx - end_idx + temp;
						} else {
							ans = end_idx - (start_idx + temp) + temp;
						}
					}
				}
				
				
				
				System.out.println("#"+(tc+1)+" "+ans);
				
				
				
			}
		}

	}

}
