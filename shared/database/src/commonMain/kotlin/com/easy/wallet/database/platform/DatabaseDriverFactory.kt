package com.easy.wallet.database.platform

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(password: String = ""): SqlDriver
}