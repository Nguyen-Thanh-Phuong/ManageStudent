package com.example.managestudent.controller.account

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.Account
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AccountController {
    lateinit var context: Context
    val list = mutableListOf<Account>()
    private constructor(){getAllAccount()}
    companion object
    {
        val accountController = AccountController()
        fun getInstance(context: Context): AccountController {
            accountController.context = context
            return accountController
        }
    }
    private fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("Account")
    private fun getAllAccount()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object :ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                    for(data in p0.children)
                    {
                        val value = data.getValue(Account::class.java)
                        if(value!=null)
                        {
                            list.add(value)
                            Log.d("MAINN",value.username);
                        }
                    }
            }
        })
    }
    private fun checkUser(username:String):Boolean
    {
        return (list.find { it-> it.username== username} !=null)
    }

    fun insert(account: Account)
    {
        if(checkUser(account.username))
        {
            Toast.makeText(context,"User ${account.username} is Already",Toast.LENGTH_SHORT).show();
            return
        }

        val firebase = getFirebaseInstance()
        val keyId = firebase.push().key
        if(keyId!=null)
        {
            account.key = keyId
            firebase.child(keyId).setValue(account).addOnCompleteListener {
                Toast.makeText(context,"Insert Account ${account.username} is Success",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkInvalidAccount(account: Account):Int
    {
        return list.find { it -> (it.username==account.username&& it.password==account.password)}?.type
            ?:3
    }
}