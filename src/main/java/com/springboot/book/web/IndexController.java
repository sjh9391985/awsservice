package com.springboot.book.web;


import com.springboot.book.domain.posts.PostsService;
import com.springboot.book.web.dto.PostsListResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){

        List<PostsListResponseDto> temp = postsService.findAllDesc();

        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }



}
