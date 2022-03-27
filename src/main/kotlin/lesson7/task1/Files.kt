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
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    var writer = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        if (line.isEmpty())
            writer.newLine()
        else
            if (line[0] != '_') {
                writer.write(line)
                writer.newLine()
            }
    }
    writer.close()
}


/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    var writer = File(outputName).bufferedWriter()
    var ans = StringBuilder()
    for (line in File(inputName).readLines()) {

        var str = line
        ans.append(str[0])
        for (i in 1..str.lastIndex)
            when {
                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'Ы')) -> ans.append('И')
                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'ы')) -> ans.append('и')

                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'Ю')) -> ans.append('У')
                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'ю')) -> ans.append('у')

                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'Я')) -> ans.append('А')
                (("жш".contains(str[i - 1].toLowerCase())) && (str[i] == 'я')) -> ans.append('а')

                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'Я')) -> ans.append('А')
                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'я')) -> ans.append('а')

                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'Ю')) -> ans.append('У')
                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'ю')) -> ans.append('у')

                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'Ы')) -> ans.append('И')
                (("чщ".contains(str[i - 1].toLowerCase())) && (str[i] == 'ы')) -> ans.append('и')

                else -> ans.append(str[i])
            }
        writer.write(ans.toString())
        writer.newLine()
        println(ans)
        ans.setLength(0)
    }
    writer.close()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

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


/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {

}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
    fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    var writer = File(outputName).bufferedWriter()
    var whatCanBeDivided = StringBuilder()

    var index = 0
    for (i in 0..lhv.toString().lastIndex) {
        whatCanBeDivided.append(lhv.toString()[i])
        var num = (whatCanBeDivided.toString().toInt()) / rhv
        if (Math.ceil(num.toDouble()) > 0) {
            index = i + 1
            break
        }
    }
    var deduction = (Math.ceil((whatCanBeDivided.toString().toInt() / rhv).toDouble())) * rhv
    var str1 = StringBuilder()


    if (deduction.toInt().toString().length + 1 <= whatCanBeDivided.toString().length)
        str1.append(lhv.toString() + " | ")
    else
        str1.append(" " + lhv.toString() + " | ")

    val countCharStr1 = str1.toString().length
    str1.append(rhv.toString())
    writer.write(str1.toString())

    if (whatCanBeDivided.toString().length == deduction.toInt().toString().length) {
        var str2 = StringBuilder('-' + deduction.toInt().toString())
        var countCharStr2 = str2.toString().length
        val n = countCharStr1 - countCharStr2
        for (i in 1..n)
            str2.append(' ')
        str2.append((Math.floor((lhv / rhv).toDouble())).toInt())
        writer.newLine()
        writer.write(str2.toString())
        writer.newLine()
        for (i in 1..deduction.toString().lastIndex)
            writer.write("-")
        writer.newLine()
    } else {
        var str2 = StringBuilder()
        for (i in 1..whatCanBeDivided.toString().length - (deduction.toInt().toString().length + 1))
            str2.append(" ")
        str2.append("-" + deduction.toInt().toString())
        var countCharStr2 = str2.toString().length
        val n = countCharStr1 - countCharStr2
        for (i in 1..n)
            str2.append(" ")
        str2.append((Math.floor((lhv / rhv).toDouble())).toInt())
        writer.newLine()
        writer.write(str2.toString())
        writer.newLine()

        for (i in 1..whatCanBeDivided.toString().length)
            writer.write("-")
        writer.newLine()
    }


    var res = StringBuilder((whatCanBeDivided.toString().toInt() - deduction.toInt()).toString())
    var countIndent = 1
    var k = 0

    for (i in index..lhv.toString().length) {
        if (lhv == rhv) {
            for (i in 1..lhv.toString().length)
                writer.write(" ")
            writer.write("0")
            break
        }


        if (i > lhv.toString().lastIndex) {
            res = StringBuilder((whatCanBeDivided.toString().toInt() - deduction.toInt()).toString())
            countIndent += whatCanBeDivided.toString().length + 1 - res.toString().length
            if ((k == 0) && (lhv.toString().length > (rhv * Math.floor((lhv / rhv).toDouble())).toInt()
                    .toString().length)
            )
                for (j in 3..countIndent)
                    writer.write(" ")
            else
                for (j in 2..countIndent)
                    writer.write(" ")
            writer.write(res.toString())
            break
        }

        res = StringBuilder((whatCanBeDivided.toString().toInt() - deduction.toInt()).toString())



        if (lhv >= rhv)
            res.append(lhv.toString()[i])


        if (lhv < rhv) {
            if (lhv.toString().length >= 2)
                writer.write(lhv.toString())
            else
                writer.write(" " + lhv.toString())
            break
        }

        k += 1

        if (k == 1)
            if (deduction.toInt().toString().length + 1 <= whatCanBeDivided.toString().length)
                countIndent += whatCanBeDivided.toString().length - res.toString().length
            else
                countIndent += whatCanBeDivided.toString().length + 1 - res.toString().length
        else
            countIndent += whatCanBeDivided.toString().length + 1 - res.toString().length

        if ((res.toString().toInt() == 0) && (i >= lhv.toString().lastIndex)) {
            for (j in 1..(countIndent))
                writer.write(" ")
            writer.write(res.toString())
            writer.newLine()
            for (j in 1..(countIndent))
                writer.write(" ")
            writer.write("-0")
            writer.newLine()
            for (j in 1..(countIndent))
                writer.write(" ")
            for (j in 1..2)
                writer.write("-")
            writer.newLine()
            for (j in 0..(countIndent))
                writer.write(" ")
            writer.write("0")
            break
        } else if ((res.toString().toInt() < rhv) && (i == lhv.toString().lastIndex)) {
            if (res.toString().toInt() != lhv) {
                for (j in 1..(countIndent))
                    writer.write(" ")
                writer.write(res.toString())
                writer.newLine()
                for (j in 2..(countIndent + res.toString().lastIndex))
                    writer.write(" ")
                writer.write("-0")
                writer.newLine()
                for (j in 1..(countIndent))
                    writer.write(" ")
                for (j in 1..res.toString().length)
                    writer.write("-")
                writer.newLine()
                for (j in 1..(countIndent + (res.toString().length - res.toString().toInt().toString().length)))
                    writer.write(" ")
                writer.write(res.toString().toInt().toString())
                break
            } else {
                for (j in 2..(countIndent))
                    writer.write(" ")
                writer.write(res.toString())
                break
            }
        }

        whatCanBeDivided.setLength(0)
        whatCanBeDivided.append(res)
        deduction = (Math.ceil((whatCanBeDivided.toString().toInt() / rhv).toDouble())) * rhv

        for (j in 1..countIndent)
            writer.write(" ")
        writer.write(whatCanBeDivided.toString())
        writer.newLine()

        if (whatCanBeDivided.toString().length == deduction.toInt().toString().length) {
            for (j in 2..countIndent)
                writer.write(" ")
            writer.write("-" + deduction.toInt().toString())
            writer.newLine()
            for (j in 2..countIndent)
                writer.write(" ")
            for (j in 2..deduction.toString().length)
                writer.write("-")
            writer.newLine()
        } else {
            for (j in 1..countIndent + res.toString().length - deduction.toInt().toString().length - 1)
                writer.write(" ")
            writer.write("-" + deduction.toInt().toString())
            writer.newLine()
            for (j in 1..countIndent)
                writer.write(" ")
            for (j in 1..res.toString().length)
                writer.write("-")
            writer.newLine()
        }

    }
    writer.close()
}


