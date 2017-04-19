package common.base;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = -8132783033359171261L;
	/**
	 * 创建人
	 */
	private String createUser ="admin";
	/**
	 * 创建时间
	 */
	private Long createTime = new Date().getTime();
	/**
	 * 最后修改人
	 */
	private String updateUser ="admin";
	/**
	 * 最后修改时间表
	 */
	private Long updateTime = new Date().getTime();

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}