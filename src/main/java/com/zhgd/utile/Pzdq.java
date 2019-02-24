package com.zhgd.utile;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class Pzdq {
    public Map pz() {
        Map map = new HashMap<>();
        try {
            Yaml yaml = new Yaml();
            URL url = Pzdq.class.getClassLoader().getResource("dmi.yaml");
            //ResourceBundle bundle = ResourceBundle.getBundle("resources/dmi");
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为obj，
                Object obj = yaml.load(new FileInputStream(url.getFile()));
                System.out.println(obj);
                //也可以将值转换为Map
                map = (Map) yaml.load(new FileInputStream(url.getFile()));
                System.out.println(map);
                //通过map我们取值就可以了.

            }
            return map;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return map;
        }
    }
}
