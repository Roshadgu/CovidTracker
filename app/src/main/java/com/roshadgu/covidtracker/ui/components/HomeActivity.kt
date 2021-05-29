package com.roshadgu.covidtracker.ui.components

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import com.roshadgu.covidtracker.ui.theme.CovidTrackerTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Horizontal
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


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
    val (tvSelectState, RadioGrpMetricSelection, RadioGrpTimeSelection) = createRefs()
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

    val imageView = findViewById(R.id.myimageview) as ImageView
    imageView.setImageResource(R.layout.robinhood_sparkview)

    Row( //Time Selection
      Modifier
        //.width(500.dp)
        .fillMaxWidth()
        .selectableGroup()
        .constrainAs(RadioGrpTimeSelection)
        {
          top.linkTo(RadioGrpMetricSelection.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
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

    /*Column(
      Modifier
        .constrainAs(RadioGrp)
        {
          top.linkTo(tvSelectState.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .selectableGroup()) {
        radioOptions.forEach{ text ->
          Row(
            Modifier
              .fillMaxWidth()
              .height(56.dp)
              .selectable(
                selected = (text == selectedOption),
                onClick = { onOptionSelected(text) },
                role = Role.RadioButton
              )
              .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(selected = (text == selectedOption), onClick = null)
            Text(text = text, style = MaterialTheme.typography.body1.merge(), modifier = Modifier.padding(start = 16.dp)
            )

          }
      }
    }*/
  }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun HomeScreenPreview()
{
  HomeScreen()
}