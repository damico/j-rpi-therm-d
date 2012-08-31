package br.com.infoserver.jrpithermd.thread;

import java.io.IOException;

import br.com.infoserver.jrpithermd.common.Constants;
import br.com.infoserver.jrpithermd.common.LoggerManager;
import br.com.infoserver.jrpithermd.common.ManageProperties;
import br.com.infoserver.jrpithermd.common.Utils;

public class ReadThermThread extends Thread {
	
	private String thermFilePath = null;
	String location = null;
	String var1 = null;
	String var2 = null;
	String thermUrl = null;
	int readCycle = 60000;
    
	public ReadThermThread(String thermFilePath, String location, String var1, String var2, String thermUrl, int readCycle){
		this.thermFilePath = thermFilePath;
		this.var1 = var1;
		this.var2 = var2;
		this.thermUrl = thermUrl;
		this.readCycle = readCycle;
		this.location = location;
	}
	
    public void run() {
    	
    	while(true){
    		
    		String thermFileContent = null ;
    		try {
    			thermFileContent = Utils.getInstance().getStringFromFile(thermFilePath);
    		} catch (IOException e) {
    			LoggerManager.getInstance().logAtExceptionTime(this.getName(), e.getMessage());
    		}
    		
    		
    		
    		
    		Double preTemp = -9999.9;
    		if(thermFileContent.contains("YES") && !thermFileContent.contains("ff ff ff ff ff ff ff ff ff")){
    			String[] result = thermFileContent.split("t=");
    			if(result.length == 2) preTemp = Double.parseDouble(result[1])/1000;
    		}
    		
    		
    		String urlStr = thermUrl + "?" + var1 + "=" + preTemp+"&" + var2 + "=" +location;
    		urlStr = urlStr.trim();
    		
    		try {
    			Utils.getInstance().getStringFromUrl(urlStr);
    			LoggerManager.getInstance().logAtDebugTime(this.getName(), "posted "+urlStr);
    		} catch (IOException e) {
    			LoggerManager.getInstance().logAtExceptionTime(this.getName(), "["+urlStr+"] " + e.getMessage());
    		}
    		
    		try {
				this.sleep(readCycle);
				LoggerManager.getInstance().logAtDebugTime(this.getName(), "sleeping ");
			}catch (Exception e) {
				LoggerManager.getInstance().logAtExceptionTime("CollectSend", "["+urlStr+"] " + e.getMessage());
			}
    	}
    	
    }

}
