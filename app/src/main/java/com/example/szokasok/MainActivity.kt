package com.example.szokasok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.szokasok.ui.theme.SzokasokTheme
import kotlin.collections.mutableListOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Szokas()
            }
        }
    }
}

@Composable
fun Szokas() {
    val habits = remember { mutableStateListOf<Szokas>(Szokas("ivas"), Szokas("alvas"), Szokas("valami")) }

    var newHabit by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
        {
            TextField(value = newHabit,
                onValueChange = {newHabit = it},
                label = {Text("Új szokás neve")})

            Button(onClick = {
                if (newHabit.isNotBlank()){
                    habits.add(Szokas(newHabit))
                }
            },
                modifier = Modifier
                .padding(top = 16.dp)
            ) {
                Text("Új szokás hozzáadása")
            }

            if(habits.isEmpty()){
                Text("Még nincs szokás")
            }
            else{
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), verticalArrangement = Arrangement.Center,) {
                    items(habits) { habit ->
                        Habit(
                            habit = habit)
                    }
                }
            }
    }

}




@Composable
fun Habit(habit: Szokas){
    var checked by remember { mutableStateOf(habit.done) }

    Row( modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = habit.name)
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                habit.done = it
            }
        )
    }
}