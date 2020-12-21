package com.sml.test.a_z;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sml.test.R;

import java.util.List;

public class AZListAdapter extends RecyclerView.Adapter<AZListAdapter.ViewHolder> {

    private List<Content> contentList = null;
    private Context context;

    public AZListAdapter(List<Content> contentList, Context context) {
        this.contentList = contentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View azItem = View.inflate(context, R.layout.layout_a_z_item, null);
        return new ViewHolder(azItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.indexTv.setVisibility(contentList.get(position).isShowIndex() ? View.VISIBLE : View.GONE);
        holder.indexTv.setText(contentList.get(position).getFirstLetter());
        holder.contentTv.setText(contentList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView indexTv;
        public TextView contentTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexTv = itemView.findViewById(R.id.item_index_view);
            contentTv = itemView.findViewById(R.id.item_content_view);
        }
    }
}
