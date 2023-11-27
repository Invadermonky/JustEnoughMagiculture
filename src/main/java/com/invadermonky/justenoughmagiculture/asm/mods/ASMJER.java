package com.invadermonky.justenoughmagiculture.asm.mods;

import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.CustomJEIConfig;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class ASMJER {
    public static void transformJEIConfig(ClassNode classNode, boolean isObfuscated) throws Exception {
        final String JEICONFIG = "register";
        final String JEICONFIG_DESC = "(Lmezz/jei/api/IModRegistry;)V";

        for(MethodNode method : classNode.methods) {
            if(method.name.equals(JEICONFIG) && method.desc.equals(JEICONFIG_DESC)) {
                AbstractInsnNode returnNode = null;
                for(AbstractInsnNode instruction : method.instructions.toArray()) {
                    if(instruction.getOpcode() == RETURN) {
                        //Continue iteration to make sure it's the last RETURN.
                        returnNode = instruction;
                    }
                }
                if(returnNode != null) {
                    LogHelper.info("Inserting injectRegister method.");
                    InsnList toInsert = new InsnList();
                    toInsert.add(new VarInsnNode(ALOAD, 1));
                    toInsert.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(CustomJEIConfig.class), "injectRegister", JEICONFIG_DESC, false));

                    method.instructions.insertBefore(returnNode, toInsert);
                } else {
                    throw new Exception("Could not inject new JEIConfig. Custom JER wrappers will not function.");
                }
            }
        }
    }
}
