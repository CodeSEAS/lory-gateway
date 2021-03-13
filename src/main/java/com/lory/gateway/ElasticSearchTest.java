package com.lory.gateway;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ElasticSearchTest {
    private static TransportClient client;
    static {
        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "hadoop").build();
            client = new TransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress("host1", 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void index() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");
        IndexResponse response = client.prepareIndex("books", book)
                .setSource(source).execute()
    }
}
