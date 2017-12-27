package com.allinpay.channel.credit.base.common.util;



import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期Util类
 */
public class DateUtil {
	public static final String defaultDatePattern = "yyyyMMdd";
	
	public static final String DatePattern_yyyy_MM_dd = "yyyy-MM-dd";
	
	public static final String DatePattern_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String defaultTimePattern = "HHmmss";
	
	public static final String defaultTimePattern_HH_mm_ss = "HH:mm:ss";

	/**
	 * 获得默认的 date pattern
	 */
	private static String getDatePattern() {
		return defaultDatePattern;
	}
	/**
	 * 获得默认的 date pattern
	 */
	private static String getTimePattern() {
		return defaultTimePattern;
	}

	/**
	 * 返回 当前日期字符串
	 * @return yyyyMMdd 
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}
	/**
	 * 返回 当前日期前一天字符串
	 * @return yyyyMMdd 
	 */
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		String yesterday = format(cal.getTime());
		return yesterday;
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 * return YYYYMMDD
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}
	
	/**
	 * 使用预设Format格式化Date成字符串
	 * return HHmmss
	 */
	public static String formatTime(Date date) {
		return date == null ? "" : format(date, getTimePattern());
	}


	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isEmpty(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}
	/**
	 * 使用参数Format将字符串转为Date yyyy-MM-dd HH:mm:ss
	 */
	public static Date parseDateTime(String strDate) throws ParseException {
		return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(DatePattern_yyyy_MM_dd_HH_mm_ss).parse(strDate);
	}
	
	/**
	 * 使用参数Format将字符串转为Date yyyy-MM-dd HH:mm:ss
	 */
	public static Date parseDate(String strDate) throws ParseException {
		try{
			return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(DatePattern_yyyy_MM_dd_HH_mm_ss).parse(strDate);
		}catch (ParseException e) {
			return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(DatePattern_yyyy_MM_dd).parse(strDate);
		}
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 获得月末日
	 */
	public static Date getLastDayOfMonth(String year, String month, int flag) {

		Calendar cal = Calendar.getInstance();
		//年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		//月，因为Calendar里的月是从0开始，所以要-1
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		//日，设为一号
		cal.set(Calendar.DATE, 1);
		//月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, flag);
		//下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获得月末日: flag=1:获得上月月末日 flag=0：获得本月月末日
	 */
	public static Date getLastDayOfMonth(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//日，设为一号
		cal.set(Calendar.DATE, 1);
		//月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, flag);
		//下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获得周末日
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数天
	 * @param date
	 * @param n
	 * @return 
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数分
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMin(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}
	/**
	 * 得到当前时间
	 * @return HHmmss
	 */
	public static String GetCurrentTime() {
		StringBuffer result = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		if (h < 10) {
			result.append("0");
		}
		result.append(h);
		if (m < 10) {
			result.append("0");
		}
		result.append(m);
		if (s < 10) {
			result.append("0");
		}
		result.append(s);
		return result.toString();
	}

	/**
	 * 得到当前日期时间 
	 * @return yyyyMMddHHmmss
	 */
	public static String getCurrDateTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * Formats a Date object to return a date using the global locale.
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat(DatePattern_yyyy_MM_dd);
		return outFormat.format(date);
	}

	/**
	 * Formats a Date object to return a date and time using the global locale.
	 * 
	 * 返回日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat(DatePattern_yyyy_MM_dd_HH_mm_ss);
		return outFormat.format(date);
	}

	public static String formatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate5(Date myDate) {
		String strDate = getYear(myDate) + "-" + getMonth(myDate) + "-" + getDay(myDate);
		return strDate;
	}

	public static String formatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static long Date2Long(int year, int month, int date) {
		Calendar cld = Calendar.getInstance();
		month = month - 1;
		cld.set(year, month, date);
		return cld.getTime().getTime();
	}

	public static long Time2Long(int year, int month, int date, int hour, int minute, int second) {
		Calendar cld = Calendar.getInstance();
		month = month - 1;
		cld.set(year, month, date, hour, minute, second);
		return cld.getTime().getTime();
	}

	public static int getYear(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.YEAR);
	}

	public static int getMonth(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.MONTH) + 1;
	}

	public static int getDay(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.MINUTE);
	}

	public static int getSecond(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new Date(t));
		}
		return cld.get(Calendar.SECOND);
	}

	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.SECOND);
	}

	public static int getYear() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(Calendar.YEAR);
	}

	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(Calendar.MONTH) + 1;
	}

	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 把日期转成2006/12/30格式
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		if (date != null && date.trim().length() >= 8) {
			return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
		}
		return date;
	}

	public static String formatTime(String time) {
		if (time != null && time.trim().length() >= 6) {
			return time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
		}
		return time;
	}

	public static String formateTimeOfDay(String time) {
		if (time != null && time.trim().length() >= 12) {
			return time.substring(0, 4) + "/" + time.substring(4, 6) + "/" + time.substring(6, 8) + " "
					+ time.substring(8, 10) + "：" + time.substring(10, 12);
		}
		return time;
	}

	/**
	 * 获得两天之间相差天数 date-date1
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int getDiffDate(Date date, Date date1) {
		return (int) ((date.getTime() - date1.getTime()) / (24 * 3600 * 1000));
	}

	/**
	 * 获取两个时间的秒差  date-date1
	 * @param date
	 * @param date1
	 * @return
	 */
	public static long getDiffTime(Date date, Date date1) {
		return (date.getTime() - date1.getTime()) / 1000;
	}

	/**
	 * 从 yyyyMMddHHmmss格式的时间中 获取日期 yyyyMMdd
	 * @param time
	 * @return yyyyMMdd
	 */
	public static String getDateFromyyyyMMddHHmmss(String time) {
		return time.substring(0, 8);
	}

	/**
	 * 从 yyyyMMddHHmmss格式的时间中 获取时间 HHmmss
	 * @param time
	 * @return HHmmss
	 */
	public static String getTimeFromyyyyMMddHHmmss(String time) {
		return time.substring(8, 14);
	}

	
	/**
	    * 验证日期时间,短时间，形如  YYYYMMDD(20110101)
	    * @param
	    * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	    */
	    public static boolean isDateYYYYMMDD(String validateString) {
	       String regex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))$";
	       return patternValidator(regex, validateString);
	    }
	    
	    /**
	    * 正则表达式验证数据
	    * @param
	    * @param validateString 验证字符串
	    * @return 如果validateString 符合 regex的正则表达式格式,返回 <b>true </b>,否则为 <b>false </b>
	    */
	    public static boolean patternValidator(String regex, String validateString) {
	       Pattern pattern = Pattern.compile(regex);
	       Matcher m = pattern.matcher(validateString);
	       return m.matches();
	    }
	    
	    /**
	     * 获取当前时间是本月第几天
	     * @return
	     */
	    public static int getMonthDay(){
	    	Calendar calendar = Calendar.getInstance();  
	        int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);  
	        return DAY_OF_MONTH;
	    }
	    
	    /**
	     * 获取上个月第一天
	     * @return
	     */
	    public static String getLastMonthFirstDay(){
	    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    	Calendar cal = Calendar.getInstance();//获取当前日期 
	        cal.add(Calendar.MONTH, -1);
	        cal.set(Calendar.DAY_OF_MONTH,1);
	        String firstDay = format.format(cal.getTime());
	        return firstDay;
	    }
	    
	    /**
	     * 获取上个月最后一天
	     * @return
	     */
	    public static String getLastMonthLastDay(){
	    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    	Calendar cale = Calendar.getInstance();   
	        cale.set(Calendar.DAY_OF_MONTH,0);
	        String lastDay = format.format(cale.getTime());
	        return lastDay;
	    }
	    
	    /**
	     * 获取前一天
	     * @return
	     */
	    public static String getLastDay(){
	    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    	Calendar cale = Calendar.getInstance();   
	        cale.add(Calendar.DAY_OF_MONTH, -1);
	        String lastDay = format.format(cale.getTime());
	        return lastDay;
	    }
	    
	    /**
	     * 把接收到的日期转换为月日
	     * @return MM-dd
	     * @throws ParseException 
	     */
	    public static String formatMmDd(String date) throws ParseException{
	    	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	    	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
	    	Date dt = format1.parse(date);
	    	return format2.format(dt);
	    }
	    
	    
	    /**
	     * 	    计算当前时间与另一时间的时间间:分钟
	       
	    *@param ,，形如yyyyMMddHHmmss
	    
	    *@return interval 分钟
	  
	    *@throws ParseException 
	    
	    *@Time 上午11:40:36
	     */
	    public static long intervalTime(String time) throws ParseException{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	    	long timems = sdf.parse(time).getTime();//毫秒
	    	//long time2ms = sdf.parse(time2).getTime();//毫秒
	    	long  interval = System.currentTimeMillis() - timems;
	    	return interval/60000;
	    	
	    }

	    
	    /**
		    * 验证日期时间,短时间，形如  HHmmss(121212)
		    * @param
		    * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
		    */
		    public static boolean isHHmmss(String validateString) {
		       String regex = "^([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";
		       return patternValidator(regex, validateString);
		    }  
	    
	    
	    //判断一个字符串是不是一个合法的日期格式
	    public static boolean isValidDate(String str) {
	    	       boolean convertSuccess=true;
	   	    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");	
	    	            try {
	    	            	//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		    	            format.setLenient(false);
							format.parse(str);
						} catch (ParseException e) {
							// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			    	           convertSuccess=false;
						}
	    	         
	    	       return convertSuccess;
	    	 }
	    
	    //信用卡到期日分期数可用
	    public static boolean   isValiUse(String card_end_date,int nper) {
	    	//String card_end_date ="0616";
	    	//到期日年
	    	String cardYY = card_end_date.substring(2);
	    	//System.out.println("到期年："+cardYY);
	    	String cardMM = card_end_date.substring(0,2);
	    	//System.out.println("到期月："+cardMM);
            String DateString =  getToday();
           
            //当前年
            String dateYY = DateString.substring(2,4);
         //   System.out.println("当前年："+dateYY);
            //当前月
            String dateMM = DateString.substring(4,6);
          //  System.out.println("当前月："+dateMM);
            Integer yy =  (Integer.parseInt(cardYY) - Integer.parseInt(dateYY))*12;
            Integer mm =  Integer.parseInt(cardMM) - Integer.parseInt(dateMM);
         //   System.out.println("还剩可用月份："+(yy+mm));
          /*  if(yy+mm<nper){
           /* if(yy+mm<nper){
            	return false;
            }
	        return true;*/
            return ((yy+mm)>nper);
	    	
	    	
	    }
	    
	    /**
	     * 把接收到的日期yyyyMMdd转换为yyyy-MM-dd
	     * @return yyyy-MM-dd
	     * @throws ParseException 
	     */
	    public static String formatYyMmDd(String date){
			try {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = format1.parse(date);
				return format2.format(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }
}