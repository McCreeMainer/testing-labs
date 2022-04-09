package lesson7.fuzzing

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import java.io.File

object FilesFuzzerTarget {

    @JvmStatic
    fun fuzzerTestOneInput(data: FuzzedDataProvider) {
        val inName = "in.txt"
        val fileIn = File(inName)
        fileIn.createNewFile()
        fileIn.writeBytes(data.consumeBytes(10000))

        val outName = "out.txt"
        val fileOut = File(outName)
        fileOut.createNewFile()

        lesson7.task1.alignFileByWidth(inName, outName)
        fileIn.delete()
        fileOut.delete()
    }
}
