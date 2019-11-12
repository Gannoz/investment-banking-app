package model;

import java.sql.Date;

public class DebtorRequest {
	
	private int id;
	private int debtorId;
	private long amtRequested;
	private boolean managed;
	private boolean fulfilled;
	private Date timeCreated;
	private Date timeManaged;
	
	public DebtorRequest(int id, int debtorId, long amtRequested, boolean managed, Date timeCreated) {
		this.id = id;
		this.debtorId = debtorId;
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
		return debtorId;
	}

	public void setDebtorId(int debtorId) {
		this.debtorId = debtorId;
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
