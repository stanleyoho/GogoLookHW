package com.gogolook.hw.app.ui

import com.gogolook.hw.app.data.ApiGetImageModel
import com.gogolook.hw.app.enums.LayoutTypeEnum

interface MainActivityContract {

    interface View {
        fun updateRecycler(data: ApiGetImageModel)
        fun initRecyclerViewLayout(type:LayoutTypeEnum)
    }

    interface Presenter {
        fun updateRemoteConfig()
        fun getImages(queryString:String)
    }

}