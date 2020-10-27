package org.example.server.controller

import org.example.server.config.auth.LoginUser
import org.example.server.config.auth.dto.SessionUser
import org.example.server.controller.dto.PostsResponseDto
import org.example.server.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IndexController(
    private val postsService: PostsService
) {
    @GetMapping("/")
    fun index(model: Model, @LoginUser user: SessionUser?): String {
        model.addAttribute("posts", postsService.findAllDesc())
        if (user != null) {
            model.addAttribute("userName", user.name)
        }
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave(): String {
        return "posts-save"
    }

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        val dto: PostsResponseDto = postsService.findById(id)
        model.addAttribute("post", dto)
        return "posts-update"
    }
}
