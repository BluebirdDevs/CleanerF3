package bluebird.cleanerdebug.client;

public class ConfigValues {

    public boolean hide_debug_hints = true;
    public boolean hide_targeted_block_tags = false;
    public boolean hide_targeted_blockstate_tags = true;
    public int targeted_block_max_distance = 20;

    public Position pos = new Position();

    public static class Position {
        public boolean simplified_pos = false;
        public String pos_name = "XYZ";
        public boolean simplified_direction = true;
        public boolean hide_chunk_position = false;
        public boolean hide_facing = false;
        public boolean hide_dimension = false;
    }
}