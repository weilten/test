package org.mail.entity;

/**
 * 客户
 * @author VIC
 *
 */
public class Customer {
	
	private Integer cid;
	private String caccount;
	private String cpassword;
	public Customer(String account, String password) {
		this.caccount = account;
		this.cpassword = password;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCaccount() {
		return caccount;
	}
	public void setCaccount(String caccount) {
		this.caccount = caccount;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	
}
