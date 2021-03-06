package board.springboot.web;

import board.springboot.service.posts.PostsService;
import board.springboot.web.dto.PostsResponseDto;
import board.springboot.web.dto.PostsSaveRequestDto;
import board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @RequiredArgsConstructor
 * - Controller, Service에서 보통 @Autowired를 사용하여 Bean을 주입한다.
 * - Bean을 주입하는 방법은 다음과 같은 방식이 있다(@Autowired, setter, 생성자)
 * - 이 중 가장 권장되는 방식은 생성자이다. (@Autowired는 권장하지 않음)
 * - @RequiredArgsConstructor에서 생성자는 해결해준다.
 * - final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해준 것.
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
