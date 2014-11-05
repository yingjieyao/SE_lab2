/*
 * Lab1_version1.0
 * date : 2014.09.22.7:01
 * note : the "RESULT.txt" contains the answer of this problem
 *        the time this code needs is print on the console
 *          
 * name ： 1120310204_lab1_code.java
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	// NumOfAct[i] the i'th activity's number
	// of services private static stor[][] S 
	// new stor[20][555];
	// S[i][j], the i'th activity's j'th services
	private static int[] NumOfAct = new int[20];
	private static int StorMon[][] = new int[20][505];
	private static double StorRea[][] = new double[20][505];
	private static int StorId[][] = new int[20][505];
	
	// the request  money and  reliability
	private static int Tmo[] = new int[6], Tre[] = new int[6];

	// dp[i][j] when come to the i'th activity with the j money ,the max
	// reliability we can get
	private static double dp[][] = new double[20][10011];

	// pre[i][j] when the i'th activity with money j,chose which service
	private static int pre[][] = new int[20][10111];

	// ans[i] means for activity i we chose service ans[i]
	private static int ans[] = new int[20];

	private static int edgeu[] = new int[50];
	private static int edgev[] = new int[50];
	private static int EdgeNumOfAct = 0;
	
	// to judge a node is whether in the query
	private static boolean inGrapgh[] = new boolean[33];
	
	// the pre pos of pos i in grapgh
	private static int pree[] = new int[33];
	
	// the max pos of these node which is in the grapgh
	private static int Maxpos;
	
	private static int Reqcnt;
	// for output the answer to a file "RESULT.txt"
	private static BufferedWriter fout = null;

	public static void Ini() throws IOException {
		EdgeNumOfAct = 0;
		Reqcnt = 0;
		try {
			fout = new BufferedWriter(new FileWriter("RESULT.txt"));
		} catch (IOException e) {
			System.out.println("Error to open RESULT.txt");
			return;
		}
	}

	public static void addedge(int u, int v) {
		edgeu[EdgeNumOfAct] = u;
		edgev[EdgeNumOfAct] = v;
		EdgeNumOfAct++;
	}

	public static void GetAns(int mo, int t) {
		int tt = mo;
		for (int i = t; i >= 1; i = pree[i]) {
			int ptt = pre[i][tt];
			ans[i] = ptt;
			tt = tt - StorMon[i][ptt];
		}
	}

	public static void PrintAns() throws IOException {
		for (int i = 0; i < EdgeNumOfAct; i++) {
			char u = (char) ('A' - 1 + edgeu[i]);
			char v = (char) ('A' - 1 + edgev[i]);
			int tu = ans[edgeu[i]];
			int tv = ans[edgev[i]];
			fout.write("(" +u + "-" + tu + "," + v + "-" + tv + "),");
		}
	}

	public static void Solve(int Num) {
		for (int i = 0; i <= Maxpos; i++) {// ini for dp process
			Arrays.fill(dp[i], 0);
			Arrays.fill(pre[i], -1);
		}
		Arrays.fill(ans, 0);
		for (int i = 1; i <= NumOfAct[1]; i++) {
			dp[1][StorMon[1][i]] = Math.max(dp[1][StorMon[1][i]], StorRea[1][i]);
			pre[1][StorMon[1][i]] = StorId[1][i];
		}
		for (int i = 2; i <= Maxpos; i++) {
			if (inGrapgh[i]) {
				for (int j = 1; j <= NumOfAct[i]; j++) {
					for (int mo = 0; mo <= Tmo[Num]; mo++) {
						if (mo < StorMon[i][j])
							continue;

						if (dp[pree[i]][mo - StorMon[i][j]] != 0) {
							double NewAns = dp[pree[i]][mo - StorMon[i][j]] * StorRea[i][j];
							if (NewAns > dp[i][mo]) {
								pre[i][mo] = StorId[i][j];
								dp[i][mo] = NewAns;

							}
						}
					}
				}
			}
		}
		double AnsQ = 0;
		int AnsMon = -1;
//		System.out.println("maxpos "+Maxpos);
		for (int i = 0; i <= Tmo[Num]; i++) {
			double tmp = dp[Maxpos][i] - i * 1.0 / 10000;
			if (tmp > AnsQ) {
				AnsQ = tmp;
				AnsMon = i;
			}
		}
//		System.out.println(AnsMon+"       "+Maxpos);
		GetAns(AnsMon, Maxpos);
		try {
			PrintAns();
			fout.write("Reliability="
					+ new java.text.DecimalFormat("0.00")
							.format(dp[Maxpos][AnsMon])
					+ ", Cost="
					+ new java.text.DecimalFormat("0.00")
							.format(AnsMon / 100.0) + ", Q="
					+ new java.text.DecimalFormat("0.00").format(AnsQ)
					+ "\r\n");
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

	public static void SelectNoUseService() {
		boolean vis[] = new boolean[500];
		Arrays.fill(vis, false);
		boolean can = false;
		int TmpId[] = new int[500];
		int TmpMon[] = new int[500];
		double TmpRea[] = new double[500];
		int NumOfActt = 0;
		for (int p = 1; p <= 14; p++) {
			NumOfActt = 0;
			for (int i = 1; i <= NumOfAct[p]; i++) {
				can = true;
				for (int j = 1; j <= NumOfAct[p]; j++) {
					if (i == j)
						continue;
					// if there exits a service which is better than itself ,it
					// is no useful
					if (StorMon[p][i] >= StorMon[p][j]
							&& StorRea[p][i] <= StorRea[p][j]) {
						can = false;
						break;
					}
				}
				if (can) {
					TmpId[NumOfActt] = StorId[p][i];
					TmpMon[NumOfActt] = StorMon[p][i];
					TmpRea[NumOfActt++] = StorRea[p][i];
				}
			}
			NumOfAct[p] = NumOfActt;
			// update the process in each activity
			for (int i = 1; i <= NumOfActt; i++) {
				StorMon[p][i] = TmpMon[i - 1];
				StorRea[p][i] = TmpRea[i - 1];
				StorId[p][i] = TmpId[i - 1];
			}
		}
	}

	public static boolean ReadProcessAndSolve() throws IOException {
		BufferedReader fin = null;
		try {
			fin = new BufferedReader(new FileReader("PROCESS.txt"));
		} catch (IOException e) {
			System.out.println("Error to open PROCESS.txt");
			return false;
		}
		int t = 1;
		int tmp=0;
		while (fin.ready()&&tmp<Reqcnt) {
			Arrays.fill(inGrapgh, false);
			tmp++;
			String s = fin.readLine();
			EdgeNumOfAct=0;
			Maxpos = 0;
			int len = s.length();
			for (int i = 0; i < len; i++) {
				if (i % 6 == 1) {
					int t1 = (int) (s.charAt(i) - 'A' + 1);
					int t2 = (int) (s.charAt(i + 2) - 'A' + 1);
//					System.out.println(t1+" !!! "+t2);
					addedge(t1, t2);
					Maxpos = Math.max(t1, Maxpos);
					Maxpos = Math.max(t2, Maxpos);
					inGrapgh[t1] = true;
					inGrapgh[t2] = true;
				}
			}
			Arrays.fill(pree, 0);
			// to get which is the previous node that appeared in the grapgh
			// for the first example A B C D G and pree['G'] = 'D' that is
			for (int i = 1; i <= 26; i++) {
				if (inGrapgh[i]) {
					for (int j = i - 1; j >= 1; j--) {
						if (inGrapgh[j]) {
							pree[i] = j;
							break;
						}
					}
				}
			}
			long time1 = System.currentTimeMillis();
//			System.out.println("T "+t);
			Solve(t);
			long time2 = System.currentTimeMillis();
			// System.out.println(time1 +"  "+ time2+" "+(time2-time1));
			t++;
		}
		fin.close();
		return true;
	}

	public static boolean ReadReq() throws IOException {
		BufferedReader fin = null;
		try {
			fin = new BufferedReader(new FileReader("REQ.txt"));
		} catch (IOException e) {
			System.out.println("Error to open REQ.txt");
			return false;
		}
		int t = 1;
		while (fin.ready()) {
			String s = fin.readLine();
			if(s.charAt(0)!='('){
				fin.close();
				return true;
			};
			Reqcnt++;
			int st=0;
			int len=s.length();
			while(s.charAt(st)!=',')st++;
//			System.out.println("SSSSS  "+s);
			double tre = Double.parseDouble(s.substring(1, st));
			double tmo = Double.parseDouble(s.substring(st+1, len-1));
			Tre[t] = (int) (tre * 100);
			Tmo[t] = (int) (tmo * 100);
			t++;
		}
		fin.close();
		return true;
	}

	public static boolean ReadServices() throws IOException {
		BufferedReader fin = null;
		try {
			fin = new BufferedReader(new FileReader("SERVICE.txt"));
		} catch (IOException e) {
			System.out.println("Error to open SERVICE.txt");
			return false;
		}
		for (int i = 0; i < 15; i++) {
			Arrays.fill(StorMon[i], 0);
			Arrays.fill(StorRea[i], 0);
			Arrays.fill(StorId[i], 0);
		}
		Arrays.fill(NumOfAct, 0);
		while (fin.ready()) {
			String s = fin.readLine();
			String ss[] = s.split(" ");
			int PosOfAct = (int) (ss[0].charAt(0) - 'A' + 1);
			int PosOfSer = Integer.parseInt(ss[0].substring(2));
			NumOfAct[PosOfAct]++;
			double re, mon;
			re = Double.parseDouble(ss[2]);
			mon = Double.parseDouble(ss[4]);
			mon *= 100;
			int Mon = (int) mon;
			StorMon[PosOfAct][PosOfSer] = Mon;
			StorRea[PosOfAct][PosOfSer] = re;
			StorId[PosOfAct][PosOfSer] = PosOfSer;
		}
		fin.close();
		return true;
	}

	// print the Services of every activity,for debug
	public static void PrintServices() {
		for (int i = 1; i <= 14; i++) {
			System.out.println(NumOfAct[i]);
			for (int j = 1; j <= NumOfAct[i]; j++) {
				System.out.println(StorMon[i][j] + " " + StorRea[i][j] + " "
						+ StorId[i][j]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		Ini(); 
		if(!ReadServices())return;
		SelectNoUseService();
		if(!ReadReq())return;
		if(!ReadProcessAndSolve())return;
		fout.close();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
