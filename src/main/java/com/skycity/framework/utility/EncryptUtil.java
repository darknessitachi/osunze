package com.skycity.framework.utility;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.skycity.framework.Constant;

/**
 * 对称加密
 * @author YingBo
 *
 */
public class EncryptUtil {
    /**
     * encrypt src by key
     * @param keybyte
     * @param src
     * @return
     */
    public static String encryptMode(String key, String src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(new BASE64Decoder().decodeBuffer(key), Constant.ALGORITHM); // 加密
            Cipher c1 = Cipher.getInstance(Constant.ALGORITHM);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] result = c1.doFinal(src.getBytes());
            return new BASE64Encoder().encode(result);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
 
    /**
     * decrypt src by key
     * @param keybyte
     * @param encryStr
     * @return
     */
    public static String decryptMode(String keybyte, String encryStr) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(new BASE64Decoder().decodeBuffer(keybyte), Constant.ALGORITHM); // 解密
            Cipher c1 = Cipher.getInstance(Constant.ALGORITHM);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            byte[] result = c1.doFinal(new BASE64Decoder().decodeBuffer(encryStr));
            return new String(result);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    /**
     * create key
     * @return
     */
    public static String createSecretKey() {
		KeyGenerator keygen = null;
		try {
			keygen = KeyGenerator.getInstance(Constant.ALGORITHM);
			SecretKey deskey = keygen.generateKey();
			return new BASE64Encoder().encode(deskey.getEncoded());
		} catch (Exception e) {
			LogUtil.error("NoSuchAlgorithmException!");
		}
		return null;
	}
    
    public static void main(String[] args){
        final String key = createSecretKey();    
        System.out.println("密钥"+key+"  "+key.length());
        String szSrc = "admin";
        System.out.println("加密前的字符串:" + szSrc);
        String password = encryptMode(key,szSrc);
        System.out.println("加密后的字符串:" + password);
		String result = decryptMode(key, password);
        System.out.println("解密后的字符串:" + result);
    }
}