package life.xigua.community.community.controller;


import life.xigua.community.community.dto.AccessTokenDTO;
import life.xigua.community.community.dto.GithubUser;
import life.xigua.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

        @GetMapping("/callback")
    public String callback(@RequestParam( name = "code") String code,
                           @RequestParam(name = "state") String state){
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
            accessTokenDTO.setClient_id("c262f46212b251232578");
            accessTokenDTO.setClient_secret("f0bd0ffe17d639e80d7003baac8bf573e57902b3");
            accessTokenDTO.setCode(code);
            accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
            accessTokenDTO.setState(state);
            String accessToken = githubProvider.getAccessToken(accessTokenDTO);
            GithubUser user = githubProvider.getUser(accessToken);
            System.out.println(user.getName());
            return "index";
        }
}
