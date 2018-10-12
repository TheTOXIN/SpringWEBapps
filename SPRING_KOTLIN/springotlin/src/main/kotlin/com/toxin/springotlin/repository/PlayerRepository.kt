package com.toxin.springotlin.repository

import com.toxin.springotlin.entity.Player
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : CrudRepository<Player, String> {
    fun findTop3ByOrderByTotalScoreDesc() : List<Player>
}