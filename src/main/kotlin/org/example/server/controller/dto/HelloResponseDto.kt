package org.example.server.controller.dto

import lombok.Getter
import lombok.RequiredArgsConstructor

@Getter
@RequiredArgsConstructor
data class HelloResponseDto(
    val name: String,
    val amount: Int
)
