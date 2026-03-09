package units

data class Archive(val name: String,
                   val marks: MutableList<Mark> = mutableListOf()
) {

    companion object {
        const val SEPARATOR_LINE = "<--------------------------------------------------->\n"
    }
    override fun toString(): String {
        return SEPARATOR_LINE +
                "Архив ${name}\nЧисло заметок: ${marks.size}\n"+
                SEPARATOR_LINE
    }
}

data class Mark(val name: String, var text: String) {

    companion object {
        const val SEPARATOR_LINE = "<--------------------------------------------------->\n"
    }

    override fun toString(): String {
        return SEPARATOR_LINE +
                "Заметка ${name}:\n${text}\n"+
                SEPARATOR_LINE
    }
}