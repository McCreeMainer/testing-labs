@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import kotlin.text.StringBuilder

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun countLen(lst: List<String>): Int {
    var len = 0
    for (i in 0..lst.lastIndex)
        len += lst[i].length
    return len
}

fun alignFileByWidth(inputName: String, outputName: String) {
    var writer = File(outputName).bufferedWriter()
    var maxLen = 0
    for (line in File(inputName).readLines()) {
        var strForHelp = "  " + line
        var listSplit = strForHelp.split(Regex(""" +"""))
        var lstStr = mutableListOf<String>()
        lstStr = listSplit.toMutableList()
        if (lstStr[lstStr.lastIndex] == "")
            lstStr.removeAt(lstStr.lastIndex)
        maxLen = Math.max(maxLen, countLen(lstStr) + lstStr.size - 2)
    }
    for (line in File(inputName).readLines()) {
        var strForHelp = "  " + line
        var listSplit = strForHelp.split(Regex(""" +"""))
        var lstStr = listSplit.toMutableList()
        if (lstStr[lstStr.lastIndex] == "")
            lstStr.removeAt(lstStr.lastIndex)

        if ((!(line.isEmpty())) && (lstStr.size > 2)) {
            if (countLen(lstStr) + lstStr.size - 2 < maxLen) {
                writer.write(lstStr[1])
                var countSpace = maxLen - (countLen(lstStr) + lstStr.size - 2)
                var inOnePass = (Math.floor((countSpace / (lstStr.size - 2)).toDouble())).toInt()
                var remain = countSpace % (lstStr.size - 2)
                for (i in 2..lstStr.lastIndex) {
                    if (remain > 0) {
                        for (j in 1..(inOnePass + 1 + 1))
                            writer.write(" ")
                        remain -= 1
                    } else {
                        for (j in 1..(inOnePass + 1))
                            writer.write(" ")
                    }
                    writer.write(lstStr[i])
                }
            } else {
                writer.write(lstStr[1])
                for (i in 2..lstStr.lastIndex) {
                    writer.write(" ")
                    writer.write(lstStr[i])
                }
            }
        }
        if (lstStr.size == 2)
            writer.write(lstStr[1])
        writer.newLine()
    }
    writer.close()
}
