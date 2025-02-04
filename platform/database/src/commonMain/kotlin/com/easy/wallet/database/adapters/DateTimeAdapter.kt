package com.easy.wallet.database.adapters

import app.cash.sqldelight.ColumnAdapter
import com.easy.wallet.core.commom.DateTimeDecoder
import kotlinx.datetime.LocalDateTime

class DateTimeAdapter : ColumnAdapter<LocalDateTime, Long> {
  override fun decode(databaseValue: Long): LocalDateTime = DateTimeDecoder.decodeToDateTime(databaseValue)

  override fun encode(value: LocalDateTime): Long = DateTimeDecoder.encodeToLong(value)
}
