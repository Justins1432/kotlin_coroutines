import kotlinx.coroutines.*

/**
 * Вынесение кода корутин в отдельную функцию
 * функция main должна возвращать значение Unit.
 * можно типизировать функцию coroutineScope типом Unit
 * Можно запускать несколько корутин
 */

suspend fun main() = coroutineScope<Unit> {
    launch {
        subtraction(10, 10)
    }

    launch {
        addition(20, 20)
    }

    launch {
        multiplication(5, 5)
    }

    launch {
        division(10, 1)
    }
}

suspend fun subtraction(a: Int, b: Int) {
    val c = a - b
    println(c)
    delay(400L)
}

suspend fun addition(a: Int, b: Int) {
    val c = a + b
    println(c)
    delay(500L)
}

suspend fun multiplication(a: Int, b: Int) {
    val c = a * b
    println(c)
    delay(600L)
}

suspend fun division(a: Int, b: Int){
    val c = a / b

    if (b.equals(0)){
        println("На 0 делить нельзя")
    } else {
        println(c)
        delay(700L)
    }
}