package com.example.astrodaily.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.astrodaily.Article;
import com.example.astrodaily.ArticleAdapter;
import com.example.astrodaily.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    String api_key = "1jxCp2tL-mQiovGapjkMwVXUVFDfE2QYVFTaqyGIFwk";
    String sort = "relevancy";
    String url = "https://api.newscatcherapi.com/v2/search?q=\"Astronomy\" AND \"Space\"&lang=en&topic=science&sort_by=";
    ListView list;

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);

        Spinner s = view1.findViewById(R.id.spinner1);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getSelectedItem().toString();

                switch (selected) {
                    case "Relevancy":
                        sort = "relevancy";
                        break;
                    case "Date":
                        sort = "date";
                        Log.d("SORT TEST", url + sort);
                        break;
                    case "Rank":
                        sort = "rank";
                        break;
                }

                populate(view1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        populate(view1);
    }

    public void populate(View view) {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        Log.d("SORT TEST 1", url + sort);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + sort, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list = (ListView)view.findViewById(R.id.list);
                ArrayList<Article> articles = createList(response);

                ArticleAdapter articleAdapter = new ArticleAdapter(getActivity(), articles);
                list.setAdapter(articleAdapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(articles.get(i).getLink()));
                        startActivity(browser);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-api-key", api_key);
                return params;
            }
        };

        queue.add(request);
    }

    public ArrayList<Article> createList(JSONObject res) {
        ArrayList<Article> list = new ArrayList<>();
        try {
            JSONArray articles = res.getJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                JSONObject a = articles.getJSONObject(i);
                Article article = new Article(a.getString("title"), a.getString("author"),
                        a.getString("published_date"), a.getString("link"), a.getString("clean_url"),
                        a.getString("excerpt"), a.getString("summary"), a.getString("media"),
                        a.getString("is_opinion"));
                list.add(article);
            }

            return list;
        }
        catch (JSONException e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}