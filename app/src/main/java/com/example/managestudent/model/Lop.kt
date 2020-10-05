package com.example.managestudent.model

import com.google.firebase.database.*

class Lop(var MaLop:String,var TenLop:String,var MaKhoa:String) {
    constructor() : this("","","")
    constructor(TenLop:String,MaKhoa:String):this("",TenLop,MaKhoa)

    companion object
    {
        fun getInstanceFirebase(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference("Lop")
        }


        fun getAllLop():MutableList<Lop>
        {
            var lopMutableList:MutableList<Lop> = mutableListOf()
            getInstanceFirebase()
                .addValueEventListener(object :ValueEventListener
                {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists())
                        {
                            for (data in p0.children)
                            {
                                val row = data.getValue(Lop::class.java)
                                if (row != null) {
                                    lopMutableList.add(row)
                                }
                            }
                        }
                    }
                })
            return lopMutableList;
        }
    }
}