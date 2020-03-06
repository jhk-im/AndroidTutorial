package com.jroomstudio.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDBManager extends SQLiteOpenHelper {

    static final String DB_STUDENTS="Students.db";
    static final String TABLE_STUDENT = "Students";
    static final int DB_VERSION = 2;
    Context context = null;
    private static StudentDBManager dbManager = null;

    public static StudentDBManager getInstance(Context context){
        if(dbManager==null){
            dbManager = new StudentDBManager(context,DB_STUDENTS,null,DB_VERSION);
        }
        return dbManager;
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public StudentDBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "number TEXT,"
                + "name TEXT,"
                + "department TEXT,"
                + "age TEXT,"
                + "grade INTEGER);");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        }
    }

    public long insert(ContentValues addRowValue){
        return getWritableDatabase().insert(TABLE_STUDENT,null,addRowValue);
    }

    public int insertAll(ContentValues[] values){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        for(ContentValues contentValues : values){
            db.insert(TABLE_STUDENT,null,contentValues);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return values.length;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs,
                        String groupBy, String having, String orderBy){
        return getReadableDatabase()
                .query(TABLE_STUDENT,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public int update(ContentValues updateRowValue, String whereClause, String[] whereArgs){
        return getWritableDatabase().update(TABLE_STUDENT,updateRowValue,whereClause,whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(TABLE_STUDENT,whereClause,whereArgs);
    }
}
