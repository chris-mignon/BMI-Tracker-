package com.migs.bmitracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Record record);

    @Query("DELETE FROM record_table")
    void deleteAll();

    @Query("SELECT * from record_table ORDER BY record Desc")
    LiveData<List<Record>> getAllRecords();

    @Query("SELECT * from record_table LIMIT 1")
    Record[] getAnyRecord();

    @Delete
    void deleteRecord(Record record);
}
