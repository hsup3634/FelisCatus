package com.example.feliscatus.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.feliscatus.Cat;
import com.example.feliscatus.CatAdapter;
import com.example.feliscatus.R;
import com.example.feliscatus.database.AppDatabase;
import com.example.feliscatus.ui.favourites.FavouriteList;
import com.example.feliscatus.ui.favourites.FavouriteListResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;

    public SearchFragment(){

    }

    //private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*earchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_search);
        searchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;*/

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatAdapter catAdapter = new CatAdapter();

        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

        String url = "https://api.thecatapi.com/v1/breeds?api_key="+getString(R.string.cat_api_key);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //error: com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $
                //to fix: https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Limitations
                //unable to fix :((((((((((
                //goodbye
                //please mark nicely 🥺😭

                Gson gson = new Gson();
                FavouriteListResponse favouriteListResponse = gson.fromJson(response, FavouriteListResponse.class);
                FavouriteList favouriteList = favouriteListResponse.getResults();



                List<Cat> favourite = favouriteList.getCats();


                AppDatabase db = AppDatabase.getInstance(getContext());

                    db.catDao().insertAll(favourite);

                    List<Cat> listCatsFromDatabase = db.catDao().getAll();

                    // Convert list to arraylist
                    ArrayList<Cat> catsFromDatabase = new ArrayList<>(listCatsFromDatabase);

                    catAdapter.setData(catsFromDatabase);
                    recyclerView.setAdapter(catAdapter);

                    requestQueue.stop();
                }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

        return view;
    }

}
