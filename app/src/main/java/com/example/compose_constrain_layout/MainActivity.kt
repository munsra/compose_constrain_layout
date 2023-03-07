package com.example.compose_constrain_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.compose_constrain_layout.ui.theme.Compose_constrain_layoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraints = ConstraintSet {
                val greenbox = createRefFor("greenbox")
                val foodList = createRefFor("food_list")
                val redBox = createRefFor("redbox")

                constrain(greenbox){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(100.dp)
                }
                constrain(foodList) {
                    top.linkTo(greenbox.bottom)
                    bottom.linkTo(redBox.top)
                    start.linkTo(parent.start)
                    height = Dimension.fillToConstraints
                }
                constrain(redBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(100.dp)
                }
            }
            ConstraintLayout(constraintSet = constraints, modifier = Modifier.fillMaxSize()) {
                val itemsTest = listOf("Wiener Schnitzel", "Gianni's Ljubljanska", "Gorizia's Rose", "Gnocchi al Cinghiale", "Prosciutto Cotto e Kren", "Tiramisù", "Gubana", "Kipfel", "Salame con Aceto", "Repa", "Crauti", "Frittata alle Erbe", "Mortadella", "Brovada e Muset", "Gulash")
                val scrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .background(Color.Green)
                        .layoutId("greenbox")
                )
                Column(modifier = Modifier.verticalScroll(scrollState).layoutId("food_list")) {
                    itemsTest.forEach {
                        Card(modifier = Modifier
                            .fillMaxWidth()){
                            Box(modifier = Modifier.padding(16.dp)){
                                Text(it)
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .layoutId("redbox")
                )
            }
        }
    }
}
