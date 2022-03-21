package lesson6.task1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestFirstDuplicateIndex {

    @Test
    fun `Test firstDuplicateIndex common string`() {
        assertEquals(
            -1,
            firstDuplicateIndex("a b o b a")
        )
        assertEquals(
            9,
            firstDuplicateIndex("Он пошёл в в школу")
        )
        assertEquals(
            4,
            firstDuplicateIndex("sas SuS sus sAs")
        )
        assertEquals(
            0,
            firstDuplicateIndex("!@#$ !@#$")
        )
        assertEquals(
            6,
            firstDuplicateIndex("11111 2222 2222 11111")
        )
    }
}