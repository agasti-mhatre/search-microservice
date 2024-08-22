package recommender.recommenderBackend.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Service
public class SearchUserService {

  @Autowired
  private SearchUserRepository searchUserRepository;

  public List<String> searchUser(String userId) {

    try {

      return traverseResponses(userId);

    } catch (IOException e) {

      e.printStackTrace();
    }

    return null;
  }

  private List<String> traverseResponses(String userId) throws IOException {

    List<String> usernameIDs = new ArrayList<>();

    List<Hit<UserRequestDTO>> listOfMatches = getResponses(userId);
    for (Hit<UserRequestDTO> hit : listOfMatches) {

      UserRequestDTO currUsername = hit.source();
      usernameIDs.add(currUsername.getId());
    }

    return usernameIDs;
  }

  private List<Hit<UserRequestDTO>> getResponses(String userId) throws IOException {

    SearchResponse<UserRequestDTO> searchResponse = searchUserRepository.searchUser(userId);
    return searchResponse.hits().hits();
  }

}
