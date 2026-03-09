package units.screens

import units.Action
import units.GlobalScanner
import units.Mark

class MarkScreen(val mark: Mark): Screen("Меню для работы с заметкой ${mark.name}") {

    companion object {
        const val VIEW_TEXT_OF_MARK = "Просмотр текста заметки"
        const val INPUT_TEXT_OF_MARK = "Введите текст заметки"
        const val CHANGE_TEXT_OF_MARK = "Изменить текста заметки"
        const val TEXT_WAS_CHANGED = "Текст изменен"

    }

    init {
        actions.add(Action(VIEW_TEXT_OF_MARK) { println(mark) })
        actions.add(Action(CHANGE_TEXT_OF_MARK) { changeTextOfMark() })
    }

    private fun changeTextOfMark() {
        mark.text = getValidatedTextOfMark()
        println(TEXT_WAS_CHANGED)
    }

    private fun getValidatedTextOfMark(): String = getNoEmptyTextFromUser(INPUT_TEXT_OF_MARK)

    override fun printTitle() {
        println()
        println(mark)
    }
}