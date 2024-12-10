package net.xdev789.day9;

import java.util.ArrayList;
import java.util.List;

public class Drive {
    private final List<Block> blocks = new ArrayList<>();

    public void addBlock(int code, int size) {
        blocks.add(new Block(code, size));
    }

    public void runDefragmentation() {
        int firstFreeBlock = 0;

        boolean hasChanged;

        do {
            hasChanged = false;

            for (int tail = blocks.size() - 1; tail >= 0; tail--) {
                Block tailBlock = blocks.get(tail);
                if (tailBlock.isEmpty()) {
                    continue;
                }

                boolean foundFreeBlock = false;
                for (int head = firstFreeBlock; head < tail; head++) {
                    Block headBlock = blocks.get(head);
                    if (!headBlock.isEmpty()) {
                        continue;
                    }

                    if (!foundFreeBlock) {
                        firstFreeBlock = head;
                        foundFreeBlock = true;
                    }

                    int headLength = headBlock.size();
                    int tailLength = tailBlock.size();

                    if (headLength < tailLength) {
                        continue;
                    }

                    blocks.set(head, tailBlock);
                    blocks.set(tail, Block.free(tailLength));

                    int diff = headLength - tailLength;
                    if (diff > 0) {
                        blocks.add(head + 1, Block.free(diff));
                        tail++;
                    }

                    hasChanged = true;
                    break;
                }
            }

        } while (hasChanged);
    }

    public long checksum() {
        long blockId = 0;
        long result = 0;

        for (Block block : blocks) {
            for (int i = 0; i < block.size(); i++) {
                int code = block.code() == -1 ? 0 : block.code();

                result += blockId++ * code;
            }
        }

        return result;
    }

    private record Block(int code, int size) {
        public static Block free(int size) {
            return new Block(-1, size);
        }

        public boolean isEmpty() {
            return code == -1;
        }
    }
}