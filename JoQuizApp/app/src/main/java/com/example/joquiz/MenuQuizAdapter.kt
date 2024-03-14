import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.joquiz.QuizList
import com.example.joquiz.R

// Define a RecyclerView adapter for handling a list of QuizList items.
class MenuQuizAdapter(
    private val data: List<QuizList>,// A list of quiz items to be displayed in the RecyclerView.
    private val listener: RecyclerViewEvent
) : RecyclerView.Adapter<MenuQuizAdapter.ItemViewHolder>() { // Extend RecyclerView.Adapter with ItemViewHolder defined below.

    /** Define a ViewHolder that holds references to the views for each data item.
    It's about providing access to all the views for a data item in a view holder.
     */
    inner class ItemViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val quizName: TextView =
            view.findViewById(R.id.titleTextView) // Reference to the TextView in the layout.

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    /**
    Called when RecyclerView needs a new ViewHolder of the given type to represent
    an item. This is where you inflate the item layout and create the ViewHolder.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemViewHolder {
        // Inflate the custom layout (item_menu_quiz_layout.xml) for each item of the RecyclerView.
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_quiz_layout, parent, false)
        // Return a new ViewHolder instance.
        return ItemViewHolder(inflatedView)
    }

    /**Called by RecyclerView to display the data at the specified position.
    This method should update the contents of the ViewHolder's view to reflect the item at the given position.
     */
    override fun onBindViewHolder(holder: MenuQuizAdapter.ItemViewHolder, position: Int) {
        val quiz: QuizList = data[position] // Get the data model based on position.

        holder.quizName.text = quiz.title // Set the quiz name to the TextView.
    }

    /**
     * Return the size of your dataset (invoked by the layout manager).
     */
    override fun getItemCount(): Int {
        return data.size // Return the total number of items in the data list.
    }
}

interface RecyclerViewEvent {
    fun onItemClick(position: Int)
}



