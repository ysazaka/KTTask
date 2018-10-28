package repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import util.DatabaseConstants

class TaskDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // static
    companion object {
        private val DATABASE_VERSION: Int = 1
        private val DATABASE_NAME: String = "task.db"
    }

    // SQLite
    /* Available data types: INTEGER, REAL, TEXT, BLOB */

    // These quotation marks avoid writing "string + valor + string"
    private val createTableUser = """ CREATE TABLE ${DatabaseConstants.USER.TABLE_NAME} (
        ${DatabaseConstants.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseConstants.USER.COLUMNS.NAME} TEXT,
        ${DatabaseConstants.USER.COLUMNS.EMAIL} TEXT,
        ${DatabaseConstants.USER.COLUMNS.PASSWORD} TEXT
    );
    """

    private val deleteTableUser = "DROP TABLE IF EXISTS ${DatabaseConstants.USER.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Delete + Insert on the new database
        db.execSQL(deleteTableUser)
        db.execSQL(createTableUser)

        when (oldVersion){
            1 -> {
                // Assuming that a use has version 1 of the database, and he needs to update until version 4
                // Update from 1 to 2
                // Update from 2 to 3
                // Update from 3 to 4
            }
        }
    }


}