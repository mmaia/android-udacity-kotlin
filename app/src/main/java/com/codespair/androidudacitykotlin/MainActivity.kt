package com.codespair.androidudacitykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.codespair.androidudacitykotlin.utilities.NetworkUtils
import com.example.android.datafrominternet.R


class MainActivity : AppCompatActivity() {

  var mSearchBoxEditText: EditText? = null
  var mUrlDisplayTextView: TextView? = null
  var mSearchResultsTextView: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mSearchBoxEditText = findViewById(R.id.et_search_box)
    mUrlDisplayTextView = findViewById(R.id.tv_url_display)
    mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json)
  }

    /**
     * This method retrieves the search text from the EditText, constructs
     * the URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our (not yet created) {@link GithubQueryTask}
     */
    fun makeGithubSearchQuery() {
        val githubQuery = mSearchBoxEditText!!.getText().toString()
        val githubSearchUrl = NetworkUtils.buildUrl(githubQuery)
        mUrlDisplayTextView!!.setText(githubSearchUrl.toString())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
