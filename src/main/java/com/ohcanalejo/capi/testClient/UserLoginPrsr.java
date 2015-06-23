package com.ohcanalejo.capi.testClient;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohcanalejo.capi.handler.Handler;
import com.ohcanalejo.capi.testClient.SystemUser.UserType;


public class UserLoginPrsr implements Handler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int iCrGvrId;
	private String sUsrType;
	private String[] iPatientId;
	private String[] iCpId;
	private String[] sfullName;
	private boolean[] iUsrAttrs;
	private int iVendorId;
	private String sTelfPolice;
	private int iLanguageId;
	private boolean mobileModeOnDesktop;
	
	public UserLoginPrsr() {
	}


	@Override
	public <T> T handle() {
		SystemUser user = null;
		try {
			logger.info("\nHandling User data... ");
			if (this.iCrGvrId > 0) {
				user = new SystemUser();
				user.setUserId(this.iCrGvrId);
				user.setVendorId(this.iVendorId);
				user.setLanguage(iLanguageId);
				user.setMobileModeOnDesktop(this.mobileModeOnDesktop);
				if (this.sUsrType != null && this.sUsrType.equalsIgnoreCase("Administrator")) {
					user.setUserType(UserType.ROLE_ADMIN);
				}else if (this.sUsrType != null && this.sUsrType.contains("Caregiver")){
					user.setUserType(UserType.ROLE_CAREGIVER);
				}
				if(iPatientId != null) {
					List<SystemUser> pList = new ArrayList<>();
					for(int i=0; i < iPatientId.length; i++) {
						SystemUser p = new SystemUser();
						p.setUserId(Integer.parseInt(iPatientId[i]));
//						p.setCpId(iCpId[i]);
						p.setFirstName(sfullName[i]);
						pList.add(p);
					}
					user.setPatients(pList);
				}
			}
		}catch(Exception ex) {
//			throw new ParseException(ex,"Unexpected parse error");
		}
		return (T) user;
	}
	
	
	public int getiCrGvrId() {
		return iCrGvrId;
	}


	public void setiCrGvrId(int iCrGvrId) {
		this.iCrGvrId = iCrGvrId;
	}


	public String getsUsrType() {
		return sUsrType;
	}


	public void setsUsrType(String sUsrType) {
		this.sUsrType = sUsrType;
	}


	public String[] getiPatientId() {
		return iPatientId;
	}


	public void setiPatientId(String[] iPatientId) {
		this.iPatientId = iPatientId;
	}


	public String[] getSfullName() {
		return sfullName;
	}


	public void setSfullName(String[] sfullName) {
		this.sfullName = sfullName;
	}


	public boolean[] getiUsrAttrs() {
		return iUsrAttrs;
	}


	public void setiUsrAttrs(boolean[] iUsrAttrs) {
		this.iUsrAttrs = iUsrAttrs;
	}


	public String[] getiCpId() {
		return iCpId;
	}


	public void setiCpId(String[] iCpId) {
		this.iCpId = iCpId;
	}


	public int getiVendorId() {
		return iVendorId;
	}


	public void setiVendorId(int iVendorId) {
		this.iVendorId = iVendorId;
	}

	@JsonProperty("TelfPolice")
	public String getsTelfPolice() {
		return sTelfPolice;
	}

	@JsonProperty("TelfPolice")
	public void setsTelfPolice(String sTelfPolice) {
		this.sTelfPolice = sTelfPolice;
	}
	
	@JsonProperty("LanguageId")
	public int getiLanguageId() {
		return iLanguageId;
	}
	@JsonProperty("LanguageId")
	public void setiLanguageId(int iLanguageId) {
		this.iLanguageId = iLanguageId;
	}


	public boolean getMobileModeOnDesktop() {
		return mobileModeOnDesktop;
	}


	public void setMobileModeOnDesktop(boolean mobileModeOnDesktop) {
		this.mobileModeOnDesktop = mobileModeOnDesktop;
	}

	
}
