package com.example.superapp

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main() {

    val compositeDisposable = CompositeDisposable()

    val numbers = arrayOf(1, 2, 3, 4, 5)
    val vowels = listOf('a', 'e', 'i', 'o', 'u')


    val stringObservable: Observable<String> = Observable.just("Hello Message")
    val intObservable: Observable<Int> = Observable.just(1, 2, 3, 4, 5)
    val number = Observable.fromArray(numbers)
    val vowelsObservable = Observable.fromIterable(vowels)

    val customObs = Observable.create { emitter ->
        emitter.onNext("Hello")
        emitter.onNext("World")
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(true)
        emitter.onComplete()

    }

    val rangeObs = Observable.range(1, 10)

    compositeDisposable.add(
        rangeObs.subscribeBy {
            println("Range $it")
        }
    )
    compositeDisposable.add(
        customObs.subscribeBy {
            println("Custom $it")
        }
    )
    compositeDisposable.add(
        stringObservable.subscribe {
            println("String $it")
        }
    )
    compositeDisposable.add(
        intObservable.subscribe {
            println("Int $it")
        }
    )
    compositeDisposable.add(
        number.subscribe {
            println("Number ${it.toList()}")
        }
    )
    compositeDisposable.add(
        vowelsObservable.subscribe {
            println("Vowels $it")
        }
    )

    closeSub(compositeDisposable)
}

fun closeSub(compositeDisposable: CompositeDisposable) {
    compositeDisposable.dispose()
    println("Disposed")
}