import kotlinx.coroutines.*


/**
 * Диспетчер корутины
 */

/**
 * Контекст корутины включает себя такой элемент как диспетчер корутины.
 * Диспетчер корутины определяет какой поток или какие потоки будут использоваться для выполнения корутины.
 */

/**
 *  Все построители корутины, в частности, функции launch и async в качестве необязательного параметра
 *  принимают объект типа CoroutineContext, который может использоваться для определения диспетчера создаваемой корутины.
 */

/**
 * Когда функция launch вызывается без параметров, она перенимает контекст, в котором она создается и запускается.
 * Например, используем метод Thread.currentThread(), который предоставляет JDK, чтобы получить данные потока корутины:
 */

/**
 *  Dispatchers.Default: применяется по умолчанию, если тип диспетчера не указан явным образом.
 *  Этот тип использует общий пул разделяемых фоновых потоков и подходит для вычислений,
 *  которые не работают с операциями ввода-вывода (операциями с файлами, базами данных, сетью)
 *  и которые требуют интенсивного потребления ресурсов центрального процессора.
 */

/**
 * Dispatchers.IO: использует общий пул потоков, создаваемых по мере необходимости,
 * и предназначен для выполнения операций ввода-вывода
 * (например, операции с файлами или сетевыми запросами).
 */

/**
 * Dispatchers.Main: применяется в графических приложениях,
 * например, в приложениях Android или JavaFX.
 */

/**
 *  Dispatchers.Unconfined: корутина не закреплена четко за определенным потоком или пулом потоков.
 *  Она запускается в текущем потоке до первой приостановки.
 *  После возобновления работы корутина продолжает работу в одном из потоков, который сторого не фиксирован.
 *  Не рекомендуется для использования!!!
 */

/**
 * newSingleThreadContext и newFixedThreadPoolContext:
 * позволяют вручную задать поток/пул для выполнения корутины
 */

suspend fun main() = coroutineScope {
    /**
     * с помощью переменной Thread.currentThread().name мы можем получить имя потока
     */

    launch {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")

    println()
    getDispatcherDefault()

    println()
    getDispatcherUnconfined()

    println()
    getNewSingleThreadContext()
}

/**
 * Задаём для корутины диспетчер
 * getDispatcherDefault
 */

suspend fun getDispatcherDefault() = coroutineScope {
    launch(Dispatchers.Default) {
        println("Выполнение корутины на потоке: ${Thread.currentThread().name}")
    }
    println("Функция getDispatcherDefault выполняется на потоке: ${Thread.currentThread().name}")
}

/**
 * Dispatchers.Unconfined
 * Тип Dispatchers.Unconfined запускает корутину в текущем вызывающем потоке до первой приостановки.
 * После возобновления корутина продолжает работу в одном из потоков, который строго не фиксирован.
 * Подобный тип подходит для корутин, которым не требуется интенсивно потреблять время CPU или работать с общими данными,
 * наподобие объектов пользовательского интерфейса.
 */

suspend fun getDispatcherUnconfined() = coroutineScope {
    launch(Dispatchers.Unconfined) {
        println("Поток корутины до остановки: ${Thread.currentThread().name}")
        delay(1000L)
        println("Поток корутины после остановки: ${Thread.currentThread().name}")
    }
    println("Поток функции getDispatcherUnconfined: ${Thread.currentThread().name}")
}

/**
 * newSingleThreadContext
 * newSingleThreadContext вручную запускает поток с указанным именем
 */

suspend fun getNewSingleThreadContext() = coroutineScope {
    launch(newSingleThreadContext("Custom Thread")) {
        println("Поток корутины: ${Thread.currentThread().name}")
    }
    println("Поток функции getNewSingleThreadContext: ${Thread.currentThread().name}")
}