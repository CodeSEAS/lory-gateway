package com.lory.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoinMarketAPI {
    private static String apiKey = "968f4ca6-20ba-474b-8fbc-8ca9a821d5ad";

    public static void main(String[] args) {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("start","1"));
        paratmers.add(new BasicNameValuePair("limit","5000"));
        paratmers.add(new BasicNameValuePair("convert","USD"));

        try {
            Map result = makeAPICall(uri, paratmers);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException, SchedulerException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(response_content, Map.class);
        LinkedHashMap map1 = (LinkedHashMap)((ArrayList)map.get("data")).get(0);
        LinkedHashMap map2 = (LinkedHashMap)(map1.get("quote"));
        LinkedHashMap map3 = (LinkedHashMap) map2.get("USD");
        Double Bitcoin_Price = (Double) map3.get("price");
//        JsonNode rootNode = mapper.readTree(response_content);
//        ArrayList list = (ArrayList) map.get("data");
        if (Bitcoin_Price > 50000) {
            String messageBody = "Hi, " + System.lineSeparator() + "The Bitcoin price is: " + Bitcoin_Price;
            EmailCronJob.body = messageBody;
            QuartzTest.trigger();
        }
        return map;
    }
}
