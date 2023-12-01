package com.invadermonky.justenoughmagiculture.asm;

import com.invadermonky.justenoughmagiculture.asm.mods.ASMJER;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.Arrays;

public class ASMTransformer implements IClassTransformer {
    private static final String[] classesBeingTransformed = {
            "jeresources.jei.JEIConfig"
    };

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
                    ASMJER.transformJEIConfig(classNode, isObfuscated);
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
}
