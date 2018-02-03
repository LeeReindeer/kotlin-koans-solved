
# kotlin-koans-solved

## Contents
- [1.Introduction](#1-introduction)

- [2.Collection](#2-collections)

- [3.Conventions](#3-conventions)

- [4.Properties](#4-properties)

- [5.Builders](#5-builders)

- [6.Generics](#6-generics)


## 1. Introduction

### 1.0 Hello World

solution:
``` kotlin
fun task0(): String {
    return "OK"
}
```

### 1.1 Java to Kotlin Converter

solution:
```kotlin
fun task1(collection: Collection<Int>): String {
    val sb = StringBuilder()
    sb.append("{")
    val iterator = collection.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        sb.append(element)
        if (iterator.hasNext()) {
            sb.append(", ")
        }
    }
    sb.append("}")
    return sb.toString()
}
```

### 1.2 Named Arguments
solution:
```kotlin
fun task2(collection: Collection<Int>): String {
    return collection.joinToString(prefix = "{",postfix = "}")
}
```

### 1.3 Default Arguments

solution:
```kotlin
fun foo(name: String,number: Int=42,toUpperCase:Boolean=false): String
     =(if (toUpperCase) name.toUpperCase() else name) + number

fun task3(): String {
    return (foo("a") +
            foo("b", number = 1) +
            foo("c", toUpperCase = true) +
            foo(name = "d", number = 2, toUpperCase = true))
}
```

### 1.4 Lambdas

solution:
```kotlin
fun task4(collection: Collection<Int>): Boolean = collection.any { it %42==0 }
```

### 1.5 String Templates

solution:
```kotlin
val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
fun task5()="""\d{2}\s$month\s\d{4}"""
```

### 1.6 Data Classes

solution:
```kotlin
data class Person(val name: String, val age: Int)

fun task6(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}
```

### 1.7 Nullable Types

solution:
```kotlin
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    val personalInfo = client?.personalInfo ?:return
    val email = personalInfo.email ?: return
    if (message != null){
        mailer.sendMessage(email,message)
    }
    //todoTask7(client, message, mailer)
}
```

### 1.8 Smart Casts

solution:
```kotlin
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.left) + eval(e.right)
        }
```

### 1.9 Extension Functions

solution:
```kotlin
data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this,1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first,this.second)

fun main(args:Array<String>){
  val l= mutableListOf(1,2,3)
    l.swap(0,2)
    print(l)
    print(4.r())
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
```

### 1.10 Object Expressions

solution:
```kotlin
fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, object: Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            return o2!! - o1!!
        }

    })
    return arrayList
}
```

### 1.11 SAM Conversions

solution:
```kotlin
fun task11(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, { x, y -> y-x })
    return arrayList
}
```

### 1.12 Extensions On Collections

solution:
```kotlin
fun task12(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}
```

<<<<<<< HEAD
## 2. Collections

### 2.13 Introduction

```kotlin
fun Shop.getSetOfCustomers(): Set<Customer> = this.customers.toSet()
```

### 2.14 FilterMap

```kotlin
// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = this.customers.map {it.city}.toSet()

// Return a list of the customers who live in the given city
fun Shop.getCustomersFrom(city: City): List<Customer> = this.customers.filter {it.city == city}
```


### 2.15 All Any and other predicates

```kotlin
// Return true if all customers are from the given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = this.customers.all{it.city == city}

// Return true if there is at least one customer from the given city
fun Shop.hasCustomerFrom(city: City): Boolean = this.customers.any{it.city == city}

// Return the number of customers from the given city
fun Shop.countCustomersFrom(city: City): Int = this.customers.count{it.city == city}

// Return a customer who lives in the given city, or null if there is none
fun Shop.findAnyCustomerFrom(city: City): Customer? = this.customers.firstOrNull{it.city == city}
```

### 2.16 FlatMap

```kotlin
// Return all products this customer has ordered
val Customer.orderedProducts: Set<Product> get() {
    return this.orders.flatMap{it.products}.toSet()
}

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product> get() {
    return this.customers.flatMap{it.orders}.flatMap{it.products}.toSet()
}
```

### 2.17 Max Main

```kotlin
// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = this.customers.maxBy {it.orders.size}

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = this.orders.flatMap {it.products}.maxBy {it.price}
```

### 2.18 Sort

```kotlin
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = this.customers.sortedBy {it.orders.size}
```

### 2.19 Sum

```kotlin
fun Customer.getTotalOrderPrice(): Double = this.orders.flatMap {it.products}.sumByDouble {it.price}
```

### 2.20 GroupBy

```kotlin
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = this.customers.groupBy {it.city}
```

### 2.21 Partition

```kotlin
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = this.customers.filter {
    val (y, n) = it.orders.partition {it.isDelivered}
    y.size < n.size
}.toSet()
```

### 2.22 Fold

```kotlin
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    return this.customers.fold(this.customers.flatMap{it.orders}.flatMap{it.products}.toSet(), {
        orderByAll, customer ->
        val thisProducts = customer.orders.flatMap { it.products }.toSet()
        orderByAll.intersect(thisProducts)
    })
}
```

### 2.23 Compound Tasks

```kotlin
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return this.orders.filter {it.isDelivered}.flatMap {it.products}.maxBy {it.price}
}


fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return this.customers.flatMap {it.orders}.flatMap {it.products}.count {it == product}
}
```

### 2.24 Extension On Collections

```kotlin
fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection.groupBy { it.length }

    return groupsByLength.values.maxBy { it.size }
}
```

## 3. Conventions

### 3.25 Comparison

```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}
```

### 3.26 In range

```kotlin
class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(values: MyDate): Boolean = (values >= start) && (values <= endInclusive)
}
```

### 3.27 Range to

```kotlin
class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>{

    override fun contains(value: MyDate): Boolean =((endInclusive>=start)&&(start<=value)&&(endInclusive>=value))
}
```

### 3.28 For loop

```kotlin
class DateRange(val start: MyDate, val end: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }
}

class DateIterator(private val dateRange: DateRange) : Iterator<MyDate> {

    var current: MyDate = dateRange.start

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.end

}
```

### 3.29 Operator overloading

```kotlin
class RepeatedTime(val time: TimeInterval, val number: Int)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(timeInterval: RepeatedTime): MyDate = this.addTimeIntervals(timeInterval.time, timeInterval.number)

operator fun TimeInterval.times(time: Int) = RepeatedTime(this, time)
```

### 3.30 Destructuring declarations

```kotlin
fun isLeapDay(date: MyDate): Boolean {

    val (year, month, dayOfMonth) = date

    // 29 February of a leap year
    return year % 4 == 0 && month == 2 && dayOfMonth == 29
}
```

### 3.31 Invoke

```kotlin
class Invokable {
    var numberOfInvocations: Int = 0
        private set
    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this
    }
}
```

## 4. Properties

### 4.32 Properties

```kotlin
class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value){
            field = value
            this.counter++
        }
}
```

### 4.33 Lazy property

```kotlin
    var value: Int? = null
    val lazy: Int
        get() {
            if (value == null) {
                value = initializer()
            }
            return value!!
        }
```

### 4.34 Delegates example

```kotlin
    val lazyValue: Int by lazy {
        initializer()
    }
```

### 4.35 How delegates work

```kotlin
class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}
```

## 5.Builders

### 5.36 Extension function literals

```kotlin
    val isEven: Int.() -> Boolean = { this % 2 == 0 }
    val isOdd: Int.() -> Boolean = { this % 2 != 0 }
```

### 5.37 String and map build

```kotlin
    fun <K, V>buildMap(build: HashMap<K, V>.() -> Unit) :HashMap<K, V>{
        val mapBuilder =  HashMap<K, V>()
        mapBuilder.build()
        return mapBuilder
    }
```

### 5.38 The function apply

```kotlin
fun <T> T.myApply(f: T.() -> Unit): T { f(); return this }
```

### 5.39 Html builders

```kotlin
fun renderProductTable(): String {
    return html {
        table {
            tr (color = getTitleColor()) {
                td {
                    text("Product")
                }
                td {
                    text("Price")
                }
                td {
                    text("Popularity")
                }
            }
            val products = getProducts()
            //todoTask39()
            for ((index, product) in products.withIndex()) {
                tr {
                    td(color = getCellColor(index, 0)) { 
                        text(product.description)
                    }
                    td(color = getCellColor(index, 1)) {
                        text(product.price)
                    }
                    td(color = getCellColor(index, 2)) {
                        text(product.popularity)
                    }
                }
            }
        }
    }.toString()
}
```

### 5.40 How builders work

Answer:

```kotlin
val answers = mapOf<Int, Answer?>(
        1 to c, 2 to b, 3 to b, 4 to c
)
```

## 6.Generics

### 6.41 Generic functions

```kotlin
fun <T, C : MutableCollection<in T>> Collection<T>.partitionTo(c0: C, c1: C, predicate: (T) -> Boolean): Pair<C, C> {
    for (element in this) {
        if (predicate(element)) {
            c0.add(element)
        } else {
            c1.add(element)
        }
    }
    return Pair(c0, c1)
}
```

## License
MIT
