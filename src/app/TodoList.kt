package app

import kotlinx.html.InputType
import model.Todo
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import utils.translate

class TodoList : RComponent<TodoList.Props, RState> () {
    override fun RBuilder.render() {
        ul(classes = "todo-list"){
            props.todos.mapIndexed{ _, todo ->

                val classes = when{
                    todo.completed -> "completed"
                    else -> ""
                }

                li(classes = classes){
                    todoItem(
                            todo,
                            props.obliterateTodo,
                            props.updateTodo
                    )
                }
            }
        }
    }

    class Props(
            var todos: List<Todo>,
            var obliterateTodo: (Todo) -> Unit,
            var updateTodo: (Todo) -> Unit
    ) : RProps
}

fun RBuilder.todoList(
        todos: List<Todo>,
        obliterateTodo: (Todo) -> Unit,
        updateTodo: (Todo) -> Unit
) = child(TodoList::class) {
    attrs.todos = todos
    attrs.obliterateTodo = obliterateTodo
    attrs.updateTodo = updateTodo
}