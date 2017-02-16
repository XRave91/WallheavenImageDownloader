package com.xrave.wall_downloader;

import java.io.BufferedInputStream;

/**
 * Created by 2 on 16.01.2017.
 */
interface Saver {
    // потому что нам не важно, куда мы сохраняем. это вполне может быть дропбокс или вк
    // тогда мы заранее зададим все настройки и будем сохранять по имени
    boolean saveData(BufferedInputStream data, String name);
}
