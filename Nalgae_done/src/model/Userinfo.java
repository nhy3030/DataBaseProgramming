package model;

/**
 * 사용자 관리를 위하여 필요한 도메인 클래스.
 * USERINFO 테이블의 각 칼럼에 해당하는 setter와 getter를 가진다. 
 */
public class Userinfo {
	private String user_id = null;
	private String user_pwd = null;
	private String user_name = null;
	private String phone = null;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 비밀번호가 일치하는지 여부를 결정하는 메써드.
	 */
	public boolean isMatchPassword(String inputPassword){
		if (getUser_pwd().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}
}
