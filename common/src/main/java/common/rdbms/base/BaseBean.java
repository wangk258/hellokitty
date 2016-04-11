package common.rdbms.base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


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
	private Timestamp createTime = new Timestamp(new Date().getTime());
	/**
	 * 最后修改人
	 */
	private String updateUser ="admin";
	/**
	 * 最后修改时间表
	 */
	private Timestamp updateTime = new Timestamp(new Date().getTime());
	@Column(length=20)
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(length=20)
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}