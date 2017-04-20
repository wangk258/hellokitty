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
	private Integer createUser = 0;
	/**
	 * 创建时间
	 */
	private Long createTime = new Date().getTime();
	/**
	 * 最后修改人
	 */
	private Integer updateUser = 0;
	/**
	 * 最后修改时间表
	 */
	private Long updateTime = new Date().getTime();

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}