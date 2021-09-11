package com.example.kbg.suit

open class Suit(val element: String) {

    open fun loseWith(): String{
        return ""
    }
    open fun winFrom(): String{
        return ""
    }
    open fun drawFrom(): String{
        return ""
    }

    open fun actionVersus(suit: Suit): ResultSuit{
        return when (suit.element){
            loseWith() -> ResultSuit(ResultSuit.LOSE)
            winFrom() -> ResultSuit(ResultSuit.WIN)
            drawFrom() -> ResultSuit(ResultSuit.DRAW)
            else -> ResultSuit(ResultSuit.EMPTY)
        }
    }

}