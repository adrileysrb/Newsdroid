package com.example.news.view;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.example.news.R;
import com.example.news.model.Articles;
import com.example.news.model.Headlines;
import com.example.news.viewmodel.Adapter;
import com.example.news.viewmodel.ApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }
    String query;
    public SearchFragment(String query) {
        this.query = query;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        String country = "br";
        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        lottieAnimationView = view.findViewById(R.id.lotieSearch);
        lottieAnimationView2 = view.findViewById(R.id.lotieSearch2);
       /* int yourColor = ContextCompat.getColor(getActivity(), R.color.primaryTextColor);
        lottieAnimationView.setVisibility(LottieAnimationView.GONE);
        lottieAnimationView.setPadding(-320, -320, -320, -320);
        SimpleColorFilter filter = new SimpleColorFilter(yourColor);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        lottieAnimationView.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);*/
        lottieAnimationView.setVisibility(LottieAnimationView.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        retrieveJson(query,API_KEY);



        return view;

    }
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView lottieAnimationView2;
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
                    //lottieAnimationView.setVisibility(LottieAnimationView.VISIBLE);
                    //splashScreen(SPLASH_SCREEN_TIME);
                    //lottieAnimationView.setVisibility(LottieAnimationView.GONE);
                    lottieAnimationView2.setVisibility(LottieAnimationView.GONE);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(getActivity(), articles);
                    recyclerView.setAdapter(adapter);
                    if(articles.size()==0){
                        lottieAnimationView.setVisibility(LottieAnimationView.VISIBLE);
                        //lottieAnimationView.setAnimation(R.raw.sad_search);
                       //lottieAnimationView.pauseAnimation();
                       //lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
                    }
                    else {
                        lottieAnimationView.setVisibility(LottieAnimationView.GONE);
                        lottieAnimationView2.setVisibility(LottieAnimationView.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
            }
        });
    }
    private final int SPLASH_SCREEN_TIME = 3000;
    private void splashScreen(int timeSplashScreen) {
        final Thread thread = new Thread(() -> {
            try {
                sleep(timeSplashScreen);
                //startActivity(new Intent(, SelectionActivity.class));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}