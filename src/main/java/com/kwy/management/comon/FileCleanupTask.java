package com.kwy.management.comon;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author haoy
 * @description
 * @date 2023/7/20 23:13
 */
@Component
public class FileCleanupTask {

    // 每天凌晨1点执行清理任务
    @Scheduled(cron = "0 17 23 * * ?")
    public void cleanupFolder() {
        String folderPath = "src/main/resources/stastic/temporary";
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // 删除文件
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }
}
