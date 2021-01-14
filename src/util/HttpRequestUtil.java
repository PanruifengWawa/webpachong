package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class HttpRequestUtil {

	 private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };

	    private static void trustAllHosts() {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return new java.security.cert.X509Certificate[]{};
	            }

	            public void checkClientTrusted(X509Certificate[] chain, String authType) {
	            }

	            public void checkServerTrusted(X509Certificate[] chain, String authType) {
	            }
	        }};
	        // Install the all-trusting trust manager
	        try {
	            SSLContext sc = SSLContext.getInstance("TLS");
	            sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	public static String sendGet(String httpUrl) {
		BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    trustAllHosts();
	    try {
	        URL url = new URL(httpUrl);
	        HttpsURLConnection connection = (HttpsURLConnection) url
	                .openConnection();
	        connection.setRequestProperty("Accept", "*/*");
	        connection.setRequestMethod("GET");
	        // replace it with your own cookie
	        connection.setRequestProperty("cookie", "__wpkreporterwid_=48d7df25-4e0b-48d4-069f-511d40c5fa31; lzd_cid=f644a4dc-d797-4b09-eccd-c29ca4c4ab67; t_uid=f644a4dc-d797-4b09-eccd-c29ca4c4ab67; t_fv=1610464921392; t_sid=iOA1oD6WPX2UArNR8RWnmaK5tC7uoQXs; utm_origin=https://www.baidu.com/link?url=JTrVubyXLf8x84iz30mhDSbWkXM9jmAXsVbs4hMQjl9_qFDUbewXI1-Td6mvBhk2&wd=&eqid=a472fbfb002f77f4000000045ffdbe93; utm_channel=SEO; cna=wBX2FxPPaiYCAW+7VFy76CR1; _gcl_au=1.1.1438261807.1610464923; lzd_sid=12713a9374aba3fc04baab2f2ceaa493; _m_h5_tk=a9eecdc51dd92b4d6042193e2b18cde2_1610472483991; _m_h5_tk_enc=13aef590f0e194b2dd1f4eaac9e3b704; _bl_uid=3Xkh5jp1udz5y98q2gLXopv8bgsF; hng=MY|en-MY|MYR|458; userLanguageML=en; _tb_token_=7e549ebea76bb; xlly_s=1; JSESSIONID=6955237B2B4FE56338B8E2A1977F53EC; EGG_SESS=S_Gs1wHo9OvRHCMp98md7JFZCbWWJtvJeK5Of42eKXFez6plqeaUv_QiFIqtqgc5PkhLWlDAWgah0GjzpK_FSrC2MqMLm77vpkHMvcVCJP25xCSfhjpimtRXxjgI4r6QkwmyP_Q6UraZfQDZfPdN9Ex7_YsD68aoYRxYY5VIeXM=; _ga=GA1.3.544745731.1610464982; _gid=GA1.3.1517932885.1610464982; x5sec=7b22617365727665722d6c617a6164613b32223a2233626530626661303739313735623665343361626162613339356336616531624349372f39763846454b4c557765752f71344b6d3841453d227d; _uetsid=f083f89054e911ebb6c37ba1277589ea; _uetvid=f0843e7054e911eba5010dc742636557; _gat_UA-30335348-1=1; tfstk=c_tNBn9YyiAQ1FsjyGs4lW6Mn5sOZwxkDWWfSEidSWwZ0tQGimNAKjIf8a1-xNf..; l=eBPOiW-4OqsBL_ADBOfahurza77OSIRvkuPzaNbMiOCPOq1p5QAhWZ8Qg789C3hVhs1MR3-n_RoHBeYBcQOSnxvtRsGtxDDmn; isg=BOLiWDroycB3bNWc4kmeCZM-M25EM-ZNCtLjfSx7aNUs_4N5FsKLXDlvKDsDOV7l; _fbp=fb.2.1610465241363.728279277");
	        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)");
	        //add some other headers
	        
	        
	        connection.setHostnameVerifier(DO_NOT_VERIFY);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead + "\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public static String sendPost(String httpUrl,String postData) {
		BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type",
	                        "application/x-www-form-urlencoded");

	        connection.setDoOutput(true);
	        connection.getOutputStream().write(postData.getBytes("UTF-8"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
