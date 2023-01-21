package sh4k4w4t.github.io.androidvolleylibrary.StringRequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

import sh4k4w4t.github.io.androidvolleylibrary.databinding.ActivityMainBinding;

public class StringRequestActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url= "https://jsonplaceholder.typicode.com/users";
        ArrayList<String> nameArrayList= new ArrayList<>();

        StringRequest request= new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        String name= jsonObject.getString("name");
                        nameArrayList.add(name);
                    }

                    ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String >(StringRequestActivity.this,
                            android.R.layout.simple_expandable_list_item_1,nameArrayList);
                    binding.listItem.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StringRequestActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}