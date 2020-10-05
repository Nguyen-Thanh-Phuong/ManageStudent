package com.example.managestudent.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.managestudent.R
import com.example.managestudent.controller.account.AccountController
import com.example.managestudent.menu.MenuActivity
import com.example.managestudent.model.Account
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var radiotypeGroup: RadioGroup? = null
    private var loai:String =""
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
        var username:String= editTextTextPersonName.text.toString()
        var password:String= editTextTextPassword.text.toString()
        if(username.equals("")||password.equals("")){
            Toast.makeText(this,"User and password not null ",Toast.LENGTH_SHORT).show();
            return
        }
        val account = Account(username,password,0)
        val type = instance.checkInvalidAccount(account)
        if(type==1)
        {
        val intent = Intent(this,MenuActivity::class.java)
        startActivity(intent)
        }
        if(type==0){
            Toast.makeText(this,"Login the user successfully",Toast.LENGTH_SHORT).show();
        }
        if(type==3){
            Toast.makeText(this,"Account or password is not correct",Toast.LENGTH_SHORT).show();
        }
    }
    private  fun register()
    {
        var type1:Int = 0
        val index = RGroup.checkedRadioButtonId;
        val radio:RadioButton = findViewById(index)
        val pass = editTextTextPassword.text.toString()
        if(!pass.equals(txtConfirmPass.text.toString()))
        {
            Toast.makeText(this,"Password is not Confirm ",Toast.LENGTH_SHORT).show();
            return
        }
        if (pass.length<8)
        {
            Toast.makeText(this,"Password is not sort ",Toast.LENGTH_SHORT).show();
            return
        }
        val user = editTextTextPersonName.text.toString()
        var m:String = radio.text.toString()
        if(m.equals("Admin")){
          type1 =1
        }
        val account = Account(user,pass,type1)
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
        textView2.text ="Login"
        txtConfirmPass.visibility = View.INVISIBLE
        RGroup.visibility = View.INVISIBLE
        type.visibility = View.INVISIBLE
        textView5.visibility =View.VISIBLE
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
        textView2.text ="Singup"
        txtConfirmPass.visibility = View.VISIBLE
        RGroup.visibility = View.VISIBLE
        type.visibility = View.VISIBLE
        textView5.visibility = View.INVISIBLE
        idRoot.setBackgroundResource(R.mipmap.signup)
        setTextBtnSubmit(view)
    }
    override fun onDestroy() {
        super.onDestroy()

    }
}