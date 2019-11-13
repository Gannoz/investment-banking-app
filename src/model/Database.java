package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

	private List<Investor> investors;
	private List<Investor> unmanagedInvestors;
	private List<Investor> managedInvestors;
	private List<Investor> unpaidInvestors;

	private List<Debtor> debtors;
	private List<DebtorRequest> unmanagedDebtorRequests;
	private List<DebtorRequest> managedDebtorRequests;
	private List<Debtor> unpaidDebtors;
	
	private Connection con = null;
	private Statement stt = null;
	private ResultSet rs = null;
	
	private String url 		= "jdbc:mysql://localhost:3306/InvestmentBankingApp?serverTimezone=UTC";
	private String user 	= "root";
	private String password = "testpassword";

	public Database() {

		investors = new LinkedList<Investor>();
		unmanagedInvestors = new LinkedList<Investor>();
		managedInvestors = new LinkedList<Investor>();
		unpaidInvestors = new LinkedList<Investor>();
		debtors = new LinkedList<Debtor>();
		unmanagedDebtorRequests = new LinkedList<DebtorRequest>();
		managedDebtorRequests = new LinkedList<DebtorRequest>();
		unpaidDebtors = new LinkedList<Debtor>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			// Create and Select DB
			stt.execute("CREATE DATABASE IF NOT EXISTS InvestmentBankingApp");
			stt.execute("USE InvestmentBankingApp");
			
			// CREATE TABLES
			stt.execute("DROP TABLE IF EXISTS investors");
			stt.execute("CREATE TABLE IF NOT EXISTS investors("
					+ "id BIGINT AUTO_INCREMENT,"
					+ "nik BIGINT,"
					+ "name VARCHAR(50),"
					+ "gender ENUM('M', 'F'),"
					+ "address VARCHAR(50),"
					+ "rtrw VARCHAR(50),"
					+ "village VARCHAR(50),"
					+ "district VARCHAR(50),"
					+ "religion VARCHAR(20),"
					+ "marriageStatus VARCHAR(20),"
					+ "occupation VARCHAR(20),"
					+ "nationality VARCHAR(20),"
					+ "amountInvested BIGINT,"
					+ "managed BOOLEAN,"	
					+ "paid BOOLEAN,"
					+ "timeCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "timeManaged TIMESTAMP,"
					+ "PRIMARY KEY(id),"
					+ "UNIQUE(nik)"
					+ ")");
			
//			stt.execute("DROP TABLE IF EXISTS debtorFees");
//			stt.execute("DROP TABLE IF EXISTS debtorManaged");
//			stt.execute("DROP TABLE IF EXISTS debtorRequests");
//			stt.execute("DROP TABLE IF EXISTS debtors");
			
			stt.execute("CREATE TABLE IF NOT EXISTS debtors("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "nik VARCHAR(25),"
					+ "name VARCHAR(50),"
					+ "gender ENUM('M', 'F'),"
					+ "address VARCHAR(50),"
					+ "rtrw VARCHAR(50),"
					+ "village VARCHAR(50),"
					+ "district VARCHAR(50),"
					+ "religion VARCHAR(20),"
					+ "marriageStatus VARCHAR(20),"
					+ "occupation VARCHAR(20),"
					+ "nationality VARCHAR(20),"
					+ "PRIMARY KEY(id),"
					+ "UNIQUE(nik)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS debtorRequests("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "debtorId BIGINT,"
					+ "amtRequested BIGINT,"
					+ "managed BOOLEAN DEFAULT false,"
					+ "timeCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (id),"
					+ "FOREIGN KEY (debtorId) REFERENCES debtors(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS debtorManaged("
					+ "requestId BIGINT NOT NULL,"
					+ "fulfilled BOOLEAN DEFAULT FALSE,"
					+ "timeManaged TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (requestId),"
					+ "FOREIGN KEY (requestId) REFERENCES debtorRequests(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS debtorFees("
					+ "requestId BIGINT NOT NULL,"
					+ "lastPaid TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "feePercent FLOAT,"
					+ "feePending FLOAT,"
					+ "PRIMARY KEY (requestId),"
					+ "FOREIGN KEY (requestId) REFERENCES debtorRequests(id)"
					+ ")");
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		updateLists();

	}
	
	// SQL
	
	private void close() {
		try { rs.close(); } catch(Exception e) { };
		try { stt.close(); } catch(Exception e) { };
		try { con.close(); } catch(Exception e) { };
	}
	
	private void updateLists() {
		
		// DEBTORS
		debtors.clear();
		
		try {
				
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url, user, password);
				
				stt = con.createStatement();
				
				rs = stt.executeQuery("SELECT * FROM debtors");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String nik = rs.getString("nik");
					String name = rs.getString("name");
					Gender gender = null;
					String address = rs.getString("address");
					String rtrw = rs.getString("rtrw");
					String village = rs.getString("village");
					String district = rs.getString("district");
					String religion = rs.getString("religion");
					String marriageStatus = rs.getString("marriageStatus");
					String occupation = rs.getString("occupation");
					String nationality = rs.getString("nationality");
	 
					String genderCat = rs.getString("gender");
						if (genderCat.equals("M")) {
							gender = Gender.M;
						}else if(genderCat.equals("F")) {
							gender = Gender.F;
						}
						
					Debtor debtor = new Debtor(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);
						
					debtors.add(debtor);	
				}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		// UNMANAGED DEBTORS
		unmanagedDebtorRequests.clear();
		
		try {
				
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			rs = stt.executeQuery("SELECT * FROM debtorRequests WHERE managed = false");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int debtorId = rs.getInt("debtorId");
				long amtRequested = rs.getLong("amtRequested");
				boolean managed = rs.getBoolean("managed");
				Date timeCreated = rs.getDate("timeCreated");
					
				DebtorRequest request = new DebtorRequest(id, debtorId, amtRequested, managed, timeCreated);
				
				unmanagedDebtorRequests.add(request);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();	
		}
		
		// MANAGED DEBTORS
		managedDebtorRequests.clear();
		
		try {
				
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			rs = stt.executeQuery("SELECT * FROM debtorRequests WHERE managed = true");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int debtorId = rs.getInt("debtorId");
				long amtRequested = rs.getLong("amtRequested");
				boolean managed = rs.getBoolean("managed");
				Date timeCreated = rs.getDate("timeCreated");
					
				DebtorRequest request = new DebtorRequest(id, debtorId, amtRequested, managed, timeCreated);
				
				managedDebtorRequests.add(request);
				
			rs = stt.executeQuery("SELECT * FROM debtorManaged WHERE fulfilled = false");
			
			while(rs.next()) {
				int requestId = rs.getInt("requestId");
				Date timeManaged = rs.getDate("timeManaged");
				
				for(DebtorRequest managedRequest : managedDebtorRequests) {
					if(managedRequest.getId() == requestId) {
						managedRequest.setTimeManaged(timeManaged);
					}
				}
				
			}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();	
		}
		
	}

	// INVESTOR

	public List<Investor> getInvestors() {

		return Collections.unmodifiableList(investors);
	}

	public List<Investor> getUnmanagedInvestors() {

		return Collections.unmodifiableList(unmanagedInvestors);
	}

	public List<Investor> getManagedInvestors() {

		return Collections.unmodifiableList(managedInvestors);
	}

	public List<Investor> getUnpaidInvestors() {
		return Collections.unmodifiableList(unpaidInvestors);
	}

	public Investor getInvestor(int id) {

		Investor selectedInvestor = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				selectedInvestor = investor;
			}
		}

		return selectedInvestor;
	}

	public void addInvestor(Investor investor) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String values = String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'", investor.getNik(), investor.getName(), investor.getGender(), investor.getAddress(), investor.getRtrw(), investor.getVillage(), investor.getDistrict(), investor.getReligion(), investor.getMarriageStatus(), investor.getOccupation(), investor.getNationality());
			
			stt.execute("INSERT INTO debtors(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality) VALUES("
					+ values
					+ ")");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		investor.setTimeCreated(LocalDateTime.now().format(dtf));

		investors.add(investor);
		unmanagedInvestors.add(investor);
	}

	public void editInvestor(int id, Investor newInvestor) {

		Investor investorEdited = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investorEdited = investor;
			}
		}
		int index = investors.indexOf(investorEdited);
		investors.remove(investorEdited);
		investors.add(index, newInvestor);

		try {
			index = unmanagedInvestors.indexOf(investorEdited);
			unmanagedInvestors.remove(investorEdited);
			unmanagedInvestors.add(index, newInvestor);
		} catch (Exception e) {

		}

		try {
			index = managedInvestors.indexOf(investorEdited);
			managedInvestors.remove(investorEdited);
			managedInvestors.add(index, newInvestor);
		} catch (Exception e) {

		}
	}

	public void manageInvestor(int id) {

		Investor investorManaged = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setManaged(true);
				investorManaged = investor;
			}
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		investorManaged.setTimeManaged(LocalDateTime.now().format(dtf));

		unmanagedInvestors.remove(investorManaged);
		managedInvestors.add(investorManaged);
		unpaidInvestors.add(investorManaged);
	}

	public void unmanageInvestor(int id) {

		Investor investorUnmanaged = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setManaged(false);
				investorUnmanaged = investor;
			}
		}

		unmanagedInvestors.add(investorUnmanaged);
		managedInvestors.remove(investorUnmanaged);
		unpaidInvestors.remove(investorUnmanaged);
	}

	public void paidInvestor(int id) {

		Investor unpaidInvestor = null;

		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investor.setPaid(true);
				unpaidInvestor = investor;
			}
		}

		unpaidInvestors.remove(unpaidInvestor);
	}

	public void removeInvestor(int id) {

		Investor investorRemoved = null;
		for (Investor investor : investors) {
			if (investor.getId() == id) {
				investorRemoved = investor;
			}
		}
		investors.remove(investorRemoved);
		
		try {
			unmanagedInvestors.remove(investorRemoved);
		} catch (Exception e) {

		}

		try {
			managedInvestors.remove(investorRemoved);
		} catch (Exception e) {

		}
	}

	// DEBTOR

	public List<Debtor> getDebtors() {
					
	return Collections.unmodifiableList(debtors);

	}

	public List<DebtorRequest> getUnmanagedDebtors() {
		
		return Collections.unmodifiableList(unmanagedDebtorRequests);
	}

	public List<DebtorRequest> getManagedDebtors() {
		

		return Collections.unmodifiableList(managedDebtorRequests);
	}

	public List<Debtor> getUnpaidDebtors() {
		return Collections.unmodifiableList(unpaidDebtors);
	}

	public Debtor getDebtor(int id) {
		
		Debtor selectedDebtor = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String query = String.format("SELECT * FROM debtors WHERE id=%d", id);
			
			rs = stt.executeQuery(query);
			
			while(rs.next()) {
				String nik = rs.getString("nik");
				String name = rs.getString("name");
				Gender gender = null;
				String address = rs.getString("address");
				String rtrw = rs.getString("rtrw");
				String village = rs.getString("village");
				String district = rs.getString("district");
				String religion = rs.getString("religion");
				String marriageStatus = rs.getString("marriageStatus");
				String occupation = rs.getString("occupation");
				String nationality = rs.getString("nationality");
 
				String genderCat = rs.getString("gender");
					if (genderCat.equals("M")) {
						gender = Gender.M;
					}else if(genderCat.equals("F")) {
						gender = Gender.F;
					}
					
				selectedDebtor = new Debtor(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);	
			}
			
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close();
	}
		
		return selectedDebtor;
	}

	public void addDebtor(Debtor debtor) {
		
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		con = DriverManager.getConnection(url, user, password);
		
		stt = con.createStatement();
		
		String values = String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'", debtor.getNik(), debtor.getName(), debtor.getGender(), debtor.getAddress(), debtor.getRtrw(), debtor.getVillage(), debtor.getDistrict(), debtor.getReligion(), debtor.getMarriageStatus(), debtor.getOccupation(), debtor.getNationality());
		
		stt.execute("INSERT INTO debtors(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality) VALUES("
				+ values
				+ ")");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		updateLists();
		
	}
	
	public void addDebtorRequest(int debtorId, int amountRequested) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String values = String.format("%d, %d", debtorId, amountRequested);
			
			stt.execute("INSERT INTO debtorRequests (debtorId, amtRequested) VALUES("
					+ values
					+ ")");
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		updateLists();
		
		
	}

	public void editDebtor(int id, Debtor newDebtor) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String update = String.format("SET nik='%s', name='%s', gender='%s', address='%s', rtrw='%s', village='%s', district='%s', religion='%s', marriageStatus='%s', occupation='%s', nationality='%s' WHERE id=%d", newDebtor.getNik(), newDebtor.getName(), newDebtor.getGender(), newDebtor.getAddress(), newDebtor.getRtrw(), newDebtor.getVillage(), newDebtor.getDistrict(), newDebtor.getReligion(), newDebtor.getMarriageStatus(), newDebtor.getOccupation(), newDebtor.getNationality(), id);
			
			stt.execute("UPDATE debtors "
					+ update
					+ "");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateLists();

	}

	public void manageDebtor(int id) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String update = String.format("SET managed = %b WHERE id = %d", true, id);
			
			stt.execute("UPDATE debtorRequests "
					+ update
					+ "");
			
			String value = String.format("%d", id);
			
			stt.execute("INSERT INTO debtorManaged(requestId) VALUES("
					+ value
					+ ")");
			
			stt.execute("SELECT amtRequested");
		
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateLists();
	}

	public void unmanageDebtor(int id) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String update = String.format("SET managed = %b WHERE id = %d", false, id);
			
			stt.execute("UPDATE debtorRequests "
					+ update
					+ "");
			
			String value = String.format("WHERE requestId=%d", id);
			
			stt.execute("DELETE FROM debtorManaged "
					+ value
					+ "");
		
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateLists();

	}

	public void paidDebtor(int id) {

		Debtor unpaidDebtor = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtor.setPaid(true);
				unpaidDebtor = debtor;
			}
		}

		unpaidDebtors.remove(unpaidDebtor);
	}

	public void removeDebtor(int id) {
		
		try {	
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url, user, password);
				
				stt = con.createStatement();
				
				String query = String.format("DELETE FROM debtors WHERE id = %d;", id);
				
				stt.execute(query);
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
						
		
		
		
		Debtor debtorRemoved = null;
		for (Debtor debtor : debtors) {
			if (debtor.getId() == id) {
				debtorRemoved = debtor;
			}
		}

		debtors.remove(debtorRemoved);

//		try {
//			unmanagedRequests.remove(debtorRemoved);
//		} catch (Exception e) {
//
//		}
//
//		try {
//			managedRequests.remove(debtorRemoved);
//		} catch (Exception e) {
//
//		}
	}
}