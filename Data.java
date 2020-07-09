package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data
{
			public static final String DB_NAME = "zscaler.sqlite";
			public static final String CONNECTION_STRING ="jdbc:sqlite:D:\\Softwares\\Databases\\"+ DB_NAME;
			public static final String TABLE_RECORD = "qwerty";
			private Connection conn;
			public boolean open()
			{
				try {
					conn = DriverManager.getConnection(CONNECTION_STRING);
					return true;
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
					return false;
				}	
			}
			public void close()
			{
				try
				{
					if(conn!= null)
					{
						conn.close();
					}
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			public List<Record> queryRecords(String b, String c)
			{
				StringBuilder sb = new StringBuilder("SELECT * FROM ");
				sb.append(TABLE_RECORD);
				sb.append(" WHERE ");
				sb.append(c);
				sb.append(" LIKE \"");
				String a= "%"+b;
				sb.append(a);
				sb.append("%\"");
				System.out.println(sb.toString());
				try(Statement statement = conn.createStatement();
					ResultSet results = statement.executeQuery(sb.toString()))
				{
					List<Record> records = new ArrayList<>();
					while(results.next())
					{
						Record record = new Record();
						record.setNo(results.getString("No."));
						record.setET(results.getString("Event Time"));
						record.setU(results.getString("User"));
						record.setURL(results.getString("URL"));
						record.setPA(results.getString("Policy Action"));
						record.setCAC(results.getString("Cloud Application Class"));
						record.setCA(results.getString("Cloud Application"));
						record.setURLC(results.getString("URL Class"));
						record.setURLSC(results.getString("URL Super Category"));
						record.setURLCa(results.getString("URLCategory"));
						record.setTSC(results.getString("Threat Super Category"));
						record.setTC(results.getString("Threat Category"));
						record.setMD5(results.getString("MD5"));
						record.setTN(results.getString("ThreatName"));
						record.setSC(results.getString("Suspicious Content"));
						record.setL(results.getString("Location"));
						record.setD(results.getString("Department"));
						record.setCI(results.getString("Client IP"));
						record.setCEI(results.getString("Client External IP"));
						record.setURLCM(results.getString("URL Categorization Method"));
						record.setFN(results.getString("File Name"));
						record.setPT(results.getString("Protocol Type"));
						record.setPTy(results.getString("Policy Type"));
						record.setCSR(results.getString("Client Session Reused"));
						record.setCCV(results.getString("Certificate Chain Validity"));
						record.setSSR(results.getString("Server Session Reused"));
						record.setSCVT(results.getString("Server Certificate Validation Type"));
						record.setSCVP(results.getString("Server Certificate Validity Period"));
						record.setSWC(results.getString("Server Wildcard Certificate"));
						record.setSCSS(results.getString("Server Certificate Self Signed"));
						record.setDLPMD5(results.getString("DLP MD5"));
						record.setDLPI(results.getString("DLP Identifier"));
						record.setDN(results.getString("Device Name"));
						record.setDo(results.getString("Device ostype"));
						record.setDov(results.getString("Device osversion"));
						record.setDM(results.getString("Device Model"));
						record.setDO(results.getString("Device Owner"));
						record.setDH(results.getString("Device Hostname"));
						record.setALPNP(results.getString("ALPN Protocol"));
						record.setUT(results.getString("Unscannable Type"));
						record.setFR(results.getString("Forwarding Rule"));
						record.setFM(results.getString("Forwarding Method"));
						record.setAS(results.getString("Application Segment"));
						record.setGIP(results.getString("Gateway IP"));
						record.setGN(results.getString("Gateway Name"));
						record.setGN(results.getString("URL String"));
						
						records.add(record);
					}
					return records;
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
					return null;
				}
				
			}
			
			}