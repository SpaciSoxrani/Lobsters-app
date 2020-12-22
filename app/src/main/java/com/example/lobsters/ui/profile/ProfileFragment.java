package com.example.lobsters.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lobsters.R;
import com.example.lobsters.serverapi.DemoServerApi;
import com.example.lobsters.utils.UiUtils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

public class ProfileFragment extends Fragment {

    private MusicTagsViewModel musicTagsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicTagsViewModel = ViewModelProviders.of(this).get(MusicTagsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final ShapeableImageView shapeImage = root.findViewById(R.id.avatar);
        shapeImage.setImageResource(R.drawable.avatar);
        final AppCompatTextView profileName = root.findViewById(R.id.profile_name);
        final AppCompatTextView profileLocation = root.findViewById(R.id.profile_location);
        final ChipGroup tagsContainer = root.findViewById(R.id.tags_container);

        profileName.setText(DemoServerApi.NAMES[0]);
        profileLocation.setText(DemoServerApi.LOCATION[1]);

        musicTagsViewModel.getTags().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] tags) {
                redrawMusicTags(tags, tagsContainer);
            }
        });
        musicTagsViewModel.setTags(DemoServerApi.TAGS);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_settings) {
            //todo advanced search dialog
            UiUtils.ShowSimpleDialog("Редактирование", "Редактирование профиля", getContext());
        }
        return super.onOptionsItemSelected(item);
    }

    private void redrawMusicTags(String[] tags, ChipGroup container) {
        container.removeAllViews();
        for (int i = 0; i < tags.length; i++) {
            View tag = LayoutInflater.from(getContext()).inflate(R.layout.music_tag, container, false);
            Chip chip = tag.findViewById(R.id.main_tag);
            chip.setText(tags[i]);
            container.addView(tag);
        }
    }
}
