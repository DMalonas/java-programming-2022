import java.util.Random;

public abstract class Character {
    protected String name;
    protected int strength;
    protected int resilience;
    protected boolean hasCastSpellSkill;
    protected boolean hasDodgeAttackSkill;

    public int attackEnemy() {
        Random random = new Random();

        int damageDealt;
        if (hasCastSpellSkill) {
            int spellDamage = random.nextInt(5);
            damageDealt = this.strength + spellDamage;
        } else {
            damageDealt = strength;
        }
        return damageDealt;
    }

    public int takeHit() {
        Random random = new Random();

        int damageDealt = random.nextInt(15);
        int damageTaken;
        if (hasDodgeAttackSkill) {
            double chanceToDodge = random.nextDouble();

            if (chanceToDodge > 0.50) {
                damageTaken = 0;
            } else {
                damageTaken = damageDealt - resilience;
            }
        } else {
            damageTaken = damageDealt - resilience;
        }

        if (damageTaken < 0) {
            damageTaken = 0;
        }
        return damageTaken;
    }
}
