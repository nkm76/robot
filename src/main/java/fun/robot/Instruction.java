package fun.robot;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    L, // Left
    R, // Right
    F; //Forward


    public static List<Instruction> parse(String str) {
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            try {
                instructions.add(Instruction.valueOf(String.valueOf(str.charAt(i))));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Illegal instruction '" + str.charAt(i) + "' at index " + i);
            }
        }
        return instructions;
    }

}
