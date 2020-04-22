package com.example.restaurantorder
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {

    lateinit var databaseHelper: MyDBHandler
    lateinit var adapter: RestaurantOrderAdapter
    private var wordsList: MutableList<Order> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = MyDBHandler(this, null, null, 1)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        wordList.layoutManager = LinearLayoutManager(this)
        val testData = createTestData()

        // Create the PartAdapter
        // 1st parameter: our generated testData
        // 2nd parameter: item click handler function (implemented below) as function parameter
        adapter = RestaurantOrderAdapter(testData) { word : String -> partItemClicked(word) }
        wordList.adapter = adapter
    }

    private fun createTestData(): MutableList<Order> = mutableListOf(Order("Kevin", "burger"))
    private fun partItemClicked(word: String) {
        Toast.makeText(this, "Clicked: $word", Toast.LENGTH_LONG).show()
    }

    fun save(view: View) {
        databaseHelper.addOrder(Order(wordToAddTxt.text.toString(), nameToAddTxt.text.toString()))
        wordToAddTxt.setText("")
        nameToAddTxt.setText("")
        Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()

        wordsList = databaseHelper.getAllWords()
        adapter.updateOrder(wordsList)
    }
}
