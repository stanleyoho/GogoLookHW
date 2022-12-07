package com.gogolook.hw.app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gogolook.hw.app.InstanceData
import com.gogolook.hw.app.R
import com.gogolook.hw.app.adapter.RecyclerViewAdapter
import com.gogolook.hw.app.data.ApiGetImageModel
import com.gogolook.hw.app.enums.LayoutTypeEnum
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , MainActivityContract.View{
    val presenter = MainActivityPresenter(this)
    var aaa = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchLayout: SearchView = searchView

        presenter.updateRemoteConfig()

        searchLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val queryString = p0 ?: ""
                presenter.getImages(queryString)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean { return false }
        })

    }

    override fun updateRecycler(data: ApiGetImageModel) {
        Handler(Looper.getMainLooper()).post {
            if (recyclerLayout.adapter == null) {
                recyclerLayout.adapter = RecyclerViewAdapter(this@MainActivity, InstanceData.layoutType, data)
            } else {
                (recyclerLayout.adapter as RecyclerViewAdapter).updateData(data)
            }
        }
    }

    override fun initRecyclerViewLayout(type: LayoutTypeEnum) {
        if (type == LayoutTypeEnum.LIST_LAYOUT) {
            recyclerLayout.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerLayout.layoutManager = GridLayoutManager(this, 2)
        }
    }
}