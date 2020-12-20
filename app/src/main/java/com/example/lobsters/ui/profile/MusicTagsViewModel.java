package com.example.lobsters.ui.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicTagsViewModel extends ViewModel {

    private MutableLiveData<String[]> tags;

    public MusicTagsViewModel() {
        tags = new MutableLiveData<>();
        tags.setValue(new String[]{});
    }

    public MutableLiveData<String[]> getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags.setValue(tags);
    }
}
