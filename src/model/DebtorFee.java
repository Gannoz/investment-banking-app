package model;

import java.time.LocalDate;
import java.time.Period;

public class DebtorFee {
	
	private int requestId;
	private int debtorId;
	private float feeMultiplier;
	private long amountRequested;
	private long feeAmount;
	private LocalDate lastPaid;
	private LocalDate currentDate;
	
	public DebtorFee(int requestId, LocalDate lastPaid, float feeMultiplier) {
		this.requestId = requestId;
		this.feeMultiplier = feeMultiplier;
		this.lastPaid = lastPaid;
		
	}
	
	public void update() {
		
		currentDate = LocalDate.now();
		
		Period diff = Period.between(lastPaid, currentDate);
		
		int yearDiff = diff.getYears();
		int monthDiff = diff.getMonths() + (yearDiff * 12);
		
		feeAmount =  (long)(amountRequested * feeMultiplier) * monthDiff;
		
		System.out.println("Fee updated");
		
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getDebtorId() {
		return debtorId;
	}

	public void setDebtorId(int debtorId) {
		this.debtorId = debtorId;
	}
	
	public float getFeeMultiplier() {
		return feeMultiplier;
	}

	public void setFeeMultiplier(float feeMultiplier) {
		this.feeMultiplier = feeMultiplier;
	}
	
	public float getFeePercent() {
		return this.feeMultiplier * 100;
	}

	public long getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(long amountRequested) {
		this.amountRequested = amountRequested;
	}

	public long getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(long feeAmount) {
		this.feeAmount = feeAmount;
	}

	public LocalDate getLastPaid() {
		return lastPaid;
	}

	public void setLastPaid(LocalDate lastPaid) {
		this.lastPaid = lastPaid;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	
	
	
	

}
