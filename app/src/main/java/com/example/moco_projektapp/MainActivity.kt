package com.example.moco_projektapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moco_projektapp.ui.theme.MOCO_ProjektappTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCO_ProjektappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Hier rufen wir die Navigation für die 3 Screens auf
                    AppNavigation()
                }
            }
        }
    }
}

    // 1. NAVIGATION: Steuert, welcher Screen gerade aktiv ist
    @Composable
    fun AppNavigation() {
        // Ein einfacher State, um sich zu merken, auf welchem Screen wir sind
        // "home", "tasks", "stats"
        var currentScreen by remember { mutableStateOf("home") }

        when (currentScreen) {
            "home" -> HomeScreen(
                onNavigateToTasks = { currentScreen = "tasks" },
                onNavigateToStats = { currentScreen = "stats" }
            )
            "tasks" -> TaskScreen(
                onBack = { currentScreen = "home" }
            )
            "stats" -> StatsScreen(
                onBack = { currentScreen = "home" }
            )
        }
    }


// SCREEN 1: Homescreen
@Composable
fun HomeScreen(
    onNavigateToTasks: () -> Unit,
    onNavigateToStats: () -> Unit
) {
    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Settings", fontSize = 16.sp)
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.LightGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Großer gelber Stern
            Text(
                text = "⭐",
                fontSize = 120.sp,
                color = Color(0xFFFFC107)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Hey User",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Du bist heute schon 200 Schritte\ngelaufen. Da geht bestimmt noch mehr!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Level Fortschritt
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LinearProgressIndicator(
                    progress = { 0.65f },
                    modifier = Modifier
                        .width(220.dp)
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = Color(0xFF4CAF50),
                    trackColor = Color(0xFFE0E0E0)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Level 20",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onNavigateToTasks,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Aufgaben", color = Color.White)
                }

                Button(
                    onClick = onNavigateToStats,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE))
                ) {
                    Text("Statistik", color = Color.Black)
                }
            }
        }
    }
}
// SCREEN 2: Aufgabenscreen
@Composable
fun TaskScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF2C2C2C) // dunkler Hintergrund
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Text("✕", fontSize = 28.sp, color = Color.White)
                }
                Text("⭐", fontSize = 32.sp, color = Color(0xFFFFC107))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aufgaben",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Aufgaben-Liste
            TaskItem("1. Laufe 30 Meter", 0.75f)
            TaskItem("2. Gehe 1000 Schritte", 0.15f)
            TaskItem("3. Führe ein Sprint durch", 0.55f)

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Aktualisiert sich in 14:39",
                color = Color.LightGray,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun TaskItem(title: String, progress: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFC107), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏃", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, color = Color.White, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = Color(0xFF4CAF50),
                    trackColor = Color.DarkGray
                )
            }
        }
    }
}

// SCREEN 3: Statistikscreen
@Composable
fun StatsScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF2C2C2C) // Dunkler Hintergrund wie im Original
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Text("✕", fontSize = 28.sp, color = Color.White)
                }
                Text(
                    text = "⭐",
                    fontSize = 42.sp,
                    color = Color(0xFFFFC107)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Statistik",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Balkendiagramm
            WeeklyBarChart()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Schritte dieser Woche: 4500",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Meilensteine Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Meilensteine",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    MilestoneItem("Insgesamt gelaufene Schritte", "34.000")
                    MilestoneItem("Insgesamt zurückgelegte Strecke", "30.000")
                    MilestoneItem("Insgesamt gesammelte Erfahrungspunkte", "312.201")
                }
            }
        }
    }
}

@Composable
private fun WeeklyBarChart() {
    val days = listOf("Mo", "Di", "Mi", "Do", "Fr", "Sa", "So")
    val values = listOf(65, 45, 85, 70, 95, 80, 75) // Prozentuale Höhen

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            days.forEachIndexed { index, day ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // Gelber Balken
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .height((values[index] * 1.8).dp) // Skalierung
                            .background(
                                color = Color(0xFFFFC107),
                                shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Wochentag
                    Text(
                        text = day,
                        fontSize = 13.sp,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun MilestoneItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 15.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


// LIVE PREVIEW
    @Preview(showBackground = true)
    @Composable
    fun AppPreview() {
        AppNavigation()
    }

   
