package common.rdbms.base;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import common.bo.PageBean;
import common.bo.ResultFlag;
import net.sf.json.JSONObject;


public class BaseController<T> {

	protected static ResultFlag resultFlag=new ResultFlag();
	
	protected PageBean<T> pageBean = null;
	
	public ResultFlag setRightFlag(Object o){
		resultFlag.setFlag(true);
		resultFlag.setData(o);
		return resultFlag;
	}
	
	public ResultFlag setErrorFlag(String msg){
		resultFlag.setFlag(false);
		resultFlag.setMsg(msg);
		return resultFlag;
	}
	
	public void writeFlag(HttpServletResponse response) throws Exception{
		OutputStream out=response.getOutputStream();
		out.write(JSONObject.fromObject(resultFlag).toString().getBytes());
	}
	
	public void writeObject(HttpServletResponse response) throws Exception{
		response.getWriter().write(JSON.toJSONString(pageBean));
	}
}
