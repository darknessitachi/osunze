package osunze;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2016年8月2日 上午10:58:55 
 * 程序的简单说明 
 */
public class EncyptTest {
	@Test
	public void doCreatePassword(){
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String algorithmName="sha-256";
		final int hashIterations = 1024;
		String salt = randomNumberGenerator.nextBytes().toHex();
		System.out.println(salt);
//		salt="7road";
		String newPassword = new SimpleHash(algorithmName,"admin",ByteSource.Util.bytes(salt),hashIterations).toHex();
		System.out.println(newPassword);
	}
}
