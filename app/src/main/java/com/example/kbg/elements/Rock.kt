package com.example.kbg.elements

import com.example.kbg.suit.ResultSuit
import com.example.kbg.suit.Suit

class Rock(element: String): Suit(element) {
    override fun loseWith(): String {
        return "paper"
    }

    override fun winFrom(): String {
        return "scissors"
    }

    override fun drawFrom(): String {
        return this.element
    }
}