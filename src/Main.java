//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //String input = args[0];
        //String input = "02f43824"; // rType
        //String input = "246600e0"; // iType
        //String input = "080000c7"; // jType
        //String input = "0000000c"; //syscall

        //String input = "274700c3"; //iType addiu
        //String input = "32110039"; // iType andi
        //String input = "10c900cd"; // iType beq
        //String input = "16cb00aa"; // iType bne
        //String input = "3c1d0071"; // iType lui
        //String input = "8fb20000"; // iType lw
        //String input = "3413ff20"; // iType ori
        String input = "ac0d0000"; //iType sw

        int binary = (int) Long.parseLong(input, 16);

// & 0x3F is used to mask out and extract only the lowest 6 bits
        int opcode = (binary >>> 26) & 0x3F;
        int funct = binary & 0x3F; // for distinguishing syscall from other R-types
        String type = Instructions.InstructionType(opcode, funct);

        //System.out.println(type);

        switch (type) {

            case "R" -> Instructions.rType(opcode, funct, binary);
            case "I" -> Instructions.iType(opcode, binary);
            case "J" -> Instructions.jType(opcode, binary);
            default -> Instructions.syscall();
        }
    }
}
