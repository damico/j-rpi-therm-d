package br.com.infoserver.jrpithermd.common;

public interface Constants {

	public static final String CONF_FILE_PATH = "/etc/j-rpi-therm-d/conf.properties";
	public static final String APP_NAME = "j-rpi-therm-d";
	public static final String APP_VERSION = "0.2";
	
	public static final String LOG_NAME = "j-rpi-therm-d.log";
	public static final String LOG_FOLDER = "/var/log/j-rpi-therm-d/";
	public static final String SEVERE_LOGLEVEL = " S ";
	public static final String NORMAL_LOGLEVEL = " N ";
	public static final int FIXED_LOGLIMIT = 50000;
	
	public static final String THERM_FILE_PATH = "therm.file.path";
	public static final String VAR1 = "therm.url.var1";
	public static final String VAR2 = "therm.url.var2";
	public static final String THERM_LOCATION = "therm.location";
	public static final String THERM_URL = "therm.url";
	public static final String READ_CYCLE = "read.cycle";
	
}
