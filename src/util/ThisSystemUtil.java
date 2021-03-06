package util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThisSystemUtil {

	public static boolean isNone(String s){
		return s==null||(s.trim()).length()==0;
	}
	
	public static int parseInt(String s,int defaultValue){
		try{
			int value=Integer.parseInt(s);
			return value;
		}catch(Exception e){
			return defaultValue;	
		}
	}
	public static int totalPage(int total,int pageSize){
		int page=total/pageSize;
		if(total%pageSize!=0){
			page++;
		}
		return page;
	}
	
	public static Date StringToDate(String s){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date d=sdf.parse(s);
			return d;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(System.currentTimeMillis());
	}
	
	public static String md5Password(String password) {    
        try {  
            // 得到一个信息摘要器  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte[] result = digest.digest(password.getBytes());  
            StringBuffer buffer = new StringBuffer();  
            // 把没一个byte 做一个与运算 0xff;  
            for (byte b : result) {  
                // 与运算  
                int number = b & 0xff;// 加盐  
                String str = Integer.toHexString(number);  
                if (str.length() == 1) {  
                    buffer.append("0");  
                }  
                buffer.append(str);  
            }  
            // 标准的md5加密后的结果  
            return buffer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    }  
}
