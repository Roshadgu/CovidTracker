package com.roshadgu.covidtracker

import java.util.*

data class CovidData(
  val dateChecked: Date,
  val positivieIncrease: Int,
  val negativeIncrease: Int,
  val deathIncrease: Int,
  val state: String
)