package com.codespair.androidudacitykotlin

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.codespair.androidudacitykotlin.R.id.tv_toy_names


class MainActivity : Activity() {

  private var mToysListTextView: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mToysListTextView = findViewById (tv_toy_names);

    val toyNames : Array<String> = ToyBox.getToyNames()

    for (toyName in toyNames) {
      mToysListTextView?.append(toyName + "\n\n\n")
    }

  }
}
