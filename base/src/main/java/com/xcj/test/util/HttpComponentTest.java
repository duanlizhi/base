package com.xcj.test.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.xcj.common.db.DataSource;

public class HttpComponentTest {

	public CloseableHttpClient createHttpsClient() {

		X509TrustManager x509mgr = new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] xcs, String string) {

			}

			public void checkServerTrusted(X509Certificate[] xcs, String string) {

			}

			public X509Certificate[] getAcceptedIssuers() {

				return null;
			}
		};

		SSLContext sslContext = null;

		try {

			sslContext = SSLContext.getInstance("TLS");

		} catch (NoSuchAlgorithmException e1) {


			e1.printStackTrace();

		}

		try {

			sslContext.init(null, new TrustManager[] { x509mgr }, null);

		} catch (KeyManagementException e) {


			e.printStackTrace();

		}

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(

		sslContext,

		SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		return HttpClients.custom().setSSLSocketFactory(sslsf).build();

	}

	/**
	 * 
	 * @param args
	 * 
	 */
	public static String readTxtFile(String filePath){
		InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        OutputStream outputStream = null;
        try
        {
            InputStream inputStream = new FileInputStream(filePath);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);
             
            // 读取一行
            String line = null;
            StringBuffer strBuffer = new StringBuffer();
                 
            while ((line = bufferReader.readLine()) != null)
            {
                strBuffer.append(line);
            } 
            return strBuffer.toString();
             
        }
        catch (IOException e)
        {
        	return null;
        }
        finally
        {
        }
    }
	

}