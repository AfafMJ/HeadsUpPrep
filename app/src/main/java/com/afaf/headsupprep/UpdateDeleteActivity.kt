package com.afaf.headsupprep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDeleteActivity : AppCompatActivity() {

    private lateinit var celebNameDPEditTexts: EditText
    private lateinit var celebTaboo1DPEditTexts: EditText
    private lateinit var celebTaboo2DPEditTexts: EditText
    private lateinit var celebTaboo3DPEditTexts: EditText
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var backButton: Button
    private lateinit var celebItem: CelebItem
    var celebID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_del)




        celebNameDPEditTexts = findViewById(R.id.etPutDelName)
        celebTaboo1DPEditTexts = findViewById(R.id.etPutDelTaboo1)
        celebTaboo2DPEditTexts = findViewById(R.id.etPutDelTaboo2)
        celebTaboo3DPEditTexts = findViewById(R.id.etPutDelTaboo3)

        celebItem = CelebItem (
            intent.extras!!.getInt("celeb_id", 0),
            intent.extras!!.getString("celeb_name", ""),
            intent.extras!!.getString("celeb_taboo1", ""),
            intent.extras!!.getString("celeb_taboo2", ""),
            intent.extras!!.getString("celeb_taboo3", ""),
        )

        celebNameDPEditTexts.setText(celebItem.name)
        celebTaboo1DPEditTexts.setText(celebItem.taboo1)
        celebTaboo2DPEditTexts.setText(celebItem.taboo2)
        celebTaboo3DPEditTexts.setText(celebItem.taboo3)

        deleteButton = findViewById(R.id.btDel)
        updateButton = findViewById(R.id.btPut)
        backButton = findViewById(R.id.btBack2)




        deleteButton.setOnClickListener {
            deleteCeleb()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        updateButton.setOnClickListener {
            updateCeleb()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun updateCeleb() {
        celebItem = CelebItem(
            celebItem.pk,

            celebNameDPEditTexts.text.toString(),
            celebTaboo1DPEditTexts.text.toString(),
            celebTaboo2DPEditTexts.text.toString(),
            celebTaboo3DPEditTexts.text.toString(),
        )
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.updateUser(celebItem.pk, celebItem)
            .enqueue(object : Callback<CelebItem> {

            override fun onResponse(
                call: retrofit2.Call<CelebItem>,
                response: Response<CelebItem>
            ) {
                Toast.makeText(applicationContext, "User update!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: retrofit2.Call<CelebItem>, t: Throwable) {

            }

        })
    }

    private fun deleteCeleb() {
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.deleteUser(
            celebItem.pk
        )

            .enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "User deleted!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

            })
    }
}