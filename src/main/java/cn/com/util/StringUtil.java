package cn.com.util;

import java.util.Collection;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class StringUtil {
	/**
	 * 判断对象是否为空
	 * User:T.L
	 * Description:
	 * @param obj
	 * @return
	 *
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && String.valueOf(obj).trim().equals("")) {
			return true;
		} 
//		else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
//			return true;
//		} 
		else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 将对象转为字符串
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		return obj==null?"":obj.toString();
	}
}
