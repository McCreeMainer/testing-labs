@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1


// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var useStr = str + ' '
    var ans = -1
    var listWords = str.split(" ")
    for (i in 0..listWords.lastIndex - 1) {
        if ((listWords[i].toLowerCase()) == (listWords[i + 1].toLowerCase())) {
            ans += 1
            return ans
        }
        ans += listWords[i].length
        ans += 1
    }
    ans = -1
    return ans
}
