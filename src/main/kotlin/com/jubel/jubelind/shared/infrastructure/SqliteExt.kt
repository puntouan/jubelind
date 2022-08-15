package com.jubel.jubelind.shared.infrastructure

import kotlinx.coroutines.*
import org.sqlite.SQLiteException
import java.sql.PreparedStatement

fun PreparedStatement.executeUpdateWithDelay(delayMills: Long, lastChance: Boolean = false): Int? {
    val ps = this
    return runBlocking {
        CoroutineScope(Dispatchers.IO).async {
            try {
                delay(delayMills)
                ps.executeUpdate()
            }catch(ex: SQLiteException){
                if (ex.message?.contains("[SQLITE_BUSY]") == true && !lastChance){
                    null
                }else{
                    println("Exception when executing update: $ps")
                    throw ex
                }
            }
        }.await()
    }

}

fun PreparedStatement.patientExecuteUpdate(): Int {
    
    val maxAttempts = 8

    var ret: Int? = null
    var attempt = 0
    while (ret == null){
        attempt++
        val delay = (attempt - 1) * 200L
        ret = this.executeUpdateWithDelay(delay, attempt == maxAttempts)
    }
    return ret

}
