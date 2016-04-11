package common.bo;

public class ResultFlag {
	
	private Boolean flag;
	
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
	
	public ResultFlag(Boolean flag) {
		this.flag = flag;
	}

	
	public ResultFlag(Boolean flag, Object data) {
		this.flag = flag;
		this.data = data;
	}

	public ResultFlag(Boolean flag, String msg, Object data) {
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}

	public ResultFlag(Boolean flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}