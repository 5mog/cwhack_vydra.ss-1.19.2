package net.io.fabric.loader.module.setting;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.io.fabric.loader.gui.component.ButtonComponent;
import net.io.fabric.loader.gui.component.Component;
import net.io.fabric.loader.gui.window.Window;
import net.io.fabric.keybind.Keybind;
import net.io.fabric.loader.module.Module;

import static net.io.fabric.antarctica.MC;

public class KeybindSetting extends Setting<Keybind> {

    private Keybind value;

    private KeybindSetting(Builder builder) {
        super(builder.name, builder.description, builder.module);
        value = builder.value;
    }

    @Override
    public Keybind get() {
        return value;
    }

    @Override
    public void set(Keybind value) {
        this.value = value;
    }

    @Override
    public Component makeComponent(Window parent) {
        return new ButtonComponent(parent, 0, 0, 20, getName(), () ->
                MC.setScreen(new Screen(Text.literal("")) {

                    private final Screen prev = MC.currentScreen;

                    @Override
                    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
                        value.setKey(keyCode);
                        MC.setScreen(prev);
                        return false;
                    }

                    @Override
                    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                        renderBackground(matrices);
                        drawCenteredText(matrices, MC.textRenderer, "Please input your key...", width / 2, height / 2, 0xFFFFFF);
                    }
                }), () -> String.valueOf(value.getKey()));
    }

    public static class Builder {
        private String name;
        private String description;
        private Module module;
        private Keybind value;

        public static Builder newInstance() {
            return new Builder();
        }

        public KeybindSetting build() {
            return new KeybindSetting(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setModule(Module module) {
            this.module = module;
            return this;
        }

        public Builder setValue(Keybind value) {
            this.value = value;
            return this;
        }
    }
}
