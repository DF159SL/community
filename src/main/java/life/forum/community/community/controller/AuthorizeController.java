package life.forum.community.community.controller;

import life.forum.community.community.dto.AccessTokenDTO;
import life.forum.community.community.dto.GithubUser;
import life.forum.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("20517cfc504c952b02e6");
        accessTokenDTO.setClient_secret("12cd666e90593e82d7c83540752873584fdb1ac9");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser user=githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";

    }
}
