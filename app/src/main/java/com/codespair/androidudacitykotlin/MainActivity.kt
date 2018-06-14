/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codespair.androidudacitykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity(), GreenAdapter.ListItemClickListener {
  private val NUM_LIST_ITEMS = 100

  /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
  private var mAdapter: GreenAdapter? = null
  private var mNumbersList: RecyclerView? = null

  // COMPLETED (9) Create a Toast variable called mToast to store the current Toast
  /*
     * If we hold a reference to our Toast, we can cancel it (if it's showing)
     * to display a new Toast. If we didn't do this, Toasts would be delayed
     * in showing up if you clicked many list items in quick succession.
     */
  private var mToast: Toast? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
    mNumbersList = findViewById(R.id.rv_numbers) as RecyclerView

    /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         */
    val layoutManager = LinearLayoutManager(this)
    mNumbersList!!.setLayoutManager(layoutManager)

    /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
    mNumbersList!!.setHasFixedSize(true)

    // COMPLETED (13) Pass in this as the ListItemClickListener to the GreenAdapter constructor
    /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
    mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)
    mNumbersList!!.setAdapter(mAdapter)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    getMenuInflater().inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    val itemId = item.itemId

    when (itemId) {
    /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
      R.id.action_refresh -> {
        // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
        mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)
        mNumbersList!!.setAdapter(mAdapter)
        return true
      }
    }

    return super.onOptionsItemSelected(item)
  }

  // COMPLETED (10) Override ListItemClickListener's onListItemClick method
  /**
   * This is where we receive our callback from
   * [com.example.android.recyclerview.GreenAdapter.ListItemClickListener]
   *
   * This callback is invoked when you click on an item in the list.
   *
   * @param clickedItemIndex Index in the list of the item that was clicked.
   */
  override fun onListItemClick(clickedItemIndex: Int) {
    // COMPLETED (11) In the beginning of the method, cancel the Toast if it isn't null
    /*
         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
         * ensures that our new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         *
         * Comment out these three lines, run the app, and click on a bunch of
         * different items if you're not sure what I'm talking about.
         */
    if (mToast != null) {
      mToast!!.cancel()
    }

    // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
    /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
    val toastMessage = "Item #$clickedItemIndex clicked."
    mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG)

    mToast!!.show()
  }
}
