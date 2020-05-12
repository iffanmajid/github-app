package com.po.kemon.data.model

data class Pokemon(
        val id: String,
        val name: String,
        val sprites: Sprites,
        val stats: List<Statistic>)