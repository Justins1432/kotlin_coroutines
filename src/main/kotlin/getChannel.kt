import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/**
 * Каналы
 */

/**
 * Каналы позволяют передавать потоки данных.
 * В Kotlin каналы представлены интерфейсом Channel,
 * у которого следует выделить два основных метода
 * abstract suspend fun send(element: E): Unit
Отправляет объект element в канал
abstract suspend fun receive(): E
Получает данные из канала
 */

suspend fun main() = coroutineScope {
    /**
     * Реализация простейшего канала
     */

    val channel = Channel<Int>() //представляет канал
    launch {
        for (i in 1..5) {
            //отправляем данные через канал
            //передаётся значение которое мы хотим отправить
            channel.send(i)
        }
    }

    //получаем данные
    //определяем функцию, которая будет выполняться 5 раз
    repeat(5) {
        val num = channel.receive()
        println(num)
    }

    println()
    getStringChannel()

    println()
    /**
     * Для потребления данных из канала может применяться метод consumeEach() объекта ReceiveChannel,
     */
    val names = getUsers()
    names.consumeEach { name -> println(name) }

}

/**
 * Отправка строк через канал
 */
suspend fun getStringChannel() = coroutineScope {
    val channel = Channel<String>()
    launch {
        val names = listOf<String>("Vlad", "Damir", "Lekha")
        for (name in names) {
            channel.send(name)
        }
        //Чтобы указать, что в канале больше нет данных
        channel.close() //закрываем канал
    }

    for (name in channel) {
        println(name)
    }
}

/**
 * Паттерн producer-consumer
 * функция produce() представляет построитель корутины,
 * который создает корутину, в которой передаются данные в канал.
 * Здесь определяется функция getUsers(). Причем она определяется как функция интерфейса CoroutineScope.
 * Функция должна возвращать объект ReceiveChannel, типизированный типом передаваемых данных
 * Функция getUsers() представляет корутину, создаваемую построителем корутин produce.
 * В корутине опять же проходим по списку строк и с помощью функции send передаем в канал данные.
 */

fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce {
    val names = listOf("Vlad", "Nastya", "Kirill")
    for (name in names) {
        send(name)
    }
}


