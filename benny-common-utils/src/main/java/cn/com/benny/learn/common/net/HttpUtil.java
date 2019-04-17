/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-16 下午5:19
 * LastModified: 19-4-16 下午5:19
 */

package cn.com.benny.learn.common.net;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    //http连接池管理器
    private PoolingHttpClientConnectionManager pool;
    private RequestConfig requestConfig;


    private String proxyHostName ;

    private int socketTimeout = 5000;
    private int connectionRequestTimeout = 5000;
    private int connectTimeout = 5000;
    private int maxTotal = 200;
    private int defaultMaxPerRoute = 20;

    private static HttpUtil httpUtil = null;

    protected HttpUtil(String proxyHostName) {
        this.proxyHostName = proxyHostName;
        init();
    }

    protected HttpUtil() {
        init();
    }
    public static HttpUtil getInstance() {
        if(httpUtil == null){
            synchronized (HttpUtil.class){
                if(httpUtil == null){
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }
    public static HttpUtil getInstance(String proxyHostName) {
        if(httpUtil == null){
            synchronized (HttpUtil.class){
                if(httpUtil == null){
                    httpUtil = new HttpUtil(proxyHostName);
                }
            }
        }
        return httpUtil;
    }

    @PostConstruct
    private void init() {
        SSLConnectionSocketFactory sslsf = null;
        try {

            SSLContext sslContext  = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                    // TODO Auto-generated method stub
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null, NoopHostnameVerifier.INSTANCE);

        } catch (Exception e) {
            logger.error("",e );
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .build();
        pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        pool.setMaxTotal(this.maxTotal);
        pool.setDefaultMaxPerRoute(this.defaultMaxPerRoute);

        RequestConfig.Builder builder = RequestConfig.custom()
                .setSocketTimeout(this.socketTimeout)
                .setConnectionRequestTimeout(this.connectionRequestTimeout)
                .setConnectTimeout(this.connectTimeout);
        if (null == proxyHostName || "".equals(proxyHostName.trim())) {
            requestConfig = builder.build();
        } else {
            String[] split = proxyHostName.split(":");
            HttpHost proxy = new HttpHost(split[0], Integer.parseInt(split[1]));
            requestConfig = builder.setProxy(proxy).build();
        }
    }

    /**
     * 获取HttpClient
     *
     * @return HttpClient
     */
    public CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(pool)
                .build();
    }

    public HttpResult jsonPost(String url, Map<String, Object> params) {
        return jsonPost(url, new JSONObject(params));
    }

    public HttpResult jsonPost(String url, JSONObject params) {

        return post(url, "application/json", params.toJSONString());
    }

    public HttpResult newFormUrlencodedPost(String url, Map<String, Object> params) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("charset", "utf-8");
        List<BasicNameValuePair> collect = params.entrySet()
                .stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue().toString()))
                .collect(Collectors.toList());
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(collect, Charset.forName("UTF-8"));
        post.setEntity(urlEncodedFormEntity);
        HttpResult httpResult = getHttpResult(post);
        return httpResult;
    }

    public HttpResult formPost(String url, Map<String, Object> params) {
        String s = params.entrySet()
                .stream()
                .map(e -> new StringBuilder(e.getKey()).append("=").append(e.getValue().toString()).toString())
                .reduce((a1, a2) -> new StringBuilder(a1).append("&").append(a2).toString()).orElse("");

        return formPost(url,s);
    }

    public HttpResult post(String url,String contentType,  String params) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", contentType);
        post.setHeader("charset", "utf-8");
        post.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
        HttpResult httpResult = getHttpResult(post);
        return httpResult;
    }


    public HttpResult formPost(String url, String params) {
        return post(url,"application/x-www-form-urlencoded", params);
    }

    public HttpResult newXmlPost(String url, String xmlStr) {
        return post(url,"application/xml", xmlStr );
    }

    public static class HttpResult {
        public int statusCode;
        public String responseBody;
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResult postJson(String url, Map<String, Object> params){
        JSONObject jsonObject = new JSONObject(params);
        HttpResult httpResult = postJson(url, jsonObject);
        return  httpResult;
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResult postJson(String url, JSONObject params){
        logger.info("url:{}, postBody:{}",url, params.toJSONString());
        HttpResult httpResult = getInstance().jsonPost(url, params);

        return httpResult;
    }
    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResult postForm(String url, Map params){
        HttpResult httpResult = getInstance().formPost(url, params);
        return httpResult;
    }
    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResult postForm(String url, String params){
        HttpResult httpResult = getInstance().formPost(url, params);
        return httpResult;
    }

    /**
     *
     * @param url
     * @param jsonStr
     * @return
     */
    public static HttpResult postJson(String url, String jsonStr){
        if(null ==url || "".equals(url.trim())){
            return null;
        }
        logger.info("url:{}, postBody:{}",url, jsonStr);
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("charset", "utf-8");
        if(null != jsonStr){
            post.setEntity(new StringEntity(jsonStr, Charset.forName("UTF-8")));
        }
        return getHttpResult( post);
    }

    private static HttpResult getHttpResult(HttpPost post) {
        CloseableHttpClient httpClient = httpUtil.getHttpClient();
        HttpResult httpResult = new HttpResult();
        try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            httpResult.statusCode = statusCode;

            if (statusCode == HttpStatus.SC_OK) {
                String responseStr = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
                httpResult.responseBody = responseStr;
            }
            return httpResult;
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("HttpUtil.postJson 调用错误 url:{} ,params{}",url,params);
            throw new  RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

        String objStr = "{\"jzbMsg\":{\"data\":\"47DA790B715DB4C129BE43C8F1F1E4AB5D1A1616FC9C433084C09B6E58E0A3B05F49AA6184EB07CD6FEB6B0F05E5F64CB639A3A017D2D5084C3DA5173258679FBE6770DBDE59C0F7115A51260F78D50ADB2778A1232758FB2F10BDB617913A0C163594B1FA5EBDE622E8C95A94F8930EB2C983385A103CE4BCD4E8532FDD775BA442A87F27CBD90324A6505BEF0517E976AA78B72BEE282262B33954D0A84C28DF3B612B63ED7701E730DB3C3690BAD70F7415D60205FE5B726680313126AAA447B525E7731B26380D8B5DBA2AAE3554938F207C483B243FAAC55A8A708C3D1EFAEC124ED56417F295E65ADB33ACB809E662F5B84FFA85C5A2028338A3DCBED1341133057E5E4FCE89ABFCD60C6BFFEBB1F529D74E72658BA3123759976C4E41276CFAE1394EF4DD18A0B877790BAD3102F77F048289409F73E745FB5A5531D3EAD8B0F4367491B6BF2E501B6F58DC18C252D1EE7144028BEB8B14E6CF6BAF0AF62D2BDA993B1823C2BDB5B4DFEBFF6FDE24A954EFEF0C5EE7ADCEDA5681B2D9B5B13F817CAD51C0C3B3581FFD9E0BFD460EAF33F2E3C2E2BDF36598E8DABDAE475ADC6A485C24DC713A03556C8F43DF4132771FBDB65E467DA98192081FBE6C439477736FE6BC6F6E440B9957B8D1C758BE47E959B245B4631CB84213B6AA7177C0F0F04BF6D8A57FA7B59BE30C7E5E97038921FE54D0939ACFEF80AEEE0A0AC964B47F0B440B7827497603F7D87847E9114BD5B92A61714A10701804A6345C45CFE1A13802BBB2F6F9DB5A1BD930F7BA52C20D82D3ACEFE1E994620B85BCF565885C88E1DED88E676D85D818E9BB9EC0FC93CE0A0660E511CADDBF462B02FE5E5E6565FB843676CE61B4F1E23C3C0012F09C5A72E416082F73B004CD682DA9E6D197E18147A1C30319D65D78B0C20C500048B2F1F4F96C82C0B410E3F44257\",\"open_id\":\"89045a9d8bc89819e2e70ee8d563963b\",\"sign\":\"7970f52d1b456ff34e24d9544779210d\",\"timestamp\":\"1555321664\"},\"mobileNo\":\"18612345678\"}";
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "benny");
        map.put("name2", "benny2");
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = JSONObject.parseObject(objStr);
        HttpResult httpResult = postJson("https://www.baidu.com/", jsonObject1);
        System.out.println();
        System.out.println(httpResult.statusCode);
        System.out.println(httpResult.responseBody);

        HttpResult httpResult1 = postForm("https://sc-cc-test.yun.city.pingan.com/wuxi-acquirers-front/data/payorder", map);
        int statusCode = httpResult1.statusCode;
        System.out.println();
        System.out.println(statusCode);
        System.out.println(httpResult.responseBody);

    }

}