package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    companion object {
        ImageView iv
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv=memeImageView
        loadMeme()
    }

    private fun loadMeme() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val url =response.getString(url)
                Glide.with(this).load(url).into(getContext().memeImageView)
            },
            Response.ErrorListener { })

        queue.add(jsonObjectRequest)


    }

    fun showNextMeme(view: View) {
        loadMeme()
    }

    fun shareMeme(view: View) {

    }
}