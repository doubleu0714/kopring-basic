package io.doubleu0714.handson.kopring.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

private lateinit var txDelegator: TxHelper

object Tx {
    fun <T> requiresNew(func: () -> T): T = txDelegator.requiresNew(func)
}

@Configuration
private class TxConfiguration(
    private val txHelper: TxHelper,
) {
    init {
        txDelegator = this.txHelper
    }
}

@Component
private class TxHelper {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun <T> requiresNew(func: () -> T): T = func()
}