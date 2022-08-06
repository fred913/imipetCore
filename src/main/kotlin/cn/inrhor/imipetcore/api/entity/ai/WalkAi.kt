package cn.inrhor.imipetcore.api.entity.ai

import cn.inrhor.imipetcore.api.entity.PetEntity
import taboolib.module.ai.SimpleAi
import taboolib.module.ai.navigationMove

class WalkAi(val petEntity: PetEntity): SimpleAi() {

    override fun shouldExecute(): Boolean {
        val owner = petEntity.owner
        return !petEntity.isDead() && owner.isOnline && !owner.isDead &&
                (petEntity.entity?.boundingBox?.max?.distance(owner.boundingBox.max)?: 0.0) > 10.0
    }

    override fun startTask() {
        val attack = petEntity.getActionData("attack")
        if (attack != null) {
            attack.entity = null
        }
        petEntity.entity?.navigationMove(petEntity.owner, petEntity.petData.attribute.speed)
    }

    override fun continueExecute(): Boolean {
        return false
    }

}