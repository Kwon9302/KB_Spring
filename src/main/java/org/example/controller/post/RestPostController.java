package org.example.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.post.PostDto;
import org.example.dto.post.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/v1/rest")
@CrossOrigin(origins = "http://localhost:5173")
public class RestPostController {

    private final PostRepository postRepository;
    private String context = "/post";

    @GetMapping("/show")
    public List<PostDto> postList(HttpServletRequest request) {
        log.info("게시글 목록 페이지 호출, " + request.getRequestURI());
        List<PostDto> list = postRepository.findAll();

        return list;
    }

    @GetMapping("/search")
    public List<PostDto> postSearch(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request)
    {
        log.info("=============== 게시글 검색 기능 호출, " + request.getRequestURI());
        List<PostDto> searchedList = postRepository.findByCondition(title, content);
        return searchedList;
    }
    @GetMapping("/update")
    public String postUpdate(@RequestParam("id") int id, Model model) {

//        int idd = Integer.parseInt(id);
        PostDto postDto = postRepository.findById(id);
        model.addAttribute("post", postDto);

        return context + "/post-update";
    }

    @PostMapping("/update")
    public String updateContent(@RequestParam("id") int id, @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                Model model) {
//        PostDto findId =postRepository.findById(idd);
//        System.out.println("findById : " + findId.getId());
        postRepository.updatePost(id, title, content);
//        model.addAttribute("postList", updateContent);
        return "redirect:/post/v1/show";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/post/v1/show";
    }

    @GetMapping("/new")
    public String newWritePage() {
        return context + "/post-new";
    }

//    @PostMapping("/new")
//    public String postSave(@ModelAttribute PostDto postDto) {
//        postRepository.save(postDto);
//        return "redirect:/post/v1/show";
//    }

    @PostMapping("/new")
    public String postSave(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        log.info("================> 게시글 추가 기능 호출, " + request.getRequestURI());

        postRepository.save(title, content);

        return "redirect:/post/v1/show";
    }
}
