package no14890;

/*
 * -> , 어래 방향 으로 2N을 탐색, 라인의 검색을 할때는 같은지 검사 + 처음으로 나오는 차이가 양수면 오르막을
 * 음수면 내리막을 구하는 함수로 바꿔준다
 * now와 next변수를 두고
 * 차이가 2이상 나면 break;
 * 1나면 now+1인덱스부터 L을 검사 불가능할 경우 break
 * 경사로를 놓을 수 있다면 해당 경사로가 끝나는 지점을 now로 그다음을 넥스트로 다시 검사 시작
 * 라인의 검사를 모두 마치면 possibleWay++
 * 
 * 
 * 
 * 차이가 2 이상일 경우 x
 * 차이가 1 일때
 *  오르막일 경우 L을 고려해 범위를 넘는지 안넘는지 확인 하고 놓는다 하지만 이때 boolean배열에 사다리를 놓을 수 있는지 확인, 놓았다면 boolean에 체크
 *  내리막일 경우 L을 고려해 범위를 넘지 안넘는지 확인 이때도 확인, 놓았다면 불린 체크
 * 
 * 
 * 
 * 
 */

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main {

	static int N;
	static int L;
	static int[][] map;
	static int possibleWay = 0;
	static final int RowDirection = 0;
	static final int ColDirection = 1;
	static boolean way[][];
	
	static void findway(int direction) {

		//가로 방향
		if (direction == RowDirection) {

			for (int r = 0; r < N; r++) {

				int now, next;
				boolean flag = true;
				int c = 0;
				
				while (c < N - 1) {
					now = map[r][c];
					next = map[r][c + 1];
					
					//현재와 다음것이 같으면
					if (now == next) {
						c++;
						continue;
					} else { //같지 않으면
						int t = Math.abs(now - next);

						if (t > 1) { //차이가 1 이상일 경우 불가능
							flag = false;
							break;
						} else {

							if (now > next) { // 내리막의 경우

								if (c + L >= N) { //내리막을 둘 수 없으면 불가능
									flag = false;
									break;
								}

								//now다음 부터 L만큼 확인한다 이때, 내리막의 경우 경사로를 바로 둘 수 있다
								c++;
								now = map[r][c];
								way[r][c] = true;
								for (int idx = 0; idx < L - 1; idx++) { 
									next = map[r][c + 1];
									way[r][c + 1] = true; //두었다면 불린 체크

									if (now == next) { //경사로의 범위에 있는 것들의 나우와 넥스트가 같으면
										now = next;
										c++;
										continue; //계속 진행
									} else {
										flag = false;
										break;// 경사로를 둘 수 없음
									}
								}

							} else {// 오르막의 경우

								if ((c - (L - 1)) < 0) { //경사로를 둘 수 있는 범위인지 확인
									flag = false;
									break;
								}
								boolean flag2 = true;
								for (int i = c - (L - 1); i <= c; i++) { 
									if (!way[r][i]) { //경사로를 두는 자리에 경사로가 없을경우
										way[r][i] = true; //둔다
									} else {//있는경우 불가능
										flag2 = false;
										break;
									}
								}
								if (flag2) {
									c++;
									continue;
								} else {
									flag = false;
									break;
								}
							}

						}

					}

					if (!flag) {
						break;
					}
				}

				if (flag) {// 더해주기}
					possibleWay++;
				}

			}
		} 
		 //세로방향
		 else {
			for (int c = 0; c < N; c++) {
				int now, next;
				boolean flag = true;

				int r = 0;
				while (r < N - 1) {
					now = map[r][c];
					next = map[r + 1][c];

					if (now == next) {
						r++;
						continue;
					} else {
						int t = Math.abs(now - next);

						if (t > 1) {
							flag = false;
							break;
						} else {

							if (now > next) { // 내리막의 경우

								if (r + L >= N) {
									flag = false;
									break;
								}

								r++;
								now = map[r][c];
								way[r][c] = true;
								for (int idx = 0; idx < L - 1; idx++) {
									next = map[r + 1][c];
									way[r + 1][c] = true;

									if (now == next) {
										now = next;
										r++;
										continue;
									} else {
										flag = false;
										break;// 경사로를 둘 수 없음
									}
								}

							} else {// 오르막의 경우

								if ((r - (L - 1)) < 0) {
									flag = false;
									break;
								}
								boolean flag2 = true;
								for (int i = r - (L - 1); i <= r; i++) {
									if (!way[i][c]) {
										way[i][c] = true;
									} else {
										flag2 = false;
										break;
									}
								}
								if (flag2) {
									r++;
									continue;
								} else {
									flag = false;
									break;
								}
							}

						}

					}

					if (!flag) {
						break;
					}
				}

				if (flag) {// 더해주기}
					possibleWay++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		way = new boolean[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		findway(RowDirection);
		way = new boolean[N][N];
		findway(ColDirection);
		System.out.println(possibleWay);
		
	}

}
