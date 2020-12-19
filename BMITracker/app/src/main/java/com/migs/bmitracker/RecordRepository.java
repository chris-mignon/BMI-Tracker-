package com.migs.bmitracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordRepository {
    private RecordDao rRecordDao;
    private LiveData<List<Record>> rAllRecords;

    RecordRepository(Application application) {
        RecordDatabase db = RecordDatabase.getDatabase(application);
        rRecordDao = db.recordDao();
        rAllRecords = rRecordDao.getAllRecords();
    }
    LiveData<List<Record>> getAllRecords() {
        return rAllRecords;
    }
    public void insert (Record record) {
        new insertAsyncTask(rRecordDao).execute(record);
    }
    public void deleteAll()  {
        new deleteAllWordsAsyncTask(rRecordDao).execute();
    }
    public void deleteRecord(Record record)  {
        new deleteWordAsyncTask(rRecordDao).execute(record);
    }
    private static class insertAsyncTask extends AsyncTask<Record, Void, Void> {

        private RecordDao mAsyncTaskDao;

        insertAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Record... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    private static class deleteWordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao mAsyncTaskDao;

        deleteWordAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Record... params) {
            mAsyncTaskDao.deleteRecord(params[0]);
            return null;
        }
    }
}


