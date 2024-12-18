package com.example.myrepository.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myrepository.R;
import com.example.myrepository.data.Result;
import com.example.myrepository.data.local.entity.NewsEntity;
import com.example.myrepository.databinding.FragmentNewsBinding;

import java.util.List;

public class NewsFragment extends Fragment {
    public static final String ARG_TAB = "tab_name";
    public static final String TAB_NEWS = "news";
    public static final String TAB_BOOKMARK = "bookmark";
    private FragmentNewsBinding binding;
    private String tabName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            tabName = getArguments().getString(ARG_TAB);
        }

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        NewsViewModel viewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);

        NewsAdapter newsAdapter = new NewsAdapter();

        if (tabName.equals(TAB_NEWS)) {
            viewModel.getHeadlineNews().observe(getViewLifecycleOwner(), result -> {
                if (result != null) {
                    if (result instanceof Result.Loading){
                        binding.progressBar.setVisibility(View.VISIBLE);
                    } else if (result instanceof Result.Success){
                        binding.progressBar.setVisibility(View.GONE);
                        List<NewsEntity> newsData = ((Result.Success<List<NewsEntity>>) result).getData();
                        newsAdapter.submitList(newsData);
                    } else if (result instanceof Result.Error){
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Terjadi kesalahan"+ ((Result.Error<List<NewsEntity>>) result).getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setHasFixedSize(true);
        binding.rvNews.setAdapter(newsAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}