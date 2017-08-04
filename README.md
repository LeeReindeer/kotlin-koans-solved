
# kotlin-koans-solved

## Contents
- [Introduction](#1-introduction)
  - [Hello World](#10-hello-world)
  - [Java to Kotlin Converter](#11-java-to-kotlin-converter)
  - [Named Arguments](#12-named-arguments)
  - [Default Arguments](#13-default-arguments)
  - [Lambdas](#14-lambdas)
  - [String Templates](#15-string-templates)
  - [Data Classes](#16-data-classes)
  - [Nullable Types](#17-nullable-types)
  - [Smart Casts](#18-smart-casts)
  - [Extension Functions](#19-extension-functions)
  - [Object Expressions](#110-object-expressions)
  - [SAM Conversions](#111-sam-conversions)
  - [Extensions On Collections](#112-extensions-on-collections)
- [Conventions](#2-conventions)


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
    Collections.sort(arrayList) { x, y -> y-x }
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

## 2. Conventions