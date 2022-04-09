package lesson6.fuzzing

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import kotlin.random.Random

object ParseFuzzerTarget {

    @JvmStatic
    fun fuzzerTestOneInput(data: FuzzedDataProvider) {
        lesson6.task1.firstDuplicateIndex(data.consumeString(Random.nextInt(0, 10000)))
    }
}