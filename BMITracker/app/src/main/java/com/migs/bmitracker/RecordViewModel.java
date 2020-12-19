package com.migs.bmitracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel
{
    private RecordRepository rRepository;
    private LiveData<List<Record>> rAllRecords;
    public RecordViewModel (Application application)
    {
        super(application);
        rRepository = new RecordRepository(application);
        rAllRecords = rRepository.getAllRecords();
    }
    LiveData<List<Record>> getAllRecords()
    {
        return rAllRecords;
    }
    public void insert(Record record)
    {
        rRepository.insert(record);
    }
    public void deleteAll() {rRepository.deleteAll();}
    public void deleteWord(Record record) {rRepository.deleteRecord(record);}
}
