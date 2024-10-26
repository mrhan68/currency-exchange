package amldev.currency.domain.commands

/***********************************************************************************************************************
 * Created by Anartz Mugika on 22/07/2017.
 * ---------------------------------------------------------------------------------------------------------------------
 * Está clase es la que se encarga de definir los comandos que usaremos en RequestCurrencyCommand.
 ***********************************************************************************************************************/
interface Command<T> {
    fun execute(): T
}