package com.codespair.androidudacitykotlin

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GreenAdapter(numberOfItems: Int, listener: ListItemClickListener) : RecyclerView.Adapter<GreenAdapter.NumberViewHolder>() {

  private val TAG = GreenAdapter::class.java.simpleName

  // COMPLETED (3) Create a final private ListItemClickListener called mOnClickListener
  /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
  val mOnClickListener: ListItemClickListener = listener

  /*
     * The number of ViewHolders that have been created. Typically, you can figure out how many
     * there should be by determining how many list items fit on your screen at once and add 2 to 4
     * to that number. That isn't the exact formula, but will give you an idea of how many
     * ViewHolders have been created to display any given RecyclerView.
     *
     * Here's some ASCII art to hopefully help you understand:
     *
     *    ViewHolders on screen:
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 0 |
     *        *-----------------------------*
     *        |         ViewHolder index: 1 |
     *        *-----------------------------*
     *        |         ViewHolder index: 2 |
     *        *-----------------------------*
     *        |         ViewHolder index: 3 |
     *        *-----------------------------*
     *        |         ViewHolder index: 4 |
     *        *-----------------------------*
     *        |         ViewHolder index: 5 |
     *        *-----------------------------*
     *        |         ViewHolder index: 6 |
     *        *-----------------------------*
     *        |         ViewHolder index: 7 |
     *        *-----------------------------*
     *
     *    Extra ViewHolders (off screen)
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 8 |
     *        *-----------------------------*
     *        |         ViewHolder index: 9 |
     *        *-----------------------------*
     *        |         ViewHolder index: 10|
     *        *-----------------------------*
     *        |         ViewHolder index: 11|
     *        *-----------------------------*
     *
     *    Total number of ViewHolders = 11
     */
  private var viewHolderCount: Int = 0

  private val mNumberItems: Int = 0


  /**
   * The interface that receives onClick messages.
   */
  interface ListItemClickListener {
    fun onListItemClick(clickedItemIndex: Int)
  }

  /**
   *
   * This gets called when each new ViewHolder is created. This happens when the RecyclerView
   * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
   *
   * @param viewGroup The ViewGroup that these ViewHolders are contained within.
   * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
   * can use this viewType integer to provide a different layout. See
   * [android.support.v7.widget.RecyclerView.Adapter.getItemViewType]
   * for more details.
   * @return A new NumberViewHolder that holds the View for each list item
   */
  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NumberViewHolder {
    val context = viewGroup.context
    val layoutIdForListItem = R.layout.number_list_item
    val inflater = LayoutInflater.from(context)
    val shouldAttachToParentImmediately = false

    val view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately)
    val viewHolder = NumberViewHolder(view)

    viewHolder.viewHolderIndex.setText("ViewHolder index: $viewHolderCount")

    val backgroundColorForViewHolder = ColorUtils
        .getViewHolderBackgroundColorFromInstance(context, viewHolderCount)
    viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder)

    viewHolderCount++
    Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: $viewHolderCount")
    return viewHolder
  }

  /**
   * OnBindViewHolder is called by the RecyclerView to display the data at the specified
   * position. In this method, we update the contents of the ViewHolder to display the correct
   * indices in the list for this particular position, using the "position" argument that is conveniently
   * passed into us.
   *
   * @param holder   The ViewHolder which should be updated to represent the contents of the
   * item at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
    Log.d(TAG, "#$position")
    holder.bind(position)
  }

  /**
   * This method simply returns the number of items to display. It is used behind the scenes
   * to help layout our Views and for animations.
   *
   * @return The number of items available
   */
  override fun getItemCount(): Int {
    return mNumberItems
  }

  // COMPLETED (5) Implement OnClickListener in the NumberViewHolder class
  /**
   * Cache of the children views for a list item.
   */
  inner class NumberViewHolder
  /**
   * Constructor for our ViewHolder. Within this constructor, we get a reference to our
   * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
   * onClick method below.
   * @param itemView The View that you inflated in
   * [GreenAdapter.onCreateViewHolder]
   */
  (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    // Will display the position in the list, ie 0 through getItemCount() - 1
    var listItemNumberView: TextView
    // Will display which ViewHolder is displaying this data
    var viewHolderIndex: TextView

    init {

      listItemNumberView = itemView.findViewById(R.id.tv_item_number) as TextView
      viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance) as TextView
      // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
      itemView.setOnClickListener(this)
    }

    /**
     * A method we wrote for convenience. This method will take an integer as input and
     * use that integer to display the appropriate text within a list item.
     * @param listIndex Position of the item in the list
     */
    fun bind(listIndex: Int) {
      listItemNumberView.text = listIndex.toString()
    }


    // COMPLETED (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
    /**
     * Called whenever a user clicks on an item in the list.
     * @param v The View that was clicked
     */
    override fun onClick(v: View) {
      val clickedPosition = getAdapterPosition()
      mOnClickListener.onListItemClick(clickedPosition)
    }
  }

}