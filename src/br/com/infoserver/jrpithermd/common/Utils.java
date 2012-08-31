package br.com.infoserver.jrpithermd.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static Utils INSTANCE = null;
	private Utils(){}
	public static Utils getInstance(){
		if(INSTANCE == null) INSTANCE = new Utils();
		return INSTANCE;
	}

	public String getStringFromFile(String filePath) throws IOException {
		StringBuffer ret = new StringBuffer();

		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String str;
		while ((str = in.readLine()) != null) {
			ret.append(str+"\n");
		}
		in.close();

		return ret.toString();
	}

	public String execute(String[] cmdArray) throws Exception
	{
		Process process = Runtime.getRuntime().exec(cmdArray);
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuffer output = new StringBuffer();
		String line = "";
		while((line = input.readLine()) != null)
		{

			System.out.println("exec: "+line);
			output.append(line);

		}
		process.destroy();
		process = null;
		return output.toString();
	}

	public String getCurrentDateTimeFormated(String format){
		Date date = new Date();
		Format formatter = new SimpleDateFormat(format);
		String stime = formatter.format(date);
		return stime;
	}

	public String getStringFromUrl(String urlPath) throws IOException{

		StringBuffer ret = new StringBuffer();

		URL url = new URL(urlPath);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String str;
		while ((str = in.readLine()) != null) ret.append(str);
		in.close();

		return ret.toString();
	}

}
