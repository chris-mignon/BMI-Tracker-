package com.migs.bmitracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {
    public abstract RecordDao recordDao();
    private static RecordDatabase INSTANCE;

    public static RecordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecordDatabase.class, "record_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RecordDao rDao;
        String[] records = {"Add a record"};

        PopulateDbAsync(RecordDatabase db) {
            rDao = db.recordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            // mDao.deleteAll();

            if (rDao.getAnyRecord().length < 1) {
                for (int i = 0; i <= records.length - 1; i++) {
                    Record record = new Record(records[i]);
                    rDao.insert(record);
                }
            }
            return null;
        }
    }
}
