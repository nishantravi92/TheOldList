package com.example.theoldlist.settings.wallpaper

import com.example.theoldlist.R

object WallpaperData {
    private val idToResMap =
        mapOf(
            Pair(1, R.drawable.compressed_mountain),
            Pair(2, R.drawable.compressed_waterfall),
            Pair(3, R.drawable.straight_road),
            Pair(4, R.drawable.milky_way_2)
        )

    val wallpaperIdAndResList: List<Pair<Int, Int>> get() = idToResMap.toList()

    val defaultWallpaperId get() = 3

    fun getWallPaperDrawableResId(wallpaperId: Int): Int = idToResMap[wallpaperId]!!
}