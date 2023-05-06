package org.exploremore;

import java.util.HashMap;

public class IDandPassword {

	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	IDandPassword(){
		
		logininfo.put("nick","bucas");
		logininfo.put("test1","yes");
		logininfo.put("mom","my");
	}
	
	public HashMap<String, String> getLoginInfo(){
		return logininfo;
	}
}