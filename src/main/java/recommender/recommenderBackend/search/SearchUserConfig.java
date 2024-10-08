package recommender.recommenderBackend.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
public class SearchUserConfig {

  @Value("${elasticsearch.conn}")
  private String conn;

  @Value("${elasticsearch.port}")
  private int port;

  @Bean
  public ElasticsearchClient elasticsearchClient() {

    RestClient restClient = RestClient.builder(
            new HttpHost(conn, port)
    ).build();

    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper()
    );

    return new ElasticsearchClient(transport);
  }

}
