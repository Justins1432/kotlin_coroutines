import kotlinx.coroutines.*

/**
 * Область корутины
 */

/**
 * И для создания области корутин в Kotlin может использоваться ряд функций, которые создают объект интерфейса CoroutineScope.
 * Одной из функций является coroutineScope. Она может применяться к любой функции.
 * Функция coroutineScope(), которая создает область корутин, будет ожидать завершения всех определенных в этой области корутин.
 * функция main завршит выполнение, когда будут завершены все корутины.
 */

suspend fun main() {
    getMath(2, 2)
    println()
    runScope(10, 10)
    println()
    getCor(5, 2)
}

suspend fun getMath(a: Int, b: Int) = coroutineScope {
    val subtraction = a - b
    val addition = a + b
    val multiplication = a * b
    val division = a / b

    launch {
        println(subtraction)
        delay(1000L)
    }

    launch {
        println(addition)
        delay(1500L)
    }

    launch {
        println(multiplication)
        delay(2000L)
    }

    launch {
        println(division)
        delay(2500L)
    }
}

/**
 * Кроме функции coroutineScope для создания контекста корутины может применяться функция runBlocking.
 * Функция runBlocking блокирует вызывающий поток, пока все корутины внутри вызова runBlocking { ... } не завершат свое выполнение.
 * coroutineScope не блокирует вызывающий поток, а просто приостанавливает выполнение, освобождания поток для использования другими ресурсами.
 */

suspend fun runScope(a: Int, b: Int) = runBlocking {
    launch {
        val c = a + b
        val d = a - b
        println(c)
        delay(200L)
        println(d)
        delay(300L)
    }

    launch {
        val c = a * b
        val d = a / b
        println(c)
        delay(500L)
        println(d)
        delay(600L)
    }
}

/**
 * Вложенные корутины
 * Одна корутина может содержать другие корутины
 */

suspend fun getCor(a: Int, b: Int) = coroutineScope {
    launch {
        println("Outer coroutine")
        val c = a + b
        println(c)
        launch {
            println("Inner coroutine")
            val d = a * b
            println(d)
            delay(400L)
        }
    }
    println("End of main")
}