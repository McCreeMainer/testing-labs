package lesson7.task1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestAlignFileByWidth {

    @Test
    fun `Test alignFileByWidth files`() {
        val resLocation = "src/test/resources/TestFiles"
        val spacePattern = """\s+""".toRegex()
        for (file in File(resLocation).walkTopDown()) {
            if (!file.isFile) continue

            assertDoesNotThrow("Fail on ${file.name} : Unexpected Exception") {
                alignFileByWidth("$resLocation/${file.name}", "$resLocation/out/${file.name}")
            }

            val original = File("$resLocation/${file.name}").readLines()
            val result = File("$resLocation/out/${file.name}").readLines()

            assertEquals(original.size, result.size, "Fail on ${file.name} : Rule 4")

            var longestLine = -1

            for (i in original.indices) {
                val originalLine = original[i]
                val resultLine = result[i]

                assertEquals(originalLine, originalLine.trim(), "Fail on ${file.name}, ${i+1} : Rule 1")
                assertEquals(resultLine, resultLine.trim(), "Fail on ${file.name}, ${i+1} : Rule 1")

                if (originalLine.isBlank()) assertTrue(resultLine.isEmpty(), "Fail on ${file.name}, ${i+1} : Rule 2")

                val resultSpaces = spacePattern.findAll(resultLine)
                var prevSpaceLen = -1

                resultSpaces.forEach {
                    val spaceLen = it.value.length

                    if (prevSpaceLen > -1) {
                        assertTrue(prevSpaceLen - it.value.length in (0..1), "Fail on ${file.name}, ${i + 1} : Rule 5 or 6")
                    }
                    prevSpaceLen = spaceLen
                }

                val originalWords = originalLine.split(spacePattern)
                val resultWords = resultLine.split(spacePattern)

                assertEquals(originalWords, resultWords, "Fail on ${file.name}, ${i+1} : Rule 8")

                val cleanOriginalLine = originalLine.replace(spacePattern, " ")

                if (longestLine < cleanOriginalLine.length) {
                    longestLine = cleanOriginalLine.length
                }
            }

            for (i in result.indices) {
                val line = result[i]
                if (line.split(spacePattern).size > 1) assertTrue(line.length <= longestLine)
                if (original[i].replace(spacePattern, " ").length == longestLine) {
                    spacePattern.findAll(line).map { it.value }.forEach {
                        assertEquals(1, it.length, "Fail on ${file.name}, ${i+1} : Rule 7")
                    }
                }
            }
        }
    }
}