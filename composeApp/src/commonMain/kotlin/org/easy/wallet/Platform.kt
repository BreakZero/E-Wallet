package org.easy.wallet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform