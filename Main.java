package data;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter; 
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException 
	{
		String [] text = {"ransom","TrickBot","Wannacry","trojan","botnet","crypto","CobaltStrike","WastedLocker","Coin"};
		String [] col = {"ThreatName", "URLCategory"};
		Data d = new Data();
		Vdata v = new Vdata();
		Ldata l = new Ldata();
		Udata u = new Udata();
		if(!d.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		if(!v.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		if(!l.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		if(!u.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		int i,L;
		L= text.length;
		FileWriter fw=new FileWriter("Mail.csv");
		fw.write("User"+ '\n');
		for(i=0;i<L;i++)
		{
			List<Record> record = d.queryRecords(text[i], "ThreatName");
			if( record== null)
			{
				System.out.println("No records");
				return;
			}
			
			for(Record records : record)
			{
				fw.write(records.getU()+ '\n');
			}
		}
		List<Record> record = d.queryRecords("botnet", "URLCategory");
		if( record== null)
		{
			System.out.println("No records");
			return;
		}
		for(Record records : record)
		{
			fw.write(records.getU()+ '\n');
		}
		
		
		
		
		List<Virus> virus = v.queryVirus();
		if( virus== null)
		{
			System.out.println("No virus");
			return;
		}
		List<String> V = new ArrayList<>();
		for(Virus viru : virus)
		{
			V.add(viru.getV());
		}
		int j = V.size();
		int k;
		String q[]= new String[j];
		for(k=0;k<j;k++)
		{
			q[k]= V.get(k).replaceAll("\\r\\n|\\r|\\n", " ");
			q[k]=q[k].trim();
		}
		for(k=0;k<j;k++)
		{
		List<Record> rec = d.queryRecords(q[k], "URL");
		if( rec== null)
		{
			System.out.println("No records");
			return;
		}
		
		for(Record recor : rec)
		{
			fw.write(recor.getU()+ '\n');
		}
		}
		
		
		
		List<Loca> loc = l.queryLoc();
		if( loc== null)
		{
			System.out.println("No location");
			return;
		}
		List<String> LO = new ArrayList<>();
		for(Loca lo : loc)
		{
			LO.add(lo.getL());
		}
		int y = LO.size();
		int p;
		String f[]= new String[y];
		for(p=0;p<y;p++)
		{
			f[p]= LO.get(p).replaceAll("\\r\\n|\\r|\\n", " ");
			f[p]=f[p].trim();
		}
		for(p=0;p<y;p++)
		{
		List<Record> rec = d.queryRecords(f[p], "Location");
		if( rec== null)
		{
			System.out.println("No records");
			return;
		}
		
		for(Record recor : rec)
		{
			fw.write(recor.getU()+ '\n');
		}
		}
		
		
		List<User> use = u.queryUser();
		if( use== null)
		{
			System.out.println("No User");
			return;
		}
		List<String> U = new ArrayList<>();
		for(User us : use)
		{
			U.add(us.getUs());
		}
		int g = U.size();
		int h;
		String x[]= new String[g];
		for(h=0;h<g;h++)
		{
			x[h]= U.get(h).replaceAll("\\r\\n|\\r|\\n", " ");
			x[h]=x[h].trim();
		}
		for(h=0;h<g;h++)
		{
		List<Record> rec = d.queryRecords(x[h], "User");
		if( rec== null)
		{
			System.out.println("No records");
			return;
		}
		
		for(Record recor : rec)
		{
			fw.write(recor.getU()+ '\n');
		}
		}
		
		
		fw.close();
		d.close();
		v.close();
		l.close();
		u.close();
	}
}