package psk.example.feasthub;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.feasthub.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Mainpage extends Fragment {

    private ImageView back, userprofile, carticon;
    private TextView username, cat;
    private RecyclerView foodRV;
    private ArrayList<FoodRVModal> foodRVModalArrayList;
    private FoodRVAdapter foodRVAdapter;
    public Button tiffin1, lunch, chineese, sandwitch, chats, milkshake;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_mainpage, container, false);

        username = rootView.findViewById(R.id.UserName);
        userprofile = rootView.findViewById(R.id.userprofile);
        foodRV = rootView.findViewById(R.id.FoodRV);
        cat = rootView.findViewById(R.id.foodname);
        carticon = rootView.findViewById(R.id.cart);

        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String name = preferences.getString("user_display_name", "DefaultName");
        String profileImageUriString = preferences.getString("user_photo_uri", null);

        username.setText("Hey " + name + ",");

        if (profileImageUriString != null) {
            Uri userProfileImageUri = Uri.parse(profileImageUriString);
            Picasso.get().load(userProfileImageUri).into(userprofile);
        }

        foodRVModalArrayList = new ArrayList<>();
        foodRVAdapter = new FoodRVAdapter(requireContext(), foodRVModalArrayList);
        foodRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        foodRV.setAdapter(foodRVAdapter);

        tiffin1 = rootView.findViewById(R.id.tiffin);
        lunch = rootView.findViewById(R.id.lunch);
        sandwitch = rootView.findViewById(R.id.sandwich);
        chats = rootView.findViewById(R.id.chats);
        chineese = rootView.findViewById(R.id.chineese);
        milkshake = rootView.findViewById(R.id.milkshake);

        carticon = rootView.findViewById(R.id.cart);

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddCartActivity.class);
                startActivity(intent);
            }
        });


        new FetchtiffenTask().execute();

        tiffin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                lunch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chats.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chineese.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                milkshake.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchtiffenTask().execute();
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                lunch.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chats.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chineese.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                milkshake.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchFoodTask().execute();
            }
        });

        sandwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                chats.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chineese.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                milkshake.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchsandwitchTask().execute();
            }
        });

        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chats.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                lunch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chineese.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                milkshake.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchchatsTask().execute();
            }
        });

        chineese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chineese.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                lunch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chats.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                milkshake.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchmilkchineeseTask().execute();
            }
        });

        milkshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milkshake.setBackground(requireContext().getDrawable(R.drawable.greystroke));
                lunch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                tiffin1.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                sandwitch.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chats.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                chineese.setBackground(requireContext().getDrawable(R.drawable.circlereduced));
                new FetchmilkshakeTask().execute();
            }
        });


        return rootView;
    }

    // AsyncTask classes can be defined here

    public class FetchFoodTask extends AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... voids) {
            String weatherApiUrl = "https://api.json-generator.com/templates/IamXeM2XolVM/data?access_token=1oek829zqbx6nuzrv0dybt00t9s9fk5wdsmn5l46";

            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fooditemslunch");
                    JSONArray lunchArr = food.getJSONArray("lunch");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Lunch");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public class FetchsandwitchTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String weatherApiUrl = "https://api.json-generator.com/templates/VH1mgU_17460/data?access_token=e0qwf8nqvx5krc31qyuq8s035p0iain0v654c9kp";
            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fooditemssandwich");
                    JSONArray lunchArr = food.getJSONArray("sandwich");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Sandwitch");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public class FetchchatsTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String weatherApiUrl = "https://api.json-generator.com/templates/mkcAoHcithZg/data?access_token=hzc8n9cducaa7v5l6rpop9o8l7mnnx2hnocynfg3";

            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fooditemschats");
                    JSONArray lunchArr = food.getJSONArray("chaat");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Chaat");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public class FetchmilkshakeTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String weatherApiUrl = "https://api.json-generator.com/templates/brQOZITxXa9R/data?access_token=5d8o50zp97pl35w4qd70bt576gxyroashcgnvgxg";

            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fetchitemmilkshake");
                    JSONArray lunchArr = food.getJSONArray("milkshake");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Milkshake");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public class FetchmilkchineeseTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String weatherApiUrl = "https://api.json-generator.com/templates/Que6Rnwl9kwx/data?access_token=eejf79jyjz5t6adlnhu0h85ak1ls02p2o8jey63r";

            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fooditemschineese");
                    JSONArray lunchArr = food.getJSONArray("chineese");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Chineese");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public class FetchtiffenTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String weatherApiUrl = "https://api.json-generator.com/templates/KBVKSaCd6VzR/data?access_token=ry1x7nzzo0mecyrdytfwj8cjamdqy35hiob48xtj";

            try {
                URL url = new URL(weatherApiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            foodRVModalArrayList.clear();
            if (jsonResponse != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONObject food = jsonObject.getJSONObject("fooditemtiffen");
                    JSONArray lunchArr = food.getJSONArray("foodtiffen");

                    for (int i = 0; i < lunchArr.length(); i++) {
                        JSONObject lunchObj = lunchArr.getJSONObject(i);

                        String img = lunchObj.getString("image_url");
                        String fname = lunchObj.getString("name");
                        String fprice = lunchObj.getString("price");
                        String fdescription = lunchObj.getString("description");

                        // Create a FoodRVModal object and add it to the list
                        foodRVModalArrayList.add(new FoodRVModal(fname, fprice, img));
                    }

                    cat.setText("Tiffen");

                    // Notify the adapter that the data has changed
                    foodRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}


