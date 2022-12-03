package com.springboot.book.web;


import com.springboot.book.domain.posts.PostsService;
import com.springboot.book.web.dto.PostsListResponseDto;
import com.springboot.book.web.dto.PostsResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";

    }

}