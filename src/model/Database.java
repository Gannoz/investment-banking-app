package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
	private List<DebtorFee> unpaidDebtors;
	
	private Connection con = null;
	private Statement stt = null;
	private ResultSet rs = null;
	private String query;
	
	private String url 		= "jdbc:mysql://localhost:3306/InvestmentBankingApp?serverTimezone=UTC";
	private String user 	= "root";
	private String password = "testpassword";
	
	private Earnings earningsObject;

	public Database() {
		
		earningsObject = new Earnings();

		investors = new LinkedList<Investor>();
		unmanagedInvestors = new LinkedList<Investor>();
		managedInvestors = new LinkedList<Investor>();
		unpaidInvestors = new LinkedList<Investor>();
		debtors = new LinkedList<Debtor>();
		unmanagedDebtorRequests = new LinkedList<DebtorRequest>();
		managedDebtorRequests = new LinkedList<DebtorRequest>();
		unpaidDebtors = new LinkedList<DebtorFee>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			// Create and Select DB
			stt.execute("CREATE DATABASE IF NOT EXISTS InvestmentBankingApp");
			stt.execute("USE InvestmentBankingApp");
			
			// CREATE TABLES
//			stt.execute("DROP TABLE IF EXISTS investors");
//			stt.execute("CREATE TABLE IF NOT EXISTS investors("
//					+ "id BIGINT AUTO_INCREMENT,"
//					+ "nik BIGINT,"
//					+ "name VARCHAR(50),"
//					+ "gender ENUM('M', 'F'),"
//					+ "address VARCHAR(50),"
//					+ "rtrw VARCHAR(50),"
//					+ "village VARCHAR(50),"
//					+ "district VARCHAR(50),"
//					+ "religion VARCHAR(20),"
//					+ "marriageStatus VARCHAR(20),"
//					+ "occupation VARCHAR(20),"
//					+ "nationality VARCHAR(20),"
//					+ "amountInvested BIGINT,"
//					+ "managed BOOLEAN,"	
//					+ "paid BOOLEAN,"
//					+ "timeCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
//					+ "timeManaged TIMESTAMP,"
//					+ "PRIMARY KEY(id),"
//					+ "UNIQUE(nik)"
//					+ ")");
//
//			stt.execute("DROP TABLE IF EXISTS investments");
//			stt.execute("DROP TABLE IF EXISTS investorFees");
//			stt.execute("DROP TABLE IF EXISTS investorManaged");
//			stt.execute("DROP TABLE IF EXISTS investorRequests");
//			stt.execute("DROP TABLE IF EXISTS investors");
			
			stt.execute("CREATE TABLE IF NOT EXISTS investors("
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
			
			stt.execute("CREATE TABLE IF NOT EXISTS investorRequests("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "investorId BIGINT,"
					+ "amtOffered BIGINT,"
					+ "managed BOOLEAN DEFAULT false,"
					+ "timeCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (id),"
					+ "FOREIGN KEY (investorId) REFERENCES investors(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS investorManaged("
					+ "requestId BIGINT NOT NULL,"
					+ "fulfilled BOOLEAN DEFAULT FALSE,"
					+ "timeManaged TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (requestId),"
					+ "FOREIGN KEY (requestId) REFERENCES investorRequests(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS investorFees("
					+ "requestId BIGINT NOT NULL,"
					+ "lastPaid TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "feeMultiplier FLOAT,"
					+ "PRIMARY KEY (requestId),"
					+ "FOREIGN KEY (requestId) REFERENCES investorRequests(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS investments("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "requestId BIGINT,"
					+ "investment BIGINT,"
					+ "invested BIGINT,"
					+ "timePaid TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY(requestId) REFERENCES investorRequests(id)"
					+ ")");
			
//			stt.execute("DROP TABLE IF EXISTS earnings");
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
					+ "feeMultiplier FLOAT,"
					+ "PRIMARY KEY (requestId),"
					+ "FOREIGN KEY (requestId) REFERENCES debtorRequests(id)"
					+ ")");
			
			stt.execute("CREATE TABLE IF NOT EXISTS earnings("
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "requestId BIGINT,"
					+ "earning BIGINT,"
					+ "timePaid TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY(requestId) REFERENCES debtorRequests(id)"
					+ ")");
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		updateData();

	}
	
	// SQL
	
	private void close() {
		try { rs.close(); } catch(Exception e) { };
		try { stt.close(); } catch(Exception e) { };
		try { con.close(); } catch(Exception e) { };
	}
	
	private void updateData() {
		
		// INVESTORS
		investors.clear();
		
		try {
				
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(url, user, password);
				
				stt = con.createStatement();
				
				rs = stt.executeQuery("SELECT * FROM investors");
				
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
						
					Investor debtor = new Investor(id, nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality);
						
					investors.add(debtor);	
				}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
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
		
		// UNPAID DEBTORS
		unpaidDebtors.clear();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			rs = stt.executeQuery("SELECT * FROM debtorFees");
			
			while(rs.next()) {
				int requestId = rs.getInt("requestId");
				Date date = rs.getDate("lastPaid");
				LocalDate lastPaid = date.toLocalDate();
				float feeMultiplier = rs.getFloat("feeMultiplier");
		
				DebtorFee fee = new DebtorFee(requestId, lastPaid, feeMultiplier);
				
				unpaidDebtors.add(fee);
			}
				
			rs = stt.executeQuery("SELECT * FROM debtorRequests WHERE managed=true");
			
			while(rs.next()) {
				int requestId = rs.getInt("id");
				int debtorId = rs.getInt("debtorId");
				int amountRequested = rs.getInt("amtRequested");
				
				for(DebtorFee fee: unpaidDebtors) {
					if(requestId == fee.getRequestId()) {
						fee.setDebtorId(debtorId);
						fee.setAmountRequested(amountRequested);
						fee.update();
						
						if(fee.getFeeAmount() == 0) {
							unpaidDebtors.remove(fee);
						}
					}
				}
				
			}
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();	
		}
		
		// EARNINGS
		
		earningsObject.resetEarnings();;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			rs = stt.executeQuery("SELECT * FROM earnings");
			
			while(rs.next()) {
				long earning = rs.getInt("earning");
				LocalDate timePaid = rs.getDate("timePaid").toLocalDate();
				
				earningsObject.addEarnings(earning, timePaid);
				System.out.println(earning + " is added");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();	
		}
		
		
	}
	
	public long calcDebtorFeeAmount(long amount) {
		return earningsObject.calcDebtorFeeAmount(amount);
	}

	public float calcDebtorFeeMultiplier(long amount) {
		return earningsObject.calcDebtorFeeMultiplier(amount);
	}
	
	public long getTotalEarnings() {
		return earningsObject.getTotalEarnings();
	}
	
	public long getMonthEarnings() {
		return earningsObject.getMonthEarnings();
	}
	
	public long getYearEarnings() {
		return earningsObject.getYearEarnings();
	}
	
	public void addEarnings(int requestId, long earning) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			query = String.format("INSERT INTO earnings(requestId,earning) VALUES(%d, %d)",requestId, earning);
			
			stt.execute(query);
			
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
			
			stt.execute("INSERT INTO investors(nik, name, gender, address, rtrw, village, district, religion, marriageStatus, occupation, nationality) VALUES("
					+ values
					+ ")");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		updateData();

