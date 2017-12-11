package io.github.jerukan.util

/** Wraps a boolean with a string name  */
class NamedFlag {

    var name: String? = null
        private set

    private var defaultState: Boolean = false
    var state: Boolean = false

    constructor() {
        name = "flag"
        defaultState = true
        state = true
    }

    constructor(name: String, defaultState: Boolean) {
        this.name = name
        this.defaultState = defaultState
        state = this.defaultState
    }

    fun reset() {
        state = defaultState
    }
}
