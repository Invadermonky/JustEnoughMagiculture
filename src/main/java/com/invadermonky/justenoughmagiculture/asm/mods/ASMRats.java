package com.invadermonky.justenoughmagiculture.asm.mods;

import com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats.JERLayerRatEyes;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class ASMRats {
    public static void transformLayerRatEyes(ClassNode classNode, boolean isObfuscated) throws Exception {
        try {
            if (!JEMConfig.RATS.enableRenderFixes)
                return;

            final String RENDERRATINIT = "<init>";
            final String RENDERRATINIT_DESC = "()V";

            final String LAYERRATEYES_DESC = "com/github/alexthe666/rats/client/render/entity/LayerRatEyes";

            final String ADDLAYER = "addLayer";
            final String ADDLAYER_DESC = "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z";

            for (MethodNode method : classNode.methods) {
                if (method.name.equals(RENDERRATINIT) && method.desc.equals(RENDERRATINIT_DESC)) {
                    AbstractInsnNode targetNode = null;
                    AbstractInsnNode popNode = null;
                    for (AbstractInsnNode instruction : method.instructions.toArray()) {
                        if (instruction.getOpcode() == ALOAD && ((VarInsnNode) instruction).var == 0) {
                            if (instruction.getNext().getOpcode() == NEW && ((TypeInsnNode) instruction.getNext()).desc.equals(LAYERRATEYES_DESC)) {
                                targetNode = instruction;
                                continue;
                            }
                        }
                        if(targetNode != null && instruction.getOpcode() == INVOKEVIRTUAL) {
                            MethodInsnNode methodNode = (MethodInsnNode) instruction;
                            if(methodNode.name.equals(ADDLAYER) && methodNode.desc.equals(ADDLAYER_DESC)) {
                                popNode = instruction;
                                break;
                            }
                        }
                    }

                    if (targetNode != null && popNode != null) {
                        LabelNode newLabelNode = new LabelNode();

                        /*
                            methodVisitor.visitVarInsn(ALOAD, 0);
                            methodVisitor.visitTypeInsn(NEW, "com/github/alexthe666/rats/client/render/entity/LayerRatEyes");
                            methodVisitor.visitInsn(DUP);
                            methodVisitor.visitVarInsn(ALOAD, 0);
                            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/alexthe666/rats/client/render/entity/LayerRatEyes", "<init>", "(Lcom/github/alexthe666/rats/client/render/entity/RenderRat;)V", false);
                            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/github/alexthe666/rats/client/render/entity/RenderRat", "addLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", false);
                        */

                        InsnList toInsert = new InsnList();
                        toInsert.add(new VarInsnNode(ALOAD, 0));
                        toInsert.add(new TypeInsnNode(NEW, Type.getInternalName(JERLayerRatEyes.class)));
                        toInsert.add(new InsnNode(DUP));
                        toInsert.add(new VarInsnNode(ALOAD, 0));
                        toInsert.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(JERLayerRatEyes.class), "<init>", "(Lcom/github/alexthe666/rats/client/render/entity/RenderRat;)V", false));
                        toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "com/github/alexthe666/rats/client/render/entity/RenderRat", "addLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", false));
                        toInsert.add(new InsnNode(POP));
                        toInsert.add(new JumpInsnNode(GOTO, newLabelNode));

                        method.instructions.insertBefore(targetNode, toInsert);
                        method.instructions.insert(popNode, newLabelNode);
                    }
                }
            }
        } catch (Exception e) {
            LogHelper.warn("Failed to modify RenderRat.");
            e.printStackTrace();
        }
    }
}
