package com.xcj.test.pyq;

import java.util.HashMap;

import org.htmlparser.util.ParserException;

import com.xcj.common.util.common.HttpUtil;

public class TestClient {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParserException {
		HashMap map =new HashMap();
		String url="http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5OTc5NDMyMQ==&appmsgid=200040132&itemidx=2&sign=48a8e39768b7ce2d78607baba43d358e&scene=2&from=timeline&isappinstalled=0#wechat_redirect";
		String url1="http://www.vshgj.com/index.php?g=Wap&m=Index&a=content&token=qtbdpy1412086805&id=143&wecha_id=o4QnrjjiFxUR7bpD_W1PFsT28gXo";
		String result=HttpUtil.doGet(url, map);
 	System.out.println(result);
		int s=result.indexOf("<title>");
		int e=result.indexOf("</title>");
		System.out.println(result.substring(s+7, e));
		
	}

}
