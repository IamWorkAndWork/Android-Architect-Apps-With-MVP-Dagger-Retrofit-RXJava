package app.workrepo.finaleexample.helper

import androidx.recyclerview.widget.DiffUtil
import app.workrepo.finaleexample.http.apiModel.Result

class MyDiffUtils(
    private var oldList: List<Result?>?,
    private var newList: List<Result?>?
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id!! == newList?.get(newItemPosition)?.id!!
    }

    override fun getOldListSize(): Int {
        return oldList?.size!!
    }

    override fun getNewListSize(): Int {
        return newList?.size!!
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)!!.equals(newList?.get(newItemPosition)!!)
    }
}