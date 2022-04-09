package lesson5.fuzzing

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import kotlin.random.Random

object MapFuzzerTarget {

    @JvmStatic
    fun fuzzerTestOneInput(data: FuzzedDataProvider) {
        val capacity = data.consumeInt()
        val treasures = mutableMapOf<String, Pair<Int, Int>>()
        for (i in 0..Random.nextInt(0, 1000)) {
            treasures[data.consumeString(16)] = Pair(data.consumeInt(), data.consumeInt())
        }
        lesson5.task1.bagPacking(treasures.toMap(), capacity)
    }
}