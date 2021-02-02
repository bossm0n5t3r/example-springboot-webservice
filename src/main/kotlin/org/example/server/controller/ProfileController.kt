package org.example.server.controller

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    private val env: Environment
) {
    @GetMapping("/profile")
    fun profile(): String {
        val profiles = env.activeProfiles.toList()
        val realProfiles = listOf("real", "real1", "real2")
        val defaultProfile = if (profiles.isEmpty()) "default" else profiles[0]
        return profiles.stream()
            .filter { realProfiles.contains(it) }
            .findAny()
            .orElse(defaultProfile)
    }
}
