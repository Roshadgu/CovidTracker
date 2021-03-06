package com.roshadgu.covidtracker.ui.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import com.roshadgu.covidtracker.ui.theme.CovidTrackerTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.robinhood.spark.SparkView
import com.robinhood.ticker.TickerView
import com.roshadgu.covidtracker.R

class HomeActivity : AppCompatActivity()
{
  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContent {
      CovidTrackerTheme{
        HomeScreen()
      }
    }
  }
}

@Composable
fun HomeScreen()
{
  Surface(color = MaterialTheme.colors.surface) {}

  ConstraintLayout(modifier = Modifier.fillMaxSize())
  {
    val radioOptions = listOf("Negative", "Positve", "Death") //edit later
    val (tvSelectState, RadioGrpMetricSelection, RadioGrpTimeSelection, RHSparkView, RHTickerView, DateLabel) = createRefs()
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Text(
      text = "State",
      modifier = Modifier
        .constrainAs(tvSelectState){
          top.linkTo(parent.top, margin = 16.dp)
          start.linkTo(parent.start, margin = 16.dp)
        })

    Row( //Metric Selection
      Modifier
        //.width(500.dp)
        .fillMaxWidth()
        .selectableGroup()
        .constrainAs(RadioGrpMetricSelection)
        {
          top.linkTo(tvSelectState.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        },
      horizontalArrangement = Arrangement.Center)

    {
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, bottom = 8.dp, end = 3.dp))
      Text(text = "Negative")
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, end = 3.dp))
      Text(text = "Positive")
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, end = 3.dp))
      Text(text = "Death")
    }

    //Robhinhood Spark View
    AndroidView(
      modifier = Modifier
        .fillMaxHeight()
        .constrainAs(RHSparkView)
        {
          top.linkTo(RadioGrpMetricSelection.bottom)
          bottom.linkTo(RadioGrpTimeSelection.top)
        },
      factory = { context: Context ->
        val sparkview = LayoutInflater.from(context).inflate(R.layout.robinhood_spark, null, false)
        /*
        SparkView(context).apply {
          ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }*/
        sparkview
      }
    )

    Row( //Time Selection
      Modifier
        //.width(500.dp)
        .fillMaxWidth()
        .selectableGroup()
        .constrainAs(RadioGrpTimeSelection)
        {
          //top.linkTo(RHSparkView.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          bottom.linkTo(RHTickerView.top)
        },
      horizontalArrangement = Arrangement.Center)
    {
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, bottom = 8.dp, end = 3.dp))
      Text(text = "Week")
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, end = 3.dp))
      Text(text = "Month")
      RadioButton(selected = false, onClick = { /*TODO*/ }, Modifier.padding(start = 3.dp, end = 3.dp))
      Text(text = "Max")
    }

    Text(
      text = "May 31, 2020",
      Modifier
        .constrainAs(DateLabel)
        {
          start.linkTo(parent.start)
          top.linkTo(RadioGrpTimeSelection.bottom)
          end.linkTo(RHTickerView.start)
          bottom.linkTo(parent.bottom)
        }
        .padding(start = 16.dp)
        .height(80.dp)
    )

    Text(
      text = "4,354",
      //fontSize = 30.sp,
      Modifier
        .constrainAs(RHTickerView)
        {
          start.linkTo(DateLabel.end)
          end.linkTo(parent.end)
          //top.linkTo(RadioGrpTimeSelection.bottom)
          bottom.linkTo(parent.bottom)
        }
        .fillMaxWidth()
    )
  }
/*
  //Robinhood Ticker View
  AndroidView(
    modifier = Modifier
      .fillMaxSize()
      .constrainAs(RHTickerView)
      {
        //start.linkto(parent.end)
      },
    factory = { context -> TickerView(context).apply { } }
  )*/
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun HomeScreenPreview()
{
  HomeScreen()
}