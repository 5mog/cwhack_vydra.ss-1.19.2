package net.io.fabric.loader.event.events;

import net.minecraft.client.util.math.MatrixStack;
import net.io.fabric.loader.event.Event;
import net.io.fabric.loader.event.Listener;

import java.util.ArrayList;

public interface RenderHudListener extends Listener {
    void onRenderHud(MatrixStack matrices, double partialTicks);

    class RenderHudEvent extends Event<RenderHudListener> {

        private final MatrixStack matrices;
        private final double partialTicks;

        public RenderHudEvent(MatrixStack matrices, double partialTicks) {
            this.matrices = matrices;
            this.partialTicks = partialTicks;
        }

        @Override
        public void fire(ArrayList<RenderHudListener> listeners) {
            listeners.forEach(e -> e.onRenderHud(matrices, partialTicks));
        }

        @Override
        public Class<RenderHudListener> getListenerType() {
            return RenderHudListener.class;
        }
    }
}
