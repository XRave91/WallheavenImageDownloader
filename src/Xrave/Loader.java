package com.xrave.wall_downloader;

import java.io.BufferedInputStream;

/**
 * Created by 2 on 16.01.2017.
 */
interface Loader {
    // опять же, мы можем сделать loader с диска, или из vk через rest_api или с еще чего-нибудь, поэтому url не единственный вариант
    BufferedInputStream getData(String path);
}
