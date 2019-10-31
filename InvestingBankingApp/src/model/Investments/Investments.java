package model.Investments;

public class Investments {

	private long totalInvestments;
	private long totalInvested;

	public void addTotalInvestment(long investment) {
		totalInvestments += investment;
	}

	public void addTotalInvested(long invested) {
		totalInvested += invested;
	}
	
	public void subtractTotalInvestment(long investment) {
		totalInvestments -= investment;
	}

	public void subtractTotalInvested(long invested) {
		totalInvested -= invested;
	}

	public long getTotalInvestments() {
		return totalInvestments;
	}

	public long getTotalInvested() {
		return totalInvested;
	}

}
