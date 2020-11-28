package board.springboot.web;

import board.springboot.config.auth.LoginUser;
import board.springboot.config.auth.dto.SessionUser;
import board.springboot.service.posts.PostsService;
import board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /**
     * Model
     * - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
     * - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
     *
     * @LoginUser
     * - 기존에 (User)httpSession.getAttribute("user"); 로 세션 정보를 가져오던 것을 개선
     * - 이제 어떤 컨트롤러에서든지 @LoginUser 어노테이션을 사용해 세션 정보를 가져올 수 있음.
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
