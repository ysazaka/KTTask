package com.yakuzasqn.kttask.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.yakuzasqn.kttask.util.DatabaseConstants

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
    );"""

    private val createTablePriority = """ CREATE TABLE ${DatabaseConstants.PRIORITY.TABLE_NAME} (
        ${DatabaseConstants.PRIORITY.COLUMNS.ID} INTEGER PRIMARY KEY,
        ${DatabaseConstants.PRIORITY.COLUMNS.DESCRIPTION} TEXT
    );"""
    private val createTableTask = """ CREATE TABLE ${DatabaseConstants.TASK.TABLE_NAME} (
        ${DatabaseConstants.TASK.COLUMNS.ID} INTEGER PRIMARY KEY,
        ${DatabaseConstants.TASK.COLUMNS.USERID} INTEGER PRIMARY KEY,
        ${DatabaseConstants.TASK.COLUMNS.PRIORITYID} INTEGER PRIMARY KEY,
        ${DatabaseConstants.TASK.COLUMNS.DESCRIPTION} TEXT,
        ${DatabaseConstants.TASK.COLUMNS.COMPLETE} TEXT,
        ${DatabaseConstants.TASK.COLUMNS.DUEDATE} TEXT
    );"""

    private val insertPriorities = """INSERT INTO ${DatabaseConstants.PRIORITY.TABLE_NAME}
        VALUES (1, 'Baixa'),
        VALUES (2, 'Média'),
        VALUES (3, 'Alta'),
        VALUES (4, 'Crítica')
        """

    private val deleteTableUser = "DROP TABLE IF EXISTS ${DatabaseConstants.USER.TABLE_NAME}"
    private val deleteTablePriority = "DROP TABLE IF EXISTS ${DatabaseConstants.PRIORITY.TABLE_NAME}"
    private val deleteTableTask = "DROP TABLE IF EXISTS ${DatabaseConstants.TASK.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableUser)
        db.execSQL(createTablePriority)
        db.execSQL(createTableTask)
        db.execSQL(insertPriorities)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Delete + Insert on the new database
        db.execSQL(deleteTableUser)
        db.execSQL(deleteTablePriority)
        db.execSQL(deleteTableTask)

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