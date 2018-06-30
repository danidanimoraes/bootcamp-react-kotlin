package app

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onKeyDownFunction
import kotlinx.html.js.onSubmitFunction
import kotlinx.html.onSubmit
import model.Todo
import react.*
import react.dom.h1
import react.dom.header
import react.dom.input
import react.dom.section
import utils.translate
import utils.value

class HeaderInput : RComponent<HeaderInput.Props, HeaderInput.State> () {

    override fun componentWillMount() {
        setState {
            title = ""
        }
    }
    override fun RBuilder.render() {
        header(classes = "header"){
            h1{
                +"todos".translate()
            }
            input(classes = "new-todo", type = InputType.text){
                attrs{
                    autoFocus = true
                    placeholder = "What needs to be done?"

                    // Eu tinha esquecido isso e não limpava o input
                    value = state.title

                    onChangeFunction = { event ->

                        // (Cópia do App.kt)
                        // event.value porque sobrescrevemos o getter do Event no utils.kt
                        val value = event.value

                        setState{
                            title = value
                        }
                    }

                    onKeyDownFunction = { event ->
                        val keyPressed = event.asDynamic().key as String
                        if(keyPressed == "Enter" && state.title.isNotBlank())
                        {
                            props.createTodo(Todo(title = state.title))
                            setState {
                                title = ""
                            }
                        }

                    }
                }
            }
        }
    }

    class Props(var createTodo: (Todo) -> Unit) : RProps
    class State(var title: String): RState
}

fun RBuilder.headerInput(createTodo: (Todo) -> Unit) = child(HeaderInput::class) {
    attrs.createTodo = createTodo
}