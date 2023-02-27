package com.softwear.webapp5.data;

private class PassChange {

	private String oldPass;
	private String newPass;
	private String newConfPass;

	public PassChange(String oldPass, String newPass, String newConfPass) {
		
		this.oldPass = oldPass;
		this.newPass = newPass;
		this.newConfPass = newConfPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getNewConfPass() {
		return newConfPass;
	}

	public void setNewConfPass(String newConfPass) {
		this.newConfPass = newConfPass;
	}
	
	
	
}
