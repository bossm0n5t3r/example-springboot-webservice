package org.example.server.controller

import org.example.server.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(
    private val postsService: PostsService
) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave(): String {
        return "posts-save"
    }
}
