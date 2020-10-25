package org.example.server.controller

import org.example.server.config.auth.dto.SessionUser
import org.example.server.controller.dto.PostsResponseDto
import org.example.server.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpSession

@Controller
class IndexController(
    private val postsService: PostsService,
    private val httpSession: HttpSession
) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        var user = httpSession.getAttribute("user")
        if (user != null) {
            user = user as SessionUser
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
