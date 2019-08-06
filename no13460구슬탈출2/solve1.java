package no13460����Ż��2;
import java.io.*;
import java.util.*;


//BFS�� Ǯ�� , �ڵ尡 ��ȿ�����ε� .. ��
//����ó���� �� ���־�� �ϴ� ����!
//������ Ǯ������ ���쿡 � ��Ȳ�� �������� case���� �� �����ϰ� �ڵ带 ¥���ҵ��ϴ�


// ��ġ�� �̵�Ƚ���� ������ Ŭ���� Loc
class Loc{
	int row, col , count;
	Loc(int r, int c, int co){
		this.row = r; this.col = c; this.count = co;
	}
}

public class solve1 {

	static int row, col; //N,M
	static char map[][]; //����
	static Queue<Loc> rQ; //���������� Q
	static Queue<Loc> bQ; //�Ķ������� Q

	
	static void bfs() {
		//������ 'O'�� ���� ��Ÿ���� ����
		boolean rflag=false, bflag=false;
		
		while(!rQ.isEmpty()) {
			
			Loc rtemp = rQ.poll();
			Loc btemp = bQ.poll();

			int rrow = rtemp.row;	// Red�� ��
			int rcol = rtemp.col;	// Red ��
			int brow = btemp.row;	// Blue ��
			int bcol = btemp.col; 	// Blue ��
			int bco = btemp.count;	// �̵�Ƚ��
			int rco = rtemp.count;
			
			
			int nextrr, nextrc, nextbr, nextbc; //Red Blue�� ���� ��ǥ Row(r),Col(c)
			boolean temprflag, tempbflag; // temprflag = true -> red �� �̵���ο� blue�� �ִ� 
			

			//�Ʒ�����
			nextrr = rrow; nextrc = rcol; nextbr = brow; nextbc = bcol;
			temprflag = false; tempbflag = false;
			rflag = false; bflag = false;
			
			//�̵������� ���κ� ã��, 'O'�� �ִ��� ã��(rflag), �̵������ R�Ǵ� B�� �ִ��� ã�� (temprflag)
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
			//�̵������� ���κ�ã�� //// ���⼭ next������ Red,Blue ������ �̵���� ���� �ִ�  '#'��ǥ��
			
			//���� �̵�Ƚ���� 10 �̸��̸� ����
			if(rco+1<10) {

				//Case1 : �̵������ Blue��  ������, Blue�� 'O'�� ������ �ʾҴ�
				if(temprflag && !tempbflag && !bflag) {

					// �̵��Ϸ��� ���� ������ ��ġ�� ������ Q�� ���� �ʴ´�
					if(nextrr-2==rrow && nextrc == rcol && nextbr-1 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-2,nextrc, rco+1)); // Red�տ� Blue�� �����Ƿ�  #BR ���� �Ǿ���
						bQ.add(new Loc(nextbr-1,nextbc, bco+1));
					}
				}

				//Case2 : �̵������ Red�� ������, Blue�� 'O'�� ������ �ʾҴ�
				else if(!temprflag && tempbflag && !bflag) {

					// �̵��Ϸ��� ���� ������ ��ġ�� ������ Q�� ���� �ʴ´�
					if(nextrr-1==rrow && nextrc == rcol && nextbr-2 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-1,nextrc, rco+1)); 
						bQ.add(new Loc(nextbr-2, nextrc, bco +1)); //Blue�տ� Red�� �����Ƿ� #RB���� �Ǿ���
					}
				}

				//Case3 : �̵������ RorB ����͵� ���� ������, Blue�� 'O'�� ������ �ʾҴ�
				else if(!temprflag && !tempbflag && !bflag) {

					// �̵��Ϸ��� ���� ������ ��ġ�� ������ Q�� ���� �ʴ´�
					if(nextrr-1==rrow && nextrc == rcol && nextbr-1 == brow && nextbc == bcol) {}

					else {
						rQ.add(new Loc(nextrr-1, nextrc, rco + 1)); //Red, Blue���� �տ� ���θ��� �ٸ� ������ �����Ƿ� #R, #B���� ��
						bQ.add(new Loc(nextbr-1, nextbc, bco+1));
					}
				}

			}

			//�̵��� ��ġ�� Red��  'O'�� Ż���ϰ� Blue�� Ż���� ��������� �츮�� ���ϴ� ����̹Ƿ� ����ϰ� �޼ҵ� ����
			if(rflag && !bflag) {System.out.println(rco+1); return;}


			// ������ ���⿡ ���ؼ� �����ϰ� ����, �� �̵����⿡ ���� ��ǥ�� ��ȭ�� ����
			
			//��������
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

			
			//������
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
	
			
			
			//���ʹ���
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
