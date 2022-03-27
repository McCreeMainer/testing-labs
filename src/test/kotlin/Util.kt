import kotlin.random.Random

private val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"

fun randomString(len: Long): String = (1..len)
    .map { Random.nextInt(0, charset.length) }
    .map(charset::get)
    .joinToString("");