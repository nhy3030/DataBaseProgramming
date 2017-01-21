package model;

/**
 * ����� ������ ���Ͽ� �ʿ��� ������ Ŭ����.
 * USERINFO ���̺��� �� Į���� �ش��ϴ� setter�� getter�� ������. 
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
	 * ��й�ȣ�� ��ġ�ϴ��� ���θ� �����ϴ� �޽��.
	 */
	public boolean isMatchPassword(String inputPassword){
		if (getUser_pwd().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}
}
