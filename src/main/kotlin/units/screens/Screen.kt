package units.screens

import units.Action
import units.GlobalScanner
import kotlin.random.Random

abstract class Screen(
    protected val title: String,
    protected val actions: MutableList<Action> = mutableListOf()
) {

    companion object {
        const val EXIT = "Выход"
        const val INPUT_NUMBER_OF_ACT_IN_MENU = "Введите номер действия из списка:"
        const val ZERO_STRING_IS_NOT_ALLOWED = "Пустая строка запрещена!"

    }
    protected var isScreenRunning: Boolean = true
    init {
        actions.add(Action(EXIT) {exit()})
    }

    open protected fun printTitle() {
        println(title)
    }

    open protected fun printActs() {
        actions.forEachIndexed { index, act -> println("${index} - ${act.description}") }
    }

    fun interact(){
        while (isScreenRunning) {
            printTitle()
            printActs()
            val choice = getUsersNumberOfChoice()
            actions[choice].act()
        }
    }

    private fun getUsersNumberOfChoice(): Int {
        var action = -1
        do {
            println(INPUT_NUMBER_OF_ACT_IN_MENU)
            if (GlobalScanner.scanner.hasNextInt()) {
                action = GlobalScanner.scanner.nextInt()
                GlobalScanner.scanner.nextLine()
            } else {
                GlobalScanner.scanner.nextLine()
            }
        } while ( action !in 0..actions.size-1 )
        return action
    }

    protected fun getNoEmptyTextFromUser(invitation: String): String {
        var text = ""
        while (text.isEmpty()) {
            println(invitation)
            println(ZERO_STRING_IS_NOT_ALLOWED)
            text = GlobalScanner.scanner.nextLine().trim()
        }
        return text
    }

    open protected fun exit() {
        isScreenRunning = false
    }

    open protected fun goToNextScreen(nextScreen: Screen) {
        nextScreen.interact()
    }
}


