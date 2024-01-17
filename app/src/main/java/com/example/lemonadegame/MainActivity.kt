package com.example.lemonadegame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonadegame.ui.theme.LemonadeGameTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeGameTheme {
                mainScreen()
            }
        }
    }
}

@Composable
fun mainScreen(){

//    TopMenu()

    var currentStep by remember{ mutableStateOf(1) }

    var squeezeCount by remember{ mutableStateOf(0) }

        when(currentStep){
                1 -> {
                    lemonadeScreen(
                        textId = R.string.Lemon_tree,
                        ResourceId = R.drawable.lemon_tree,
                        contentDescription = R.string.lemon_tree_description,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    lemonadeScreen(
                        textId = R.string.Lemon,
                        ResourceId = R.drawable.lemon_squeeze,
                        contentDescription = R.string.lemon_description,
                        onImageClick = {
                            squeezeCount --
                            if(squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }
                3 -> {
                    lemonadeScreen(
                        textId = R.string.Glass,
                        ResourceId = R.drawable.lemon_restart,
                        contentDescription = R.string.Glass_description,
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }
                4 -> {
                    lemonadeScreen(
                        textId = R.string.Empty,
                        ResourceId = R.drawable.lemon_drink,
                        contentDescription = R.string.Empty_description,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }

        }


//    lemonadeScreen()
}

@Composable
fun lemonadeScreen(
    textId:Int,
    ResourceId:Int,
    contentDescription:Int,
    onImageClick: () -> Unit,
    modifier: Modifier=Modifier) {
    Box(modifier =modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = onImageClick,
                    shape= RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius))
            
                ) {
                Image(
                    painter = painterResource(id = ResourceId),
                    contentDescription = stringResource(id = contentDescription),
                    modifier= Modifier
                        .width(dimensionResource(id = R.dimen.button_image_width))
                        .height(dimensionResource(id = R.dimen.button_image_height))
                        .padding(dimensionResource(id = R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                stringResource(id = textId),
                style = MaterialTheme.typography.bodyLarge

            )

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenu() {
 Scaffold (
     topBar = {
         TopAppBar(
             title = {
                 Text("Lemonade Game")
             }
         )
     }
         ){

 }
  }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeGameTheme {
//        lemonadeScreen()
    }
}