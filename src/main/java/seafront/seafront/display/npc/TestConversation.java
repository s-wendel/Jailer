package seafront.seafront.display.npc;

public class TestConversation extends NPCConversation {

    public TestConversation() {
        super("testy");
    }


    @NPCVariable
    public boolean isAlive = false;




    @NPCVariable
    public Double convoCount = 0.0;

    @Override
    public void execute() {
        convoCount += 1;
        player.sendMessage("I FRICKING HATE YOU AAAAAAAAAAAAAAa #" +  convoCount.intValue());
        if (isAlive) {
            player.sendMessage("Im sowwy");
            isAlive = false;
        } else {
            player.sendMessage("FUCK U");
            isAlive = true;
        }
    }
}
