package com.toxin.springotlin.entity

import org.springframework.data.annotation.TypeAlias
import javax.persistence.Id

@TypeAlias("player")
data class Player(
        @Id val handle: String,
        val totalScore: Int = 0,
        val history: List<Score> = listOf()
) {
    operator fun plus(score: Int) =
            Player(
                    handle,
                    totalScore + score,
                    history + Score(score)
            )
}

