import kotlinx.coroutines.*

/**
 * launch и Job
 */

suspend fun main() = coroutineScope {
    getA(1, 1)
    println()
    getJob(2, 4)
    println()
    getB(3.001, 4.124)
    println()
    getC(2.001)
}

/**
 * Построитель корутин launch возвращает объект Job, с помощью которого можно управлять запущеной корутиной
 */

suspend fun getA(a: Int, b: Int) = coroutineScope {
    launch {
        val res1 = a + b; println(res1)
        val res2 = a - b; println(res2)
        val res3 = a * b; println(res3)
        val res4 = a / b; println(res4)
        delay(1000L)
    }

    println("Начало")
    println("Конец")

}

/**
 * Применение интерфейса Job
 * Здесь корутина также запускается с помощью launch, однако благодаря методу join() полученного объекта
 * Job функция main остановит выполнение и будет ожидать завершения корутины и только после ее завершения продолжит работу.
 */
suspend fun getJob(a: Int, b: Int) = coroutineScope {
    val job = launch {
        val res1 = a + b; println(res1)
        val res2 = a - b; println(res2)
        val res3 = a * b; println(res3)
        val res4 = a / b; println(res4)
        delay(1000L)
    }
    println("Начало")
    job.join()
    println("Конец")
}

/**
 * Отложенное выполнение
 * Функция getB применяет стандартное выполнение корутины
 * Функция getC применяет отложенное выполнение корутины
 */

suspend fun getB(a: Double, b: Double) = coroutineScope {
    var res: Double? = null
    launch {
        delay(300L)
        res = a + b
        println("Процесс начался")
    }
    delay(1000L)
    println(res)
    println("Процесс закончился")
}

suspend fun getC(a: Double) = coroutineScope {
    var result: Double? = null
    //корутина создана но не запущена
    val job = launch(start = CoroutineStart.LAZY) {
        delay(300L)
        for (i in 1..4){
            result = i * a
            println(result)
        }
        println("Старт корутины")
    }
    delay(2000L)
    job.start() // запуск корутины
    println("Процесс выполнения закончился")
}