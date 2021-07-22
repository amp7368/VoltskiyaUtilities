package voltskiya.apple.utilities.util.minecraft;

import org.bukkit.block.BlockFace;
import voltskiya.apple.utilities.util.data_structures.Triple;

public class FacingUtils {
    public static BlockFace getNewFacing(BlockFace f1, double yaw) {
        if (yaw < 90) {

        } else if (yaw < 180) {

        } else if (yaw < 270) {

        } else {

        }
        //todo
        return f1;
    }

    public static Triple<Integer, Integer, Integer> rotate(Triple<Integer, Integer, Integer> xyz, float yaw) {
        // todo
        return xyz;
    }
}
