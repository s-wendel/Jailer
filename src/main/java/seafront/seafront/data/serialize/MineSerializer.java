package seafront.seafront.data.serialize;

import seafront.seafront.blocks.Mine;

public class MineSerializer extends GsonSerializer<Mine> {

    public MineSerializer() {
        super();
    }
    @Override
    public String serialize(Mine data) {
        data.save();
        return super.serialize(data);
    }

    @Override
    public Mine deserialize(String data) {
        Mine mine = super.deserialize(data);
        mine.load();
        return mine;
    }
}
