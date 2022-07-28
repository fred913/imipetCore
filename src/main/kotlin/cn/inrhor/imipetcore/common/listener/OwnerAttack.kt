package cn.inrhor.imipetcore.common.listener

import cn.inrhor.imipetcore.api.manager.PetManager.followingPet
import cn.inrhor.imipetcore.api.manager.PetManager.getOwner
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * 主人攻击目标，宠物参与战斗
 */
object OwnerAttack {

    @SubscribeEvent
    fun e(ev: EntityDamageByEntityEvent) {
        if (ev.damager !is Player) return
        val player = ev.damager as Player
        val target = ev.entity
        if (target.getOwner() == player) {
            ev.isCancelled = true
            return
        }
        player.followingPet().forEach {
            it.state.updateState(it, "attack", ev.entity)
        }
    }

}