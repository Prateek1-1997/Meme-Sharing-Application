package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String?=null

    class string {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()

    }

    private fun loadMeme() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                currentImageUrl = response.getString("url")
                Glide.with(this).load(currentImageUrl).into(memeImageView)
            },
            {
                Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)


    }

    fun showNextMeme(view: View) {

        loadMeme()
    }

    fun shareMeme(view: View) {
       val intent =Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"checkout this new meme$currentImageUrl")
        val chooser=Intent.createChooser(intent,"share this meme using..")
        startActivity(chooser)
    }
}

