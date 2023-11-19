package com.invadermonky.justenoughmagiculture.asm;

import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.CustomJEIConfig;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.*;

public class ASMTransformer implements IClassTransformer {
    private static final String[] classesBeingTransformed = {"jeresources.jei.JEIConfig"};

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        boolean isObfuscated = !name.equals(transformedName);
        int index = Arrays.asList(classesBeingTransformed).indexOf(transformedName);

        return index != -1 ? transform(index, basicClass, isObfuscated) : basicClass;
    }

    private static byte[] transform(int index, byte[] basicClass, boolean isObfuscated) {
        System.out.println("Transforming: " + classesBeingTransformed[index]);
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, 0);

            switch(index) {
                case 0:
                    transformJEIConfig(classNode, isObfuscated);
                    break;
            }

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicClass;
    }

    private static void transformJEIConfig(ClassNode classNode, boolean isObfuscated) throws Exception {
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
                    throw new Exception("Everything is borked.");
                }
            }
        }
    }
}
