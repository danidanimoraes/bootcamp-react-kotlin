package app

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import react.*
import react.dom.*
import logo.*
import model.Todo
import ticker.*
import utils.omg
import utils.translate
import utils.value

class App2 : RComponent<RProps, App2.State>() {

    init{
        state.todos = loadTodos()
    }

    fun loadTodos() : List<Todo>{
        return listOf(
                Todo(id = 1.00, title = "Cry copiously"),
                Todo(id = 2.00, title = "Sleep for 15 hours"),
                Todo(id = 3.00, title = "Kill self")
        )
    }

    // adiciona um to do na lista
    fun createTodo(todo: Todo){
        val oldTodos = state.todos
        setState{
            todos = oldTodos + todo
        }
    }

    fun obliterateTodo(todo: Todo){
        val oldTodos = state.todos
        setState{
            todos = oldTodos - todo
        }
    }

    fun updateTodo(todo: Todo){
        val newTodos = state.todos.map { oldTodo ->
            if(todo.id == oldTodo.id){
                // Troca o to-do em vez de alterar os valores do to-do existente porque os valores são imutáveis, é tudo
                // imutável, a lista tb.
                // Consegue reciclar melhor os objetos e ter controle
                todo
            }
            else{
                oldTodo
            }
        }

        setState{
            todos = newTodos
        }
    }

    override fun RBuilder.render() {
        section(classes = "todoapp") {
            headerInput(::createTodo)
            section(classes = "main") {
                todoList(state.todos, ::obliterateTodo, ::updateTodo)
            }

            /**
              header(classes = "header"){
                h1{
                    +"todos".translate()
                }
                input(classes = "new-todo", type = InputType.text){
                    attrs{
                        autoFocus = true
                        placeholder = "What needs to be done?"
                    }
                }
            }
            */
        }
    }

    class State(var todos: List<Todo>): RState
}

fun RBuilder.app2() = child(App2::class) {}
