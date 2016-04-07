package domainLayer;

public class OperationResult {

	private Status status;
	private String message;

	public OperationResult(Status status, String cause) {
		this.status = status;
		this.message = cause;
	}
	
	public OperationResult(Status status) {
		this.status = status;
		this.message = "";
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
