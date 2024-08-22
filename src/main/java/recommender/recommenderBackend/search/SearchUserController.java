package recommender.recommenderBackend.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchUserController {

  @Autowired
  private SearchUserService searchUserService;

  @GetMapping("/searchUser")
  public List<String> searchUser(@RequestBody UserRequestDTO userRequested) {

    return searchUserService.searchUser(userRequested.getId());
  }
}