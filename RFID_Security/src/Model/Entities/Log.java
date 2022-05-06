package Model.Entities;

import java.time.LocalDateTime;

public class Log {
	private String logId;
	private String tagId;
	private LocalDateTime time;
	private Integer gateNumber;
	private String status;
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Integer getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(Integer gateNumber) {
		this.gateNumber = gateNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Log() {
		super();
	}

	public Log(String logId, String tagId, LocalDateTime time, Integer gateNumber, String status) {
		super();
		this.logId = logId;
		this.tagId = tagId;
		this.time = time;
		this.gateNumber = gateNumber;
		this.status = status;
	}
	
	
	
}
