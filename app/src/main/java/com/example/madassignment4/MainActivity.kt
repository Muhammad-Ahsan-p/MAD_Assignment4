package com.example.madassignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var btnCity:Button
    private lateinit var edtCity:EditText
    private lateinit var txtTemperature:TextView

    private var url = "https://api.openweathermap.org/data/2.5/weather?&appid=553a31c240415d44501e3166b29c8de1&units=metric"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCity = findViewById(R.id.btn_city)
        edtCity = findViewById(R.id.edt_city)
        txtTemperature = findViewById(R.id.txt_temperature)
        btnCity.setOnClickListener {
            getTemperature()
        }
    }
    private fun getTemperature(){
        var city = edtCity.text.toString()

        if(city != ""){
            val queue = Volley.newRequestQueue(this)
            var url = url+"&q=${city}"

            val request = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener { response ->
                    txtTemperature.setText(response.getJSONObject("main").getString("temp").toString()+"\u2103")
                },
                Response.ErrorListener {
                    txtTemperature.setText("Oops!!")
                }
            )
            queue.add(request)
        }

    }
}