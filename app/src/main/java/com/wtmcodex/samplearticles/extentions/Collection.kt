package com.wtmcodex.samplearticles.extentions

fun Collection<String>.containsIgnoreCase(item: String) = any {
    it.equals(item, ignoreCase = true)
}