package common.bo;

public class ResultFlag {
	
	private Boolean error;
	
	private String msg;
	
	private Object data;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultFlag(){
		
	}
	
	public ResultFlag(Boolean error) {
		this.error = error;
	}

	
	public ResultFlag(Boolean error, Object data) {
		this.error = error;
		this.data = data;
	}

	public ResultFlag(Boolean error, String msg, Object data) {
		this.error = error;
		this.msg = msg;
		this.data = data;
	}

	public ResultFlag(Boolean error, String msg) {
		this.error = error;
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
}