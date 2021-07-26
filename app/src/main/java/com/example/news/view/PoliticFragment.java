package com.example.news.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.model.Articles;
import com.example.news.model.Headlines;
import com.example.news.viewmodel.Adapter;
import com.example.news.viewmodel.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PoliticFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_politic, container, false);

        // Inflate the layout for this fragment
        String country = "br";
        retrieveJson("politica",API_KEY);
        recyclerView = view.findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }
    private RecyclerView recyclerView;
    final String API_KEY = "390479b539504208affee84fe60035fe";
    final String API_KEY2 = "c119cfe37f764c6d9104cf74a60b0d66";
    final String API_KEY3 = "855d205a76df4d9e8a5462f219c910fa";

    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    public void retrieveJson(String query, String apiKey){


        Call<Headlines> call;
        call= ApiClient.getInstance().getApi().getSpecificData(query, apiKey);


        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){

                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(getActivity(), articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
            }
        });
    }

}