//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//		investor.setTimeCreated(LocalDateTime.now().format(dtf));
//
//		investors.add(investor);
//		unmanagedInvestors.add(investor);
	}

	public void editInvestor(int id, Investor newInvestor) {

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String update = String.format("SET nik='%s', name='%s', gender='%s', address='%s', rtrw='%s', village='%s', district='%s', religion='%s', marriageStatus='%s', occupation='%s', nationality='%s' WHERE id=%d", newInvestor.getNik(), newInvestor.getName(), newInvestor.getGender(), newInvestor.getAddress(), newInvestor.getRtrw(), newInvestor.getVillage(), newInvestor.getDistrict(), newInvestor.getReligion(), newInvestor.getMarriageStatus(), newInvestor.getOccupation(), newInvestor.getNationality(), id);
			
			stt.execute("UPDATE investors "
					+ update
					+ "");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateData();
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

		try {	
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String query = String.format("DELETE FROM investors WHERE id = %d;", id);
			
			stt.execute(query);
					
		}catch(Exception e) {
			e.printStackTrace();	
		}finally {
			close();
		}
						
		updateData();
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

	public List<DebtorFee> getUnpaidDebtors() {
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
	
	public DebtorFee getDebtorFee(int requestId) {
		
		DebtorFee selectedFee = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			query = String.format("SELECT * FROM debtorFees WHERE requestId=%d", requestId);
			
			rs = stt.executeQuery(query);
			
			while(rs.next()) {
				Date date = rs.getDate("lastPaid");
				LocalDate lastPaid = date.toLocalDate();
				float feeMultiplier = rs.getFloat("feeMultiplier");
		
				DebtorFee fee = new DebtorFee(requestId, lastPaid, feeMultiplier);
				
				rs = stt.executeQuery("SELECT * FROM debtorRequests WHERE managed=true AND id=" + requestId);
				
				while(rs.next()) {

					int debtorId = rs.getInt("debtorId");
					int amountRequested = rs.getInt("amtRequested");
					
					if(requestId == fee.getRequestId()) {
						fee.setDebtorId(debtorId);
						fee.setAmountRequested(amountRequested);
						fee.update();
						
						selectedFee = fee;
					}
					
					
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();	
		}
		
		return selectedFee;
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
		
		updateData();
		
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
		
		updateData();
		
		
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

		updateData();

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
			
			query = String.format("SELECT * FROM debtorRequests WHERE id=%d", id);
			
			rs = stt.executeQuery(query);
			
			long amtRequested = 0;
			
			while(rs.next()) {
				amtRequested = rs.getInt("amtRequested");
			}
			
			
			float feePercent = earningsObject.calcDebtorFeeMultiplier(amtRequested);
			
			
			query = String.format("INSERT INTO debtorFees (requestId, feeMultiplier) VALUES(%d, %f)", id, feePercent);
			
			stt.execute(query);
		
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateData();
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
			
			String condition = String.format("WHERE requestId=%d", id);
			
			stt.execute("DELETE FROM debtorManaged "
					+ condition
					+ "");
			
			stt.execute("DELETE FROM debtorFees "
					+ condition
					+ "");
		
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		updateData();

	}

	public void paidDebtor(int requestId) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
			stt = con.createStatement();
			
			String update = String.format("SET lastPaid = CURRENT_TIMESTAMP WHERE requestId = %d", requestId);
			
			stt.execute("UPDATE debtorFees "
					+ update
					+ "");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

//		Debtor unpaidDebtor = null;
//		for (Debtor debtor : debtors) {
//			if (debtor.getId() == requestId) {
//				debtor.setPaid(true);
//				unpaidDebtor = debtor;
//			}
//		}
//
//		unpaidDebtors.remove(unpaidDebtor);
		updateData();
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
						
		updateData();

	}
}
