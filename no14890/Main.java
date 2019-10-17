package no14890;

/*
 * -> , � ���� ���� 2N�� Ž��, ������ �˻��� �Ҷ��� ������ �˻� + ó������ ������ ���̰� ����� ��������
 * ������ �������� ���ϴ� �Լ��� �ٲ��ش�
 * now�� next������ �ΰ�
 * ���̰� 2�̻� ���� break;
 * 1���� now+1�ε������� L�� �˻� �Ұ����� ��� break
 * ���θ� ���� �� �ִٸ� �ش� ���ΰ� ������ ������ now�� �״����� �ؽ�Ʈ�� �ٽ� �˻� ����
 * ������ �˻縦 ��� ��ġ�� possibleWay++
 * 
 * 
 * 
 * ���̰� 2 �̻��� ��� x
 * ���̰� 1 �϶�
 *  �������� ��� L�� ����� ������ �Ѵ��� �ȳѴ��� Ȯ�� �ϰ� ���´� ������ �̶� boolean�迭�� ��ٸ��� ���� �� �ִ��� Ȯ��, ���Ҵٸ� boolean�� üũ
 *  �������� ��� L�� ����� ������ ���� �ȳѴ��� Ȯ�� �̶��� Ȯ��, ���Ҵٸ� �Ҹ� üũ
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

		//���� ����
		if (direction == RowDirection) {

			for (int r = 0; r < N; r++) {

				int now, next;
				boolean flag = true;
				int c = 0;
				
				while (c < N - 1) {
					now = map[r][c];
					next = map[r][c + 1];
					
					//����� �������� ������
					if (now == next) {
						c++;
						continue;
					} else { //���� ������
						int t = Math.abs(now - next);

						if (t > 1) { //���̰� 1 �̻��� ��� �Ұ���
							flag = false;
							break;
						} else {

							if (now > next) { // �������� ���

								if (c + L >= N) { //�������� �� �� ������ �Ұ���
									flag = false;
									break;
								}

								//now���� ���� L��ŭ Ȯ���Ѵ� �̶�, �������� ��� ���θ� �ٷ� �� �� �ִ�
								c++;
								now = map[r][c];
								way[r][c] = true;
								for (int idx = 0; idx < L - 1; idx++) { 
									next = map[r][c + 1];
									way[r][c + 1] = true; //�ξ��ٸ� �Ҹ� üũ

									if (now == next) { //������ ������ �ִ� �͵��� ����� �ؽ�Ʈ�� ������
										now = next;
										c++;
										continue; //��� ����
									} else {
										flag = false;
										break;// ���θ� �� �� ����
									}
								}

							} else {// �������� ���

								if ((c - (L - 1)) < 0) { //���θ� �� �� �ִ� �������� Ȯ��
									flag = false;
									break;
								}
								boolean flag2 = true;
								for (int i = c - (L - 1); i <= c; i++) { 
									if (!way[r][i]) { //���θ� �δ� �ڸ��� ���ΰ� �������
										way[r][i] = true; //�д�
									} else {//�ִ°�� �Ұ���
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

				if (flag) {// �����ֱ�}
					possibleWay++;
				}

			}
		} 
		 //���ι���
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

							if (now > next) { // �������� ���

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
										break;// ���θ� �� �� ����
									}
								}

							} else {// �������� ���

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

				if (flag) {// �����ֱ�}
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
