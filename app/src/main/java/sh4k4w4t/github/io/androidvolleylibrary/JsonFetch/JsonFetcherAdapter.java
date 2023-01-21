package sh4k4w4t.github.io.androidvolleylibrary.JsonFetch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sh4k4w4t.github.io.androidvolleylibrary.R;
import sh4k4w4t.github.io.androidvolleylibrary.databinding.CellUserLayoutBinding;

public class JsonFetcherAdapter extends RecyclerView.Adapter<JsonFetcherAdapter.holder> {
    ArrayList<UserModel> arrayList;
    Context context;

    public JsonFetcherAdapter(ArrayList<UserModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_user_layout, parent,false);
        return new holder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        UserModel userModel = arrayList.get(position);

        holder.binding.name.setText(userModel.getFirst_name()+" "+userModel.getLast_name());
        holder.binding.email.setText(userModel.getEmail()+" ");

        Glide.with(context)
                .load(userModel.getAvatar())
                .into(holder.binding.profileImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        CellUserLayoutBinding binding;
        public holder(@NonNull View itemView) {
            super(itemView);
            binding= CellUserLayoutBinding.bind(itemView);
        }
    }
}
