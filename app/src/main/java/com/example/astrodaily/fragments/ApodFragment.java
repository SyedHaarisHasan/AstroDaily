package com.example.astrodaily.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astrodaily.MainActivity;
import com.example.astrodaily.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ApodFragment extends Fragment {

    public ApodFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apod, container, false);
    }

    ImageView imgView;
    TextView titleView;
    TextView explanationView;
    String api_key = "VVqaOUj53EDunGGtiern6yVKyhfycmmS9VTjhYfD";
    String url = "https://api.nasa.gov/planetary/apod?api_key=" + api_key;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populate(view);
    }

    public void populate(View view) {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        imgView = view.findViewById(R.id.image);
        titleView = view.findViewById(R.id.title);
        explanationView = view.findViewById(R.id.explanation);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String title = "", url = "", explanation = "";

                try {
                    title = response.getString("title");
                    url = response.getString("url");
                    explanation = response.getString("explanation");

                    titleView.setText(title);
                    Picasso.get().load(url).into(imgView);
                    explanationView.setText(explanation);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}