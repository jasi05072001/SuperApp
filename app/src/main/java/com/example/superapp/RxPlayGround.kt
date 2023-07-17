package com.example.superapp

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

/**
In Reactive programming three things are important:
 * 1. Observable -> emits the data
 * 2. Observer -> listens to the data
 * 3. Subscription -> connects the observable and observer
 */


/**
This is how we create an observable
 */
var stringObservable: Observable<String> = Observable.just("Hello Message")

val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

val intObservable: Observable<Int> = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

val number = Observable.fromArray(numbers)

fun demo() {
    val disposable = intObservable.subscribeBy(
        onNext = {
            println("Inside onNext $it")
        },
        onError = {
            println("Error $it")
        },
        onComplete = {
            println("Completed")
        }
    )
}

fun main() {
    demo()
}



