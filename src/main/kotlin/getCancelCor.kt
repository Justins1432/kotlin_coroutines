import kotlinx.coroutines.*

/**
 * Отмена выполнения корутин
 */

/**
 * Для отмены выполнения корутины у объекта Job
 * может применяться метод cancel()
 */

suspend fun main() = coroutineScope {
    get(2.345)
    println()
    getCancellationException(3.2f)

    /**
     * Отмена async-корутины
     */
    val result = async {
        getNumber(2)
    }

    result.cancelAndJoin()

    try {
        println("результат: ${result.await()}")
    } catch (e: CancellationException) {
        println("Coroutine has been canceled")
    }

    println("Программа закончила операцию")

}

/**
 * Вызов метода downloader.cancel() сигнализирует корутине, что надо прервать выполнение.
 * С помощью метода join() ожидаем завершения корутины, которая прервана.
 * Также вместо двух методов cancel() и join() можно использовать один сборный метод cancelAndJoin()
 */
suspend fun get(a: Double) = coroutineScope {
    var r: Double?
    var s: Double?

    val result: Job = launch {
        println("Начинаем умножение")
        for (i in 1..10) {
            r = i * a
            println(r)
            delay(500L)
        }
    }

    delay(1000L)
    println("Надоело! Прерву расчёт")
    result.cancel() //прерываем работу корутины
    result.join() //ожидаем завершение корутины
    println("Работа завершена")

    println()

    val result1: Job = launch {
        println("Начинаем деление")
        for (i in 1..12) {
            s = i / a
            println(s)
            delay(500L)
        }
    }

    delay(1000L)
    println("Надоело! Прерву расчёт")
    result1.cancelAndJoin() //отменяем корутину и ожидаем её завершения
    println("Работа завершена")
}

/**
 * Обработка исключения CancellationException
 * Все suspend-функции в пакете kotlinx.coroutines являются прерываемыми (cancellable).
 * Это значит, что они проверяют, прервана ли корутина.
 * И если ее выполнение прервано, они генерируют исключение типа CancellationException.
 */

suspend fun getCancellationException(a: Float) = coroutineScope {
    var res: Float?

    val sum: Job = launch {
        try {
            println("Начинаём расчёт")
            for (i in 1..15) {
                res = i * a
                println(res)
                delay(1000L)
            }
        } catch (e: CancellationException) {
            println("Работа закончена")
        } finally {
            println("Расчёт окончен")
        }
    }

    delay(6000L)
    println("Надоело! Прерву расчёт")
    sum.cancelAndJoin() //отменяем корутину и ожидаем её завершения
    println("Работа завершена")
}

suspend fun getNumber(a: Int) {
    var res: Int?
    for (i in 1..100) {
        res = i + a
        println(res)
    }
    delay(600L)
}
