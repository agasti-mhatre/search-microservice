package recommender.recommenderBackend.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;

@Repository
public class SearchUserRepository {

  @Autowired
  private ElasticsearchClient elasticsearchClient;

  @Value("${elasticsearch.usernameSearch.index}")
  private String userSearchIndex;

  @Value("${elasticsearch.usernameSearch.id}")
  private String id;

  public SearchResponse<UserRequestDTO> searchUser(String userId) throws IOException {

    return elasticsearchClient.search(createSearchRequest(userId), UserRequestDTO.class);
  }

  public SearchRequest createSearchRequest(String userId) {

    SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
    searchRequestBuilder.index(userSearchIndex);
    searchRequestBuilder.query(createQuery(userId));

    return searchRequestBuilder.build();
  }

  private Query createQuery(String userId) {

    Query.Builder queryBuilder = new Query.Builder();
    queryBuilder.match(createMatchQuery(userId));

    return queryBuilder.build();
  }

  private MatchQuery createMatchQuery(String userId) {

    MatchQuery.Builder matchQueryBuilder = new MatchQuery.Builder();
    matchQueryBuilder.field(id);
    matchQueryBuilder.query(userId);

    return matchQueryBuilder.build();
  }

}
