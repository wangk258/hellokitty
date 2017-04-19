package common.base;

import com.alibaba.fastjson.JSON;
import common.bo.PageBean;
import common.bo.ResultFlag;
import net.sf.json.JSONObject;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.OutputStream;

public class BaseController<T> {

	protected static ResultFlag resultFlag = new ResultFlag();

	protected PageBean<T> pageBean;

	public void setRightFlag(Object o) {
		resultFlag.setError(false);
		resultFlag.setMsg("");
		resultFlag.setData(o);
	}

	public void setErrorFlag(String msg) {
		resultFlag.setError(true);
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

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(new Timestamp(Long.valueOf(text)));
			}
		});
	}
}
