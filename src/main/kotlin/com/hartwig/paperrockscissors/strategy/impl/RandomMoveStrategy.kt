package com.hartwig.paperrockscissors.strategy.impl

import com.hartwig.paperrockscissors.core.Move
import com.hartwig.paperrockscissors.strategy.Strategy
import java.util.*

/**
 * This strategy generates random moves
 */
class RandomMoveStrategy : Strategy {
    var random: Random = Random()
    override fun move(): Move {
        return Move.values().get(random.nextInt(3))
    }
}