package com.landmuc.kmmtranslator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform