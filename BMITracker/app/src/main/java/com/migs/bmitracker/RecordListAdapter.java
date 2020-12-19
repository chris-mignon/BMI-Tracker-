package com.migs.bmitracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.RecordViewHolder> {

    class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView recordItemView;

        private RecordViewHolder(View itemView) {
            super(itemView);
            recordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater rInflater;
    private List<Record> rRecords; // Cached copy of words
    public Record getRecordAtPosition (int position) {
        return rRecords.get(position);
    }
    RecordListAdapter(Context context) { rInflater = LayoutInflater.from(context); }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = rInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Record current = rRecords.get(position);
        holder.recordItemView.setText(current.getRecord());
    }

    void setWords(List<Record> records){
        rRecords = records;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (rRecords != null)
            return rRecords.size();
        else return 0;
    }
}


