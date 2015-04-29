package com.imeet.yunchanbao.http;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 
 * @usage HTTP访问辅助工具
 * @author arjinmc
 * @email arjinmc@hotmail.com
 * @website http://www.hicsg.com
 * @version 2014-1-16
 */
public class HttpHelper {
	
	//设置超时时间
	public static final int TIMEOUT_DURATION=5000;
	//超时提示
	public static final String TIMEOUT = "timeout";
	/**jso n解析错误*/
	public static final String JSON_ERROR = "jsonerror";
	
	/**标记为请求完*/
	public static final int REQUEST_FINISH= 0xffff;
	public static final int REQUEST_SUCCESS = 10000;
	public static final int REQUEST_NEWORK_ERROR = -11111; 
	
	/**Context对象*/
	public static Context myContext;
	
	/***
	 * http POST访问
	 * @param context
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttp(Context context,String url,List<NameValuePair> params){
		String result = TIMEOUT;
		
		
		if(isNeworkWorking(context)){
			 // HttpPost连接对象  
	        HttpPost httpRequest = new HttpPost(url);  
	        
	        HttpParams httpParameters = new BasicHttpParams();
		    // Set the timeout in milliseconds until a connection is established.
		    HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_DURATION);
		    // Set the default socket timeout (SO_TIMEOUT)
		    // in milliseconds which is the timeout for waiting for data.
		    HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_DURATION);
	       
		    
	        try {  
	        	
	    		 // 设置字符
	            HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");  
	            // 请求httpRequest  
	            httpRequest.setEntity(httpentity);  
	            // 取得默认的HttpClient  
	            HttpClient httpclient = new DefaultHttpClient(httpParameters);  
	            // 取得HttpResponse  
	            HttpResponse httpResponse = httpclient.execute(httpRequest);  
	            Log.d("http", httpResponse.getStatusLine().getStatusCode()+"");
	            // HttpStatus.SC_OK表示连接成功  
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
	                // 取得返回的字符串  
	                String strResult = EntityUtils.toString(httpResponse.getEntity());  
	                result = strResult;
	            }
	           
	        } catch (ClientProtocolException e) {  
	        } catch (IOException e) {  
	        } catch (Exception e) { 
	        } finally{
	        	return result;
	        }
		}       
		
		return result;
	}
	
	/**
	 * http GET访问
	 * @param context
	 * @param url
	 * @return
	 */
	public static String getHttp(Context context,String url){
		String result = TIMEOUT;
		
		
		if(isNeworkWorking(context)){
			 // HttpPost连接对象  
	        HttpPost httpRequest = new HttpPost(url);  
	        
	        HttpParams httpParameters = new BasicHttpParams();
		    // Set the timeout in milliseconds until a connection is established.
		    HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_DURATION);
		    // Set the default socket timeout (SO_TIMEOUT)
		    // in milliseconds which is the timeout for waiting for data.
		    HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_DURATION);	       
		    
		    HttpClient client = new DefaultHttpClient();  
	        HttpGet get = new HttpGet(url);  
	        HttpResponse response;  
	        try {  
	            response = client.execute(get);  
	            int status = response.getStatusLine().getStatusCode();  
	          if (status == HttpStatus.SC_OK) {  
	        	  Log.d("http", status+"");
	        	  result = EntityUtils.toString(response.getEntity(),"UTF-8");  
	          }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally{
	        	return result;
	        }
		}       
		
		return result;
	}	
	/**
	 * 从服务器请求获取到JSON数据格式的字符串
	 * @param url_path
	 * @return
	 */
	public static String getJsonContent(String url_path) {
        try {
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000); // 请求超时时间3s
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            int code = connection.getResponseCode(); // 返回状态码
            if (code == 200) {
                // 或得到输入流，此时流里面已经包含了服务端返回回来的JSON数据了,此时需要将这个流转换成字符串
                return changeInputStream(connection.getInputStream());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

    private static String changeInputStream(InputStream inputStream) {
        // TODO Auto-generated method stub
        String jsonString = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] data = new byte[1024];
        try {
            while (-1 != (length = inputStream.read(data))) {
                outputStream.write(data, 0, length);
            }
            // inputStream流里面拿到数据写到ByteArrayOutputStream里面,
            // 然后通过outputStream.toByteArray转换字节数组，再通过new String()构建一个新的字符串。
            jsonString = new String(outputStream.toByteArray());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return jsonString;
    }
	/**
	 * 判断网络是否可用
	 * @param act
	 * @return
	 */
	public static  boolean isNeworkWorking(Context context) { 
		ConnectivityManager manager = null;
		if(context!=null){
			manager = (ConnectivityManager) context  
		    		   .getSystemService(  
		                     Context.CONNECTIVITY_SERVICE);  
		}
		
       if (manager == null) {  
           return false;  
       }  
        
       NetworkInfo networkinfo = manager.getActiveNetworkInfo();  
        
       if (networkinfo == null || !networkinfo.isAvailable()) {  
           return false;  
       }  
   
       return true;  
	}  
	
	/**
     * 以流的方式读返回信息
     * @param is
     * @return
     * @throws IOException
     */
    private static String readInputStream(InputStream is) throws IOException{
        if(is == null)
            return null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while((len = is.read(buf))!=-1)
        {
            bout.write(buf, 0, len);
        }
        is.close();
        return new String(bout.toByteArray());
    }
	
}
