package com.mobifyall.geniusplaza.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.app.androidapp.service.RetrofitFactory
import com.mobifyall.geniusplaza.R
import com.mobifyall.geniusplaza.databinding.AddUserDialogBinding
import com.mobifyall.geniusplaza.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

class AddUserDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        val model = getModelUser()
        callServiceToAdd(model)
    }

    private fun callServiceToAdd(model: Model.User?) {
        if(model != null)
        {
            RetrofitFactory.create().postUser(model).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    run {
                        Toast.makeText(context, "User added Successfully", Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                },
                    { error ->
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    })
        }
    }

    private fun getModelUser(): Model.User? {


        if(binding.etFName.text.isEmpty())
        {
            binding.etFName.setError(context.getString(R.string.error_first_name))
            return null;
        }

        if(binding.etLName.text.isEmpty())
        {
            binding.etLName.setError(context.getString(R.string.error_first_name))
            return null;

        }

        if(binding.etEmail.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches())
        {
            binding.etEmail.setError(context.getString(R.string.error_email))
            return null;

        }
        return Model.User(-1,binding.etEmail.text.toString(), binding.etFName.text.toString(), binding.etLName.text.toString(), "")

    }

    lateinit var binding: AddUserDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener(this)

    }


}