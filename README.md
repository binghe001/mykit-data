# 作者及联系方式
作者：冰河  
微信：sun_shine_lyz  
QQ：2711098650  
微信公众号： 冰河技术

# 项目介绍
支持插件化、可视化的数据异构中间件，支持的数据异构方式如下  
```
MySQL <——> MySQL（增量、全量）
MySQL <——> Oracle（增量、全量）
Oracle <——> MySQL （增量、全量）
Oracle <——> Oracle（增量、全量）
```
# 使用步骤

## 运行系统

### 下载mykit-data源码。

```bash
git clone https://github.com/sunshinelyz/mykit-data
```

### 使用Maven编译mykit-data

```bash
mvn clean package 
```

### 运行程序

```bash
java -jar mykit-data-starter-xxx.jar
```

### 打开系统连接

```bash
http://localhost:8888
```

## 配置数据同步

### 添加MySQL8数据库连接器

![](https://img-blog.csdnimg.cn/20201020211922665.jpg)


### 添加MySQL5.7数据库连接器
![](https://img-blog.csdnimg.cn/20201020211933342.jpg)


### 添加Oracle数据库连接器

![](https://img-blog.csdnimg.cn/20201020211943138.jpg)


### 添加后的效果

![](https://img-blog.csdnimg.cn/20201020211954830.jpg)


### 配置Oracle全量同步数据到MySQL8

![](https://img-blog.csdnimg.cn/20201020212012981.jpg)


**配置字段的映射关系**
![](https://img-blog.csdnimg.cn/20201020212039267.jpg)


![](https://img-blog.csdnimg.cn/20201020212101356.jpg)


**配置后的效果**

![](https://img-blog.csdnimg.cn/2020102021211766.jpg)


### 配置Oracle增量同步数据到MySQL8

![](https://img-blog.csdnimg.cn/20201020212133632.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212149315.jpg)


### 配置MySQL8全量同步数据到Oracle
![](https://img-blog.csdnimg.cn/20201020212209907.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212224269.jpg)


### 配置MySQL8增量同步数据到Oracle

![](https://img-blog.csdnimg.cn/20201020212239857.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212254685.jpg)


### 配置Oracle全量同步数据到MySQL5.x
![](https://img-blog.csdnimg.cn/20201020212556144.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212611563.jpg)


### 配置Oracle增量同步数据到MySQL5.x

![](https://img-blog.csdnimg.cn/20201020212628293.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212647851.jpg)


### MySQL5.x全量同步数据到Oracle

![](https://img-blog.csdnimg.cn/20201020212719700.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212733300.jpg)


### 配置MySQL5.x增量同步数据到Oracle

![](https://img-blog.csdnimg.cn/20201020212747500.jpg)


**配置字段映射关系与“配置Oracle数据全量同步到MySQL8”的步骤相同。**

**配置后的效果**

![](https://img-blog.csdnimg.cn/20201020212803940.jpg)


### 最终配置效果

![](https://img-blog.csdnimg.cn/20201020212836664.jpg)


![](https://img-blog.csdnimg.cn/20201020212848208.jpg)


### 启动任务

![](https://img-blog.csdnimg.cn/2020102021290178.jpg)


### 同步数据产生的日志

![](https://img-blog.csdnimg.cn/2020102021291161.jpg)


### 注意事项

**以日志形式增量同步MySQL数据到Oracle数据时，需要开启MySQL的binlog日志。**


# 扫一扫关注微信公众号

**你在刷抖音，玩游戏的时候，别人都在这里学习，成长，提升，人与人最大的差距其实就是思维。你可能不信，优秀的人，总是在一起。** 
  
扫一扫关注冰河技术微信公众号  
![微信公众号](https://img-blog.csdnimg.cn/20200906013715889.png)  
