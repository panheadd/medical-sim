package com.yunus.ai.util;

import java.io.File;

public class PathUtils {

    public static String getJarDir() {
        try {
            String path = PathUtils.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            File jarFile = new File(path);

            if (jarFile.isFile()) {
                return jarFile.getParent();
            }

            return new File("").getAbsolutePath();

        } catch (Exception e) {
            return new File("").getAbsolutePath();
        }
    }
}
