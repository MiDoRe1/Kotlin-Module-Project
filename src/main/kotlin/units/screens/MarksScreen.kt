package units.screens

import units.Action
import units.Archive
import units.Mark

class MarksScreen(val archive: Archive): Screen("Меню управления заметками для архива '${archive.name}'.") {

    companion object {
        const val CREATE_MARK = "Создание заметки"
        const val CREATE_MARK_PROCESS = "Создаём заметку"
        const val INPUT_NAME_OF_NEW_MARK = "Введите название заметки"
        const val INPUT_TEXT_OF_NEW_MARK = "Введите текст заметки"
    }

    init {
        actions.add(Action(CREATE_MARK) {createMark()})
        archive.marks.forEach{
            actions.add(
                Action("Просмотр заметки с названием '${it.name}'") {
                    MarkScreen(it).interact()
                }
            )
        }
    }

    fun createMark() {
        println(CREATE_MARK_PROCESS)
        val nameOfMark = getValidatedNameOfMark()
        val textOfMark = getValidatedTextOfMark()
        val newMark = Mark(nameOfMark, textOfMark)
        archive.marks.add(newMark)
        println("Заметка с названием '${newMark.name}' добавлена")
        actions.add(Action("Просмотр заметки с названием '${newMark.name}'") {
            MarkScreen(newMark).interact()
        })
    }

    private fun getValidatedNameOfMark(): String = getNoEmptyTextFromUser(INPUT_NAME_OF_NEW_MARK)

    private fun getValidatedTextOfMark(): String = getNoEmptyTextFromUser(INPUT_TEXT_OF_NEW_MARK)

    override fun printTitle() {
        println()
        super.printTitle()
        println(archive)
    }

}