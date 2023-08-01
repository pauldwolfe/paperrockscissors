package com.hartwig.paperrockscissors.strategy

import com.hartwig.paperrockscissors.core.Move

interface Strategy {
    fun move(): Move
}