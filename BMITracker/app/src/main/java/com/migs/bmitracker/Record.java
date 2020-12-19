package com.migs.bmitracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "record_table")
public class Record {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="record")
    protected String rRecord;

    public Record(@NonNull String rRecord) {
        this.rRecord = rRecord;
    }

    @NonNull
    public String getRecord() {
        return this.rRecord;
    }

    public void setRecord(@NonNull String record) {
        this.rRecord = record;
    }
}