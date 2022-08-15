package com.jubel.jubelind.shared

import java.io.File

open class SQLiteBase {

    companion object{

        init{

            val original = File("/Users/juan/fuente/propio/jbelind.db")
            val target = File("/Users/juan/fuente/propio/jbelind.test.db")
            original.copyTo(target,true)

        }

    }
}