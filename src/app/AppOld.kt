package app

import kotlinx.html.js.onChangeFunction
import react.*
import react.dom.*
import logo.*
import ticker.*
import utils.omg
import utils.translate
import utils.value

class App : RComponent<RProps, App.State>() {

    class State(var inputValue: String) : RState

    // Construtor da classe App
    init{}

    override fun componentWillMount() {
        setState {
            inputValue = "Initial input value".translate()
        }
    }

    // Método mínimo abstrato que tem que estender
    override fun RBuilder.render() {

        h1{
            + "Hello!".translate()
        }

        div{
            p{ + "Type anything below :)".translate()}
            input{
                attrs{
                    value = state.inputValue
                    onChangeFunction = { event ->

                        // event.value porque sobrescrevemos o getter do Event no utils.kt
                        val value = event.value

                        setState{
                            inputValue = value
                        }
                    }
                }
            }
        }

        // Tô construindo o test falando pra ele que o value é o value do state atual
        test(state.inputValue)

        /**
        div("App-header") {
            logo()
            h2 {
                +"Welcome to React with Kotlin"
            }
        }
        p("App-intro") {
            +"To get started, edit "
            code { +"app/App.kt" }
            +" and save to reload."
        }
        p("App-ticker") {
            ticker()
        }
        */
    }
}

class Test : RComponent<Test.Props, App.State>() {

    // É um componente que só mostra o valor, então não precisa ter um state, por isso props
    class Props(var value: String) : RProps

    override fun RBuilder.render() {
        div{
            span{
                +props.value
            }
        }
    }
}

// Integrou o componente novo na DSL
// O test recebe um value e automaticamente o value do attrs recebe esse value
fun RBuilder.test(value: String) = child(Test::class) {
    attrs.value = value
}

fun RBuilder.app() = child(App::class) {}
