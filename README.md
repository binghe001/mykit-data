# 作者及联系方式
作者：冰河  
微信：hacker_binghe  
QQ：2711098650  
微信公众号： 冰河技术 

<div align="center">
    <a href="https://github.com/binghe001/BingheGuide">关注冰河技术，解锁更多技能！</a>
</div>

## 加群交流

本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “冰河” 微信(hacker_binghe)，备注：`学习加群`。

<div align="center">
    <img src="https://binghe001.github.io/images/personal/hacker_binghe.jpg?raw=true" width="180px">
    <div style="font-size: 9px;">冰河微信</div>
    <br/>
</div>


## 公众号

分享各种编程语言、开发技术、分布式与微服务架构、分布式数据库、分布式事务、云原生、大数据与云计算技术和渗透技术。另外，还会分享各种面试题和面试技巧。内容在 **冰河技术** 微信公众号首发，强烈建议大家关注。

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20210426115714643.jpg?raw=true" width="180px">
    <div style="font-size: 9px;">公众号：冰河技术</div>
    <br/>
</div>



## 星球

加入星球 **[冰河技术](http://m6z.cn/6aeFbs)**，可以获得本站点所有学习内容的指导与帮助。如果你遇到不能独立解决的问题，也可以添加冰河的微信：**hacker_binghe**， 我们一起沟通交流。另外，在星球中不只能学到实用的硬核技术，还能学习**实战项目**！

关注 [冰河技术](https://img-blog.csdnimg.cn/20210426115714643.jpg?raw=true)公众号，回复 `星球` 可以获取入场优惠券。

<div align="center">
    <img src="https://binghe001.github.io/images/personal/xingqiu.png?raw=true" width="180px">
    <div style="font-size: 9px;">知识星球：冰河技术</div>
    <br/>
</div>

## 我的新书
<div align="center">
    <img src="https://img-blog.csdnimg.cn/fe76310aea734752b3b79c4df1438943.jpeg?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/13190783.html">《深入理解高并发编程：核心原理与案例实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/5ee367b68023466a87f66763a64a4133.jpg?raw=true" width="100%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/12972343.html">《深入理解分布式事务：原理与实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20210426115257555.png?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/13036154.html">《MySQL技术大全：开发、优化与运维实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20200828011209412.png?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/12710993.html">《海量数据处理与大数据技术实战》</a></div>
    <br/>
</div>

# 冰河整理PDF

关注 **冰河技术** 微信公众号：  

回复 “**并发编程**” 领取《深入理解高并发编程（第1版）》PDF电子书。   

回复 “**并发源码**” 领取《并发编程核心知识（源码分析篇 第1版）》PDF电子书。  

回复 “**渗透笔记**” 领取《冰河的渗透实战笔记》PDF电子书。  

回复 “**我要进大厂**” 领取《我要进大厂系列之面试圣经（第1版）》PDF电子书。  

回复 ”**限流**“ 领取《亿级流量下的分布式解决方案》PDF电子书。  

回复 “**设计模式**” 领取《深入浅出Java23种设计模式》PDF电子书。  

回复 “**Java8新特性**” 领取 《Java8新特性教程》PDF电子书。    

回复 “**分布式存储**” 领取《跟冰河学习分布式存储技术》 PDF电子书。  

回复 “**Nginx**” 领取《跟冰河学习Nginx技术》PDF电子书。  

回复 “**互联网工程**” 领取《跟冰河学习互联网工程技术》PDF电子书。  


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
git clone https://github.com/binghe001/mykit-data
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

**以日志形式增量同步MySQL数据到Oracle数据库时，需要开启MySQL的binlog日志。**


# 扫一扫关注微信公众号

**你在刷抖音，玩游戏的时候，别人都在这里学习，成长，提升，人与人最大的差距其实就是思维。你可能不信，优秀的人，总是在一起。** 
  
扫一扫关注冰河技术微信公众号  
![微信公众号](https://img-blog.csdnimg.cn/20200906013715889.png)  
