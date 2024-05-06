package com.example.verifeye

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var mediaBiasInfoAdapter: MediaBiasInfoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        val educationLayout = findViewById<LinearLayout>(R.id.educationLayout)

        educationLayout.setOnClickListener {
            val intent = Intent(this, EducationActivity::class.java)
            startActivity(intent)
        }

        val discussionLayout = findViewById<LinearLayout>(R.id.discussionForumLayout)

        discussionLayout.setOnClickListener {
            val intent = Intent(this, DiscussionActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        searchView = findViewById(R.id.searchView)

        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close).apply {
            drawerLayout.addDrawerListener(this)
            syncState()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mediaBiasInfoAdapter = MediaBiasInfoAdapter(ArrayList())
        recyclerView.adapter = mediaBiasInfoAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isEmpty()) {
                    recyclerView.visibility =
                        View.VISIBLE
                    mediaBiasInfoAdapter.filter.filter(newText)
                } else {
                    recyclerView.visibility = View.GONE
                }
                return true
            }
        })


        loadMediaBiasData()

        navigationView.setNavigationItemSelectedListener { item ->
            handleNavigationItemSelected(item)
            true
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressed()
                }
            }
        })
    }

    private fun loadMediaBiasData() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://political-bias-database.p.rapidapi.com/MBFCdata")
            .get()
            .addHeader("X-RapidAPI-Key", "2bee28da37msh91c49b1497646dap1aa6afjsnd8860462fe6a")
            .addHeader("X-RapidAPI-Host", "political-bias-database.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread { Toast.makeText(this@MainActivity, "Error loading data", Toast.LENGTH_LONG).show() }
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonString = response.body!!.string()
                    val jsonArray = JSONArray(jsonString)
                    val mediaBiasInfoList = mutableListOf<MediaBiasInfo>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val mediaBiasInfo = MediaBiasInfo(
                            jsonObject.getString("name"),
                            jsonObject.getString("url"),
                            jsonObject.getString("bias"),
                            jsonObject.getString("factual"),
                            jsonObject.getString("credibility")
                        )
                        mediaBiasInfoList.add(mediaBiasInfo)
                    }
                    runOnUiThread { mediaBiasInfoAdapter.updateData(mediaBiasInfoList) }
                }
            }
        })
    }

    private fun handleNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_preferences -> Toast.makeText(this, "Preferences", Toast.LENGTH_SHORT).show()
            R.id.nav_settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.nav_report -> Toast.makeText(this, "Report", Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> {
                Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}