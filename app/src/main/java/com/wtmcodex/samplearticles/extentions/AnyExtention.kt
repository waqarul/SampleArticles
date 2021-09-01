package com.wtmcodex.samplearticles.extentions

fun Any?.isNotNull(): Boolean {
    return this != null
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.doOnNotNull(f: () -> Unit) {
    if (this != null) {
        f()
    }
}