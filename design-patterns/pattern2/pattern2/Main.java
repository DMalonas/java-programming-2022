import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Character char1 = new Orc("Grumlin");
        Character char2 = new Elf("Therae");

        int damageDealt = char1.attackEnemy();
        System.out.println(char1.name + " has attacked an enemy " +
                "and dealt " + damageDealt + " damage");

        char1.hasCastSpellSkill = true;

        damageDealt = char1.attackEnemy();
        System.out.println(char1.name + " has attacked an enemy " +
                "and dealt " + damageDealt + " damage");

        int damageTaken = char2.takeHit();
        System.out.println(char2.name + " has taken a hit and " +
                "been dealt " + damageTaken + " damage");

        char2.hasDodgeAttackSkill = true;

        damageTaken = char2.takeHit();
        System.out.println(char2.name + " has taken a hit and " +
                "been dealt " + damageTaken + " damage");
    }
}
