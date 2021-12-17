package com.example.theoldlist.settings

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.theoldlist.settings.wallpapersetting.WallpaperData


object PreferenceUtils {

    private const val CURRENTLY_SET_WALLPAPER_KEY = "wallpaper_key"

    private lateinit var sharedPreferences: SharedPreferences

    fun setup(activity: Activity) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    }


    val currentlySetWallpaperId: Int
        get() =
            sharedPreferences.getInt(
                CURRENTLY_SET_WALLPAPER_KEY,
                WallpaperData.defaultWallpaperId
            )

    val currentlySetWallpaper: Int
        get() =
            WallpaperData.getWallPaperDrawableResId(
                sharedPreferences.getInt(
                    CURRENTLY_SET_WALLPAPER_KEY,
                    WallpaperData.defaultWallpaperId
                )
            )


    fun setWallpaper(wallpaperId: Int) {
        with(sharedPreferences.edit()) {
            putInt(CURRENTLY_SET_WALLPAPER_KEY, wallpaperId)
            apply()
        }
    }

}