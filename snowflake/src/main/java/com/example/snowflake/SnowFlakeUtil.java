package com.example.snowflake;

/**
 * 雪花算法
 * 0-00000000000000000000000000000000000000000-0000000000-000000000000
 * 1) 1位，不用。二进制中最高位为1的都是负数，但是我们生成的id一般都使用整数，所以这个最高位固定是0
 * 2) 41位，用来记录时间戳（毫秒）。41位可以表示2^41−1个数字，如果只用来表示正整数（计算机中正数包含0），可以表示的数值范围是：0 至 2^41−1，
 * 减1是因为可表示的数值范围是从0开始算的，而不是1。也就是说41位可以表示2^41−1个毫秒的值，转化成单位年则是(2^41−1)/(1000∗60∗60∗24∗365)=69年
 * 3) 10位，用来记录工作机器id。可以部署在2^10=1024个节点，包括5位datacenterId和5位workerId，5位（bit）可以表示的最大正整数是2^5−1=31，
 * 即可以用0、1、2、3、....31这32个数字，来表示不同的datecenterId或workerId
 * 4) 12位，序列号，用来记录同毫秒内产生的不同id。12位（bit）可以表示的最大正整数是2^12−1=4095，即可以用0、1、2、3、....4094这4095个数字，
 * 来表示同一机器同一时间截（毫秒)内产生的4095个ID序号由于在Java中64bit的整数是long类型，所以在Java中SnowFlake算法生成的id就是long来存储的。
 * <p>
 *
 * @author TMW
 * @since 2020/10/12
 */
public class SnowFlakeUtil {
    /**
     * 起始时间戳：2020-01-01 00:00:00
     */
    private final static long START_TIMESTAMP = 1577808000000L;
    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private final static long DATACENTER_BIT = 5;
    /**
     * 每一部分的最大值：先进行左移运算，在进行非运算取反
     * <p>
     * 用位运算计算出最大支持的数据中心数量：31
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);

    /**
     * 用位运算计算出最大支持的机器数量：31
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);

    /**
     * 用位运算计算出12位能存储的最大正整数：4095
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    /**
     * 机器标志较序列号的偏移量
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * 数据中心较机器标志的偏移量
     */
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 时间戳较数据中心的偏移量
     */
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    /**
     * 序列号
     */
    private static long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private static long lastStamp = -1L;
    /**
     * 数据中心
     */
    private long datacenterId;
    /**
     * 机器标识
     */
    private long machineId;

    public SnowFlakeUtil(long machineId, long datacenterId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_MACHINE_NUM));
        }
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_NUM));
        }
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个id
     *
     * @return next id
     */
    public synchronized long nextId() {
        // 当前时间戳
        long currStamp = getNewStamp();
        // 当前时间戳小于上次时间戳，出现时钟回拨
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        // 同一毫秒内
        if (currStamp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                // 获取下一时间的时间戳并赋值给当前时间戳
                currStamp = getNextMill();
            }
        } else {
            // 不同毫秒内，重置序列为0
            sequence = 0L;
        }
        lastStamp = currStamp;
        // 时间戳 | 数据中心 | 机器码 | 序列号
        return ((currStamp - START_TIMESTAMP) << TIMESTAMP_LEFT)
                | (datacenterId << DATACENTER_LEFT)
                | (machineId << MACHINE_LEFT)
                | sequence;
    }

    /**
     * 获取下一个时间戳
     *
     * @return 时间戳
     */
    private static long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private static long getNewStamp() {
        return System.currentTimeMillis();
    }

}
