package app.workrepo.finaleexample.topmovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.workrepo.finaleexample.R
import app.workrepo.finaleexample.helper.MyDiffUtils
import app.workrepo.finaleexample.http.apiModel.Result
import kotlinx.android.synthetic.main.movie_list_row.view.*

class ListAdapter(private val list: MutableList<Result?>?) :
    RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {


//    private var list: List<ViewModel>? = arrayListOf()

    fun updateData(newList: MutableList<Result?>?) {
        Log.e("print", "item size1 = " + list?.size + " | " + newList?.size)

        val diffUtil = MyDiffUtils(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        
//        Log.e("print","diffResult = "+diffResult?.)
        
        list?.clear()
        list?.addAll(newList!!)
        diffResult.dispatchUpdatesTo(this)
//notifyDataSetChanged()
        Log.e("print", "item size2 = " + list?.size + " | " + newList?.size)

    }

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
            itemView.txtFragmentListName.text = item?.title

        }


    }
}