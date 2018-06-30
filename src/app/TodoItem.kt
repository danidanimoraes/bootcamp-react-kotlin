package app

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import model.Todo
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import utils.translate
import utils.value

class TodoItem : RComponent<TodoItem.Props, RState> () {
    override fun RBuilder.render() {
        div(classes = "view"){
            input(classes = "toggle", type = InputType.checkBox){
                attrs{
                    onChangeFunction = { event ->
                        val checked = event.currentTarget.asDynamic().checked as Boolean
                        props.updateTodo(props.todo.copy(completed = checked))
                    }

                    // Isso é porque não tá refletindo realmente o checked... mas assim também não está.
                    ref { it?.checked = props.todo.completed }
                }
            }

            label{
                +props.todo.title
            }

            button(classes = "destroy"){
                attrs{
                    onClickFunction = {
                        props.obliterateTodo(props.todo)
                    }
                }
            }
        }
    }

    class Props(
            var todo: Todo,
            var obliterateTodo: (Todo) -> Unit,
            var updateTodo: (Todo) -> Unit): RProps
}

fun RBuilder.todoItem(
        todo: Todo,
        obliterateTodo: (Todo) -> Unit,
        updateTodo: (Todo) -> Unit) = child(TodoItem::class) {
    attrs.todo = todo
    attrs.obliterateTodo = obliterateTodo
    attrs.updateTodo = updateTodo
}