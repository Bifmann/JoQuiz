import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.joquiz.QuizList

import com.example.joquiz.R

class MenuQuizAdapter(
    private val data: List<QuizList>
) : RecyclerView.Adapter<MenuQuizAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val quizName: TextView = view.findViewById(R.id.titleTextView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) : ItemViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_quiz_layout, parent, false)

        return ItemViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MenuQuizAdapter.ItemViewHolder, position: Int) {
        val quiz: QuizList = data[position]

        holder.quizName.text = quiz.name
    }

    override fun getItemCount(): Int {
        return data.size
    }
}