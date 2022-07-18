import kotlinx.coroutines.*

/**
 * Async, await, Deferred
 */

/**
 * Функция async. Эта функция применяется, когда надо получить из корутины некоторый результат.
 * async запускает отдельную корутину, которая выполняется параллельно с остальными корутинами
 * async-корутина возвращает объект Deferred, который ожидает получения результата корутины.
 * Для получения результата из объекта Deferred применяется функция await().
 */

suspend fun main() = coroutineScope {
    async {
        getPrint("Привет Влад!")
    }
    println("Выполнение закончилось")

    /**
     * для имитации продолжительной работы определена функция getAddition
     * функция getAddition возвращает объект типа Int то возвращаемый корутиной
     * объект представляет тип Deferred<Int>
     * Далее у объекта Deferred для получения результата функции getAddition() вызываем метод await().
     */

    val result: Deferred<Int> = async { getAddition() }
    println("результат: ${result.await()}")
    println("Финиш")

    println()

    /**
     * Запуск нескольких корутин которые будут выполняться параллельно
     */

    val numDef1 = async { subtraction(3.001, 1.000) }
    val res1 = numDef1.await()
    val numDef2 = async { sum(5.002, 3.123) }
    val res2 = numDef2.await()
    val numDef3 = async { multiplication(3.000, 6.001) }
    val res3 = numDef3.await()
    val numDef4 = async { division(12.001, 4.001) }
    val res4 = numDef4.await()
    println("результат_1: $res1, результат_2: $res2, результат_3: $res3, результат_4: $res4")

    /**
     * Отложенный запуск
     */

    //корутина создана не запущена
    val result1 = async(start = CoroutineStart.LAZY) {
        sum(0.003, 1.223)
    }

    delay(3000L)
    println("Actions after the coroutine creation")
    println("результат_работы: ${result1.await()}") //запуск и выполнение

    /**
     * Если необходимо чтобы корутина еще до await начала выполняться
     */

    val result2 = async(start = CoroutineStart.LAZY) {
        sum(10.350, 12.901)
    }

    delay(4500L)
    println("Actions after the coroutine creation")
    result2.start() //запуск
    println("результат_сложения: ${result2.await()}") //получение результата

}

suspend fun getPrint(a: String) {
    delay(2500L)
    println(a)
    println("Сообщение отправлено!")
}

suspend fun getAddition(): Int {
    delay(1000L)
    val a = 10
    val b = 10
    return a + b
}

suspend fun subtraction(a: Double, b: Double): Double {
    delay(1000L)
    return a - b
}

suspend fun sum(a: Double, b: Double): Double {
    delay(1000L)
    return a + b
}

suspend fun multiplication(a: Double, b: Double): Double {
    delay(1000L)
    return a * b
}

suspend fun division(a: Double, b: Double): Double {
    delay(1000L)
    return a / b
}



