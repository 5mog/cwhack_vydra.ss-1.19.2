package net.io.fabric.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.io.fabric.loader.event.EventManager;
import net.io.fabric.loader.event.events.EntityDespawnListener;
import net.io.fabric.loader.event.events.EntitySpawnListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.io.fabric.antarctica.MC;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void onAddEntityPrivate(int id, Entity entity, CallbackInfo info) {
        if (entity != null)
            EventManager.fire(new EntitySpawnListener.EntitySpawnEvent(entity));
    }

    @Inject(method = "removeEntity", at = @At("TAIL"))
    private void onFinishRemovingEntity(int entityId, Entity.RemovalReason removalReason, CallbackInfo info) {
        Entity entity = MC.world.getEntityById(entityId);
        if (entity != null)
            EventManager.fire(new EntityDespawnListener.EntityDespawnEvent(entity));
    }

}
