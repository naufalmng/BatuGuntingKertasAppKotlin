package com.example.kbg.elements

import com.example.kbg.suit.Suit

class Scissors(element: String): Suit(element) {
    override fun loseWith(): String {
        return "rock"
    }

    override fun winFrom(): String {
        return "paper"
    }

    override fun drawFrom(): String {
        return this.element
    }
}