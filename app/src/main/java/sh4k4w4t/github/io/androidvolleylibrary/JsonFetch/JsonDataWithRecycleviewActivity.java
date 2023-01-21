package sh4k4w4t.github.io.androidvolleylibrary.JsonFetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sh4k4w4t.github.io.androidvolleylibrary.databinding.ActivityJsonDataWithRecycleviewBinding;

public class JsonDataWithRecycleviewActivity extends AppCompatActivity {
    ActivityJsonDataWithRecycleviewBinding binding;
    ArrayList<UserModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJsonDataWithRecycleviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = "https://reqres.in/api/users";


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject singleJSONObject = jsonArray.getJSONObject(i);
                        int id = singleJSONObject.getInt("id");
                        String fName = singleJSONObject.getString("first_name");
                        String lName = singleJSONObject.getString("last_name");
                        String email = singleJSONObject.getString("email");
                        String avatar = singleJSONObject.getString("avatar");
                        UserModel userModel = new UserModel(id, email, fName, lName, avatar);
                        arrayList.add(userModel);
                    }

                    JsonFetcherAdapter adapter= new JsonFetcherAdapter(arrayList,JsonDataWithRecycleviewActivity.this);
                    LinearLayoutManager linearLayoutManager= new LinearLayoutManager(JsonDataWithRecycleviewActivity.this);
                    binding.recyclerViewForJsonData.setLayoutManager(linearLayoutManager);
                    binding.recyclerViewForJsonData.setAdapter(adapter);

                    Toast.makeText(JsonDataWithRecycleviewActivity.this, "Success", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JsonDataWithRecycleviewActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}