package app.workrepo.finaleexample.topmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.workrepo.finaleexample.R
import kotlinx.android.synthetic.main.movie_list_row.view.*

class ListAdapter(private val list: List<ViewModel>) :
    RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {


//    private var list: List<ViewModel>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_row, parent, false)
        return ListItemViewHolder((v))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {

        holder.bindItem(position)
    }

    inner class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItem(position: Int) {

            val item = list?.get(position)
            itemView.txtFragmentListName.text = item.country
            itemView.txtFragmentListCountryName.text = item.name

        }


    }
}