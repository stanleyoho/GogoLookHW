package com.gogolook.hw.app.ui

import com.gogolook.hw.app.InstanceData
import com.gogolook.hw.app.R
import com.gogolook.hw.app.data.ApiGetImageModel
import com.gogolook.hw.app.enums.LayoutTypeEnum
import com.gogolook.hw.app.tasks.GetImageTask
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivityPresenter(val view : MainActivityContract.View) : MainActivityContract.Presenter {

    override fun updateRemoteConfig(){
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener {
            InstanceData.layoutType = LayoutTypeEnum.getLayoutType(mFirebaseRemoteConfig.getDouble("layoutConfig").toInt())
            view.initRecyclerViewLayout(InstanceData.layoutType)
        }
    }

    override fun getImages(queryString: String) {
        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                val responseString = GetImageTask.get(queryString)
                println("1234")
                println(responseString)
            }
        }
    }
}