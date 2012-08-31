package br.com.infoserver.jrpithermd.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LoggerManager{

	private static final Logger logger = Logger.getLogger("LoggerManager");

	private static LoggerManager INSTANCE;
	public static LoggerManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoggerManager();
		}
		return INSTANCE;
	}
	private LoggerManager(){}

	public void logAtDebugTime(String className, String logLine){
		log(className, logLine, false);
	}

	public void logAtExceptionTime(String className, String logLine) {
		log(className, logLine, true);
	}

	/** 
	 * Check if log direction is SystemOut or a separate file
	 * Check if a log file exist
	 * If there is no file create, on the contrary, use the file
	 * @throws IOException 
	 *
	 */
	private void log(String className, String logLine, boolean logLevel) {
		String formatedLog = " ("+className+") "+logLine;


		String fileName = Constants.LOG_NAME;

		fileName = Constants.LOG_FOLDER + fileName;

		

		String stime = Utils.getInstance().getCurrentDateTimeFormated("yyyyMMMdd_HH:mm:ss");
		if(logLevel){
			formatedLog = stime+Constants.SEVERE_LOGLEVEL+formatedLog+"\n";
		}else{
			formatedLog = stime+Constants.NORMAL_LOGLEVEL+formatedLog+"\n";
		}

		int limit = Constants.FIXED_LOGLIMIT;

		File listenerLog = null;
		FileWriter fw = null;
		BufferedWriter bwr = null;
		try{

			listenerLog = new File(fileName);
			if(!listenerLog.exists()){
				listenerLog.createNewFile();
			} else if(listenerLog.length() > limit){
				/* 
				 * check if file is too big
				 */
				 listenerLog.delete();
				listenerLog.createNewFile();

			}

			fw = new FileWriter(fileName, true);
			bwr = new BufferedWriter(fw);
			bwr.write(formatedLog);
			bwr.close();
			fw.close();
		}catch(IOException ioe){
			System.err.println("write log error");
		}finally{
			try {
				if(bwr!=null) bwr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(fw!=null) fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listenerLog = null;
			fw = null;
			bwr = null;
		}




	}

//	public ArrayList getLogsData(){
//		/* 
//		 * Scan logs folder
//		 */
//		ArrayList logsArray = new ArrayList();
//		LogData logData = null;
//
//		File foldersLog = new File(Constants.APP_FOLDER);
//
//		String[] logsList = foldersLog.list();
//
//		if (logsList == null) {
//			logAtExceptionTime(this.getClass().getName(), "No log files found! Check system configuration.");
//		} else {
//			for (int i=0; i< logsList.length; i++) {
//				logData = new LogData();
//				String logName = logsList[i];
//				logData.setLogName(logName);
//				logData.setLogPrefix(logName.replaceAll("_"+Constants.LOG_NAME, ""));
//				logData.setLogSize(getLogSize(logName));
//				logData.setLogLines(getLogLines(logName));
//				logsArray.add(logData); 
//			}
//		}
//		return logsArray;
//	}

//	private int getLogLines(String fileName) {
//		int lines = 0;
//		try {
//			FileReader fr =  new FileReader(Constants.APP_FOLDER+fileName);
//			BufferedReader fileInput = new BufferedReader(fr);
//			while ((fileInput.readLine()) != null) {
//				lines++;
//			}
//			fileInput.close();
//			fr.close();
//		} catch (IOException e) {
//			logAtExceptionTime(this.getClass().getName(), "getLogLines(String fileName) "+e.getMessage());
//		}
//		return lines;
//	}

//	private long getLogSize(String fileName) {
//		File listenerLog = new File(Constants.APP_FOLDER+fileName);
//		return listenerLog.length();
//	}


}