import java.util.*;

public class Sol{
    
    
    public static void main(String[] args) {
        String str1 = "e+e+e+";
        String str2 = "E+e+E+";

        str1 = str1.toLowerCase();  //다시 넣어주는게 중요
        str2 = str2.toLowerCase();

        ArrayList<String> li1 = new ArrayList<>();
        ArrayList<String> li2 = new ArrayList<>();
        
        for(int i=0; i<str1.length()-1; i++){
            //97~122
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i+1);
            
            if(c1>=97 && c1<=122 && c2>=97&& c2<=122){
                
                li1.add(str1.substring(i, i+2));
            }
        }

        for(int i=0; i<str2.length()-1; i++){
            //97~122
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i+1);
            
            if(c1>=97 && c1<=122 && c2>=97&& c2<=122){
                
                li2.add(str2.substring(i, i+2));
            }
        }
        
        //각각의 2음절에 숫자를 부여, countarr[][]의 열값에 해당한다
        //행값은 str1 = 0, str2 = 1;
        int countarr[][] = new int[2][str1.length()+str2.length()-1];
        
        int index = 1; //각각의 2음절에 숫자를 부여하는 index, countarr의 열값에 해당
        
        //이미 같은게 있었는지 확인하는 체크배열
        boolean ck1[] = new boolean[li1.size()];
        boolean ck2[] = new boolean[li2.size()];

        int i=0; int j=0;

        //li1에서 하나씩 고르기
        for(i=0; i<li1.size(); i++){
            if(!ck1[i]){
                String temp = li1.get(i);

                for(j=0; j<li1.size(); j++){
                    if(!ck1[j] && temp.equals(li1.get(j))){
                        countarr[0][index]++;
                        ck1[j]=true;
                    }
                }
                for(j=0; j<li2.size(); j++){
                    if(!ck2[j] && temp.equals(li2.get(j))){
                        countarr[1][index]++;
                        ck2[j]=true;
                    }
                }

                index++;
            }

        }
        //li2에서 하나씩 고르기
        for(i=0; i<li2.size(); i++){
            if(!ck2[i]){
                String temp = li2.get(i);

                for(j=0; j<li2.size(); j++){
                    if(!ck2[j] && temp.equals(li2.get(j))){
                        countarr[1][index]++;
                        ck2[j]=true;
                    }
                }

                index++;
            }
        }

        index--;

        double union=0;
        double cross=0;

        for(i=1; i<=index; i++){
            union += Math.max(countarr[0][i], countarr[1][i]);
            cross += Math.min(countarr[0][i], countarr[1][i]);
        }

        if(union==0){System.out.println(65536);}
        else{System.out.println((int)((cross/union)*65536));}

    }
}