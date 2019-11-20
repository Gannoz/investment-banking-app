package model;

import java.sql.Date;

public class InvestorRequest {
	
	private int id;
	private int investorId;
	private long amtRequested;
	private boolean managed;
	private boolean fulfilled;
	private Date timeCreated;
	private Date timeManaged;
	
	public InvestorRequest(int id, int investorId, long amtRequested, boolean managed, Date timeCreated) {
		this.id = id;
		this.investorId = investorId;
		this.amtRequested = amtRequested;
		this.managed = managed;
		this.timeCreated = timeCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDebtorId() {
		return investorId;
	}

	public void setDebtorId(int investorId) {
		this.investorId = investorId;
	}

	public long getAmtRequested() {
		return amtRequested;
	}

	public void setAmtRequested(long amtRequested) {
		this.amtRequested = amtRequested;
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public Date getTimeManaged() {
		return timeManaged;
	}

	public void setTimeManaged(Date timeManaged) {
		this.timeManaged = timeManaged;
	}
	
	
}
