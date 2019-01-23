package com.chain.modules.app.rocksDB;

import com.chain.common.utils.PathUtils;
import org.rocksdb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 7:00 2019/1/22 0022
 * @Modified By
 */
public class RocksJavaUtil {
    private static final Logger logger = LoggerFactory.getLogger(RocksJavaUtil.class);
    private static String dbPath = PathUtils.getDataFileDir();
    private RocksDB rocksDB;
    public static Map<Integer, RocksDB> rockSDBMap = new HashMap<Integer, RocksDB>();

    public RocksJavaUtil(Integer number) {

        try {
            rocksDB = rockSDBMap.get(number);
            if (rocksDB == null) {

                String rocksDBPath = dbPath + number;
                RocksDB.loadLibrary();


                List<ColumnFamilyDescriptor> columnFamilyDescriptors = new ArrayList<>();
                Options options = new Options();
                options.setCreateIfMissing(true);

                List<byte[]> cfs = RocksDB.listColumnFamilies(options, rocksDBPath);
                if (cfs.size() > 0) {
                    for (byte[] cf : cfs) {
                        columnFamilyDescriptors.add(new ColumnFamilyDescriptor(cf, new ColumnFamilyOptions()));
                    }
                } else {
                    columnFamilyDescriptors.add(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, new ColumnFamilyOptions()));
                }

                List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
                DBOptions dbOptions = new DBOptions();
                dbOptions.setCreateIfMissing(true);

                rocksDB = RocksDB.open(dbOptions, rocksDBPath, columnFamilyDescriptors, columnFamilyHandles);
                rockSDBMap.put(number, rocksDB);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void set(String key) {
        try {
            rocksDB.put(key.getBytes(), key.getBytes());

        } catch (Exception ex) {
            logger.error("rocksDB.put异常", ex);
        }
    }

    public void put(String key, String value) {
        try {
            rocksDB.put(key.getBytes(), value.getBytes());
        } catch (Exception ex) {
            logger.error("rocksDB.put异常", ex);
        }
    }

    public byte[] get(String key) {
        try {

            return rocksDB.get(key.getBytes());
        } catch (Exception ex) {
            logger.error("rocksDB.get异常", ex);
        }
        return null;
    }

    public void delete(String key) {
        try {
            rocksDB.delete(key.getBytes());
        } catch (Exception ex) {
            logger.error("rocksDB.delete异常", ex);
        }
    }

    public static void main(String[] args) {
        RocksJavaUtil test = new RocksJavaUtil(50);

    }

}
