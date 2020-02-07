package cn.sayhallo.hallotools.ui.tools

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolsViewModel : ViewModel() {

    // Create a LiveData with a String
    private var mCurrentName: MutableLiveData<String>? = null

    fun getCurrentName(): MutableLiveData<String> {
        if (mCurrentName == null) {
            mCurrentName = MutableLiveData()
        }
        return mCurrentName as MutableLiveData<String>
    }

}