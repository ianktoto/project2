package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun generateRandomDate(startDate: LocalDate, endDate: LocalDate): LocalDate {
    val days = startDate.until(endDate).days
    val randomDays = Random.nextInt(days + 1)
    return startDate.plusDays(randomDays.toLong())
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf<User>()
        val startDate = LocalDate.of(2024, 3, 1)
        val endDate = LocalDate.of(2024, 3, 6)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
        val regions = listOf("russia", "usa", "china", "japan", "france", "italy")
        for (i in 0..20) {
            val randomDate = generateRandomDate(startDate, endDate)
            val regionIndex = Random.nextInt(regions.size)
            list.add(User("login${i}", Random.nextInt(0, 2),"last seen: ${randomDate.format(formatter)}", "region: ${regions[regionIndex]}"))
        }
        val customAdapter = UserListAdapter(list)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.adapter = customAdapter
    }
}

class User(
    val login: String,
    val type: Int,
    val last_seen: String,
    val region: String
)