package com.example.managestudent.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.managestudent.MainActivity
import com.example.managestudent.R
import com.example.managestudent.controller.account.AccountController
import com.example.managestudent.menu.MenuActivity
import com.example.managestudent.model.Account
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val instance =AccountController.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnSignUp_loginForm.setOnClickListener(::onClickSignUp)
        btnLogin_convertForm.setOnClickListener(::onClickLogin_Convert)
        btnSubmit.setOnClickListener(::onClickSubmitForm)
    }
    private fun login()
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private  fun register()
    {

        val pass = editTextTextPassword.text.toString()
        if(!pass.equals(txtConfirmPass.text.toString()))
        {
            Toast.makeText(this,"Password is not Confirm ",Toast.LENGTH_SHORT).show();
            return
        }

        val user = editTextTextPersonName.text.toString()
        val account = Account(user,pass)
        instance.insert(account)

    }
    private fun onClickSubmitForm(view: View?) {
        if((view as Button).text.toString() == btnLogin_convertForm.text.toString())
            login()
        else register()

    }
    private fun setTextBtnSubmit(view: View?)
    {
        btnSubmit.text = (view as Button).text.toString()
    }
    private fun onClickLogin_Convert(view: View?)
    {
        setBackgroundLoginConvertButton(view as Button)
        txtConfirmPass.visibility = View.INVISIBLE
        idRoot.setBackgroundResource(R.mipmap.login)
        setTextBtnSubmit(view)
    }
    private fun setBackgroundLoginConvertButton(bt: Button)
    {
        bt.setBackgroundResource(R.drawable.loginbutton_shape)
        if(bt.id == btnLogin_convertForm.id)
            btnSignUp_loginForm.setBackgroundResource(R.drawable.signup_button_shape)
        else btnLogin_convertForm.setBackgroundResource(R.drawable.signup_button_shape)
    }
    private fun onClickSignUp(view: View)
    {
        setBackgroundLoginConvertButton(view as Button)
        txtConfirmPass.visibility = View.VISIBLE
        idRoot.setBackgroundResource(R.mipmap.signup)
        setTextBtnSubmit(view)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}