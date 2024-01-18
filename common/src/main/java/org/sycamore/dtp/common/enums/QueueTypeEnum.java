package org.sycamore.dtp.common.enums;

/**
 * 队列类型枚举
 */
public enum QueueTypeEnum {

    /**
     * {@link java.util.concurrent.ArrayBlockingQueue}
     */
    ARRAY_BLOCKING_QUEUE(1),

    /**
     * {@link java.util.concurrent.LinkedBlockingQueue}
     */
    LINKED_BLOCKING_QUEUE(2),

    /**
     * {@link java.util.concurrent.LinkedBlockingDeque}
     */
    LINKED_BLOCKING_DEQUE(3),

    /**
     * {@link java.util.concurrent.SynchronousQueue}
     */
    SYNCHRONOUS_QUEUE(4),

    /**
     * {@link java.util.concurrent.LinkedTransferQueue}
     */
    LINKED_TRANSFER_QUEUE(5),

    /**
     * {@link java.util.concurrent.PriorityBlockingQueue}
     */
    PRIORITY_BLOCKING_QUEUE(6),

    /**
     * {@link org.sycamore.dtp.common.ResizableCapacityLinkedBlockIngQueue}
     */
    RESIZABLE_LINKED_BLOCKING_QUEUE(7);

    public Integer type;

    QueueTypeEnum(int type) {
        this.type = type;
    }

}
