package model.mnginfo;

public class Mnginfo {
	private String manager_id;
	private String manager_pwd;
	private String manager_name;
	
	
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_pwd() {
		return manager_pwd;
	}
	public void setManager_pwd(String manager_pwd) {
		this.manager_pwd = manager_pwd;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	
	public boolean isMatchPassword(String inputPassword){
		if (getManager_pwd().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}
}
