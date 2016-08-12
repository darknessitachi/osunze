package com.skycity.framework.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File 工具类
 * @author YingBo
 *
 */
public class FileUtil {

	public static File normalizeFile(File f) {
		String path = f.getAbsolutePath();
		return new File(path);
	}

	/**
	 * readfile
	 * @param f
	 * default encoding UTF-8
	 * @return
	 */
	public static String readText(File f){
		return readText(f,"UTF-8");
	}
	
	/**
	 * read file with customer encoding
	 * @param f
	 * @param encoding
	 * @return
	 */
	public static String readText(File f, String encoding) {
		f = normalizeFile(f);
		try {
			InputStream is = new FileInputStream(f);
			String str = readText(is, encoding);
			is.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * read file with inputstream
	 * @param is
	 * encoding UTF-8
	 * @return
	 */
	public static String readText(InputStream is){
		return readText(is,"UTF-8");
	}

	public static String readText(InputStream is, String encoding) {
		try {
			byte[] bs = readByte(is);
			return new String(bs, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String readText(String fileName){
		return readText(fileName,"UTF-8");
	}
	
	public static String readText(String fileName, String encoding) {
		try {
			InputStream is = new FileInputStream(fileName);
			String str = readText(is, encoding);
			is.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readByte(InputStream is) {
		byte[] buffer = new byte[8192];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		do {
			int bytesRead = -1;
			try {
				bytesRead = is.read(buffer);
			} catch (IOException e) {
				throw new RuntimeException("File.readByte() failed");
			}
			if (bytesRead != -1)
				try {
					os.write(buffer, 0, bytesRead);
				} catch (Exception e) {
					throw new RuntimeException("File.readByte() failed");
				}
			else
				return os.toByteArray();
		} while (true);
	}
	
	
	public static String normalizePath(String path)
	  {
	    path = path.replace('\\', '/');
//	    path = StringUtil.replaceEx(path, "../", "/");
//	    path = StringUtil.replaceEx(path, "./", "/");
	    if (path.endsWith("..")) {
	      path = path.substring(0, path.length() - 2);
	    }
	    path = path.replaceAll("/+", "/");
	    return path;
	  }
	
	
	public static boolean writeText(String fileName,String content,String encoding){
		normalizePath(fileName);
		try {
//	      byte[] bs = content.getBytes(encoding);
//	      if ((encoding.equalsIgnoreCase("UTF-8")) && (bomFlag)) {
//	        bs = ArrayUtils.addAll(StringUtil.BOM, bs);
//	      }
//	      writeByte(fileName, bs);
	    } catch (Exception e) {
	      return false;
	    }
	    return true;
	}
}