package com.xcj.common.util.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;

import com.xcj.admin.xcjenum.XMLTypes;

/**
 * 提供字符窜处理的一些常用方法
 * 
 * @author su_jian
 * @version 1.0
 * @create <br>
 *         2011-08-18
 */
public class StringUtil {
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
			"yyyy-MM-dd");

	/* 创建中国的货币格式 */
	private static NumberFormat currencyFormat = NumberFormat
			.getCurrencyInstance(Locale.CHINA);
	
	/**
	 * 
	 * 
     * <b>function:</b>计算分数
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static Integer countScore(String answer,Map xml){
		 int score = 0;
		 if(answer!=""	&&	answer!=null){
			 String[] answers = answer.split(",");
			  for(int i=0;i<answers.length;i++){
				  score = score+isNullM(xml.get(answers[i]));
			  }
		 }	
		return score;
	}
	
	/**
	 * 
     * <b>function:</b> 去重复
     * @project xcjedu
     * @package com.xcj.common.util  
     * @fileName @param url
     * @fileName @return
     * @createDate Jul 16, 2014 3:04:03 PM
     * @author yy.niu
	 */
	public static Integer array_unique(String[] a) {
		Set<String> set = new HashSet<String>();
		String[] pass = new String[] {};
		if(a!=null){
			set.addAll(Arrays.asList(a));
			pass = set.toArray(new String[0]);
			
		}
		 if(pass.length==14){
			 return 1;
		 }else
		 {
			 return 0;
		 }
		
	}
	
 
	/**
	 * 
     * <b>function:</b> 对比分数是否通过
     * @project xcjedu
     * @package com.xcj.common.util  
     * @fileName @param url
     * @fileName @return
     * @createDate Jul 16, 2014 3:04:03 PM
     * @author yy.niu
	 */
	public static Integer isPass(Integer score,Integer isScore){
		if(isScore!=null	&& score!=null){
			if(score>=isScore){
				return 1;
			}
		}
		
		return 0;
	}

	/**
	 * <b>function:</b> 判断如果为空返回0
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Sep 25, 2015 5:00:05 PM
	 * @author yy.niu
	 */
	public static Integer isNullI(String number) {
		if (number != null && !number.equals("")) {
			return Integer.parseInt(number);
		}
		return 0;
	}
	
	/**
	 * 
	 * 
     * <b>function:</b>
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static String [] toRepeat(Object object){
		Set<String> set = new HashSet<String>();
		String[] pass = new String[] {};
		if(object!=null){
			String[] aa =  object.toString().split(",");
			set.addAll(Arrays.asList(aa));
			pass = set.toArray(new String[0]);
		}
		return pass;
	}
	
	/**
     * <b>function:</b>百分比
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static String Percentage(String [] number){
		 
		if(number!=null){
			int  pre =   Integer.valueOf(number.length);
			 int x = 20;
		        double baiy = pre * 1.0;
		        double baiz = x * 1.0;
		        NumberFormat nf = NumberFormat.getPercentInstance();
		        nf.setMinimumFractionDigits(2);
				return nf.format(baiy / baiz);
		}
       return "0";
	}
	
	/**
     * <b>function:</b>
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static boolean isbool(Element element){
		if (element != null) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * 
	 * 
     * <b>function:</b>
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static boolean isType(Element element){
		if(element != null){
			if(!element.getText().endsWith("1") &&
			   !element.getText().endsWith("2")	&&
			   !element.getText().endsWith("3")){
				return false;
			}
		}
		return true;
	}
	
     
	/**
	 * 
	 * <b>function:</b> 根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Feb 25, 2015 5:00:05 PM
	 * @author yy.niu
	 */
    public static boolean DeleteFolder(String sPath) {
    	boolean flag = false;
    	File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
	
     
	/**
	 * 
	 * <b>function:</b> 删除单个文件
	 *  sPath    被删除文件的文件名
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @return 单个文件删除成功返回true，否则返回false
	 * @createDate Sep 25, 2015 5:00:05 PM
	 * @author yy.niu
	 */
    public static boolean deleteFile(String sPath) {
    	boolean flag = false;
    	File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    
    /**
	 * 
	 * <b>function:</b> 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Feb 25, 2015 5:00:05 PM
	 * @author yy.niu
	 */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 * 
	 * <b>function:</b> 判断如果为空返回0
	 * 
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Feb 25, 2015 5:00:05 PM
	 * @author yy.niu
	 */
	public static Integer isNullI(Object number) {
		if (number != null && !number.equals("")) {
			return Integer.parseInt(number.toString());
		}
		return 0;
	}

	/**
	 * <b>function:</b>判断如果为空返回0
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public static String isNullEmtp(Element element) {
		if (element != null) {
			return element.getText();
		}
		return "0";
	}
	
	/**
	* @param obj 判断传入参数是否为空，如果为空则返回false ，如果正确则返回true
	* @return
	*/
	public static boolean isTypeMatching(String fileType ){
		 
			if(!fileType.equals(XMLTypes.DMCCOURSEWARE.getType())	&&	
			   !fileType.equals(XMLTypes.SBTFAULT.getType())	&&
			   !fileType.equals(XMLTypes.SBTCOURSEWARE.getType()) &&
			   !fileType.equals(XMLTypes.WBTTESTS.getType())	&&
			   !fileType.equals(XMLTypes.WBTCOURSEWARE.getType())	&&
			   !fileType.equals(XMLTypes.EMUFAULT.getType())	&&
			   !fileType.equals(XMLTypes.EMUCOURSEWARE.getType())   &&
			   !fileType.equals(XMLTypes.SBTQUESTION.getType())){
				return false;
			}
		return true;
	}
	
	/**
	* @param obj 判断传入参数是否为空，如果为空则返回false ，如果正确则返回true
	* @return
	*/
	public static boolean isTypeNull(String fileType ){
		 
			if(!fileType.equals("sbt")	&&	
			   !fileType.equals("wbt")	&&
			   !fileType.equals("emu")  &&
			   !fileType.equals("dmc")){
				return false;
			}
		return true;
	}
	
	/**
     * <b>function:</b> String 转int
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static Integer isNullzh(Element element) {
		if (element != null) {
			return Integer.parseInt(element.getText());
		}
		return 0;
	}
	/**
	 * 
     * <b>function:</b>写入txt文件
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static boolean writeTxt(String txtUrl,String obj){
		boolean bo = true;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(txtUrl+".txt"), true));
			writer.write(obj);
	        writer.flush();
	        writer.close();
	        bo  = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return bo;
	}
	
	/**
	 * 
	 * 
	 * <b>function:</b>判断如果为空返回0
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public static Integer isNullEmtps(Element element) {
		if (element != null) {
			return Integer.parseInt(element.getText());
		}
		return 0;
	}
	
	/**
	 * 
	 * 
     * <b>function:</b>截取字符串
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public static String interceptString(String url){
		int indexbegin=url.indexOf("data");
		String urls = url.substring(indexbegin);
		return urls;
	}
	
	/**
	 * 
	 * 
	 * <b>function:</b>判断如果为空返回0
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public static String isNull(Element element) {
		if (element != null) {
			return element.getText();
		}
		return "0";
	}

	/**
	 * 
	 * <b>function:</b> map转int判断如果为空返回0
	 * 
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Sep 25, 2014 5:00:05 PM
	 * @author yy.niu
	 */
	public static Integer isNullM(Object object) {
		if (object != null && !object.equals("")) {
			return Integer.parseInt(object.toString());
		}
		return 0;
	}

	public static Integer isNullI(Integer number) {
		if (number != null && !number.equals("")) {
			return number;
		}
		return 0;
	}

	/**
	 * 
	 * <b>function:</b> 判断如果为空返回0
	 * 
	 * @project xcjedu
	 * @package com.xcj.common.util
	 * @fileName @param number
	 * @fileName @return
	 * @createDate Sep 25, 2014 5:00:05 PM
	 * @author yy.niu
	 */
	public static String isNullS(String numbers) {
		if (numbers != null && !numbers.equals("")) {
			return numbers;
		}
		return "0";
	}

	public static String isNummO(String obj) {
		if (obj != null && !obj.equals("")) {
			return obj.toString();
		}
		return "";

	}

	public static Integer isNumm(String obj) {
		if (obj != null && !obj.equals("")) {
			return Integer.parseInt(obj);
		}
		return 0;

	}

	/**
	 * 取得随机主文件名
	 */
	public synchronized static String getRndFilename() {
		return String.valueOf(System.currentTimeMillis());
		// 取得毫秒数，用来做文件的名字不会重复。
	}

	/**
	 * 取得文件的拓展名
	 * 
	 * @param filename
	 *            文件名称
	 */
	public synchronized static String getFileExtName(String filename) {
		int p = filename.indexOf(".");
		return filename.substring(p);
	}

	/**
	 * 取得格式化后的中国货币字符串
	 * 
	 * @param money
	 *            double 类型的钱
	 */
	public static String formatCcurrency(double money) {
		return currencyFormat.format(money);
	}

	/**
	 * 截取指定字节数的字符串,且确保汉字不被拆分
	 * 
	 * @param text
	 *            要截取的字符窜
	 * @param textMaxChar
	 *            字节数
	 */
	public static String cutString(String text, int textMaxChar) {
		int size, index;
		String result = null;
		if (textMaxChar <= 0) {
			result = text;
		} else {
			for (size = 0, index = 0; index < text.length()
					&& size < textMaxChar; index++) {
				size += text.substring(index, index + 1).getBytes().length;
			}
			result = text.substring(0, index);
		}
		return result;
	}

	/**
	 * 为以逗号分隔的字符串的每个单元加入引号,如:aa,bb-->'aa','bb'
	 * 
	 * @param 要分割的字符窜
	 */
	public static String formatString(String src) {
		StringBuffer result = new StringBuffer();
		result.append("");
		if (src != null) {
			String[] temp = src.split(",");
			result.append("'" + temp[0] + "'");
			for (int i = 1; i < temp.length; i++) {
				result.append(",'" + temp[i] + "'");
			}
		}
		return result.toString();
		// 本实例已经测试过
	}

	/**
	 * HTML代码的Escape处理方法
	 * 
	 * @param 要处理的HTML代码
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * HTML代码的UnEscape处理方法
	 * 
	 * @param 要处理的HTML代码
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 判断字符窜是否为空或者是否为null
	 * 
	 * @param str
	 *            要判断的字符窜
	 * @return true or false
	 */
	public static boolean isEmptyYl(String str) {
		if ("".equals(str) || null == str) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return (str != null) && (str.length() > 0);
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	/**
	 * 将第一个字母转换为大写
	 * 
	 * @param str
	 *            要转的字符窜
	 * @return 转换后的字符窜
	 */
	public static String upperFirstChar(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 去掉空格的实例
	 * 
	 * @param str
	 *            参数
	 * @return 去掉空格后的字符窜
	 */
	public static String trim(String str) {
		return str.trim();
	}

	public static String[] toStringArray(Object[] o) {
		String[] s = new String[o.length];
		for (int i = 0; i < o.length; ++i) {
			s[i] = o[i].toString();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public static String[] toStringArray(Collection c) {
		String[] s = new String[c.size()];
		int n = 0;
		for (Iterator i = c.iterator(); i.hasNext(); ++n) {
			s[n] = ((String) i.next());
		}
		return s;
	}

	public static final boolean isEmptys(String s) {
		return s == null || s.length() == 0 || s.toLowerCase().equals("null");
	}

	/**
	 * 截取字符窜的方法
	 * 
	 * @param str
	 * @return
	 */
	public static String splitStr(String str) {

		if (str.length() > 10) {
			str = str.substring(11, str.length() - 1) + "...";
		}
		return str;
	}

	/**
	 * @author su_jian 生成随机文件的名字
	 * @return
	 */
	public static String generateRandomUUid() {
		Calendar calCurrent = Calendar.getInstance();
		Date date = calCurrent.getTime();
		SimpleDateFormat fmtDate = new SimpleDateFormat("yyyyMMddhhmmss");
		String sj = fmtDate.format(date);
		Random random = new Random();
		int uuid = random.nextInt();
		uuid = uuid > 0 ? uuid : (-1) * uuid;
		return sj + UUID.randomUUID().toString();
	}

	/**
	 * 去掉左边和右边的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimLeftRight(String str) {
		return str.trim();
	}

	/**
	 * 去掉所有的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimAll(String str) {
		return str.replace(" ", "");
	}

	/**
	 * 去掉所有的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimAll1(String str) {
		return str.replaceAll("\\s*", "");
	}

	/**
	 * 
	 * @param resource
	 * @param ch
	 * @return
	 */
	public String trimAll(String resource, char ch) {
		StringBuffer buffer = new StringBuffer();
		int position = 0;
		char currentChar;

		while (position < resource.length()) {
			currentChar = resource.charAt(position++);
			if (currentChar != ch)
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://")
				|| url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	/**
	 * 分割并且去除空格
	 * 
	 * @param str
	 *            待分割字符串
	 * @param sep
	 *            分割符
	 * @param sep2
	 *            第二个分隔符
	 * @return 如果str为空，则返回null。
	 */
	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);
		// trim
		for (int i = 0, len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			StringBuilder sb = new StringBuilder(html.length());
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param keyword
	 *            源词汇
	 * @param smart
	 *            是否智能分词
	 * @return 分词词组(,拼接)
	 */
	// TODO 有待实现暂时有问题
	/*
	 * public static String getKeywords(String keyword, boolean smart) {
	 * StringReader reader = new StringReader(keyword); IKSegmenter iks = new
	 * IKSegmenter(reader, smart); StringBuilder buffer = new StringBuilder();
	 * try { Lexeme lexeme; while ((lexeme = iks.next()) != null) {
	 * buffer.append(lexeme.getLexemeText()).append(','); } } catch (IOException
	 * e) { } //去除最后一个, if (buffer.length() > 0) {
	 * buffer.setLength(buffer.length() - 1); } return buffer.toString(); }
	 */

	/**
	 * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
	 * 
	 * @param str
	 * @param search
	 * @return
	 */
	public static boolean contains(String str, String search) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}

	public static boolean containsKeyString(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		if (str.contains("'") || str.contains("\"") || str.contains("\r")
				|| str.contains("\n") || str.contains("\t")
				|| str.contains("\b") || str.contains("\f")) {
			return true;
		}
		return false;
	}

	// 将""和'转义
	public static String replaceKeyString(String str) {
		if (containsKeyString(str)) {
			return str.replace("'", "\\'").replace("\"", "\\\"").replace("\r",
					"\\r").replace("\n", "\\n").replace("\t", "\\t").replace(
					"\b", "\\b").replace("\f", "\\f");
		} else {
			return str;
		}
	}

	public static String getSuffix(String str) {
		int splitIndex = str.lastIndexOf(".");
		return str.substring(splitIndex + 1);
	}

	public static String getEmptyFromNull(String username) {
		if (username == null) {
			return "";
		} else {
			return username;
		}
	}
	
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamptoStr(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date_sdf);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, date_sdf);
		return new Timestamp(date.getTime());
	}
	/**
	 * 字符串转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	@SuppressWarnings("unused")
	public static String date2Str(SimpleDateFormat date_sdf) {
		Date date=new Date();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

		
	public static String nullToEmpty(String element) {
		if (element == null||"".equals(element)) {
			return "";
		}
		return element;
	}
	
	
	public static void main(String[] args) {
		System.out.println(StringUtil.trimAll(" x x  "));
	}
}
