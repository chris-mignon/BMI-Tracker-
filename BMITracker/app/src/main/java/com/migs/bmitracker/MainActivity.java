package com.migs.bmitracker;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private RecordViewModel mWordViewModel;
    Button calladd, deleteall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calladd = findViewById(R.id.calladd);
        calladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculateandadd.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        deleteall = findViewById(R.id.clearall);
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWordViewModel.deleteAll();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RecordListAdapter adapter = new RecordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        mWordViewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable final List<Record> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Record myRecord = adapter.getRecordAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myRecord.getRecord(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mWordViewModel.deleteWord(myRecord);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Record record = new Record(data.getStringExtra(Calculateandadd.EXTRA_REPLY));
            mWordViewModel.insert(record);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


}
