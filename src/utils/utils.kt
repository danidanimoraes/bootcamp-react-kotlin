package utils

import org.w3c.dom.events.Event

val Event.value: String
    // asDynamic: para escapar do kotlin e ir pro js
    get() = this.currentTarget.asDynamic().value as String

private val enUSLanguage = mapOf(
        "Hello!" to "Coé rapaziada!",
        "Type anything below :)" to "Digite qualquer coisa abaixo :)",
        "Initial input value" to "Valor inicial"
)

fun String.translate(): String{
    /**
     * if(lang == "PTBR"){
     *
     * }
     * if(lang == "EN" || lang == null){
     * }
     */
    return enUSLanguage.get(this) ?: "***$this"
}

fun String.omg(): String {
    return this + " OMG"
}
// Same thing
// fun String.omg(): String = return this + " OMG"