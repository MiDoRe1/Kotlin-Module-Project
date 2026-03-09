package units.screens

import units.Action
import units.Archive

class ArchivesScreen(private val archives: MutableList<Archive> = mutableListOf()):
    Screen(title="Меню управления архивами:")
{
    companion object {
        const val CREATE_ARCHIVE = "Создание архива"
        const val CREATE_ARCHIVE_PROCESS = "Создаем архив"
        const val INPUT_NAME_OF_NEW_ARCHIVE = "Введите название архива."
        const val BYE = "Пока!"
    }
    init {
        actions.add(Action(CREATE_ARCHIVE) {createArchive()})
    }

    private fun createArchive(){
        println(CREATE_ARCHIVE_PROCESS)
        val newArchive = Archive(getValidatedNameOfArchive())
        archives.add(newArchive)
        println("Архив с названием '${newArchive.name} добавлен'")
        actions.add(Action("Просмотр архива с названием '${newArchive.name}'") {
            MarksScreen(newArchive).interact()
        })
    }

    private fun getValidatedNameOfArchive(): String = getNoEmptyTextFromUser(INPUT_NAME_OF_NEW_ARCHIVE)

    override fun exit() {
        println(BYE)
        super.exit()
    }

}