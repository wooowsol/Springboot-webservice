package com.sol.web;

import com.sol.web.dto.PostsResponseDto;
import com.sol.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
// starter-mustache 로 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동 지정.
// ex) .(View Resolver) 앞은 src/main/resources/templates 뒤에는 .mustache 붙음.

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){ // Model model 서버 탬플릿 엔진에서 사용할 수 있는 객체 저장.
        model.addAttribute("posts", postsService.findAllDesc()); // 여기서 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; // /posts/save를 호출하면 posts-save.mustache를 호출하는 메소드
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}