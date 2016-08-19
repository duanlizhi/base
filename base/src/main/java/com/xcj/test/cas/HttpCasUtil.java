package com.xcj.test.cas;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.xcj.common.util.Constants;

public class HttpCasUtil {

	public static String getTicket(final String server, final String username, final String password,
			final String service) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		notNull(service, "service must not be null");
		//System.out.println("getTicketGrantingTicket:::"+getTicketGrantingTicket(server, username, password));
		 String getTicketGrantingTicket=getTicketGrantingTicket(server, username, password);
		//String getTicketGrantingTicket="TGT-37-y2iZfOD0rcGWplWRtkvrEoHonUtih37fj7eD2l1sGmfodl4nga-www.xcj.com";
		return getServiceTicket(server, getTicketGrantingTicket, service);
	}

	/**
	 * 取得ST
	 * @param server
	 * @param ticketGrantingTicket
	 * @param service
	 */
	public static String getServiceTicket(final String server, final String ticketGrantingTicket, final String service) {
		if (ticketGrantingTicket == null)
			return null;
		final HttpClient client = new HttpClient();
		final PostMethod post = new PostMethod(server + "/" + ticketGrantingTicket);
		post.setRequestBody(new NameValuePair[] { new NameValuePair("service", service) });
		try {
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
			switch (post.getStatusCode()) {
			case 200:
				return response;
			default:
				warning("Invalid response code (" + post.getStatusCode() + ") from CAS server!");
				info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
		}
		catch (final IOException e) {
			warning(e.getMessage());
		}
		finally {
			post.releaseConnection();
		}
		return null;
	}

	/**
	 * @param server
	 * @param username
	 * @param password
	 */
	public static String getTicketGrantingTicket(final String server, final String username, final String password) {
		final HttpClient client = new HttpClient();
		final PostMethod post = new PostMethod(server);
		post.setRequestBody(new NameValuePair[] { new NameValuePair("username", username),
				new NameValuePair("password", password) });
		try {
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
			info("TGT="+response);
			switch (post.getStatusCode()) {
			case 201: {
			    final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*")  
	              .matcher(response);  
				if (matcher.matches())
					return matcher.group(1);
				warning("Successful ticket granting request, but no ticket found!");
				info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
			default:
				warning("Invalid response code (" + post.getStatusCode() + ") from CAS server!");
				info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
		}

		catch (final IOException e) {
			warning(e.getMessage());
		}

		finally {
			post.releaseConnection();
		}

		return null;
	}

	public static String ticketValidate(String serverValidate, String serviceTicket, String service) {
		notNull(serviceTicket, "paramter 'serviceTicket' is not null");
		notNull(service, "paramter 'service' is not null");
		final HttpClient client = new HttpClient();
		GetMethod post = null;
		try {
			post = new GetMethod(serverValidate+"?"+"ticket="+serviceTicket+"&service="+URLEncoder.encode(service, "UTF-8"));
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
			//info(response);
			if(post.getStatusCode()==200){
				return response;
			}else{
				return "error";
			}
			
			/*switch (post.getStatusCode()) {
			case 200: {
				info("成功取得用户数据");
			}
			default: {
				info("失败");
			}
			}*/

		} catch (Exception e) {
			warning(e.getMessage());
		} finally {
			//释放资源
			post.releaseConnection();
		}
		return null;

	}
	
	/*&nbsp;&nbsp;&nbsp;删除tgt的API接口是 /v1/tickets/<tgt> ,delete方法 */

	public static String logout(String tgt,String server) {
		String result = "success";
		final HttpClient client = new HttpClient();
		final DeleteMethod delete = new DeleteMethod(server + "/" + tgt);
		try {
			client.executeMethod(delete);
			final String response = delete.getResponseBodyAsString();
			switch (delete.getStatusCode()) {
			case 200:
				break;
			default:
				result = "error";
				break;
			}
		} catch (IOException e) {
			result = "error";

		} finally {
			delete.releaseConnection();
		}
		return result;
	}
	

	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}
	
	public static void main(final String[] args) throws Exception {
	 	for (int i = 0; i < 1000; i++) {
	 		final String server = "http://cas.xautoservice.com/v1/tickets";
			final String username = "hh@163.com";
			final String password = "a123456";
			final String service = "http://www.xautoservice.com";
			final String proxyValidate = "http://cas.xautoservice.com/proxyValidate";
//			 ticketValidate(proxyValidate, getTicket(Constants.CAS_SERVER, username, password, service), service);
//		     	首次登录使用方法
//			  String tgt=getTicketGrantingTicket(Constants.CAS_SERVER, username, password);
//			 System.out.println("tgt:"+tgt);
//			  String st= getServiceTicket(Constants.CAS_SERVER, tgt, service);
//			 System.out.println("st:"+st);
//			  String result=ticketValidate(Constants.PROXYVALIDATE,st, service);
//			 System.out.println(result);   
		}
		/* if(result.contains("authenticationFailure")){
			 System.out.println("登录失败");
		 }else{
			 System.out.println("登录成功");
		 } 
		 
		 //退出使用方法
		 String logoutstr=logout("TGT-7-SubmWXZPOgSSpSEmbKNjqScYmtL7tmyzSXB5mXz7M7wajecvcN-www.xcj.com", server);
		 System.out.println(logoutstr);
		 
		 // 查是否登录
		  String rest=ticketValidate(proxyValidate, "ST-3-df5qRqU9XOOqXegGJv09-www.xcj.com", service);
		  System.out.println(rest);*/
 	}

	private static void warning(String msg) {
		System.out.println(msg);
	}

	private static void info(String msg) {
		System.out.println(msg);
	}

}