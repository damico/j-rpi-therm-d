package br.com.infoserver.jrpithermd.test;

import static org.junit.Assert.fail;

public class Test {

	@org.junit.Test
	public void test() {
		String temp = 	"69 01 4b 46 7f ff 07 10 5e : crc=5e YES\n"+
				"69 01 4b 46 7f ff 07 10 5e t=22562";

		Double preTemp = -9999.9;
		if(temp.contains("YES") && !temp.contains("ff ff ff ff ff ff ff ff ff")){
			String[] result = temp.split("t=");
			if(result.length == 2) preTemp = Double.parseDouble(result[1])/1000;
		}


		System.out.println(preTemp);
	}

}
