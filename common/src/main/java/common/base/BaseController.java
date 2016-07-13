package common.base;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import common.bo.PageBean;
import common.bo.ResultFlag;
import net.sf.json.JSONObject;

public class BaseController<T> {

	protected static ResultFlag resultFlag = new ResultFlag();

	protected PageBean<T> pageBean;

	public void setRightFlag(Object o) {
		resultFlag.setFlag(true);
		resultFlag.setMsg("");
		resultFlag.setData(o);
	}

	public void setErrorFlag(String msg) {
		resultFlag.setFlag(false);
		resultFlag.setData(null);
		resultFlag.setMsg(msg);
	}

	public void writeFlag(HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		out.write(JSONObject.fromObject(resultFlag).toString().getBytes());
	}

	public void writeObject(HttpServletResponse response,Object object) throws Exception {
		response.getWriter().write(JSON.toJSONString(object));
	}
}
