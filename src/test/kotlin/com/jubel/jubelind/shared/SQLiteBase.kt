package com.jubel.jubelind.shared

import java.io.File

open class SQLiteBase {

    companion object{

        init{

            val original = File("db/jubelind.db")
            val target = File("db/jubelind.test.db")
            original.copyTo(target,true)

        }

    }
}