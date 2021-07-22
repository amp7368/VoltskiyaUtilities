package voltskiya.apple.utilities.util.multiblock;

import org.bukkit.block.data.BlockData;
import voltskiya.apple.utilities.util.data_structures.Pair;
import voltskiya.apple.utilities.util.data_structures.Triple;

import java.util.Collections;
import java.util.Map;

public interface SingleBlockType extends MultiBlockType {
    @Override
    default Map<Triple<Integer, Integer, Integer>, BlockData> getStructure() {
        Pair<Triple<Integer, Integer, Integer>, BlockData> structureSingle = getStructureSingle();
        return Collections.singletonMap(structureSingle.getKey(), structureSingle.getValue());
    }

    Pair<Triple<Integer, Integer, Integer>, BlockData> getStructureSingle();
}
