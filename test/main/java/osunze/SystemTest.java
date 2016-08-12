package osunze;

import java.util.Properties;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2016年7月18日 下午5:23:36 
 * 程序的简单说明 
 */
public class SystemTest {
	public static void main(String args[]){
		Properties props  = System.getProperties();
		props.forEach((key,value)->{
			System.out.println(key+"="+value);
		});
	}
}
