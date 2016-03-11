package com.xiaoxuanfeng.plate.common.constants;

/**
 * @desc <h3>系统常量描述</h3>
 * @date 2014-9-4 下午03:07:43
 * @author tangk
 */
public class CommonConstants {

	/**
	 * 编号开始前缀
	 * 
	 * @author tangkun
	 * 
	 */
	public enum SHOWHOMEPAGESTATE {
		YES("是", 1), NO("否",0 );
		private String name;
		private Integer value;

		SHOWHOMEPAGESTATE(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
