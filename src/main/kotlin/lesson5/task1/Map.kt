@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1


// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */

class Item(var name: String, var weight: Int, var price: Int)
class BagPack(var iteams: MutableList<String>, var price: Int)

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    var items = mutableListOf<Item>()

    var treasureList = treasures.toList()
    for (i in 0..treasureList.size - 1)
        items.add(Item(treasureList[i].first, treasureList[i].second.first, treasureList[i].second.second))

    var bp: Array<Array<BagPack>> =
        Array(items.size + 1, { Array(capacity + 1, { BagPack(mutableListOf(""), 0) }) })

    for (i in 0..items.size)
        for (j in 0..capacity) {
            if ((i == 0) || (j == 0))
                bp[i][j] = BagPack(mutableListOf(""), 0)
            else if (i == 1) {
                if (items[0].weight <= j)
                    bp[1][j] = BagPack(mutableListOf(items[0].name), items[0].price)
                else
                    bp[1][j] = BagPack(mutableListOf(""), 0)
            } else if (items[i - 1].weight > j) {
                bp[i][j] = bp[i - 1][j]
            } else {
                var prev = bp[i - 1][j]
                var list = mutableListOf<String>()
                for (k in 0..bp[i - 1][j - items[i - 1].weight].iteams.lastIndex)
                    list.add(bp[i - 1][j - items[i - 1].weight].iteams[k])
                list.add(items[i - 1].name)
                var form = BagPack(
                    list,
                    items[i - 1].price + bp[i - 1][j - items[i - 1].weight].price
                )
                if (prev.price > form.price)
                    bp[i][j] = prev
                else
                    bp[i][j] = form
            }
        }

    var maxPrice = 0
    var ansList = listOf<String>()
    for (i in 0..items.size)
        if (bp[i][capacity].price > maxPrice) {
            maxPrice = bp[i][capacity].price
            ansList = (bp[i][capacity].iteams)
        }
    return ansList.toSet()
}

    