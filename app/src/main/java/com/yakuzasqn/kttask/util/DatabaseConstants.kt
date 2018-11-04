package com.yakuzasqn.kttask.util

class DatabaseConstants {

    object USER {
        val TABLE_NAME = "user"

        object COLUMNS {
            val ID = "id"
            val NAME = "name"
            val EMAIL = "email"
            val PASSWORD = "password"
        }
    }

    object PRIORITY {
        val TABLE_NAME = "priority"

        object COLUMNS {
            val ID = "id"
            val DESCRIPTION = "description"
        }
    }

    object TASK {
        val TABLE_NAME = "task"

        object COLUMNS {
            val ID = "id"
            val USERID = "user_id"
            val PRIORITYID = "priority_id"
            val DESCRIPTION = "description"
            val COMPLETE = "complete"
            val DUEDATE = "duedate"
        }
    }
}