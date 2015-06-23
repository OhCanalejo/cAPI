package com.ohcanalejo.capi.testClient;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * General Class for representing any user in the system
 * such as Administrator, Caregiver or Resident(Patient)
 * 
 * @author oscar.canalejo
 */
public class SystemUser {
	
		public static enum UserType {ROLE_ADMIN(1), ROLE_CAREGIVER(2), ROLE_MONITORED(3);
		private final int id;
		UserType(int id) { this.id = id;}
		public int getId() {return id;}
	}
	
	public static enum UserGender {UNKNOWN(-1),FEMALE(0), MALE(1);
		private final int id;
		UserGender(int id) { this.id = id;}
		public int getId() {return id;}
	}

	/*
	 *  Basic info attributes
	 */
    protected int userId;
    protected UserType userType;
    protected int vendorId;
    protected String generalId; // Identification Number
    protected String firstName;
    protected String lastName;
    protected String photo;
    protected String mobile;
    protected String address;
    protected String phone;
    protected UserGender gender;
    protected Date birthDate;
    protected String nick;
    protected String email;
    protected String passwd;
    protected String passwdConfirmation;
    protected String encodedPasswd;
    protected String question;
    protected String answer;
    protected String tlfPolice;
    protected int language;
    protected String accountNumber; // for Residents and Caregivers only
    protected boolean mobileModeOnDesktop; // Can the User logs-in as Mobile Mode from a Desktop Browser?
  	private List<SystemUser> patients;

    /*
     * Other attributes
     */
    private boolean bAssigned;
    private Map<String, Boolean> alertConf;
    
    public String getStringUserId() {
    	return String.valueOf(this.userId);
    }
 
    
	public String getTlfPolice() {
		return tlfPolice;
	}

	public void setTlfPolice(String tlfPolice) {
		this.tlfPolice = tlfPolice;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswdConfirmation() {
		return passwdConfirmation;
	}
	public void setPasswdConfirmation(String passwdConfirmation) {
		this.passwdConfirmation = passwdConfirmation;
	}
	public String getEncodedPasswd() {
		return encodedPasswd;
	}
	public void setEncodedPasswd(String encodedPasswd) {
		this.encodedPasswd = encodedPasswd;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserGender getGender() {
		return gender;
	}
	public void setGender(UserGender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate; //Util.parseDate(birthDate);
	}
	/**
	 * This method checks the user attributes are correct
	 * 
	 * @return boolean
	 */
	public boolean checkAttibutes() {
		// Checking empty values
		if (checkUserType()) return false;
		if (firstName == null || firstName.isEmpty()) return false;
		if (lastName == null || lastName.isEmpty()) return false;
		if (nick == null || nick.isEmpty()) return false;
		if (passwd == null || passwd.isEmpty()) return false;
		if (email == null || email.isEmpty()) return false;
		if (question == null || question.isEmpty()) return false;
		if (answer == null || answer.isEmpty()) return false;
		
		// Checking mandatory length
		if (nick.length() < 6) return false;// nick at least 6 characters
		if (passwd.length() < 8) return false;// password at least 8 characters
		
		// Checking password confirmation
		if (!passwd.equals(passwdConfirmation)) return false;// password at least 8 characters
		
		return false;
	}

	/**
	 * Checks the user type
	 * 
	 * @return boolean
	 */
	protected boolean checkUserType() {
		boolean bResult = false;
		
		for (UserType ut : UserType.values()) {
			if (ut.equals(userType)){
				bResult = true;
				break;
			}
		}
		
		return bResult;
	}
	public Map<String, Boolean> getAlertConf() {
		return alertConf;
	}
	public void setAlertConf(Map<String, Boolean> alertConf) {
		this.alertConf = alertConf;
	}
	
	public boolean isAssigned() {
		return bAssigned;
	}
	
	public void setAssigned(boolean assigned) {
		this.bAssigned = assigned;
	}

	public String getGeneralId() {
		return generalId;
	}

	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}


	public int getLanguage() {
		return language;
	}


	public void setLanguage(int language) {
		this.language = language;
	}
	

	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public boolean getMobileModeOnDesktop() {
		return mobileModeOnDesktop;
	}


	public void setMobileModeOnDesktop(boolean mobileModeOnDesktop) {
		this.mobileModeOnDesktop = mobileModeOnDesktop;
	}


	public List<SystemUser> getPatients() {
		return patients;
	}


	public void setPatients(List<SystemUser> patients) {
		this.patients = patients;
	}



}
