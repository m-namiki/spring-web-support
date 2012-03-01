package jp.co.shantery.spring.web.support.bean;

public class MyCommand {

	private String operationCode;

	private String revokeReason;

	private String operationDateFrom;

	private String operationDateTo;

	private String accessCode;

	private String[] expCountArray;

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getOperationDateFrom() {
		return operationDateFrom;
	}

	public void setOperationDateFrom(String operationDateFrom) {
		this.operationDateFrom = operationDateFrom;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String[] getExpCountArray() {
		return expCountArray;
	}

	public void setExpCountArray(String[] expCountArray) {
		this.expCountArray = expCountArray;
	}

	public String getOperationDateTo() {
		return operationDateTo;
	}

	public void setOperationDateTo(String operationDateTo) {
		this.operationDateTo = operationDateTo;
	}

}
