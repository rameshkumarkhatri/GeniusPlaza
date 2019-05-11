package com.mobifyall.geniusplaza.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.mobifyall.geniusplaza.R
import com.mobifyall.geniusplaza.model.Model
import com.squareup.picasso.Picasso

class AdapterUser(private val data: List<Model.User>) : RecyclerView.Adapter<ViewHolderUsesr>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsesr {

        val view = parent.inflate(R.layout.row_user, false);
        return ViewHolderUsesr(view);

    }

    override fun getItemCount(): Int {
        return data?.size

    }

    override fun onBindViewHolder(holder: ViewHolderUsesr, position: Int) {
        holder.setData(data[position]);
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}

class ViewHolderUsesr(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private val view: View = v;

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
    }

    fun setData(model: Model.User) {
        val iv = view.findViewById<ImageView>(R.id.iv)
        Picasso.get().load(model.avatar).into(iv)

        view.findViewById<TextView>(R.id.tv).setText(model.first_name + " " + model.last_name);
    }

    companion object {

    }

}

