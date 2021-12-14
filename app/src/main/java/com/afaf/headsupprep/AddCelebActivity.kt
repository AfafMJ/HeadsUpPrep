package com.afaf.headsupprep


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCelebActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var addButton: Button
    private lateinit var celebNameEditTexts: EditText
    private lateinit var celebTaboo1EditTexts: EditText
    private lateinit var celebTaboo2EditTexts: EditText
    private lateinit var celebTaboo3EditTexts: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        celebNameEditTexts = findViewById(R.id.etAddName)
        celebTaboo1EditTexts = findViewById(R.id.etAddTaboo1)
        celebTaboo2EditTexts = findViewById(R.id.etAddTaboo2)
        celebTaboo3EditTexts = findViewById(R.id.etAddTaboo3)

        backButton = findViewById(R.id.btBack)
        addButton = findViewById(R.id.btAdd)


        addButton.setOnClickListener {
           if (celebNameEditTexts.text.isNotEmpty()
               && celebTaboo1EditTexts.text.isNotEmpty()
               && celebTaboo2EditTexts.text.isNotEmpty()
               && celebTaboo3EditTexts.text.isNotEmpty() ){
               addCeleb()
           }else{
               Toast.makeText(this@AddCelebActivity, "Please enter the info!", Toast.LENGTH_SHORT).show()
           }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun addCeleb(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.addUser(
            CelebItem(
                0,
                celebNameEditTexts.text.toString(),
                celebTaboo1EditTexts.text.toString(),
                celebTaboo2EditTexts.text.toString(),
                celebTaboo3EditTexts.text.toString(),
            )
        ).enqueue(object : Callback<CelebItem> {

            override fun onResponse(
                call: retrofit2.Call<CelebItem>,
                response: Response<CelebItem>
            ) {
                Toast.makeText(this@AddCelebActivity, "Celebrity added!", Toast.LENGTH_SHORT).show()
                recreate()
            }

            override fun onFailure(call: retrofit2.Call<CelebItem>, t: Throwable) {
                Toast.makeText(this@AddCelebActivity, t.message, Toast.LENGTH_SHORT).show()

            }

        })
    }
}