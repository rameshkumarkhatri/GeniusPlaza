package com.mobifyall.geniusplaza.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobifyall.geniusplaza.R
import com.mobifyall.geniusplaza.adapter.AdapterUser
import com.mobifyall.geniusplaza.databinding.MainFragmentBinding
import com.mobifyall.geniusplaza.dialogs.AddUserDialog
import com.mobifyall.geniusplaza.model.Model
import com.mobifyall.geniusplaza.viewmodel.UserListViewModel
import com.mobifyall.geniusplaza.views.UserListView

class UserListFragment : Fragment(), UserListView, View.OnClickListener {
    override fun onClick(v: View?) {
        val d = context?.let { AddUserDialog(it) }
        d?.show()
    }

    override fun showResultLoadMore(result: Model.UsersData?) {

    }

    override fun hideList() {
        binding.rv.visibility = View.GONE
    }

    override fun showTryAgain() {
        binding.tvError.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager;


    override fun showError(error: Throwable?) {
        System.out.println(error.toString());
        Toast.makeText(context, "" + error.toString(), Toast.LENGTH_SHORT).show()

    }


    override fun showResult(result: Any) {
        System.out.println(result);
//        binding.root.tv.setText(result.toString())
        binding.rv.visibility = View.VISIBLE
        linearLayoutManager = LinearLayoutManager(this.context);
        binding.rv.layoutManager = linearLayoutManager;
        val user = result as Model.UsersData;
        binding.rv.adapter = AdapterUser(user.data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.btnAdd.setOnClickListener(this)
        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        viewModel.navigator = (this)
        viewModel.getUsersFromServer(false)

    }

}
