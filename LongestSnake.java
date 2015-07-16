import java.io.*;
import java.util.*;
public class LongestSnake {
	public static int n[];
	public static int len;
	public static List<Integer> answer;
	public static int longest = 0;
	public static int globalscore = 0; //keeps track of how many digits match
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String num[] = input.split("\\s+");
		len = num.length;
		n=new int[len];
		for(int i =0;i<len;i++){n[i]=Integer.parseInt(num[i]);}
		List<Integer> a = new ArrayList<Integer>();
		int gs = 0;//---------------------------------------
		boolean inString[] = new boolean[len];
		fms(a,inString,-1,0,gs);//--
		String ans = "";
		for(Integer i:answer){
			ans+=i+" ";
		}
		System.out.println(ans.trim());
	}
	public static void fms(List<Integer> a,boolean b[],int lia,int score,int gs){//lia = last index added
		for(int i=0;i<len;i++){
			if(b[i])continue;
			List<Integer> anew = new ArrayList<Integer>(a);
		    boolean[] bnew = new boolean[len];
		    System.arraycopy(b, 0, bnew,0,len);
		    int index = 0;
		    int gsnew = gs;////------------------------------;
		    int scorenew = score;
			if(!a.isEmpty()){
				int last = a.get(a.size()-1);
				if(Math.abs(last-n[i])==1){
					index = i;
				}
				else continue;
			}
			else if(a.isEmpty()){
				index = i;
			}
			anew.add(n[index]);
			bnew[index]=true;
			if(lia!=-1 && index>lia) {scorenew+=len-(index-lia);}
			else if(lia!=-1 && index<lia){scorenew-=(lia-index);}//-----------
			else if(lia==-1){scorenew = 0;}
			gsnew += scorenew;
			if(anew.size()>longest){
				longest = anew.size();
				answer = anew;
				globalscore = gsnew;
				//System.out.println(answer.toString());
				//System.out.println("best cumu so far:"+ bestcumulative.toString());
			}
			else if(anew.size()==longest && globalscore < gsnew){//---------
				longest = anew.size();
				answer = anew;
				globalscore = gsnew;
				//System.out.println(answer.toString());	
			}//----------------
			fms(anew,bnew,index,scorenew,gsnew);//------------------
		}
	}
}
