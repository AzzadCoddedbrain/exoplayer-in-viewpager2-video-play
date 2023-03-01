package com.mehuljoisar.viewpagersilder

import NestedAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehuljoisar.viewpagersilder.databinding.ActivityNestedRecyclerviewBinding

class NestedRecyclerviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityNestedRecyclerviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestedRecyclerviewBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_nested_recyclerview)

        val list = ArrayList<String>()

        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")
        list.add("this is test")

        val adapter = NestedAdapter(list)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter


    }
}