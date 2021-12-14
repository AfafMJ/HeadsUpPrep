package com.afaf.headsupprep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var celebsList: Celeb
    private lateinit var recyclerView: RecyclerView
    private lateinit var addCelebButton: Button
    private lateinit var submitButton: Button
    private lateinit var celebName: EditText
    lateinit var rvAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        celebsList = Celeb()
        recyclerView = findViewById(R.id.rvCelebs)
        addCelebButton = findViewById(R.id.btAddCeleb)
        submitButton =findViewById(R.id.btSubmit)
        celebName = findViewById(R.id.etCelebName)

        rvAdapter = RecyclerViewAdapter(celebsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gettUser()

        addCelebButton.setOnClickListener {
            val intent = Intent(this, AddCelebActivity::class.java)
            startActivity(intent)

        }
        submitButton.setOnClickListener {

            if (celebName.text.isNotEmpty()){
                searchCeleb()
            }else {

                Toast.makeText(this, "Please enter name!", Toast.LENGTH_SHORT).show()
            }


        }



    }

    private fun gettUser(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getUser()?.enqueue(object : Callback<Celeb> {

            override fun onResponse(
                call: retrofit2.Call<Celeb>,
                response: Response<Celeb>
            ) {
                celebsList = response.body()!!
                recyclerView.adapter = RecyclerViewAdapter(celebsList)
            }

            override fun onFailure(call: retrofit2.Call<Celeb>, t: Throwable) {

            }
        })
    }

    private fun searchCeleb() {

        for (C in celebsList) {
            if (celebName.text.toString() == C.name) {

                val intent = Intent(this, UpdateDeleteActivity::class.java)
                intent.putExtra("celeb_id", C.pk)
                intent.putExtra("celeb_name", C.name)
                intent.putExtra("celeb_taboo1", C.taboo1)
                intent.putExtra("celeb_taboo2", C.taboo2)
                intent.putExtra("celeb_taboo3", C.taboo3)
                startActivity(intent)
            }else {

                Toast.makeText(this, "No such celebrity", Toast.LENGTH_SHORT).show()
            }
        }

    }
}