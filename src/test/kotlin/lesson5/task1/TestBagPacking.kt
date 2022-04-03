package lesson5.task1

import org.junit.jupiter.api.Test
import randomString
import kotlin.random.Random.Default.nextInt
import kotlin.test.assertEquals
import kotlin.test.fail

class TestBagPacking {

    @Test
    fun `Test bagPacking math`() {
        assertEquals(
            setOf("Кубок"),
            bagPacking(mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)), 850),
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)), 450)
        )
        assertEquals(
            setOf("Мороженое"),
            bagPacking(mapOf("Мороженое" to (10 to 50), "Вишенка" to (1 to 1)), 10)
        )
        assertEquals(
            setOf("Магнитофон", "Кубик"),
            bagPacking(mapOf("Магнитофон" to (10 to 3000), "Кубик" to (0 to 300)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Bitcoin" to (Int.MAX_VALUE to 1), "Etherium" to (Int.MAX_VALUE to 2)), 10000)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Bitcoin" to (Int.MAX_VALUE to 10), "Etherium" to (Int.MAX_VALUE to 20)), 10000)
        )
        assertEquals(
            emptySet(),
            bagPacking(
                mapOf(
                    "Bitcoin" to (Int.MAX_VALUE to Int.MAX_VALUE),
                    "Etherium" to (Int.MAX_VALUE to Int.MAX_VALUE)
                ),
                10000
            )
        )
    }

    @Test
    fun `Test bagPacking repeating treasures data sets`() {
        assertEquals(
            setOf("Сладкий рулет"),
            bagPacking(
                mapOf(
                    "Сладкий рулет" to (1 to 2),
                    "Сладкий рулет" to (1 to 2),
                    "Сладкий рулет" to (1 to 2),
                    "Сладкий рулет" to (1 to 2),
                    "Сладкий рулет" to (1 to 2)
                ),
                10
            )
        )
    }

    @Test
    fun `Test bagPacking equal costs`() {
        assertEquals(
            setOf("Зубная паста"),
            bagPacking(
                mapOf(
                    "Молоко" to (1 to 2),
                    "Хлеб" to (1 to 2),
                    "Зубная паста" to (4 to 8),
                    "Чяй" to (1 to 2),
                    "Кофя" to (1 to 2)
                ),
                4
            )
        )
        assertEquals(
            setOf("Соленый огурец"),
            bagPacking(
                mapOf(
                    "Пустая шоколадина" to (1 to 2),
                    "Пустая шоколадина" to (1 to 2),
                    "Соленый огурец" to (4 to 8),
                    "Пустая шоколадина" to (1 to 2),
                    "Пустая шоколадина" to (1 to 2)
                ),
                4
            )
        )
    }

    @Test
    fun `Test bagPacking Int overflow`() {
        assertEquals(
            setOf("Etherium"),
            bagPacking(mapOf("Bitcoin" to (1 to 1), "Etherium" to (2 to 2)), Int.MAX_VALUE)
        )
    }

    @Test
    fun `Test bagPacking zero capacity`() {
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)), 0)
        )
        assertEquals(
            setOf("Кубик"),
            bagPacking(mapOf("Магнитофон" to (10 to 3000), "Кубик" to (0 to 300)), 0)
        )
    }

    @Test
    fun `Test bagPacking negative capacity`() {
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кубик" to (1 to 300)), -1)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кубик" to (1 to 300)), -10)
        )
    }

    @Test
    fun `Test bagPacking negative treasure values`() {
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Дырка" to (-1 to 10)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кнопка" to (10 to -1)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Кнопка" to (-1 to -1)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Магнитофон" to (-10 to 10)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Турель" to (10 to -10)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Телепорт" to (-10 to -10)), 10)
        )
        assertEquals(
            emptySet(),
            bagPacking(mapOf("Bitcoin" to (1 to Int.MIN_VALUE), "Etherium" to (2 to Int.MIN_VALUE)), 3)
        )
    }

    @Test
    fun `Test bagPacking big data set`() {
        try {
            bagPacking(
                (1..10000).associate {
                    randomString(20) to (nextInt(1, Int.MAX_VALUE) to nextInt(1, Int.MAX_VALUE))
                },
                1000000
            )
        } catch (e: Throwable) {
            fail()
        }
    }
}