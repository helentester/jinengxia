/**
 * @author Helen 
 * @date 2018年5月29日  
 */
package common;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 描述：HTTP接口测试公共类
 */
public class BaseApi {
	CloseableHttpClient httpCilent = HttpClients.createDefault();
	HttpResponse httpResponse;

	public static void main(String[] args) throws IOException, URISyntaxException {
		BaseApi api = new BaseApi();
		
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(new URI("http://api.dev.jinengxia.com/session"))
				.addParameter("username", "du001")
				.addParameter("password", "123456")
				.build();
		HttpClientContext localContext = api.httpPost(httpUriRequest);
		api.httpGet("http://api.dev.jinengxia.com/user/person-ranking?schedule_id=384&stage_id=689",localContext);
	}

	/* http get请求 ,需要传递url,localContext（记录登录需要用到），返回json结果*/
	public JSONObject httpGet(String url,HttpClientContext localContext) {
		JSONObject jsonObject = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpResponse = httpCilent.execute(httpGet,localContext);// 执行请求
			String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
			System.out.println(strResult);
			assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
			jsonObject = JSONObject.fromObject(strResult);// 把结果转为json
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/* http post请求 */
	public HttpClientContext httpPost(HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {
		HttpClientContext localContext = HttpClientContext.create();//创建本地HTTP上下文,用于获取请求头cookies等内容
		httpResponse = httpCilent.execute(httpUriRequest,localContext);
		return localContext;
	}
}
