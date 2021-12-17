package com.example.mushafconsolidated;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {
    /**
     * Live Data Instance
     */
    private MutableLiveData<String> mName = new MutableLiveData<>();
    public void setChapterno(String chapterno) {
        mName.setValue(chapterno);
    }
    public LiveData<String> getChapterNo() {
        return mName;
    }

    private MutableLiveData<String> mVerseno = new MutableLiveData<>();
    public void setVerseno(String verseno) {
        mVerseno.setValue(verseno);
    }
    public LiveData<String> getVerseno() {
        return mVerseno;
    }


}