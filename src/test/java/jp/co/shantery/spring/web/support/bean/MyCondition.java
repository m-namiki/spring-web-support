package jp.co.shantery.spring.web.support.bean;

import java.util.Date;

public class MyCondition {

	private Integer operationCode;

	private Byte revokeReason;

	private Date operationDateFrom;

	private Date operationDateTo;

	private String accessCode;

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public Byte getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(Byte revokeReason) {
		this.revokeReason = revokeReason;
	}

	public Date getOperationDateFrom() {
		return operationDateFrom;
	}

	public void setOperationDateFrom(Date operationDateFrom) {
		this.operationDateFrom = operationDateFrom;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public Date getOperationDateTo() {
		return operationDateTo;
	}

	public void setOperationDateTo(Date operationDateTo) {
		this.operationDateTo = operationDateTo;
	}

	@Override
	public String toString() {
		return null;
	}
}
