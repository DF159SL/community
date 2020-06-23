package life.forum.community.community.controller;

import life.forum.community.community.dto.AccessTokenDTO;
import life.forum.community.community.dto.GithubUser;
import life.forum.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirect;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state) throws Exception {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(client);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirect);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user=githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";

    }
}
