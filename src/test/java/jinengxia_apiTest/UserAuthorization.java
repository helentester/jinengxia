/**
 * @author Helen 
 * @date 2018年6月5日  
 */
package jinengxia_apiTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

/**
 * 描述：用户授权相关接口
 */
public class UserAuthorization {
	CloseableHttpClient httpCilent = HttpClients.createDefault();
	HttpResponse httpResponse;

	@Test(description = "用户登录")
	public void login() throws URISyntaxException, ClientProtocolException, IOException {
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(new URI("http://api.dev.jinengxia.com/session"))
				.addParameter("username", "du001").addParameter("password", "123456").build();
		HttpClientContext localContext = HttpClientContext.create();// 创建本地HTTP上下文,用于获取请求头cookies等内容
		httpResponse = httpCilent.execute(httpUriRequest, localContext);
		String strResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
		System.out.println(strResult);
	}
}
