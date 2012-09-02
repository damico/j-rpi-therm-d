package br.com.infoserver.jrpithermd.runtime;

import br.com.infoserver.jrpithermd.common.Constants;
import br.com.infoserver.jrpithermd.common.LoggerManager;
import br.com.infoserver.jrpithermd.common.ManageProperties;
import br.com.infoserver.jrpithermd.thread.ReadThermThread;

public class CollectSend {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		LoggerManager.getInstance().logAtDebugTime("CollectSend", Constants.APP_NAME + " ["+Constants.APP_VERSION+"]");

		
		/**
		 * 	69 01 4b 46 7f ff 07 10 5e : crc=5e YES
			69 01 4b 46 7f ff 07 10 5e t=22562
		 */
		
		String thermFilePath = ManageProperties.getInstance().read(Constants.THERM_FILE_PATH);
		
		int readCycle = 60000;

		try {
			readCycle = Integer.parseInt(ManageProperties.getInstance().read(Constants.READ_CYCLE));
		} catch (Exception e) {
			LoggerManager.getInstance().logAtExceptionTime("CollectSend", e.getMessage());
		}
		
		String thermLoc = ManageProperties.getInstance().read(Constants.THERM_LOCATION);
		String var1 = ManageProperties.getInstance().read(Constants.VAR1);
		String var2 = ManageProperties.getInstance().read(Constants.VAR2);
		String thermUrl = ManageProperties.getInstance().read(Constants.THERM_URL);
		
		LoggerManager.getInstance().logAtDebugTime("CollectSend", thermFilePath);
		LoggerManager.getInstance().logAtDebugTime("CollectSend", thermLoc);
		LoggerManager.getInstance().logAtDebugTime("CollectSend", var1);
		LoggerManager.getInstance().logAtDebugTime("CollectSend", var2);
		LoggerManager.getInstance().logAtDebugTime("CollectSend", thermUrl);
		LoggerManager.getInstance().logAtDebugTime("CollectSend", String.valueOf(readCycle));
		
		ReadThermThread thread = new ReadThermThread(thermFilePath, 
														thermLoc, 
														var1, 
														var2, 
														thermUrl, 
														readCycle);
		thread.start();

	}

}
